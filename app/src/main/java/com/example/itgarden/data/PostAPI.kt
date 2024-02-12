package com.example.itgarden.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.MediaType.Companion.toMediaType

// ฟังก์ชันสำหรับดึงข้อมูลปัจจุบันจาก endpoint
fun getCurrentData(): List<ModelingContent> {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("https://656afaa9dac3630cf72786e1.mockapi.io/FristContentPage")
        .build()

    val response = client.newCall(request).execute()
    val responseData = response.body?.string()

    // แปลง JSON เป็น List<TreeData>
    return Gson().fromJson(responseData, object : TypeToken<List<ModelingContent>>() {}.type)
}

// ฟังก์ชันสำหรับเพิ่มข้อมูลใหม่
fun addNewData(newData: ModelingContent) {
    val currentData = getCurrentData()

    // หา "id" ที่มีค่ามากที่สุด
    val maxId = currentData.maxByOrNull { it.id +1}?.id ?: 0
    // กำหนด "id" ให้เพิ่มไปที่ค่า "id" ที่มีค่ามากที่สุด + 1
    newData.id = maxId.toString()


    // ทำการส่งคำขอ HTTP POST โดยใส่ข้อมูลใหม่
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("https://656afaa9dac3630cf72786e1.mockapi.io/FristContentPage")
        .post(Gson().toJson(newData).toRequestBody("application/json".toMediaType()))
        .build()

    client.newCall(request).execute()
}

fun getCurrentTreeData(): List<ModelingTree> {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("https://656afaa9dac3630cf72786e1.mockapi.io/TreeDATA")
        .build()

    val response = client.newCall(request).execute()
    val responseData = response.body?.string()

    // แปลง JSON เป็น List<TreeData>
    return Gson().fromJson(responseData, object : TypeToken<List<ModelingTree>>() {}.type)
}

// ฟังก์ชันสำหรับเพิ่มข้อมูลใหม่
fun addNewTreeData(newData: ModelingTree) {
    val currentData = getCurrentTreeData()

    // หา "id" ที่มีค่ามากที่สุด
    val maxId = currentData.maxByOrNull { it.id +1}?.id ?: 0
    // กำหนด "id" ให้เพิ่มไปที่ค่า "id" ที่มีค่ามากที่สุด + 1
    newData.id = maxId.toString()


    // ทำการส่งคำขอ HTTP POST โดยใส่ข้อมูลใหม่
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("https://656afaa9dac3630cf72786e1.mockapi.io/TreeDATA")
        .post(Gson().toJson(newData).toRequestBody("application/json".toMediaType()))
        .build()

    client.newCall(request).execute()
}

fun getCurrentEnviData(): List<ModelingEnvi> {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("https://65706ff809586eff6641613c.mockapi.io/Environment")
        .build()

    val response = client.newCall(request).execute()
    val responseData = response.body?.string()

    // แปลง JSON เป็น List<TreeData>
    return Gson().fromJson(responseData, object : TypeToken<List<ModelingEnvi>>() {}.type)
}

// ฟังก์ชันสำหรับเพิ่มข้อมูลใหม่
fun addNewEnviData(newData: ModelingEnvi) {
    val currentData = getCurrentEnviData()

    // หา "id" ที่มีค่ามากที่สุด
    val maxId = currentData.maxByOrNull { it.id +1}?.id ?: 0
    // กำหนด "id" ให้เพิ่มไปที่ค่า "id" ที่มีค่ามากที่สุด + 1
    newData.id = maxId.toString()


    // ทำการส่งคำขอ HTTP POST โดยใส่ข้อมูลใหม่
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("https://65706ff809586eff6641613c.mockapi.io/Environment")
        .post(Gson().toJson(newData).toRequestBody("application/json".toMediaType()))
        .build()

    client.newCall(request).execute()
}