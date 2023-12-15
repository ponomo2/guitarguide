package com.example.guitarguide.downloader

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri

interface DownloaderInterface {
    fun downloadFile(url: String): Long
}


class AndroidDownloader(
    private val context: Context,
    private val title: String
): DownloaderInterface {

    private val downloadManager = context.getSystemService(DownloadManager::class.java)

    override fun downloadFile(url: String): Long {
        val request = DownloadManager.Request(url.toUri())
            .setMimeType("application/pdf")
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle("$title")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "$title")
        return downloadManager.enqueue(request)
    }
}