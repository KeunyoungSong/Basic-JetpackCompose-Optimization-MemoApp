package com.example.basic_jetpackcompose_optimization_memoapp.ui.content

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.basic_jetpackcompose_optimization_memoapp.R
import com.example.basic_jetpackcompose_optimization_memoapp.ui.theme.BasicJetpackComposeOptimizationMemoAppTheme

class ContentActivity : ComponentActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			ContentScreen(intent.getIntExtra("id", 0))
		}
	}
}