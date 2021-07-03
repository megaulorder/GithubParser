package com.example.githubparser

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject


class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_layout)

        val textView = findViewById<TextView>(R.id.text_view_id)

        Volley.newRequestQueue(this)
        val url = "https://api.github.com/search/repositories?q=user:megaulorder&sort=updated&order=desc"

        val stringRequest = StringRequest(url,
            { response ->
                try {
                    val jsonObject = JSONObject(response)
                    val jsonArray = jsonObject.getJSONArray("items")
                    for (i in 0 until jsonArray.length()) {
                        val jo = jsonArray.getJSONObject(i).getString("name")
                        textView.text =  String.format("%s\n%s", textView.text.toString(), jo)
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }) {
            textView.text = R.string.error_message.toString()
        }

        val requestQueue = Volley.newRequestQueue(applicationContext)
        requestQueue.add(stringRequest)
    }
}