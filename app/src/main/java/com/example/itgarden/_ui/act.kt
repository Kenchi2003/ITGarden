package com.example.itgarden._ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.itgarden.R
import com.example.itgarden.data.ModelingContent
import com.example.itgarden.ui.theme.ITGardenTheme
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

fun fetchDataFromJson(url: String): List<ModelingContent> {
    val connection = URL(url).openConnection() as HttpURLConnection
    connection.requestMethod = "GET"

    val responseCode = connection.responseCode
    if (responseCode == HttpURLConnection.HTTP_OK) {
        val reader = BufferedReader(InputStreamReader(connection.inputStream))
        val jsonString = reader.readText()
        reader.close()

        return Gson().fromJson(jsonString, Array<ModelingContent>::class.java).toList()
    } else {
        throw Exception("HTTP request failed with response code $responseCode")
    }
}

@Composable
fun ShowContent(list:List<ModelingContent>){

    ITGardenTheme {
        LazyColumn {
            items(list){item ->
                ContentUI(head = item.HeadText,
                    text = item.BodyText,
                    image = item.UrlImage,
                    onClickUIContent = {
                        if (item.UrlImage == null){
                            Log.d("User111","iten Null")
                        } else{
                            Log.d("User111",item.UrlImage)
                        }}) {

                }
            }
        }
    }
}

@Composable
fun ActMenuIcon(modifier: Modifier=Modifier) {
    Column(modifier.height(95.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            MenuIcon(stringResource(R.string.icon_menu1),
                R.drawable.icon_menu1,
                onClick = { Log.d("user442","succ")})
            MenuIcon(stringResource(R.string.menu2),R.drawable.menu2,onClick = {})
            MenuIcon(stringResource(R.string.menu3),R.drawable.menu3,onClick = {})
        }
    }
}
@Composable
fun viewmenuicon() {
    ITGardenTheme {

    }
}