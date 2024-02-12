package com.example.itgarden._ui

import androidx.compose.ui.geometry.Size
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.itgarden.R
import com.example.itgarden.data.ModelingContent
import com.example.itgarden.data.ModelingEnvi
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

fun fetchDataEnviFromJson(url: String): List<ModelingEnvi> {
    val connection = URL(url).openConnection() as HttpURLConnection
    connection.requestMethod = "GET"

    val responseCode = connection.responseCode
    if (responseCode == HttpURLConnection.HTTP_OK) {
        val reader = BufferedReader(InputStreamReader(connection.inputStream))
        val jsonString = reader.readText()
        reader.close()

        return Gson().fromJson(jsonString, Array<ModelingEnvi>::class.java).toList()
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
fun DetailAct(list: List<ModelingContent>){
    ITGardenTheme {
        LazyColumn{
            items(items = list) { item ->
                ShowActDetail(mimage = item.UrlImage, mName = item.HeadText, mBody = item.BodyText, mType = item.ActType)
            }
        }
    }
}


@Composable
fun DetailTree(list: List<ModelingTree>){
    ITGardenTheme {
        LazyColumn{
            items(items = list) { item ->
                ShowTreeDetail(mimage = item.UrlImage,
                    mTreeID = item.TreeID,
                    mName = item.TreeName,
                    mSicName = item.SicName,
                    mtype = item.SicName,
                    mAttri= item.attribute,
                    mBenifile = item.Benefit,
                    mTakeCare = item.TakeCare)
            }
        }
    }
}

@Composable
fun DetailEnvi(list:List<ModelingEnvi>){

    ITGardenTheme {
        LazyRow {
            items(list.subList(maxOf(0, list.size - 1), list.size).reversed()){item ->
                ShowEnviDetail(mimage = item.ImageURL, mBody = item.Envi)
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
fun ActMenuIcon(modifier: Modifier=Modifier,onClickAct:()->Unit,onClickTree:()->Unit,onClickEnvi:()->Unit) {
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
                    onClick = {onClickAct() })
                MenuIcon(stringResource(R.string.menu2),
                    R.drawable.menu2,
                    onClick = {onClickTree()})
                MenuIcon(stringResource(R.string.menu3),
                    R.drawable.menu3,
                    onClick = {onClickEnvi() })
            }
        }
        Image(painter = painterResource(id = R.drawable.line), contentDescription = null)
        IconButton(onClick = { expanded = !expanded}) {
            Icon(imageVector = if (expanded) Icons.Filled.KeyboardArrowUp
            else Icons.Filled.KeyboardArrowDown, contentDescription = null,Modifier.size(30.dp))
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownButton(name:String, mCities: List<String>):String{

    // Declaring a boolean value to store
    // the expanded state of the Text Field
    var mExpanded by remember { mutableStateOf(false) }

    // Create a string value to store the selected city
    var mSelectedText by remember { mutableStateOf("") }

    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}

    // Up Icon when expanded and down icon when collapsed
    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(Modifier.padding(2.dp)) {

        // Create an Outlined Text Field
        // with icon and not expanded
        OutlinedTextField(
            value = mSelectedText,
            onValueChange = { mSelectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    // This value is used to assign to
                    // the DropDown the same width
                    mTextFieldSize = coordinates.size.toSize()
                },
            label = {Text(name)},
            trailingIcon = {
                Icon(icon,"contentDescription",
                    Modifier.clickable { mExpanded = !mExpanded })
            }
        )

        // Create a drop-down menu with list of cities,
        // when clicked, set the Text Field text as the city selected
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
        ) {
            mCities.forEach { label ->
                DropdownMenuItem(
                    { Text(text = label) },
                    onClick = {
                    mSelectedText = label
                    mExpanded = false
                })
            }
        }
    }
    return mSelectedText
}
