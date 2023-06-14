package com.example.register

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val namaProfil = findViewById<TextView>(R.id.profile_name)
        val tombolLogout = findViewById<Button>(R.id.logout_button)

        val namaPengguna = "Sampai Jumpa"

        namaProfil.text = namaPengguna

        tombolLogout.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

