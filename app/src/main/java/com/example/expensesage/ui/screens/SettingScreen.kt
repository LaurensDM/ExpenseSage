package com.example.expensesage.ui.screens;

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SettingScreen() {
    Scaffold {it ->
        Text(text = "YOU HAVE BEEN GNOMED", modifier = Modifier.padding(it))
    }
}
