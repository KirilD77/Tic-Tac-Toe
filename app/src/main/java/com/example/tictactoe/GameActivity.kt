package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game.*
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    // counter for moves
    private var count: Int = 0

    //boolean variable for winner
    private var winner = false

    // boolean variable for draw case
    var draw = false

    // true if it is Players move and false otherwise
    private var turn: Boolean = false

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
            usedButtons.containsAll(
                listOf(
                    a1,
                    a2,
                    a3
                )
            ) && (a1.text == a2.text) && a1.text.isNotEmpty() -> {
                winner = true
            }
            usedButtons.containsAll(
                listOf(
                    b1,
                    b2,
                    b3
                )
            ) && (b1.text == b2.text && b1.text == b3.text) && b1.text.isNotEmpty() -> {
                winner = true
            }
            usedButtons.containsAll(
                listOf(
                    c1,
                    c2,
                    c3
                )
            ) && (c1.text == c2.text && c1.text == c3.text) && c1.text.isNotEmpty() -> {
                winner = true
            }

            // checks all verticals
            usedButtons.containsAll(
                listOf(
                    a1,
                    b1,
                    c1
                )
            ) && (a1.text == b1.text && a1.text == c1.text) && a1.text.isNotEmpty() -> {
                winner = true
            }
            usedButtons.containsAll(
                listOf(
                    a2,
                    b2,
                    c2
                )
            ) && (a2.text == b2.text && a2.text == c2.text) && a2.text.isNotEmpty() -> {
                winner = true
            }
            usedButtons.containsAll(
                listOf(
                    a3,
                    b3,
                    c3
                )
            ) && (a3.text == b3.text && a3.text == c3.text) && a3.text.isNotEmpty() -> {
                winner = true
            }

            // checks all diagonals
            usedButtons.containsAll(
                listOf(
                    a1,
                    b2,
                    c3
                )
            ) && (a1.text == b2.text && a1.text == c3.text) && a1.text.isNotEmpty() -> {
                winner = true
            }
            usedButtons.containsAll(
                listOf(
                    a3,
                    b2,
                    c1
                )
            ) && (a3.text == b2.text && a3.text == c1.text) && a3.text.isNotEmpty() -> {
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
            btn.text = if (!turn) "X" else "O"
            // switches moves
            turn = !turn
            //quantity of moves
            count += 1
            // check for a winner
            checkAWinner()
            // possible cases
            if (winner) {
                "Winner is ${if (turn) name else "Computer"}".also { tvGameState.text = it }
                reset()
                return
            } else if (draw) {
                tvGameState.text = getString(R.string.Draw_message)
                reset()
                return
            }
            // if game is not over
            compMove()
            //checking for a win or a draw case
            checkAWinner()
            if (winner) {
                "Winner is ${if (turn) name else "Computer"}".also { tvGameState.text = it }
                reset()
                return
            } else if (draw) {
                tvGameState.text = getString(R.string.Draw_message)
                reset()
                return
            }
        }
    }


    // function that resets the game(sets everything to initial state)
    private fun reset() {
        usedButtons.clear()
        unusedButtons = mutableListOf(a1, a2, a3, b1, b2, b3, c1, c2, c3)
        for (button in unusedButtons) {
            button.text = ""
        }
        count = 0
        turn = false
        winner = false
        draw = false
    }

    // checks possible winning or loosing combinations
    private fun checker(char: String): Boolean {

        var changes = false
        if (count < 5) {
            // random index of unused buttons
            val x = Random.nextInt(unusedButtons.size)
            unusedButtons[x].text = "O"
            usedButtons.add(unusedButtons[x])
            unusedButtons.removeAt(x)
            count++
            turn = !turn
            changes = true
        }
        //check first horizontal
        else if (usedButtons.containsAll(
                listOf(
                    a1,
                    a2
                )
            ) && a1.text == a2.text && a2.text == char && unusedButtons.contains(a3)
        ) {
            usedButtons.add(a3)
            a3.text = "O"
            count += 1
            unusedButtons.remove(a3)
            turn = !turn
            changes = true
        } else if (usedButtons.containsAll(
                listOf(
                    a2,
                    a3
                )
            ) && a2.text == a3.text && a3.text == char && unusedButtons.contains(a1)
        ) {
            usedButtons.add(a1)
            a1.text = "O"
            count += 1
            unusedButtons.remove(a1)
            turn = !turn
            changes = true
        } else if (usedButtons.containsAll(
                listOf(
                    a1,
                    a3
                )
            ) && a1.text == a3.text && a1.text == char && unusedButtons.contains(a2)
        ) {
            usedButtons.add(a2)
            a2.text = "O"
            count += 1
            unusedButtons.remove(a2)
            turn = !turn
            changes = true
        }

        //check second horizontal
        else if (usedButtons.containsAll(
                listOf(
                    b1,
                    b2
                )
            ) && b1.text == b2.text && b2.text == char && unusedButtons.contains(b3)
        ) {
            usedButtons.add(b3)
            b3.text = "O"
            count += 1
            unusedButtons.remove(b3)
            turn = !turn
            changes = true
        } else if (usedButtons.containsAll(
                listOf(
                    b2,
                    b3
                )
            ) && b2.text == b3.text && b3.text == char && unusedButtons.contains(b1)
        ) {
            usedButtons.add(b1)
            b1.text = "O"
            count += 1
            unusedButtons.remove(b1)
            turn = !turn
            changes = true
        } else if (usedButtons.containsAll(
                listOf(
                    b1,
                    b3
                )
            ) && b1.text == b3.text && b1.text == char && unusedButtons.contains(b2)
        ) {
            usedButtons.add(b2)
            b2.text = "O"
            count += 1
            unusedButtons.remove(b2)
            turn = !turn
            changes = true
        }

        //check third horizontal
        else if (usedButtons.containsAll(
                listOf(
                    c1,
                    c2
                )
            ) && c1.text == c2.text && c2.text == char && unusedButtons.contains(c3)
        ) {
            usedButtons.add(c3)
            c3.text = "O"
            count += 1
            unusedButtons.remove(c3)
            turn = !turn
            changes = true
        } else if (usedButtons.containsAll(
                listOf(
                    c2,
                    c3
                )
            ) && c2.text == c3.text && c3.text == char && unusedButtons.contains(c1)
        ) {
            usedButtons.add(c1)
            c1.text = "O"
            count += 1
            unusedButtons.remove(c1)
            turn = !turn
            changes = true
        } else if (usedButtons.containsAll(
                listOf(
                    c1,
                    c3
                )
            ) && c1.text == c3.text && c1.text == char && unusedButtons.contains(c2)
        ) {
            usedButtons.add(c2)
            c2.text = "O"
            count += 1
            unusedButtons.remove(c2)
            turn = !turn
            changes = true
        }

        // check first vertical
        else if (usedButtons.containsAll(
                listOf(
                    a1,
                    b1
                )
            ) && a1.text == b1.text && b1.text == char && unusedButtons.contains(c1)
        ) {
            usedButtons.add(c1)
            c1.text = "O"
            count += 1
            unusedButtons.remove(c1)
            turn = !turn
            changes = true
        } else if (usedButtons.containsAll(
                listOf(
                    b1,
                    c1
                )
            ) && b1.text == c1.text && b1.text == char && unusedButtons.contains(a1)
        ) {
            usedButtons.add(a1)
            a1.text = "O"
            count += 1
            unusedButtons.remove(a1)
            turn = !turn
            changes = true
        } else if (usedButtons.containsAll(
                listOf(
                    a1,
                    c1
                )
            ) && a1.text == c1.text && a1.text == char && unusedButtons.contains(b1)
        ) {
            usedButtons.add(b1)
            b1.text = "O"
            count += 1
            unusedButtons.remove(b1)
            turn = !turn
            changes = true
        }


        //check second vertical
        else if (usedButtons.containsAll(
                listOf(
                    a2,
                    b2
                )
            ) && a2.text == b2.text && b2.text == char && unusedButtons.contains(c2)
        ) {
            usedButtons.add(c2)
            c2.text = "O"
            count += 1
            unusedButtons.remove(c2)
            turn = !turn
            changes = true
        } else if (usedButtons.containsAll(
                listOf(
                    b2,
                    c2
                )
            ) && b2.text == c2.text && b2.text == char && unusedButtons.contains(a2)
        ) {
            usedButtons.add(a2)
            a2.text = "O"
            count += 1
            unusedButtons.remove(a2)
            turn = !turn
            changes = true
        } else if (usedButtons.containsAll(
                listOf(
                    a2,
                    c2
                )
            ) && a2.text == c2.text && a2.text == char && unusedButtons.contains(b2)
        ) {
            usedButtons.add(b2)
            b2.text = "O"
            count += 1
            unusedButtons.remove(b2)
            turn = !turn
            changes = true
        }

        //check third vertical
        else if (usedButtons.containsAll(
                listOf(
                    a3,
                    b3
                )
            ) && a3.text == b3.text && b3.text == char && unusedButtons.contains(c3)
        ) {
            usedButtons.add(c3)
            c3.text = "O"
            count += 1
            unusedButtons.remove(c3)
            turn = !turn
            changes = true
        } else if (usedButtons.containsAll(
                listOf(
                    b3,
                    c3
                )
            ) && b3.text == c3.text && b3.text == char && unusedButtons.contains(a3)
        ) {
            usedButtons.add(a3)
            a3.text = "O"
            count += 1
            unusedButtons.remove(a3)
            turn = !turn
            changes = true
        } else if (usedButtons.containsAll(
                listOf(
                    a3,
                    c3
                )
            ) && a3.text == c3.text && a3.text == char && unusedButtons.contains(b3)
        ) {
            usedButtons.add(b3)
            b3.text = "O"
            count += 1
            unusedButtons.remove(b3)
            turn = !turn
            changes = true
        }

        // check first diagonal
        else if (usedButtons.containsAll(
                listOf(
                    a1,
                    b2
                )
            ) && a1.text == b2.text && b2.text == char && unusedButtons.contains(c3)
        ) {
            usedButtons.add(c3)
            c3.text = "O"
            count += 1
            unusedButtons.remove(c3)
            turn = !turn
            changes = true
        } else if (usedButtons.containsAll(
                listOf(
                    b2,
                    c3
                )
            ) && b2.text == c3.text && b2.text == char && unusedButtons.contains(a1)
        ) {
            usedButtons.add(a1)
            a1.text = "O"
            count += 1
            unusedButtons.remove(a1)
            turn = !turn
            changes = true
        } else if (usedButtons.containsAll(
                listOf(
                    a1,
                    c3
                )
            ) && a1.text == c3.text && a1.text == char && unusedButtons.contains(b2)
        ) {
            usedButtons.add(b2)
            b2.text = "O"
            count += 1
            unusedButtons.remove(b2)
            turn = !turn
            changes = true
        }

        // check second diagonal
        else if (usedButtons.containsAll(
                listOf(
                    a3,
                    b2
                )
            ) && a3.text == b2.text && b2.text == char && unusedButtons.contains(c1)
        ) {
            usedButtons.add(c1)
            c1.text = "O"
            count += 1
            unusedButtons.remove(c1)
            turn = !turn
            changes = true
        } else if (usedButtons.containsAll(
                listOf(
                    b2,
                    c1
                )
            ) && b2.text == c1.text && b2.text == char && unusedButtons.contains(a3)
        ) {
            usedButtons.add(a3)
            a3.text = "O"
            count += 1
            unusedButtons.remove(a3)
            turn = !turn
            changes = true
        } else if (usedButtons.containsAll(
                listOf(
                    a3,
                    c1
                )
            ) && a3.text == c1.text && a3.text == char && unusedButtons.contains(b2)
        ) {
            usedButtons.add(b2)
            b2.text = "O"
            count += 1
            unusedButtons.remove(b2)
            turn = !turn
            changes = true
        }
        return changes
    }

    // function for computer move
    private fun compMove() {
        // check for a winning move
        val changes = checker("O")
        //check for a blocking move
        if (!changes) {
            checker("X")
        }

    }

}