package com.example.itgarden

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.itgarden._ui.ActMenuIcon
import com.example.itgarden._ui.HomeIcon
import com.example.itgarden._ui.IconUA
import com.example.itgarden._ui.MyTopAppBar
import com.example.itgarden._ui.SettingIcon
import com.example.itgarden._ui.ShowContent
import com.example.itgarden._ui.fetchDataFromJson
import com.example.itgarden._ui.mPageLogin
import com.example.itgarden.data.ModelingContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MyApp2()
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
fun MyApp2(modifier: Modifier = Modifier) {
    var mUser by remember { mutableStateOf(false) }
    var mAdmin by remember { mutableStateOf(false) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column (
            modifier =Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            IconUA(Image = R.drawable.peple, onClick = {mUser = true}
            )
            IconUA(Image = R.drawable.admin, onClick = {mAdmin = true}
            )
        }
        if (mAdmin){
            MyApp()
        }
        if (mUser){
            GreetingPreview()
        }
    }
}

@Composable
fun GerAPI():List<ModelingContent>{
    val jsonUrl = "https://656afaa9dac3630cf72786e1.mockapi.io/FristContentPage"
    var dataList by remember { mutableStateOf<List<ModelingContent>>(emptyList())}

    LaunchedEffect(key1 = jsonUrl) {
        dataList = withContext(Dispatchers.IO) {
            fetchDataFromJson(jsonUrl)
        }
    }
    return dataList

}

@Composable
fun GreetingPreview() {
    val dataList = GerAPI()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column (modifier = Modifier
            .padding(bottom = 60.dp)
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally){
            MyTopAppBar(modifier = Modifier.fillMaxWidth(1f))

            ActMenuIcon(modifier = Modifier.fillMaxWidth(1f))

            ShowContent(list = dataList)


        }
        Column(modifier = Modifier, verticalArrangement = Arrangement.Bottom) {
            ButtomBar()
        }
    }
}


@Composable
fun ButtomBar(){
    var PHome by remember { mutableStateOf(false) }
    var PSetting by remember { mutableStateOf(false) }
    Surface {
        Row (
            Modifier
                .fillMaxWidth(1f)
                .padding(all = 8.dp)
                .background(Color(0xffFFFFFF)),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Column (modifier= Modifier
                .height(40.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Bottom)
            {
                HomeIcon (onClick = { PHome = true } )
            }
            Column (modifier= Modifier
                .height(40.dp)
                .fillMaxWidth(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom){
                SettingIcon (onClick = { PSetting = true } )

            }

        }
        if (PHome){
            GreetingPreview()
        }
        if (PSetting){
            MyApp2()
        }
    }

}
