package kr.im.drawableanimationapp

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var wifiAnimation : AnimationDrawable
    lateinit var batteryAnimation : AnimationDrawable
    lateinit var cellularAnimation : AnimationDrawable
    lateinit var chargingAnimation : AnimationDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        wifi_imageView.setBackgroundColor(R.drawable.wifi_animation)
//        wifiAnimation = wifi_imageView.background as AnimationDrawable

        wifi_imageView.apply {
            setBackgroundResource(R.drawable.wifi_animation)
            wifiAnimation = background as AnimationDrawable
        }

        battery_imageView.apply {
            setBackgroundResource(R.drawable.battery_animation)
            batteryAnimation = background as AnimationDrawable
        }

        cellular_imageView.apply {
            setBackgroundResource(R.drawable.cellular_animation)
            cellularAnimation = background as AnimationDrawable
        }

        charging_imageView.apply {
            setBackgroundResource(R.drawable.charging_animation)
            chargingAnimation = background as AnimationDrawable
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        wifiAnimation.start()
        batteryAnimation.start()
        cellularAnimation.start()
        chargingAnimation.start()
    }
}