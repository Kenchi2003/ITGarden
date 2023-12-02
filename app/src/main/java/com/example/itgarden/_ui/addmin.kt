package com.example.itgarden._ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.itgarden.GreetingPreview
import com.example.itgarden.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun mPageLogin(onContinueClicked: () -> Unit){
    var mUN by remember { mutableStateOf(TextFieldValue("")) }
    var mPWD by remember { mutableStateOf(TextFieldValue("")) }
    val mUss = mUN.text
    val mPass = mPWD.text
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Image(
                painter = painterResource(id = R.drawable.icon),
                contentDescription = null
            )
            OutlinedTextField(
                value = mUN,
                label = { Text(text = "UserName") },
                onValueChange = {
                    mUN = it
                }, modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = mPWD,
                label = { Text(text = "Password") },
                onValueChange = {
                    mPWD = it
                }, visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )

            ElevatedButton(onClick = {if (mUss=="a"&&mPass=="a"){
                onContinueClicked()
            } }) {
                Text(
                    text = "Login",
                    style = TextStyle(fontSize = 18.sp)
                )
            }
        }
    }
}

