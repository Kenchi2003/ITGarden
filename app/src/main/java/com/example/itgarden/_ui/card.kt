package com.example.itgarden._ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.itgarden.R
import com.example.itgarden.ui.theme.ITGardenTheme


@Composable
fun MyTopAppBar(modifier:Modifier=Modifier){
    Row(
        modifier
            .fillMaxWidth(1f)
            .padding(8.dp)
            .background(Color(0xffFFFFFF)),
        verticalAlignment = Alignment.CenterVertically

    ){
        Image(painter = painterResource(id = R.drawable.icon) ,
            contentDescription = null,Modifier.size(55.dp)
        )
        Text(
            text = "ITGARDEN",
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.permanent)),
                fontWeight = FontWeight(400),
                color = Color(0xFF377E76),
            ),
            modifier= Modifier
                .padding(end = 55.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }

}

@Composable
fun MenuIcon(content: String,image:Int,onClick: () -> Unit){
    Column (modifier = Modifier
        .padding(4.dp)
        .widthIn(60.dp, 100.dp)
        .heightIn(120.dp, 150.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){

        Image(
            painter = painterResource(id = image),
            contentDescription = "image description",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .shadow(
                    elevation = 15.dp,
                    clip = true,
                    shape = CircleShape,
                    ambientColor = Color(0xff000000),
                    spotColor = Color(0xff000000)
                )
                .size(90.dp)
                .border(width = 2.dp, color = Color.Black, shape = CircleShape)
                .clip(CircleShape)
                .clickable(onClick = onClick)
        )
        Text(text = content,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick))
    }
}

@Composable
fun ButtomBar(HomeOnClick: () -> Unit,SettingOnClick: () -> Unit){
    Row (
        Modifier
            .fillMaxWidth(1f)
            .padding(all = 8.dp)
            .background(Color(0xffFFFFFF)),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Column (modifier= Modifier
            .height(40.dp)
            .clickable(onClick = SettingOnClick),
            horizontalAlignment = Alignment.Start)
        {
            Image(painter = painterResource(id = R.drawable.iconbuttom1),
                contentDescription = null,
                Modifier
                    .size(width = 45.dp, height = 48.dp)
                    .fillMaxWidth(1f)
                    .clickable(onClick = HomeOnClick)
            )
        }
        Column (modifier= Modifier
            .height(40.dp)
            .clickable(onClick = SettingOnClick)
            .fillMaxWidth(),
            horizontalAlignment = Alignment.End){
            Image(painter = painterResource(id = R.drawable.iconbuttom2),
                contentDescription = null,
                modifier= Modifier
                    .height(40.dp)
                    .width(40.dp)
                    .clickable(onClick = SettingOnClick)
            )
        }

    }
}

@Composable
fun ContentUI(modifier: Modifier=Modifier, head:String, text:String, image: String){
    Column (
        modifier
            .shadow(
                elevation = 8.dp,
                clip = true,
                shape = RectangleShape,
                ambientColor = Color(0xff000000),
                spotColor = Color(0xff000000)
            )
            .padding(4.dp)
            .background(
                Color(0xffF2EEF3),
                shape = RoundedCornerShape(size = 10.dp)
            )
    ){
        Image(
            painter = rememberImagePainter(
                data = image, // ใส่ URL ของรูปภาพที่คุณต้องการแสดง
                builder = {
                    crossfade(true) // เพื่อทำให้การเปลี่ยนรูปภาพมีการ crossfade
                }
            ),
            contentDescription = "Description for screen readers",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(8.dp),
            contentScale = ContentScale.Fit
        )
        Text(text = head,
            fontSize = 24.sp,modifier = Modifier.padding(2.dp)
        )
        Text(text = text,
            modifier = Modifier.padding(2.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun viewtopbar() {
    val mData = "https://i.postimg.cc/j2NRfmGk/icon-menu1.jpg"
    ITGardenTheme {
        Column (modifier = Modifier
            .fillMaxWidth()){
            ContentUI(head = "aksnfjh", text = "Test Test Test Test Test Test Test", image = mData)

        }
    }
}



@Preview(showBackground = true)
@Composable
fun viewmenu() {
    ITGardenTheme {
        Row {
            ButtomBar(HomeOnClick = { /*TODO*/ }) {
            }
        }
    }
}