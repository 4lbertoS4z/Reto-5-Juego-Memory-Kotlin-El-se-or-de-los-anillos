package com.example.juego

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SelectorDificultad : AppCompatActivity() {
    lateinit var btnFacil: Button
    lateinit var btnMedio:Button
    lateinit var btnDificil:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selector_dificultad)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


        btnFacil = findViewById<Button>(R.id.btnFacil)

        btnFacil.setOnClickListener {

            val intent = Intent(applicationContext, Juego::class.java)

            startActivity(intent)
            finish()
        }

        btnMedio= findViewById<Button>(R.id.btnMedio)

        btnMedio.setOnClickListener {

            val intent = Intent(applicationContext, JuegoMedio::class.java)

            startActivity(intent)
            finish()
        }

        btnDificil = findViewById<Button>(R.id.btnDificil)

        btnDificil.setOnClickListener {

            val intent = Intent(applicationContext, JuegoDificil::class.java)

            startActivity(intent)
            finish()
        }
    }
    override fun onBackPressed() {

    }
}