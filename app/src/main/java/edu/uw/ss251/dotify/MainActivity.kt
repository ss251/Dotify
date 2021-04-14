package edu.uw.ss251.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlin.random.Random

private val noOfPlays = Random.nextInt(1000, 1000000)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val noPlays = findViewById<TextView>(R.id.noPlays)


        noPlays.text = noOfPlays.toString() + " plays"

    }
}