package com.example.ui.memo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.theme.SandboxApplicationTheme
import com.example.ui.utils.Resource

@Composable
fun MemoListScreen(
    viewModel: MemoListViewModel = viewModel()
) {

    val memoListState = viewModel.flow.collectAsState(initial = Resource.Empty)

    LaunchedEffect(Unit) {
        viewModel.load()
    }

    SandboxApplicationTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            when (val state = memoListState.value) {
                is Resource.Empty -> {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Empty")
                    }
                }
                is Resource.Loading -> {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is Resource.Success -> {
                    LazyColumn {
                        items(state.value) {
                            MemoRow(memo = it)
                        }
                    }
                }
                is Resource.Failure -> {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Text(state.message)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MemoRow(memo: MemoUiModel) {
    ListItem(
        text = { Text(text = memo.id) },
        secondaryText = { Text(text = memo.title) }
    )
}
