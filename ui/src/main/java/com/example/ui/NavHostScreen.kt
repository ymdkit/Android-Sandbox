package com.example.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ui.memo.CreateMemoScreen
import com.example.ui.memo.CreateMemoViewModel
import com.example.ui.memo.MemoListScreen
import com.example.ui.memo.MemoListViewModel

@Composable
fun NavHostScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.MEMO_LIST
    ) {
        composable(Routes.MEMO_LIST) {
            val viewModel: MemoListViewModel = hiltViewModel()
            MemoListScreen(
                viewModel = viewModel,
                navigateToCreateMemo = {
                    navController.navigate(Routes.CREATE_MEMO)
                }
            )
        }
        composable(Routes.CREATE_MEMO) {
            val viewModel: CreateMemoViewModel = hiltViewModel()
            CreateMemoScreen(
                viewModel = viewModel,
                navigateToPreviousScreen = {
                    navController.popBackStack()
                }
            )
        }
    }
}

class Routes {
    companion object {
        const val MEMO_LIST = "memos"
        const val CREATE_MEMO = "memos/create"
    }
}