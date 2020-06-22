package kr.inmo.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener{
    
    var buttons = Array<Array<Button>>(3, { Array<Button>(3, {findViewById(0)}) })

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
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}