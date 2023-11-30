package com.example.itgarden

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.itgarden._ui.ActMenuIcon
import com.example.itgarden._ui.ButtomBar
import com.example.itgarden._ui.MyTopAppBar
import com.example.itgarden._ui.mPageLogin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}


@Composable
fun MyApp(modifier: Modifier = Modifier) {
    var shouldShow by remember { mutableStateOf(true) }
    Surface(modifier) {
        if (shouldShow) {
            mPageLogin( onContinueClicked = { shouldShow = false })
        } else {
            GreetingPreview()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column (modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally){
            MyTopAppBar(modifier = Modifier.fillMaxWidth(1f))
            ActMenuIcon(modifier = Modifier.fillMaxWidth(1f))
        }
        Column(modifier = Modifier, verticalArrangement = Arrangement.Bottom) {
            ButtomBar(HomeOnClick = {}, SettingOnClick = {})
        }
    }
}