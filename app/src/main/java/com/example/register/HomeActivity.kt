package com.example.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.register.jurusanku.MajorActivity
import com.example.register.kampus.UniversitasActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val jurusankuView: View = findViewById(R.id.major)
        val kampusView: View = findViewById(R.id.building)
        val profileView: View = findViewById(R.id.Profile)
        val beasiswaView: View = findViewById(R.id.beasiswa)

        jurusankuView.setOnClickListener {

            val intent = Intent(this, MajorActivity::class.java)
            startActivity(intent)
        }

        kampusView.setOnClickListener {

            val intent = Intent(this, UniversitasActivity::class.java)
            startActivity(intent)
        }

        profileView.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        beasiswaView.setOnClickListener {
            val intent = Intent(this, BeasiswaActivity::class.java)
            startActivity(intent)

        }
    }
}
