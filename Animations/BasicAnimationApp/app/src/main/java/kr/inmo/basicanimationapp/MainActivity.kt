package kr.inmo.basicanimationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        move_button.setOnClickListener {
            startActivity(Intent(applicationContext, MoveActivity::class.java))
        }

        rotate_button.setOnClickListener {
            startActivity(Intent(applicationContext, RotateActivity::class.java))
        }
    }
}