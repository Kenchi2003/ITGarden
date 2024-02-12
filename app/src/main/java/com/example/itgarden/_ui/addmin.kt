package com.example.itgarden._ui

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.itgarden.MainActivity
import com.example.itgarden.R
import com.example.itgarden.data.ModelingContent
import com.example.itgarden.data.ModelingEnvi
import com.example.itgarden.data.ModelingTree
import com.example.itgarden.data.addNewData
import com.example.itgarden.data.addNewEnviData
import com.example.itgarden.data.addNewTreeData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CardInputTree(onClickedCancle: () -> Unit,onClickSuss:()->Unit){
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    var bSent by remember { mutableStateOf(false) }
    val listItemDropDown = listOf("ไม้ยืนต้น", "ไม้เรื้อย", "ไม้ประดับ", "ไม้สงวน")
    val ListfortextField = listOf("ลิงก์รูปภาพ","รหัสประจำต้น","ชื่อต้นไม้", "ชื่อวิทยาศาสตร์", "ประโยชน์/สรรพคุณ", "การดูแล","คุณลักษณะ")

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(start = 8.dp, top = 8.dp, bottom = 60.dp)
        ) {
            item {

                var ImageUrl = myTextFieldEditor(value = ListfortextField[0])
                var TreeID = myTextFieldEditor(value = ListfortextField[1])
                var TreeName = myTextFieldEditor(value = ListfortextField[2])
                var SicName = myTextFieldEditor(value = ListfortextField[3])
                val Type = DropDownButton("ประเภทต้นไม้",listItemDropDown)
                var Benefit = myTextFieldEditor(value = ListfortextField[4])
                var TakeCare = myTextFieldEditor(value = ListfortextField[5])
                var attbuit = myTextFieldEditor(value = ListfortextField[6])

                var newData = ModelingTree("0", TreeID, ImageUrl, TreeName, SicName, Type, Benefit, TakeCare, attbuit)
                Row (modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center){

                    Button(onClick = { bSent = true }, modifier = Modifier.padding(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Green,
                            contentColor = Color.Black
                        )) { Text(text = "ส่งข้อมูล") }
                if(bSent){
                    CoroutineScope(Dispatchers.IO).launch {
                        // ทำงานที่เกี่ยวข้องกับเครือข่าย
                        addNewTreeData(newData)
                        bSent = false
                    }

                    Log.d("TestAPI", newData.toString())
                    Toast.makeText(context, "ส่งข้อมูลสำเร็จ", Toast.LENGTH_SHORT).show()
                    onClickSuss()
                }
                    OutlinedButton(onClick = { showDialog = true},modifier = Modifier.padding(10.dp)) {
                        Text(text = "ยกเลิก")
                    }
                    if (showDialog){
                        AlertDialog(onDismissRequest = { showDialog = false },
                            title = {
                                Text(text = "คุณแน่ใจหรือไม่")
                            },
                            text = {
                                Text(text = "ยืนยันว่าจะยกเลิกหรือไม่?")
                            },
                            confirmButton = {
                                Button(
                                    onClick = {
                                        showDialog = false
                                        onClickedCancle()
                                    }
                                ) {
                                    Text("ยืนยัน")
                                }
                            },
                            dismissButton = {
                                Button(
                                    onClick = { showDialog = false }
                                ) {
                                    Text("ปฏิเสธ")
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CardInputActivity(onClickedCancle: () -> Unit,onClickSuss:()->Unit){

    var showDialog by remember { mutableStateOf(false) }
    var bSent by remember { mutableStateOf(false) }
    val listItemDropDown = listOf("ทำความสะอาด", "ปลูกต้นไม้", "ดูแลต้นไม้", "อื่นๆ")
    val ListfortextField = listOf("Image","ชื่อกิจกรรม", "เนื้อหา")
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column (modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally){
            Image(painter = painterResource(id = R.drawable.icon), contentDescription = null)
            val UrlImage = myTextFieldEditor(value = ListfortextField[0])
            val HeadText = myTextFieldEditor(value = ListfortextField[1])
            val BodyText = myTextFieldEditor(value = ListfortextField[2])
            val ActType = DropDownButton("ประเภทกิจกรรม",listItemDropDown)

            val newData = ModelingContent(
                "0",
                UrlImage,
                HeadText,
                BodyText,
                ActType
            )

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Button(
                    onClick = { bSent = true },
                    modifier = Modifier.padding(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green,
                        contentColor = Color.Black
                    )
                ) {
                    Text(text = "ส่งข้อมูล")
                    if(bSent){
                        CoroutineScope(Dispatchers.IO).launch {
                            // ทำงานที่เกี่ยวข้องกับเครือข่าย
                            addNewData(newData)
                        }
                        Log.d("TestAPI", newData.toString())
                        onClickSuss()
                    }
                }
                }

                OutlinedButton(onClick = { showDialog = true  },
                    modifier = Modifier.padding(10.dp)) {
                    Text(text = "ยกเลิก")
                }
            if (showDialog){
                AlertDialog(onDismissRequest = { showDialog = false },
                title = {
                    Text(text = "คุณแน่ใจหรือไม่")
                },
                text = {
                    Text(text = "ยืนยันว่าจะยกเลิกหรือไม่?")
                },
                confirmButton = {
                    Button(
                        onClick = {
                            showDialog = false
                            onClickedCancle()
                        }
                    ) {
                        Text("ยืนยัน")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { showDialog = false }
                    ) {
                        Text("ปฏิเสธ")
                    }
                }
                )
            }

        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CardEnvi(onClickedCancle: () -> Unit,onClickSuss:()->Unit){
    var showDialog by remember { mutableStateOf(false) }
    var bSent by remember { mutableStateOf(false) }

    val ListfortextField = listOf("ลักษณะสภาพแวดล้อม","ลิงก์รูปภาพ")
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column (modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally){
            Image(painter = painterResource(id = R.drawable.icon), contentDescription = null)
            val ImageURL = myTextFieldEditor(value = ListfortextField[1])
            val Envi = myTextFieldEditor(value = ListfortextField[0])
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center){

                var newData = ModelingEnvi(
                    "0",
                    Envi,
                    ImageURL
                )
                Button(onClick = { bSent = true }, modifier = Modifier.padding(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green,
                        contentColor = Color.Black
                    )) {
                    Text(text = "ส่งข้อมูล")
                    if(bSent){
                        CoroutineScope(Dispatchers.IO).launch {
                            // ทำงานที่เกี่ยวข้องกับเครือข่าย
                            addNewEnviData(newData)
                        }

                        Log.d("TestAPI", newData.toString())
                        onClickSuss()
                    }
                }
                OutlinedButton(onClick = { showDialog = true },modifier = Modifier.padding(10.dp)) {
                    Text(text = "ยกเลิก")
                }
                if (showDialog){
                    AlertDialog(onDismissRequest = { showDialog = false },
                        title = {
                            Text(text = "คุณแน่ใจหรือไม่")
                        },
                        text = {
                            Text(text = "ยืนยันว่าจะยกเลิกหรือไม่?")
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    showDialog = false
                                    onClickedCancle()
                                }
                            ) {
                                Text("ยืนยัน")
                            }
                        },
                        dismissButton = {
                            Button(
                                onClick = { showDialog = false }
                            ) {
                                Text("ปฏิเสธ")
                            }
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun myTextFieldEditor(value:String):String{
    var TextbyME by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = TextbyME,
        label = { Text(text = value) },
        onValueChange = {
            TextbyME = it
        }, modifier = Modifier.fillMaxWidth()
    )
    return TextbyME.text
}


