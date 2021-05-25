package com.test.skydemo.ui.extension

import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.showExternalWebBrowser(url: String) {
    val webPage = Uri.parse(url)
    val intent = Intent(Intent.ACTION_VIEW, webPage)
    startActivity(intent)
}