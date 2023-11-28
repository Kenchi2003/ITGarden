package com.example.itgarden._ui

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import java.io.InputStream
import java.net.URL


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
            .clickable(onClick = HomeOnClick)
            .background(Color(0xffFFFFFF)),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Image(painter = painterResource(id = R.drawable.iconbuttom1),
            contentDescription = null,
            Modifier
                .size(width = 45.dp, height = 48.dp)
                .fillMaxWidth(1f)
        )

        Image(painter = painterResource(id = R.drawable.iconbuttom2),
            contentDescription = null,
            modifier= Modifier
                .height(40.dp)
                .clickable(onClick = SettingOnClick)
                .fillMaxWidth(),
            alignment = Alignment.CenterEnd
        )
    }
}

@Composable
fun ContentUI(modifier: Modifier=Modifier, text:String, image: Int){
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
        Image(painter = painterResource(id = R.drawable.icon_menu1),
            contentDescription = null,modifier.padding(bottom = 8.dp))

        Text(text = "กิจกรรม",
            fontSize = 24.sp,modifier = Modifier.padding(10.dp)
        )
        Text(text = "",
            modifier = Modifier.padding(10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun viewtopbar() {

    ITGardenTheme {
        Column (modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)){
            AsyncImage(
                model = "https://example.com/image.jpg",
                contentDescription = "Translated description of what the image contains"
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun viewmenu() {
    ITGardenTheme {
        Row {
            MenuIcon(stringResource(R.string.icon_menu1),R.drawable.icon_menu1,onClick = {})
            MenuIcon(stringResource(R.string.menu2),R.drawable.menu2,onClick = {})
            MenuIcon(stringResource(R.string.menu3),R.drawable.menu3,onClick = {})
        }
    }
}