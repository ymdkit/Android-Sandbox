package com.example.ui.memo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
            Scaffold(
                content = {
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
                                    MemoRow(
                                        memo = it,
                                        onClick = {
                                            viewModel.deleteMemo(it.id)
                                        }
                                    )
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
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = {
                        viewModel.createMemo()
                    }) {
                        Icon(Icons.Filled.Add, contentDescription = "create memo")
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MemoRow(
    memo: MemoUiModel,
    onClick: () -> Unit
) {
    ListItem(
        modifier = Modifier.clickable(onClick = onClick),
        text = { Text(text = memo.id) },
        secondaryText = { Text(text = memo.title) }
    )
}
