package com.example.juego

import android.content.Intent
import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class PantallaDerrota : AppCompatActivity() {
    lateinit var botonReiniciar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_derrota)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val musicDefeat= MediaPlayer.create(this,R.raw.derrota)
        musicDefeat.isLooping=true
        musicDefeat.start()


        botonReiniciar = findViewById<Button>(R.id.btnReinicio)
        botonReiniciar.setOnClickListener {
            musicDefeat.stop()
            val intent = Intent(applicationContext, SelectorDificultad::class.java)

            startActivity(intent)
            finish()
        }
        }

    override fun onBackPressed() {

    }
}