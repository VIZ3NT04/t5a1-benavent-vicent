package com.example.t5a1_benavent_vicent

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ImageView : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_image_view)

        // Inicializar el WebView
        webView = findViewById(R.id.webView)

        // Configuración del WebView
        webView.webViewClient = WebViewClient()  // Para abrir enlaces dentro del WebView
        webView.settings.javaScriptEnabled = true  // Habilitar JavaScript si es necesario

        // Cargar una URL (Ejemplo: Wikipedia)
        val url = intent.getStringExtra("URL") ?: "https://www.wikipedia.org/"
        webView.loadUrl(url)

        // Ajuste para el manejo de ventanas de sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
