package com.example.basic_jetpackcompose_optimization_memoapp.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateListOf

val memos = mutableStateListOf(
	Memo(0, "MEMO 0"),
	Memo(1, "MEMO 1"),
	Memo(2, "MEMO 2"),
	Memo(3, "MEMO 3"),
	Memo(4, "MEMO 4"),
	Memo(5, "MEMO 5"),
	Memo(6, "MEMO 6"),
	Memo(7, "MEMO 7"),
	Memo(8, "MEMO 8"),
	Memo(9, "MEMO 9"),
	Memo(10, "MEMO 10"),
)

@Immutable
data class Memo(
	val id: Int,
	val text: String
)
