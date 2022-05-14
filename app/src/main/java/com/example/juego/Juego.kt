/*  MEMORY GAME: Crea un "memograma" o "juego de memoria" basado en parejas de cartas.

NOTA: Para la revisión en directo desde Twitch se agradece crear un readme con un enlace de descarga o ejecución de la app. Gracias!

¿Quién no ha jugado alguna vez a las parejas de cartas? Desde bien pequeños hasta alguna noche de fiesta, ¿cierto? Bien, para este reto deberás elegir tu temática favorita y recrear el famoso juego. Sorpréndenos! 😉

¿En qué consiste el reto?

Los requisitos del juego son bastante sencillos. En primer lugar, tienes que elegir la temática que quieras, como Pokémon, Star Wars o incluso Peppa Pig.

La dinámica del juego consistirá en presentar un tablero con una serie de cartas boca abajo y revelar todas las parejas antes de que se acabe el tiempo. El usuario tocará cada carta para darle la vuelta y, si dos de ellas coinciden al descubrirlas,
se pondrán boca arriba. En caso contrario, se volverán a ocultar.

El juego se gana si se descubren todas las parejas dentro del límite de tiempo.  */


package com.example.juego

import android.content.Intent
import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.*
import org.w3c.dom.Text

import java.util.*

class Juego : AppCompatActivity() {


    //region Variables
    // variables para los componentes de la vista

    lateinit var img00: ImageButton
    lateinit var img01: ImageButton
    lateinit var img02: ImageButton
    lateinit var img03: ImageButton
    lateinit var img04: ImageButton
    lateinit var img05: ImageButton
    lateinit var img06: ImageButton
    lateinit var img07: ImageButton
    lateinit var img08: ImageButton
    lateinit var img09: ImageButton
    lateinit var img10: ImageButton
    lateinit var img11: ImageButton
    lateinit var img12: ImageButton
    lateinit var img13: ImageButton
    lateinit var img14: ImageButton
    lateinit var img15: ImageButton
    var tablero = arrayOfNulls<ImageButton>(16)
    lateinit var btnReinicio: Button
    var textoPuntuacion: TextView? = null
    var textoMovimientos: TextView? = null
    var textoParejas: TextView? = null
    var puntuacion = 0
    var aciertos = 0
    var movimientos = 0
    var parejas = 8

    //imagenes
    lateinit var imagenes: IntArray
    var fondo = 0

    //variables del juego
    var arrayDesordenado: ArrayList<Int>? = null
    var primero: ImageButton? = null
    var numeroPrimero = 0
    var numeroSegundo: kotlin.Int = 0
    var bloqueo = false
    val handler = Handler()
    private var progressBar: ProgressBar? = null
    private var i = 0
    var txtView: TextView? = null
    lateinit var musicNazgul: MediaPlayer
    //private val handler = Handler()

    //endregion Variables

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juego)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        musicNazgul = MediaPlayer.create(this, R.raw.nazgul)
        musicNazgul.start()
        init()
    }

    override fun onBackPressed() {

    }

    private fun loadTable() {
        img00 = findViewById(R.id.boton00)
        img01 = findViewById(R.id.boton01)
        img02 = findViewById(R.id.boton02)
        img03 = findViewById(R.id.boton03)
        img04 = findViewById(R.id.boton04)
        img05 = findViewById(R.id.boton05)
        img06 = findViewById(R.id.boton06)
        img07 = findViewById(R.id.boton07)
        img08 = findViewById(R.id.boton08)
        img09 = findViewById(R.id.boton09)
        img10 = findViewById(R.id.boton10)
        img11 = findViewById(R.id.boton11)
        img12 = findViewById(R.id.boton12)
        img13 = findViewById(R.id.boton13)
        img14 = findViewById(R.id.boton14)
        img15 = findViewById(R.id.boton15)
        tablero[0] = img00
        tablero[1] = img01
        tablero[2] = img02
        tablero[3] = img03
        tablero[4] = img04
        tablero[5] = img05
        tablero[6] = img06
        tablero[7] = img07
        tablero[8] = img08
        tablero[9] = img09
        tablero[10] = img10
        tablero[11] = img11
        tablero[12] = img12
        tablero[13] = img13
        tablero[14] = img14
        tablero[15] = img15
    }

    private fun timeBar() {
        progressBar = findViewById<ProgressBar>(R.id.progressBar5) as ProgressBar

        txtView = findViewById<TextView>(R.id.text_view)

        progressBar!!.visibility = View.VISIBLE

        i = progressBar!!.progress

        Thread(Runnable {
            Thread.sleep(2500)
            // this loop will run until the value of i becomes 99
            while (i < 100) {
                i += 1
                Log.d("Hilo", "{$i}")
                // Update the progress bar and display the current value
                handler.post(Runnable {
                    progressBar!!.progress = i
                    // setting current progress to the textview
                    txtView!!.text = i.toString() + "/" + progressBar!!.max
                })
                try {
                    Thread.sleep(600)

                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }

            progressBar!!.visibility = View.INVISIBLE

            if (i == 100) {
                musicNazgul.stop()
                val intent = Intent(applicationContext, PantallaDerrota::class.java)
                startActivity(intent)
                finish()
            } else {
                musicNazgul.stop()
                val intent = Intent(applicationContext, PantallaVictoria::class.java)
                intent.putExtra("Score", "Tu puntuación fue de: $puntuacion puntos.\n ¡¡Victoria!!")
                startActivity(intent)
                finish()
            }

        }).start()

    }

    private fun loadText() {
        textoPuntuacion = findViewById(R.id.texto_puntuacion)
        textoMovimientos = findViewById(R.id.texto_Movimientos)
        textoParejas = findViewById(R.id.texto_Parejas)
        puntuacion = 0
        aciertos = 0
        movimientos = 0
        parejas = 8
        textoPuntuacion!!.setText("Puntuacion: $puntuacion")
        textoMovimientos!!.setText("Numero de movimientos: $movimientos")
        textoParejas!!.setText("Número parejas restantes: $parejas")
    }

    private fun loadImages() {
        imagenes = intArrayOf(
            R.drawable.la0,
            R.drawable.la1,
            R.drawable.la2,
            R.drawable.la3,
            R.drawable.la4,
            R.drawable.la5,
            R.drawable.la6,
            R.drawable.la7
        )
        fondo = R.drawable.fondo
    }

    private fun shuffle(longitud: Int): ArrayList<Int>? {
        val result = ArrayList<Int>()
        for (i in 0 until longitud * 2) {
            result.add(i % longitud)
        }
        Collections.shuffle(result)
        // System.out.println(Arrays.toString(result.toArray()));
        return result
    }

    private fun check(i: Int, imgb: ImageButton?) {
        txtView = findViewById<TextView>(R.id.text_view)
        textoMovimientos = findViewById<TextView>(R.id.texto_Movimientos)
        textoParejas = findViewById<TextView>(R.id.texto_Parejas)
        if (primero == null) {
            primero = imgb
            primero!!.scaleType = ImageView.ScaleType.CENTER_CROP
            primero!!.setImageResource(imagenes[arrayDesordenado!![i]])
            primero!!.isEnabled = false
            numeroPrimero = arrayDesordenado!![i]
        } else {
            bloqueo = true
            imgb!!.scaleType = ImageView.ScaleType.CENTER_CROP
            imgb.setImageResource(imagenes[arrayDesordenado!![i]])
            imgb.isEnabled = false
            numeroSegundo = arrayDesordenado!![i]
            if (numeroPrimero == numeroSegundo) {
                primero = null
                bloqueo = false
                aciertos++
                puntuacion++
                movimientos++
                parejas--
                textoPuntuacion!!.text = "Puntuación: $puntuacion"
                textoMovimientos!!.text = "Numero de movimientos: $movimientos"
                textoParejas!!.text = "Número parejas restantes: $parejas"
                if (aciertos == imagenes.size) {

                    this.i =
                        150  // le damos a i un numero mayor a 100 para que el hilo se detenga al ganar
                    txtView!!.text = "¡¡Victoria!!"

                }
            } else {
                handler.postDelayed({
                    primero!!.scaleType = ImageView.ScaleType.CENTER_CROP
                    primero!!.setImageResource(fondo)
                    primero!!.isEnabled = true
                    imgb.scaleType = ImageView.ScaleType.CENTER_CROP
                    imgb.setImageResource(fondo)
                    imgb.isEnabled = true
                    bloqueo = false
                    primero = null
                    puntuacion--
                    movimientos++

                    if (puntuacion < 0) puntuacion = 0
                    textoPuntuacion!!.text = "Puntuación: $puntuacion"
                    textoMovimientos!!.text = "Numero de movimientos: $movimientos"
                }, 500)
            }
        }
    }

    private fun init() {
        loadTable()
        loadText()
        loadImages()
        timeBar()
        arrayDesordenado = shuffle(imagenes.size)
        for (i in tablero.indices) {
            tablero[i]!!.scaleType = ImageView.ScaleType.CENTER_CROP
            tablero[i]!!.setImageResource(imagenes[arrayDesordenado!![i]])
            //tablero[i].setImageResource(fondo);
        }
        handler.postDelayed({
            for (i in tablero.indices) {
                tablero[i]!!.scaleType = ImageView.ScaleType.CENTER_CROP
                //tablero[i].setImageResource(imagenes[arrayDesordenado.get(i)]);
                tablero[i]!!.setImageResource(fondo)
            }
        }, 2500)
        for (i in tablero.indices) {
            tablero[i]!!.isEnabled = true
            tablero[i]!!.setOnClickListener { if (!bloqueo) check(i, tablero[i]) }
        }
    }
}