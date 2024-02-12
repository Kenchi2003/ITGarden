package com.example.itgarden

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.itgarden._ui.ActMenuIcon
import com.example.itgarden._ui.CardEnvi
import com.example.itgarden._ui.CardInputActivity
import com.example.itgarden._ui.CardInputTree
import com.example.itgarden._ui.DetailAct
import com.example.itgarden._ui.DetailEnvi
import com.example.itgarden._ui.DetailTree
import com.example.itgarden._ui.HomeIcon
import com.example.itgarden._ui.IconUA
import com.example.itgarden._ui.MyTopAppBar
import com.example.itgarden._ui.SettingIcon
import com.example.itgarden._ui.ShowContent
import com.example.itgarden._ui.ShowContentTree
import com.example.itgarden._ui.fetchDataEnviFromJson
import com.example.itgarden._ui.fetchDataFromJson
import com.example.itgarden._ui.fetchDataTreeFromJson
import com.example.itgarden._ui.mPageLogin
import com.example.itgarden.data.ModelingContent
import com.example.itgarden.data.ModelingEnvi
import com.example.itgarden.data.ModelingTree
import kotlinx.coroutines.Dispatchers
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
    var pushTree by remember { mutableStateOf(false) }
    var pushAct by remember { mutableStateOf(false) }
    var pushEnvi by remember { mutableStateOf(false) }
    Surface(modifier) {
        if (shouldShow) {
            mPageLogin( onContinueClicked = { shouldShow = false })
        } else {
            PushDATA(clickPushTree = { pushTree = true },
                clickPushAct = {pushAct = true},
                clickPushEnvi = {pushEnvi = true}
            )
        }
        if (pushTree){
            CardInputTree(onClickedCancle = {
                shouldShow = false
                pushTree = false},
                onClickSuss = {
                    shouldShow = false
                    pushTree = false
                })
        }
        if (pushAct){
            CardInputActivity(onClickedCancle = {
                shouldShow = false
            pushAct = false},
                onClickSuss = {
                    shouldShow = false
                    pushAct = false
                })
        }
        if (pushEnvi){
            CardEnvi(onClickedCancle = {
                shouldShow = false
                pushEnvi = false},
                onClickSuss = {
                    shouldShow = false
                    pushEnvi = false
                })
        }
        Column(verticalArrangement = Arrangement.Bottom) {
            ButtomBarAdmin()
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
fun TreeAPI():List<ModelingTree>{
    val jsonUrl = "https://656afaa9dac3630cf72786e1.mockapi.io/TreeDATA"
    var dataList by remember { mutableStateOf<List<ModelingTree>>(emptyList())}

    LaunchedEffect(key1 = jsonUrl) {
        dataList = withContext(Dispatchers.IO) {
            fetchDataTreeFromJson(jsonUrl)
        }
    }
    return dataList
}

@Composable
fun EnviAPI():List<ModelingEnvi>{
    val jsonUrl = "https://65706ff809586eff6641613c.mockapi.io/Environment"
    var dataList by remember { mutableStateOf<List<ModelingEnvi>>(emptyList())}

    LaunchedEffect(key1 = jsonUrl) {
        dataList = withContext(Dispatchers.IO) {
            fetchDataEnviFromJson(jsonUrl)
        }
    }
    return dataList
}

@Composable
fun GreetingPreview() {
    val context = LocalContext.current
    var Activity by remember { mutableStateOf(false) }
    var Tree by remember { mutableStateOf(false) }
    var Envi by remember { mutableStateOf(false) }
    val dataList = GerAPI()
    val dataListTree = TreeAPI()
    val dataListEnvi = EnviAPI()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column (modifier = Modifier
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top) {

            MyTopAppBar(modifier = Modifier.fillMaxWidth(1f))

            ActMenuIcon(modifier = Modifier.fillMaxWidth(1f),
                onClickAct = {
                    Activity = !Activity
                    Tree = false
                    Envi = false
                             },
                onClickTree = {
                    Tree = !Tree
                    Activity = false
                    Envi = false
                              },
                onClickEnvi = {
                    Envi = !Envi
                    Activity = false
                    Tree = false})

            if (Activity == false && Tree == false && Envi == false){
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(start = 8.dp, top = 8.dp, bottom = 60.dp)
                ) {
                    item {
                        Text("กิจกรรมล่าสุด", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        ShowContent(list = dataList)
                    }
                    item {
                        Text("ต้นไม้ที่น่าสนใจ", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        ShowContentTree(list = dataListTree)
                    }
                }
            }
            if (Activity){
                DetailAct(list = dataList)
            }
            if (Tree){
                DetailTree(list = dataListTree)
            }
            if (Envi){
                DetailEnvi(list = dataListEnvi)
            }
            }

        if (Activity == false && Tree == false && Envi == false){
            Column(verticalArrangement = Arrangement.Bottom) {
                ButtomBar()
            }
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

@Composable
fun ButtomBarAdmin(){
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

            }
            Column (modifier= Modifier
                .height(40.dp)
                .fillMaxWidth(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom){
                SettingIcon (onClick = { PSetting = true } )
            }
        }
        if (PSetting){
            MyApp2()
        }
    }
}

@Composable
fun PushDATA(clickPushTree:() -> Unit,clickPushAct:() -> Unit,clickPushEnvi:() -> Unit){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column (modifier = Modifier
            .fillMaxSize(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){
            Image(painter = painterResource(id = R.drawable.icon), contentDescription = null)
            OutlinedButton(
                onClick = { clickPushTree() },
                modifier = Modifier.padding(10.dp)) {
                Text(stringResource(id = R.string.PushTree))
            }
            OutlinedButton(
                onClick = { clickPushAct() },
                modifier = Modifier.padding(10.dp)) {
                Text(stringResource(id = R.string.PushActivity))
            }
            OutlinedButton(
                onClick = { clickPushEnvi() },
                modifier = Modifier.padding(10.dp)) {
                Text(stringResource(id = R.string.PushEnvironment))
            }
        }
    }
}
