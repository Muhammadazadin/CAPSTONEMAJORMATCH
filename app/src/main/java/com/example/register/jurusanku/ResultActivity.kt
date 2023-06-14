package com.example.register.jurusanku

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.register.R
import com.example.register.SpaceItemDecoration
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class ResultActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        resultTextView = findViewById(R.id.resultTextView)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )


        val verticalSpaceHeightRes = R.dimen.item_vertical_spacing // Ganti dengan resource ID tinggi jarak yang Anda inginkan
        val itemDecoration = SpaceItemDecoration(this, verticalSpaceHeightRes)
        recyclerView.addItemDecoration(itemDecoration)
        val idQuestion = intent.getStringExtra("id_question")
        val apiUrl = "https://match-major-backendv2-7qtkfzxmja-et.a.run.app/api/recomendation/answer/$idQuestion"

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(apiUrl)
            .get()
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                runOnUiThread {
                    resultTextView.text = "Error: Failed to connect to API"
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()
                try {
                    val jsonResponse = JSONObject(responseBody)
                    val idQuestion = jsonResponse.getString("id_question")
                    val data = jsonResponse.getJSONArray("data")

                    runOnUiThread {
                        resultTextView.text = "ID Question: $idQuestion"

                        if (data.length() > 0) {
                            val dataItems = mutableListOf<String>()
                            for (i in 0 until data.length()) {
                                val dataObject = data.getJSONObject(i)
                                val prodi = dataObject.getString("prodi")
                                val university = dataObject.getInt("university")
                                val matchPercentage = calculateMatchPercentage(i)
                                val item = "Prodi: $prodi, University: $university, Match: $matchPercentage%"
                                dataItems.add(item)
                            }
                            val adapter = RecyclerViewAdapter(dataItems)
                            recyclerView.adapter = adapter
                        } else {
                            resultTextView.text = "Data not found"
                        }
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                    runOnUiThread {
                        resultTextView.text = "Error: Failed to parse JSON"
                    }
                }
            }
        })
    }

    private fun calculateMatchPercentage(index: Int): Int {
        val initialPercentage = 100
        val percentageDecrease = 10
        return initialPercentage - (index * percentageDecrease)
    }
}