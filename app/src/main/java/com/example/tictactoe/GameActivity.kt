package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {
    // counter for moves
    var count: Int = 0

    //boolean variable for winner
    var winner = false

    // boolean variable for draw case
    var draw = false

    // true if it is Players move and false otherwise
    var turn: Boolean = true

    // list for used buttons for computer functions
    private var usedButtons = mutableListOf<Button>()

    // list for unused buttons for computer functions
    private var unusedButtons = mutableListOf(a1, a2, a3, b1, b2, b3, c1, c2, c3)

    // name of user
    val name = intent.getStringExtra("EXTRA_NAME")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
    }

    //Checks for a winner
    private fun checkAWinner() {
        // true if there is a winner
        if (count < 5) {
            winner = false
        }
        // When Block checks for possible winning combinations
        when {
            // checks all horizontals
            usedButtons.containsAll(listOf(a1, a2, a3)) -> {
                winner = true
            }
            usedButtons.containsAll(listOf(b1, b2, b3)) -> {
                winner = true
            }
            usedButtons.containsAll(listOf(c1, c2, c3)) -> {
                winner = true
            }

            // checks all verticals
            usedButtons.containsAll(listOf(a1, b1, c1)) -> {
                winner = true
            }
            usedButtons.containsAll(listOf(a2, b2, c2)) -> {
                winner = true
            }
            usedButtons.containsAll(listOf(a3, b3, c3)) -> {
                winner = true
            }

            // checks all diagonals
            usedButtons.containsAll(listOf(a1, b2, c3)) -> {
                winner = true
            }
            usedButtons.containsAll(listOf(a3, b2, c1)) -> {
                winner = true
            }

            // draw case
            count == 9 && !winner ->{
                draw = true
            }

            else -> winner = false
        }
    }

    //button click handler
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