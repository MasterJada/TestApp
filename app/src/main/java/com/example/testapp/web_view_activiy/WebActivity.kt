package com.example.testapp.web_view_activiy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient

const val EXTRA_URL = "URL"

class WebActivity : AppCompatActivity() {
    private var url: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val webView = WebView(this)

        setContentView(webView)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        webView.webViewClient =  WebViewClient()
        if(intent.hasExtra(EXTRA_URL)){
            url = intent?.extras?.getString(EXTRA_URL)
            webView.loadUrl(url)
        }else{
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home){
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
