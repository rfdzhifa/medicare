package com.example.medicare.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import android.webkit.WebViewClient
import androidx.core.content.ContextCompat
import com.example.medicare.R
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        val window: Window = this@WebViewActivity.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this@WebViewActivity, R.color.blue_base)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#2B95FF")))

        wv_scan_result.webViewClient = WebViewClient()
        wv_scan_result.loadUrl(intent.getStringExtra("url")?:"")
        val webViewSettings = wv_scan_result.settings
        webViewSettings.javaScriptEnabled = true
        webViewSettings.domStorageEnabled = true

        var actionBar = getSupportActionBar()

        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onBackPressed() {
        if (wv_scan_result!!.canGoBack()) {
            wv_scan_result.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }
}