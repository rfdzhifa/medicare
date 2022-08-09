package com.example.medicare

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.webkit.WebViewClient
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        supportActionBar?.hide()
        val window: Window = this@WebViewActivity.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this@WebViewActivity, R.color.blue_base)

        wv_scan_result.webViewClient = WebViewClient()
        wv_scan_result.loadUrl(intent.getStringExtra("url")?:"")
        val webViewSettings = wv_scan_result.settings
        webViewSettings.javaScriptEnabled = true
        webViewSettings.domStorageEnabled = true
    }

    override fun onBackPressed() {
        if (wv_scan_result!!.canGoBack()) {
            wv_scan_result.goBack()
        } else {
            super.onBackPressed()
        }
    }
}