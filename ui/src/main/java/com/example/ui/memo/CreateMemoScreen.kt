package com.example.ui.memo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.ui.theme.SandboxApplicationTheme
import kotlinx.coroutines.flow.collect

@Composable
fun CreateMemoScreen(
    viewModel: CreateMemoViewModel,
    navigateToPreviousScreen: () -> Unit,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {

    val createMemoState = viewModel.stateFlow.collectAsState()

    LaunchedEffect(viewModel.effectFlow) {
        viewModel.effectFlow.collect {
            when (it) {
                is CreateMemoSideEffect.CreateSuccess -> {
                    navigateToPreviousScreen()
                }
                is CreateMemoSideEffect.CreateFailure -> {
                    scaffoldState.snackbarHostState.showSnackbar(it.message)
                }
                else -> {}
            }
        }
    }

    SandboxApplicationTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Scaffold(
                scaffoldState = scaffoldState,
                content = {
                    CreateMemoForm(
                        title = createMemoState.value.title,
                        onTitleChanged = {
                            viewModel.onTitleChanged(it)
                        },
                        onCreateMemo = {
                            viewModel.createMemo()
                        }
                    )
                },
            )
        }
    }
}

@Composable
private fun CreateMemoForm(
    title: String,
    onTitleChanged: (String) -> Unit,
    onCreateMemo: () -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = {
                onTitleChanged(it)
            },
            placeholder = { Text("メモのタイトル") },
            keyboardActions = KeyboardActions(
                onDone = {
                    onCreateMemo()
                }
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp),
            onClick = {
                onCreateMemo()
            }) {
            Text(text = "新規作成")
        }
    }
}
