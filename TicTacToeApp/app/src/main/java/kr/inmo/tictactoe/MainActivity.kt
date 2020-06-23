package kr.inmo.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener{
    
    var buttons = Array<Array<Button>>(3, { Array<Button>(3, {findViewById(0)}) })

    var player1Turn = true

    var roundCount = 0
    var player1Points = 0
    var player2Points = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for(i in 0 until 3) {
            for(j in 0 until 3) {
                val buttonID = "button_${i}${j}"
                val resID = resources.getIdentifier(buttonID, "id", packageName)
                buttons[i][j] = findViewById(resID)
                buttons[i][j].setOnClickListener(this)
            }
        }

        button_reset.setOnClickListener { v ->
            resetGame()
        }
    }

    override fun onClick(v: View?) {

        if(!(v as Button).text.toString().equals("")) {
            return
        }

        if(player1Turn) {
            v.text = "X"
        } else {
            v.text = "O"
        }

        roundCount++

        if(checkForWin()) {
            if(player1Turn) {
                player1Wins()
            } else {
                player2Wins()
            }
        } else if(roundCount == 9) {
            draw()
        } else {
            player1Turn = !player1Turn
        }
    }

    private fun checkForWin() : Boolean {

        var field = Array<Array<String>>(3, { Array<String>(3, {""}) })

        for(i in 0 until 3) {
            for(j in 0 until 3) {
                field[i][j] = buttons[i][j].text.toString()
            }
        }

        for(i in 0 until  3) {
            if(field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")) {
                return true
            }
        }

        for(i in 0 until  3) {
            if(field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")) {
                return true
            }
        }

        for(i in 0 until  3) {
            if(field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")) {
                return true
            }
        }

        for(i in 0 until  3) {
            if(field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")) {
                return true
            }
        }

        return false
    }

    private fun player1Wins() {

        player1Points++
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show()
        updatePointsText()
        resetBoard()
    }

    private fun player2Wins() {

        player2Points++
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show()
        updatePointsText()
        resetBoard()
    }

    private fun draw() {

        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show()
        resetBoard()
    }

    private fun updatePointsText() {
        text_view_p1.text = "Player 1: ${player1Points}"
        text_view_p2.text = "Player 2: ${player2Points}"
    }

    private fun resetBoard() {

        for(i in 0 until 3) {
            for(j in 0 until 3) {
                buttons[i][j].text = ""
            }
        }

        roundCount = 0
        player1Turn = true
    }

    private fun resetGame() {
        player1Points = 0
        player2Points = 0
        updatePointsText()
        resetBoard()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("roundCount", roundCount)
        outState.putInt("player1Points", player1Points)
        outState.putInt("player2Points", player2Points)
        outState.putBoolean("player1Turn", player1Turn)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        roundCount = savedInstanceState.getInt("roundCount")
        player1Points = savedInstanceState.getInt("player1Points")
        player2Points = savedInstanceState.getInt("player2Points")
        player1Turn = savedInstanceState.getBoolean("player1Turn")
    }
}