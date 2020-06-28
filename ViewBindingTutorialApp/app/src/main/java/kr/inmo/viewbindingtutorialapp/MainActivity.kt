package kr.inmo.viewbindingtutorialapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.inmo.viewbindingtutorialapp.databinding.ResultProfileBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ResultProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = ResultProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.name.text = "Test"
        binding.button.setOnClickListener {  }
    }
}