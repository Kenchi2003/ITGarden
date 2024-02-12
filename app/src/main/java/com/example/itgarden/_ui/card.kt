package com.example.itgarden._ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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


@Composable
fun IconUA(Image:Int,onClick: () -> Unit){
    Column (modifier = Modifier
        .height(250.dp)
        .padding(bottom = 20.dp)
        .shadow(
            elevation = 10.dp,
            clip = true,
            shape = RectangleShape,
            ambientColor = Color(0xffFFFFFF),
            spotColor = Color(0xff000000)
        )
        .graphicsLayer(shape = RoundedCornerShape(8.dp))
        .clip(RoundedCornerShape(8.dp))
        .clickable { onClick() }
        .background(
            Color(0xFFFFFFFF),
            shape = RoundedCornerShape(size = 18.dp)
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        Image(painter = painterResource(id = Image),
            contentDescription = null,
            modifier = Modifier
                .width(250.dp)
                .height(300.dp)
                .clip(RoundedCornerShape(16.dp))
        )
    }
}

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
        .width(100.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){

        Image(
            painter = painterResource(id = image),
            contentDescription = "image description",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .shadow(
                    elevation = 8.dp,
                    clip = true,
                    shape = CircleShape,
                    ambientColor = Color(0xff000000),
                    spotColor = Color(0xff000000)
                )
                .size(65.dp)
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
fun SettingIcon(onClick: () -> Unit){
    IconButton(onClick = { onClick()}) {
        Icon(imageVector = Icons.Filled.ArrowBack,contentDescription = null, modifier = Modifier
            .size(32.dp)
        )
    }
}


@Composable
fun HomeIcon(onClick: () -> Unit){
    Image(painter = painterResource(
        id = R.drawable.iconbuttom1),
        contentDescription = null,
        modifier = Modifier
            .size(32.dp)
            .clickable(onClick = onClick)
    )
}

@Composable
fun ContentUI(
    head: String?,
    text: String?,
    image: String?)
{ var expanded by remember { mutableStateOf(false) }
    Column (
        modifier = Modifier
            .padding(4.dp)
            .width(300.dp)
            .shadow(
                elevation = 8.dp,
                clip = true,
                shape = RectangleShape,
                ambientColor = Color(0xFF000000),
                spotColor = Color(0xFF000000)
            )
            .clickable { expanded = !expanded }
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            .background(
                Color(0xFFFFFFFF),
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
            contentDescription = null,
            modifier = Modifier
                .width(300.dp)
                .height(190.dp)
                .padding(4.dp),
            contentScale = ContentScale.Fit
        )
        if (head != null) {
            Text(text = head,
                fontSize = 18.sp,modifier = Modifier.padding(start = 5.dp)
            )
        }
        if (expanded) {
            if (text != null) {
                Text(text = text,fontSize = 16.sp,
                    modifier = Modifier.padding(start = 30.dp, bottom = 8.dp)
                )
            }
        }
    }
}

@Composable
fun ShowTreeDetail(mimage:String,
                   mTreeID:String,
                   mName: String,
                   mSicName: String,
                   mtype:String,
                   mAttri:String,
                   mBenifile: String,
                   mTakeCare: String
){
    var expanded by remember { mutableStateOf(false)
    }
    Column (modifier = Modifier
        .padding(4.dp)
        .width(300.dp)
        .shadow(
            elevation = 8.dp,
            clip = true,
            shape = RectangleShape,
            ambientColor = Color(0xFF000000),
            spotColor = Color(0xFF000000)
        )
        .clickable { expanded = !expanded }
        .animateContentSize(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
        .background(
            Color(0xFFFFFFFF),
            shape = RoundedCornerShape(size = 10.dp)
        )
    ){
        Image(painter = rememberImagePainter(
            data = mimage, // ใส่ URL ของรูปภาพที่คุณต้องการแสดง
            builder = {
                crossfade(true) // เพื่อทำให้การเปลี่ยนรูปภาพมีการ crossfade
            }
        ),
            contentDescription = null,
            modifier = Modifier
                .width(300.dp)
                .height(190.dp)
                .padding(4.dp),
            contentScale = ContentScale.Fit
        )
        Column (modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
            horizontalAlignment = Alignment.Start){
            Text(text = "ชื่อ: $mName", fontSize = 24.sp)
            if(expanded){
                Text(text = "รหัสประจำต้น: $mTreeID", fontSize = 16.sp)
                Text(text = "ชื่อวิทยาศาสตร์: $mSicName", fontSize = 16.sp)
                Text(text = "ประเภท: $mtype", fontSize = 16.sp)
                Text(text = "คุณลักษณะ: $mAttri", fontSize = 16.sp)
                Text(text = "ประโยชน์: $mBenifile \n", fontSize = 16.sp)
                Text(text = "การดูแล: $mTakeCare \n", fontSize = 16.sp)
            }

        }
    }
}


@Composable
fun ShowActDetail(
    mimage:String,
    mName:String,
    mBody:String,
    mType:String){
    var expanded by remember { mutableStateOf(false) }
    Column (
        modifier = Modifier
            .padding(4.dp)
            .width(300.dp)
            .shadow(
                elevation = 8.dp,
                clip = true,
                shape = RectangleShape,
                ambientColor = Color(0xFF000000),
                spotColor = Color(0xFF000000)
            )
            .clickable { expanded = !expanded }
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            .background(
                Color(0xFFFFFFFF),
                shape = RoundedCornerShape(size = 10.dp)
            )
    ){
        Image(painter = rememberImagePainter(
            data = mimage, // ใส่ URL ของรูปภาพที่คุณต้องการแสดง
            builder = {
                crossfade(true) // เพื่อทำให้การเปลี่ยนรูปภาพมีการ crossfade
            }
        ),
            contentDescription = null,
            modifier = Modifier
                .width(300.dp)
                .height(190.dp)
                .padding(4.dp),
            contentScale = ContentScale.Fit
        )
        Column (modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
            horizontalAlignment = Alignment.Start){
            Text(text = "ชื่อกิจกรรม: $mName", fontSize = 24.sp)
            if (expanded){
                Text(text = "รายละเอียด: $mBody", fontSize = 16.sp)
                Text(text = "ประเภท: $mType", fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun ShowEnviDetail(
    mimage:String,
    mBody:String){
    var expanded by remember { mutableStateOf(false) }
    Column (
        modifier = Modifier
            .padding(4.dp)
            .width(300.dp)
            .shadow(
                elevation = 8.dp,
                clip = true,
                shape = RectangleShape,
                ambientColor = Color(0xFF000000),
                spotColor = Color(0xFF000000)
            )
            .clickable { expanded = !expanded }
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            .background(
                Color(0xFFFFFFFF),
                shape = RoundedCornerShape(size = 10.dp)
            )
    ){
        Image(painter = rememberImagePainter(
            data = mimage, // ใส่ URL ของรูปภาพที่คุณต้องการแสดง
            builder = {
                crossfade(true) // เพื่อทำให้การเปลี่ยนรูปภาพมีการ crossfade
            }
        ),
            contentDescription = null,
            modifier = Modifier
                .width(300.dp)
                .height(190.dp)
                .padding(4.dp),
            contentScale = ContentScale.Fit
        )
        Column (modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
            horizontalAlignment = Alignment.Start){
            Text(text = "รายละเอียด: $mBody", fontSize = 16.sp)

        }
    }
}
