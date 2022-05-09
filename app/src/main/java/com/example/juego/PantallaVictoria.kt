package com.example.juego

import android.content.Intent
import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class PantallaVictoria : AppCompatActivity() {

    lateinit var botonReiniciar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_victoria)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val musicVictory = MediaPlayer.create(this,R.raw.victory)
        musicVictory.isLooping=true
        musicVictory.start()

        val bundle = intent.extras
        val dato = bundle?.getString("Score")

      //  Toast.makeText(this, dato, Toast.LENGTH_LONG).show()
        val envio = findViewById<TextView>(R.id.txtPuntos)
        envio.text= dato.toString()
        botonReiniciar = findViewById<Button>(R.id.btnReinicio)
        botonReiniciar.setOnClickListener {
            musicVictory.stop()
            val intent = Intent(applicationContext, SelectorDificultad::class.java)

            startActivity(intent)

            finish()
        }
    }

    override fun onBackPressed() {

    }
}