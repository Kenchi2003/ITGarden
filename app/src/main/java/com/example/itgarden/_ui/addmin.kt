package com.example.itgarden._ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.itgarden.R
import com.example.itgarden.data.ModelingContent
import com.example.itgarden.data.addNewData
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


@Composable
fun CardInputTree(onClickedCancle: () -> Unit){
    var showDialog by remember { mutableStateOf(false) }

    var mTreeName by remember { mutableStateOf(TextFieldValue("")) }
    var mTreeID by remember { mutableStateOf(TextFieldValue("")) }
    var mSicName by remember { mutableStateOf(TextFieldValue("")) }
    var mBenefit by remember { mutableStateOf(TextFieldValue("")) }
    var mTakeCare by remember { mutableStateOf(TextFieldValue("")) }
    val ListfortextField = listOf("รหัสประจำต้น","ชื่อต้นไม้", "ชื่อวิทยาศาสตร์", "ประโยชน์/สรรพคุณ", "การดูแล")
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column (modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally){
            Image(painter = painterResource(id = R.drawable.icon), contentDescription = null)
            var TreeID = myTextFieldEditor(value = ListfortextField[0])
            var TreeName = myTextFieldEditor(value = ListfortextField[1])
            var SicName = myTextFieldEditor(value = ListfortextField[2])
            var Benefit = myTextFieldEditor(value = ListfortextField[3])
            var TakeCare = myTextFieldEditor(value = ListfortextField[4])
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center){

                Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green,
                        contentColor = Color.Black
                    )) {
                    Text(text = "ส่งข้อมูล")
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


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CardInputActivity(onClickedCancle: () -> Unit){
    var showDialog by remember { mutableStateOf(false) }
    var bSent by remember { mutableStateOf(false) }
    val ListfortextField = listOf("Image","ชื่อกิจกรรม", "เนื้อหา")
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column (modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally){
            Image(painter = painterResource(id = R.drawable.icon), contentDescription = null)
            val ImageUrl = myTextFieldEditor(value = ListfortextField[0])
            val TextHead = myTextFieldEditor(value = ListfortextField[1])
            val TextBody = myTextFieldEditor(value = ListfortextField[2])

            val newData = ModelingContent(
                "0",
                ImageUrl,
                TextHead,
                TextBody
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

//@Composable
//fun DialogConfirm(onClickedY:()->Unit,onClickedN:()->Unit){
//    Column(modifier = Modifier
//        .fillMaxWidth()
//        .background(
//            color = Color.White,
//            shape = RoundedCornerShape(size = 10.dp)
//        )
//        .padding(start = 20.dp, end = 20.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally)
//    {
//        Text(text = "ยืนยันว่าจะยกเลิกหรือไม่?", fontSize = 32.sp ,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 4.dp, top = 4.dp, end = 4.dp, bottom = 120.dp))
//        Row {
//            Button(onClick = { onClickedY() },
//                modifier = Modifier.padding(10.dp),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color.Green,
//                    contentColor = Color.Black
//                )) {
//                Text(text = "ยืนยัน")
//            }
//            OutlinedButton(onClick = { onClickedN() },
//                modifier = Modifier.padding(10.dp)) {
//                Text(text = "ไม่")
//            }
//        }
//
//    }
//}

@Composable
fun CardEnvi(onClickedCancle: () -> Unit){
    var showDialog by remember { mutableStateOf(false) }
    var mImageUrl by remember { mutableStateOf(TextFieldValue("")) }
    val ListfortextField = listOf("ลักษณะสภาพแวดล้อม")
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column (modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally){
            Image(painter = painterResource(id = R.drawable.icon), contentDescription = null)
            var ImageUrl = myTextFieldEditor(value = ListfortextField[0])
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center){

                Button(onClick = {  }, modifier = Modifier.padding(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green,
                        contentColor = Color.Black
                    )) {
                    Text(text = "ส่งข้อมูล")
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
//
//@Composable
//fun SentData(myData: List<ModelingContent>) {
//    var result by remember { mutableStateOf<String?>(null) }
//
//    LaunchedEffect(Unit) {
//        val apiService = RetrofitClient.apiService
//        val call = apiService.postDataActivity(myData)
//
//        call.enqueue(object : Callback<ResponseBody> {
//            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                if (response.isSuccessful) {
//                    result = "Success: ${response.body()?.string()}"
//                    Log.d("TestAPI",result.toString())
//                } else {
//                    result = "Error: ${response.errorBody()?.string()}"
//                    Log.e("TestAPI",result.toString())
//                }
//            }
//            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                result = "Error: ${t.localizedMessage}"
//                Log.e("TestAPI",result.toString())
//            }
//        })
//
//    }
//
//    Image(painter = painterResource(id = R.drawable.sucsses), contentDescription = null)
//}

//@Preview
//@Composable
//fun YourComposeScreenPreview() {
//    YourComposeScreen()
//}

@Preview
@Composable
fun mp(){
    CardInputTree(onClickedCancle = {})
}

@Preview
@Composable
fun mpt(){
    CardInputActivity(onClickedCancle = {})
}
