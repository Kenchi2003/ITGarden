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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.itgarden._ui.ActMenuIcon
import com.example.itgarden._ui.ButtomBar
import com.example.itgarden._ui.MyTopAppBar
import com.example.itgarden.ui.theme.ITGardenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreetingPreview()
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ITGardenTheme {
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
}