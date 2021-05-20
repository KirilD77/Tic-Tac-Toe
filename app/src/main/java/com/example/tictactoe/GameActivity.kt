package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {
    var count: Int = 0
    var turn: Boolean = true
    var usedButtons = mutableListOf<Button>()
    var unusedButtons = mutableListOf(a1, a2, a3, b1, b2, b3, c1, c2, c3)
    val name = intent.getStringExtra("EXTRA_NAME")
    var char = "X";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
    }

    private fun game() {

    }

    fun btnClicked(it: View) {
        val btn = it as Button
        //means button is used
        if (usedButtons.contains(btn)) {
            Toast.makeText(this, "This cell is used, try another one", Toast.LENGTH_SHORT).show()
        } else {
            usedButtons.add(btn)
            unusedButtons.remove(btn)
            // means that it is Players move
            btn.text = if (turn) "X" else "O"
            // switches moves
            turn = !turn
            //quantity of moves
            count += 1
        }
    }


}