package com.example.basic_jetpackcompose_optimization_memoapp.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.basic_jetpackcompose_optimization_memoapp.ui.theme.BasicJetpackComposeOptimizationMemoAppTheme

class HomeActivity : ComponentActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			HomeScreen(homeState = HomeState(this))
		}
	}
}
