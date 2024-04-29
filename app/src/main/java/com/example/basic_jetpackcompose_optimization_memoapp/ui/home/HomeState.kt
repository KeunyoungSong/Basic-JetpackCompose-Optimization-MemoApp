package com.example.basic_jetpackcompose_optimization_memoapp.ui.home

import android.content.Intent
import com.example.basic_jetpackcompose_optimization_memoapp.ui.content.ContentActivity

class HomeState(val activity: HomeActivity) {
	
	fun showContent(index: Int) {
		activity.startActivity(Intent(activity, ContentActivity::class.java).apply {
			putExtra("id", index)
		})
	}
}