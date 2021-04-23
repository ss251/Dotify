package edu.uw.ss251.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import kotlin.random.Random

private val noOfPlays = Random.nextInt(1000, 1000000)
private var plays = noOfPlays

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val noPlays = findViewById<TextView>(R.id.noPlays)


        noPlays.text = noOfPlays.toString()

    }

    fun clickPrevious(view: View) {
        Toast.makeText(this, "Skipping to previous track", Toast.LENGTH_SHORT).show()
    }

    fun clickPlay(view: View) {
        val noPlays = findViewById<TextView>(R.id.noPlays)
        plays += 1
        noPlays.text = (plays).toString()
    }

    fun clickNext(view: View) {
        Toast.makeText(this, "Skipping to next track", Toast.LENGTH_SHORT).show()
    }

    fun clickChangeUser(view: View) {

        val userName = findViewById<TextView>(R.id.userName)
        val userNameEdit = findViewById<TextView>(R.id.userNameEdit)
        val changeUser = findViewById<TextView>(R.id.changeUser)
        userNameEdit.isVisible = true
        if(changeUser.text == "Apply") {
            userNameEdit.isVisible = false
            userName.text = userNameEdit.text
            userName.isVisible = true
            changeUser.text = "Change User"
            return
        }
        userName.isVisible = false
        changeUser.text = "Apply"
    }
}