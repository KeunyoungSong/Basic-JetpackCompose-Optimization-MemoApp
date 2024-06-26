package com.example.basic_jetpackcompose_optimization_memoapp.ui.content

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.basic_jetpackcompose_optimization_memoapp.R
import com.example.basic_jetpackcompose_optimization_memoapp.model.memos

private val MinTitleOffset = 20.dp
private val MaxTitleOffset = 100.dp
private val HzPadding = Modifier.padding(horizontal = 24.dp)

@Composable
fun ContentScreen(memoId: Int) {
	val memo = remember(memos) { memos.singleOrNull { it.id == memoId } } ?: run {
		Log.d("ContentScreen", "No memo found with ID: $memoId")
		return
	}
	
	
	Box(modifier = Modifier.fillMaxSize()) {
		val scroll = rememberScrollState(0)
		Body(scroll)
		Title(memo.text) { scroll.value }
	}
}

@Composable
fun Body(scroll: ScrollState) {
	Column(modifier = Modifier.background(Color.White)) {
		Spacer(
			modifier = Modifier
				.fillMaxWidth()
				.height(MaxTitleOffset)
		)
		Column(
			modifier = Modifier.verticalScroll(scroll)
		) {
			Surface(Modifier.fillMaxWidth()) {
				Column {
					Spacer(Modifier.height(110.dp))
					Text(
						text = stringResource(R.string.detail_placeholder),
						style = MaterialTheme.typography.bodyLarge,
						color = Color.Black,
						modifier = HzPadding
					)
					
					Spacer(Modifier.height(16.dp))
				}
			}
		}
	}
}

@Composable
private fun Title(
	memoText: String,
	scrollProvider: () -> Int
) {
	val maxOffset = with(LocalDensity.current) { MaxTitleOffset.toPx() }
	val minOffset = with(LocalDensity.current) { MinTitleOffset.toPx() }
	
	Column(modifier = Modifier
		.heightIn(min = MaxTitleOffset)
		.offset {
			val offset = (maxOffset - scrollProvider()).coerceAtLeast(minOffset)
			IntOffset(x = 0, y = offset.toInt())
		}
		.fillMaxWidth()
		.background(Color.White)) {
		Text(
			text = memoText,
			style = MaterialTheme.typography.bodyMedium,
			color = Color.Black,
			modifier = HzPadding
		)
		Spacer(Modifier.height(4.dp))
		Text(
			text = "MEMO",
			style = MaterialTheme.typography.bodySmall,
			color = MaterialTheme.colorScheme.primaryContainer,
			modifier = HzPadding
		)
		
		Spacer(Modifier.height(8.dp))
	}
}
