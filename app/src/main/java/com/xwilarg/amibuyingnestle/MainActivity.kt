package com.xwilarg.amibuyingnestle

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        object : Thread() {
            override fun run() {
                val url = URL("https://en.wikipedia.org/w/api.php?action=parse&page=List_of_Nestl%C3%A9_brands&format=json")
                val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
                try {
                    val content = BufferedReader(InputStreamReader(urlConnection.inputStream))
                    val json = JSONObject(content.readText())
                    Log.i("Debug", json.getJSONObject("parse").getJSONObject("text").getString("*"))
                } finally {
                    urlConnection.disconnect()
                }
            }
        }.start()
    }
}