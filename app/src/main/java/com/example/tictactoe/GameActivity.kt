package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {
    // counter for moves
    private var count: Int = 0

    //boolean variable for winner
    private var winner = false

    // boolean variable for draw case
    var draw = false

    // true if it is Players move and false otherwise
    private var turn: Boolean = true

    // list for used buttons for computer functions
    private var usedButtons = mutableListOf<Button>()

    // lateinit var for unused buttons
    private lateinit var unusedButtons: MutableList<Button>

    // name of user
    private lateinit var name: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        // list for unused buttons for computer functions
        unusedButtons = mutableListOf(a1, a2, a3, b1, b2, b3, c1, c2, c3)
        name = intent.getStringExtra("EXTRA_NAME").toString()
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
            usedButtons.containsAll(listOf(a1, a2, a3)) && (a1.text == a2.text) && a1.text.isNotEmpty() -> {
                winner = true
            }
            usedButtons.containsAll(listOf(b1, b2, b3)) && (b1.text == b2.text && b1.text == b3.text) && b1.text.isNotEmpty() -> {
                winner = true
            }
            usedButtons.containsAll(listOf(c1, c2, c3)) && (c1.text == c2.text && c1.text == c3.text) && c1.text.isNotEmpty() -> {
                winner = true
            }

            // checks all verticals
            usedButtons.containsAll(listOf(a1, b1, c1)) && (a1.text == b1.text && a1.text == c1.text) && a1.text.isNotEmpty() -> {
                winner = true
            }
            usedButtons.containsAll(listOf(a2, b2, c2)) && (a2.text == b2.text && a2.text == c2.text) && a2.text.isNotEmpty() -> {
                winner = true
            }
            usedButtons.containsAll(listOf(a3, b3, c3)) && (a3.text == b3.text && a3.text == c3.text) && a3.text.isNotEmpty() -> {
                winner = true
            }

            // checks all diagonals
            usedButtons.containsAll(listOf(a1, b2, c3)) && (a1.text == b2.text && a1.text == c3.text) && a1.text.isNotEmpty() -> {
                winner = true
            }
            usedButtons.containsAll(listOf(a3, b2, c1)) && (a3.text == b2.text && a3.text == c1.text) && a3.text.isNotEmpty() -> {
                winner = true
            }

            // draw case
            count == 9 && !winner -> {
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
            btn.text = if (turn) "X" else "O"
            // switches moves
            turn = !turn
            //quantity of moves
            count += 1
            // check for a winner
            checkAWinner()
            // possible cases
            when {
                draw -> {
                    tvGameState.text = getString(R.string.Draw_message)
                    reset()
                }
                winner -> {
                    tvGameState.text = if (turn) "$name won!!!" else "Computer won!!!"
                    reset()
                }
            }
        }

    }


    // function that resets the game(sets everything to initial state)
    private fun reset() {
        usedButtons.clear()
        unusedButtons = mutableListOf(a1, a2, a3, b1, b2, b3, c1, c2, c3)
        count = 0
        turn = true
        winner = false
        draw = false
    }

    // function for computer move
    private fun compMove() {
        // check for a winning move

    }

}