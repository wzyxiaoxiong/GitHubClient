package com.example.androidgithubsearch.ui.activity

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.webkit.RenderProcessGoneDetail
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.androidgithubsearch.R


class WebView2Activity : AppCompatActivity() {

    private val TAG: String = WebView2Activity::class.java.simpleName
    private var webView: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        webView =  findViewById(R.id.webView)
        webView?.setWebViewClient(AuthWebViewClient())
        webView?.getSettings()?.javaScriptEnabled = true
        val webSettings = webView?.getSettings()
        webSettings?.javaScriptCanOpenWindowsAutomatically = true
        webSettings?.javaScriptEnabled = true
        webSettings?.domStorageEnabled = true;

        val url = intent.getStringExtra("url")
        if (url != null) {
            Log.d(TAG, "onCreate url: $url")
            webView?.loadUrl(url)
        }
    }

    private inner class AuthWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            Log.d(TAG, "shouldOverrideUrlLoading url: $url")
            if (url.startsWith("your_callback_scheme://")) {
                // 处理返回的URL，提取授权码
                handleOAuthResponse(url)
                return true
            }
            // 否则，继续在WebView中加载URL
            return false
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)

            Log.d(TAG, "onPageFinished url: $url")
        }

    }

    private fun handleOAuthResponse(url: String) {
        // 解析URL以获取授权码
        // 你可以使用Uri.parse()和getQueryParameter()方法
        val uri = Uri.parse(url)
        val code = uri.getQueryParameter("code")
        // 你可以在这里添加处理授权码的代码
        // 例如，发送到服务器以交换访问令牌
    }
}