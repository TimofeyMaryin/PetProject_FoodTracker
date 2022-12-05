package android.realproject.trackerfood

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.realproject.trackerfood.data.viewModel.*
import android.realproject.trackerfood.data.viewModel.viewModelFactory.*
import android.realproject.trackerfood.model.navigation.ApplicationNavHost
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


const val TABLE_NAME = "Application_settings"
const val KEY_BG_COLOR = "key_bg_color"
const val KEY_ALPHA_BLOCK = "key_alpha_block"
const val KEY_BORDER_RADIUS = "key_border_radius"
const val KEY_BLACKOUT_BG = "key_blackout"

class MainActivity : ComponentActivity() {
    var sharedPreferences: SharedPreferences? = null

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = getSharedPreferences(TABLE_NAME, Context.MODE_PRIVATE)

        setContent {
            TrackerFoodTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    ApplicationSettings.bgColor = sharedPreferences!!.getInt(KEY_BG_COLOR, R.color.bg1)
                    ApplicationSettings.alphaElement = sharedPreferences!!.getFloat(KEY_ALPHA_BLOCK, 1f)
                    ApplicationSettings.borderRadiusFloat = sharedPreferences!!.getFloat(KEY_BORDER_RADIUS, 20f)
                    ApplicationSettings.blackOutBg = sharedPreferences!!.getFloat(KEY_BLACKOUT_BG, .6f)



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
                                navController = navController
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

                    ApplicationNavHost(
                        navController = navController,
                        viewModel = mainViewModel,
                        addFoodViewModel = addFoodViewModel,
                        selectImageViewModel = selectImageViewModel,
                        alertViewModel = alertViewModel,
                        settingViewModel = settingViewModel,
                        selectContentForBgViewModel = selectContentForBgViewModel
                    )
                }
            }
        }
    }

    private fun saveData(value: Any, index: Int){
        val edit = sharedPreferences?.edit()


        /**
         *  index:
         *  0 -> будет сохранять цвет фона (тип Int)
         *  1 -> будет сохранять непрозрачность для блоков (тип Float)
         *  2 -> будет сохранять borderRadius для блоков (тип Float)
         *  3 -> будет сохранять затемнение для фона (тип Float)
         */

        when(index){
            0 -> { edit?.putInt(KEY_BG_COLOR, value as Int) }
            1-> { edit?.putFloat(KEY_ALPHA_BLOCK, value as Float) }
            2 -> { edit?.putFloat(KEY_BORDER_RADIUS, value as Float) }
            3 -> { edit?.putFloat(KEY_BLACKOUT_BG, value as Float) }
        }

        edit?.apply()
    }

    override fun onDestroy() {
        super.onDestroy()

        saveData(ApplicationSettings.bgColor, 0)
        saveData(ApplicationSettings.alphaElement, 1)
        saveData(ApplicationSettings.borderRadiusFloat, 2)
        saveData(ApplicationSettings.blackOutBg, 3)
    }


}

