package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var name: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPlay.setOnClickListener {
            name = etName.text.toString()
            if (name.isEmpty()) {
                Toast.makeText(this, "Enter your name please)", Toast.LENGTH_SHORT).show()
            }
            else{
                intent = Intent(this, GameActivity::class.java)
                intent.putExtra("EXTRA_NAME", name)
                startActivity(intent)
            }
        }

    }
}