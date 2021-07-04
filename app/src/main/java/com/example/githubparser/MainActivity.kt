package com.example.githubparser

import android.app.Activity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.TextView
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : Activity() {
    lateinit var textView: TextView
    lateinit var button: Button
    lateinit var client: OkHttpClient

    private val REQUEST_URL = "https://api.github.com/search/repositories?q=user:megaulorder&sort=updated&order=desc"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_layout)

        textView = findViewById(R.id.text_view_id)
        button = findViewById(R.id.get_button_id)

        textView.movementMethod = ScrollingMovementMethod()

        button.setOnClickListener {
            getData()
        }
    }

    private fun getData() {
        client = OkHttpClient()

        val request = Request.Builder().url(REQUEST_URL).build()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()

                val jsonObject = JSONObject(body)
                val jsonArray = jsonObject.getJSONArray("items")

                for (i in 0 until jsonArray.length()) {
                    val jo = jsonArray.getJSONObject(i).getString("name")

                    runOnUiThread {
                        textView.text = String.format("%s\n%s", textView.text.toString(), jo)
                    }
                }
            }

            override fun onFailure(call: Call, e: IOException) {

                runOnUiThread {
                    textView.text = R.string.data_error_message.toString()
                }
            }
        })
    }
}
