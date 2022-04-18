package com.example.sandboxapplication

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.ui.NavHostScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data: Uri? = intent?.data
        println(data)

        setContent {
            NavHostScreen()
        }
        data?.let {
            Toast.makeText(baseContext, "url: $data", Toast.LENGTH_LONG).show()
        }
    }
}