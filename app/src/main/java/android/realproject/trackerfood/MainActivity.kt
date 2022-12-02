package android.realproject.trackerfood

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.realproject.trackerfood.data.viewModel.AddFoodViewModel
import android.realproject.trackerfood.data.viewModel.AlertViewModel
import android.realproject.trackerfood.data.viewModel.MainViewModel
import android.realproject.trackerfood.data.viewModel.SelectImageViewModel
import android.realproject.trackerfood.data.viewModel.viewModelFactory.AddFoodViewModelFactory
import android.realproject.trackerfood.data.viewModel.viewModelFactory.AlertViewModelFactory
import android.realproject.trackerfood.data.viewModel.viewModelFactory.MainViewModelFactory
import android.realproject.trackerfood.data.viewModel.viewModelFactory.SelectImageViewModelFactory
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
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController




class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrackerFoodTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

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
                    ApplicationNavHost(
                        navController = navController,
                        viewModel = mainViewModel,
                        addFoodViewModel = addFoodViewModel,
                        selectImageViewModel = selectImageViewModel,
                        alertViewModel = alertViewModel
                    )
                }
            }
        }
    }


}

