package com.example.basic_jetpackcompose_optimization_memoapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.basic_jetpackcompose_optimization_memoapp.model.Memo
import com.example.basic_jetpackcompose_optimization_memoapp.model.memos
import com.example.basic_jetpackcompose_optimization_memoapp.ui.theme.BasicJetpackComposeOptimizationMemoAppTheme

@Composable
fun HomeScreen(
	homeState: HomeState
) {
	BasicJetpackComposeOptimizationMemoAppTheme {
		Surface(
			modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
		) {
			val memoList = remember { memos }
			val onClickAction: (Int) -> Unit = {
				homeState.showContent(it)
			}
			
			Column { 
				AddMemo(memoList = memoList)
				MemoList(onClickAction = onClickAction, memoList = memoList)
			}
		}
	}
}

@Composable
fun AddMemo(memoList: SnapshotStateList<Memo>) {
	val inputValue = remember { mutableStateOf("") }
	
	Row(
		modifier = Modifier
			.padding(all = 16.dp)
			.height(100.dp), horizontalArrangement = Arrangement.End
	) {
		TextField(modifier = Modifier
			.fillMaxHeight()
			.weight(1f),
			value = inputValue.value,
			onValueChange = { textFieldValue -> inputValue.value = textFieldValue })
		
		Button(onClick = {
			memoList.add(index = 0, Memo(memoList.size, inputValue.value))
			inputValue.value = ""
		}, modifier = Modifier
			.wrapContentWidth()
			.fillMaxHeight()) {
			Text("ADD")
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.MemoList(
	onClickAction: (Int) -> Unit,
	memoList: SnapshotStateList<Memo>
) {
	LazyColumn(modifier = Modifier.weight(1f)) {
		
		itemsIndexed(items = memoList) { index, memo ->
			Card(
				modifier = Modifier
					.height(100.dp)
					.background(Color.White)
					.padding(horizontal = 16.dp, vertical = 8.dp)
					.fillMaxWidth(),
				onClick = {
					onClickAction(memo.id)
				},				// 배경색 지정
			) {
				Text(text = memo.text, modifier = Modifier.fillMaxSize())
			}
		}
		
	}
}