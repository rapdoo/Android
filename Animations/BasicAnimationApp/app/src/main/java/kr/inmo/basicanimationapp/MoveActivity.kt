package kr.inmo.basicanimationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_move.*

class MoveActivity : AppCompatActivity() {

    lateinit var animation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move)

        top.setOnClickListener {

            animation = AnimationUtils.loadAnimation(this, R.anim.move_top_animation)
            imageView.startAnimation(animation)
        }

        bottom.setOnClickListener {

            animation = AnimationUtils.loadAnimation(this, R.anim.move_bottom_animation)
            imageView.startAnimation(animation)
        }

        left.setOnClickListener {

            animation = AnimationUtils.loadAnimation(this, R.anim.move_left_animation)
            imageView.startAnimation(animation)
        }

        right.setOnClickListener {

            animation = AnimationUtils.loadAnimation(this, R.anim.move_right_animation)
            imageView.startAnimation(animation)
        }
    }
}