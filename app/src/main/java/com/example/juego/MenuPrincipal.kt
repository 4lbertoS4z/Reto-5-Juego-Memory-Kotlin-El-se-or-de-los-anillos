package com.example.juego

import android.content.Intent
import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MenuPrincipal : AppCompatActivity() {
   lateinit var btnInicio:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val musicOpening = MediaPlayer.create(this,R.raw.opening)
        musicOpening.isLooping=true
        musicOpening.start()
        btnInicio = findViewById<Button>(R.id.btnJugar)
        btnInicio.setOnClickListener {
            musicOpening.stop()
            val intent = Intent(applicationContext, SelectorDificultad::class.java)

            startActivity(intent)
            finish()
        }
    }
}