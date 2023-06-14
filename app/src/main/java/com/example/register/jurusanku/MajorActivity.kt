package com.example.register.jurusanku

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.register.HomeActivity
import com.example.register.R
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

class MajorActivity : AppCompatActivity() {

    private lateinit var idQuestionEditText: EditText
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_major)

        idQuestionEditText = findViewById(R.id.questionEditText)
        submitButton = findViewById(R.id.sendButton)
        val imageViewBack = findViewById<ImageView>(R.id.imageViewBack)


        imageViewBack.setOnClickListener {
            val intent = Intent(this@MajorActivity, HomeActivity::class.java)
            startActivity(intent)
        }

        submitButton.setOnClickListener {
            val idQuestion = idQuestionEditText.text.toString()
            sendRequestToAPI(idQuestion)
        }
    }

    private fun sendRequestToAPI(idQuestion: String) {
        val client = OkHttpClient()

        val url = "https://match-major-backendv2-7qtkfzxmja-et.a.run.app/api/recomendation/question"

        val mediaType = "application/json; charset=utf-8".toMediaType()
        val requestBody = """
            {
                "question": "$idQuestion"
            }
        """.trimIndent().toRequestBody(mediaType)

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    e.printStackTrace()
                    Toast.makeText(this@MajorActivity, "Network Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()
                try {
                    val jsonResponse = JSONObject(responseBody!!)
                    val idQuestion = jsonResponse.getString("id_question")
                    val message = jsonResponse.getString("message")

                    runOnUiThread {
                        val intent = Intent(this@MajorActivity, ResultActivity::class.java)
                        intent.putExtra("id_question", idQuestion)
                        intent.putExtra("message", message)
                        startActivity(intent)
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                    runOnUiThread {
                        Toast.makeText(this@MajorActivity, "Error parsing JSON", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}
