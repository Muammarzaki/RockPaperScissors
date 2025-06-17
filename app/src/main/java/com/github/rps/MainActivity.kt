package com.github.rps

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val rockButton: ImageView = findViewById(R.id.buttonRock)
        val paperButton: ImageView = findViewById(R.id.buttonPaper)
        val scissorsButton: ImageView = findViewById(R.id.buttonScissors)
        val playerChoiceImageView: ImageView = findViewById(R.id.imageViewPlayer)
        val computerChoiceImageView: ImageView = findViewById(R.id.imageViewComputer)
        val resultTextView: TextView = findViewById(R.id.textViewResult)

        rockButton.setOnClickListener {
            playGame("rock", playerChoiceImageView, computerChoiceImageView, resultTextView)
        }

        paperButton.setOnClickListener {
            playGame("paper", playerChoiceImageView, computerChoiceImageView, resultTextView)
        }

        scissorsButton.setOnClickListener {
            playGame("scissors", playerChoiceImageView, computerChoiceImageView, resultTextView)
        }
    }

    private fun playGame(
        playerChoice: String,
        playerChoiceImageView: ImageView,
        computerChoiceImageView: ImageView,
        resultTextView: TextView
    ) {
        computerChoiceImageView.setImageResource(android.R.color.transparent)
        resultTextView.text = ""

        val choices = listOf("rock", "paper", "scissors")
        playerChoiceImageView.setImageResource(getDrawableResource(playerChoice))

        findViewById<ImageView>(R.id.buttonRock).isEnabled = false
        findViewById<ImageView>(R.id.buttonPaper).isEnabled = false
        findViewById<ImageView>(R.id.buttonScissors).isEnabled = false

        Handler(Looper.getMainLooper()).postDelayed({
            val computerChoice = choices[Random.nextInt(choices.size)]
            computerChoiceImageView.setImageResource(getDrawableResource(computerChoice))

            val result = determineWinner(playerChoice, computerChoice)
            resultTextView.text = result

            Toast.makeText(this, "You chose: $playerChoice, Computer chose: $computerChoice", Toast.LENGTH_SHORT).show()
            findViewById<ImageView>(R.id.buttonRock).isEnabled = true
            findViewById<ImageView>(R.id.buttonPaper).isEnabled = true
            findViewById<ImageView>(R.id.buttonScissors).isEnabled = true
        }, 1000)
    }

    private fun getDrawableResource(choice: String): Int {
        return when (choice) {
            "rock" -> R.drawable.rock
            "paper" -> R.drawable.paper
            "scissors" -> R.drawable.scissors
            else -> R.drawable.ic_launcher_foreground
        }
    }

    private fun determineWinner(playerChoice: String, computerChoice: String): String {
        return when {
            playerChoice == computerChoice -> "It's a Draw!"
            (playerChoice == "rock" && computerChoice == "scissors") ||
                    (playerChoice == "paper" && computerChoice == "rock") ||
                    (playerChoice == "scissors" && computerChoice == "paper") -> "You Win!"

            else -> "Computer Wins!"
        }
    }


}



