package com.example.itgarden.data

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Preview
@Composable
fun DownLoad() {
    val fileUrl by remember { mutableStateOf("https://download1325.mediafire.com/3sg7t7bvsdkgfXQX3TrQBEnrzaDhD5ZTKh-m-Mk49uvWDvsfUIPM7VM29NK8vbsBJ-nnRmyyk4qiLgQvtsa6DPot1zj34-ZRFNO9yWf7A-WOQvPgAtsE2TXA_lcXfsfLjqKJL7Jz_B_FWTkejj4uANeTvEI8TNBJgkewnUoj5uaE/6c2pwwz1c8x3cwa/activity_ass0_1.json") }
    val fileName by remember { mutableStateOf("activity_ass0_1.json") }

    var progress by remember { mutableStateOf(0) }
    var isDownloading by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        checkPermission(context)
        if (fileUrl.isNotEmpty() && fileName.isNotEmpty() && !isDownloading) {
            downloadFile(context, fileUrl, fileName)
        }else{
            isDownloading = true
        }
        Text(if (isDownloading) "Success" else "Downloading...")

        // Progress indicator
        if (isDownloading) {
            LinearProgressIndicator(progress = progress.toFloat() / 100f, modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}

fun checkPermission(context: Context): Boolean {
    val permission = ContextCompat.checkSelfPermission(context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
    return permission == PackageManager.PERMISSION_GRANTED
}

@SuppressLint("Range")
private fun downloadFile(context: Context, fileUrl: String, fileName: String){
    val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    val uri = Uri.parse(fileUrl)

    val request = DownloadManager.Request(uri)
        .setTitle(fileName)
        .setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, fileName)

    val downloadId = downloadManager.enqueue(request)

    // Start a coroutine to monitor the download progress
    CoroutineScope(Dispatchers.IO).launch {
        var downloading = true
        while (downloading) {
            val q = DownloadManager.Query()
            q.setFilterById(downloadId)
            val cursor = downloadManager.query(q)
            if (cursor.moveToFirst()) {
                val bytesDownloaded = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR))
                val bytesTotal = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES))
                val progress = (bytesDownloaded * 100 / bytesTotal)

                // Update the UI with progress
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Download Progress: $progress%", Toast.LENGTH_SHORT).show()
                }

                if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
                    // Download completed
                    downloading = false
                    withContext(Dispatchers.Main) {
                    }
                }
            }
            cursor.close()
        }
    }
}