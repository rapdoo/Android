package kr.inmo.basicanimationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_rotate.*

class RotateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var animation: Animation

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rotate)

        clockWise.setOnClickListener {

            animation = AnimationUtils.loadAnimation(this, R.anim.rotate_clockwise_animation)
            imageView.startAnimation(animation)
        }
        antiClockWise.setOnClickListener {

            animation = AnimationUtils.loadAnimation(this, R.anim.rotate_anticlock_animation)
            imageView.startAnimation(animation)
        }
    }
}