package com.example.itgarden._ui

import android.media.Image
import android.text.style.UnderlineSpan
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.itgarden.R
import com.example.itgarden.data.ModelingContent
import com.example.itgarden.data.ModelingTree
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

fun fetchDataTreeFromJson(url: String): List<ModelingTree> {
    val connection = URL(url).openConnection() as HttpURLConnection
    connection.requestMethod = "GET"

    val responseCode = connection.responseCode
    if (responseCode == HttpURLConnection.HTTP_OK) {
        val reader = BufferedReader(InputStreamReader(connection.inputStream))
        val jsonString = reader.readText()
        reader.close()

        return Gson().fromJson(jsonString, Array<ModelingTree>::class.java).toList()
    } else {
        throw Exception("HTTP request failed with response code $responseCode")
    }
}


@Composable
fun ShowContent(list:List<ModelingContent>){

    ITGardenTheme {
        LazyRow {
            items(list.subList(maxOf(0, list.size - 5), list.size).reversed()){item ->
                ContentUI(head = item.HeadText,
                    text = item.BodyText,
                    image = item.UrlImage
                )
            }
        }
    }
}

@Composable
fun ShowContentTree(list:List<ModelingTree>){

    ITGardenTheme {
        LazyRow {
            items(list.subList(maxOf(0, list.size - 5), list.size).reversed()){item ->
                ContentUI(head = item.TreeName,
                    text = item.SicName,
                    image = item.UrlImage
                )
            }
        }
    }
}


@Composable
fun ActMenuIcon(modifier: Modifier=Modifier) {
    var expanded by remember { mutableStateOf(false) }
    var mHeight = 150
    if (!expanded) mHeight = 40
    Column(
        modifier
            .fillMaxWidth()
            .height(mHeight.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Row {
            if (expanded){
                MenuIcon(stringResource(R.string.icon_menu1),
                    R.drawable.icon_menu1,
                    onClick = { })
                MenuIcon(stringResource(R.string.menu2),
                    R.drawable.menu2,
                    onClick = {})
                MenuIcon(stringResource(R.string.menu3),
                    R.drawable.menu3,
                    onClick = {})
            }
        }
        Image(painter = painterResource(id = R.drawable.line), contentDescription = null)
        IconButton(onClick = { expanded = !expanded}) {
            Icon(imageVector = if (expanded) Icons.Filled.KeyboardArrowUp
            else Icons.Filled.KeyboardArrowDown, contentDescription = null,Modifier.size(30.dp))
        }
    }
}
