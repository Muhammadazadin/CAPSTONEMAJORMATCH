package com.example.register

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class BeasiswaActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_besiswa)

        webView = findViewById(R.id.webView)
        webView.webViewClient = WebViewClient()

        webView.settings.apply {
            javaScriptEnabled = true
            // Atur pengaturan lainnya jika diperlukan
        }

        webView.loadUrl("https://beasiswa.kemdikbud.go.id/informasi/")
    }
}
