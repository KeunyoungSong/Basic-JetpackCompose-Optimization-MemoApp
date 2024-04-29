package com.example.basic_jetpackcompose_optimization_memoapp.ui.content

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.basic_jetpackcompose_optimization_memoapp.R

class ContentActivity : AppCompatActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			ContentScreen(intent.getIntExtra("id",0))
		}
	}
}