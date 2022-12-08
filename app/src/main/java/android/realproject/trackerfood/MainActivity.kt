package android.realproject.trackerfood

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.realproject.trackerfood.data.viewModel.*
import android.realproject.trackerfood.data.viewModel.viewModelFactory.*
import android.realproject.trackerfood.model.navigation.ApplicationNavHost
import android.realproject.trackerfood.notify.Notification
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import android.realproject.trackerfood.ui.theme.TrackerFoodTheme
import android.realproject.trackerfood.utils.ApplicationSettings
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController



const val TABLE_NAME = "user_settings"
const val IS_USER_AGREE = "user_agree"

class MainActivity : ComponentActivity() {
    private var sharedPreferences: SharedPreferences? = null
    private var isUserAgree: Boolean = false

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {

            scheduleNotification()

            TrackerFoodTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    sharedPreferences = getSharedPreferences(TABLE_NAME, Context.MODE_PRIVATE)
                    isUserAgree = sharedPreferences?.getBoolean(IS_USER_AGREE, false)!!

                    val navController = rememberNavController()
                    val mainViewModel: MainViewModel by viewModels(
                        factoryProducer = {
                            MainViewModelFactory(application = application)
                        }
                    )
                    val addFoodViewModel: AddFoodViewModel by viewModels(
                        factoryProducer = {
                            AddFoodViewModelFactory(mainViewModel)
                        }
                    )
                    val selectImageViewModel: SelectImageViewModel by viewModels(
                        factoryProducer = {
                            SelectImageViewModelFactory(mainViewModel = mainViewModel)
                        }
                    )

                    val alertViewModel: AlertViewModel by viewModels(
                        factoryProducer = {
                            AlertViewModelFactory(
                                navController, mainViewModel
                            )
                        }
                    )
                    val settingViewModel: SettingViewModel by viewModels(
                        factoryProducer = {
                            SettingViewModelFactory(
                                navController = navController,
                                pref = sharedPreferences!!
                            )
                        }
                    )
                    val context = LocalContext.current
                    val selectContentForBgViewModel: SelectContentForBgViewModel by viewModels(
                        factoryProducer = {
                            SelectContentForBgViewModelFactory(
                                navController = navController,
                                mainViewModel = mainViewModel,
                                context = context
                            )
                        }
                    )
                    val addFoodHintViewModel: AddFoodHintViewModel by viewModels()

                    ApplicationNavHost(
                        navController = navController,
                        viewModel = mainViewModel,
                        addFoodViewModel = addFoodViewModel,
                        selectImageViewModel = selectImageViewModel,
                        alertViewModel = alertViewModel,
                        settingViewModel = settingViewModel,
                        selectContentForBgViewModel = selectContentForBgViewModel,
                        addFoodHintViewModel = addFoodHintViewModel
                    )
                }
            }
        }
    }

    private fun scheduleNotification(){
        val intent = Intent(applicationContext, Notification::class.java)
        val title = "Title extra"
        val message = "Message extra"
        intent.putExtra(Notification().titleExtra, title)
        intent.putExtra(Notification().messageExtra, message)

        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            Notification().notifyId,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time = getTime()
        alarmManager.setAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            pendingIntent
        )
    }

    private fun getTime(): Long {
        val hourToShowPush = 22
        val calendar = Calendar.getInstance().apply {
            if(get(Calendar.HOUR_OF_DAY) >= hourToShowPush){
                add(Calendar.DAY_OF_MONTH, 1)
            }

            set(Calendar.HOUR_OF_DAY, hourToShowPush)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        return calendar.timeInMillis
    }


}

