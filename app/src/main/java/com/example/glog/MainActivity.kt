package com.example.glog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.glog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)

        binding.loginBox.setOnClickListener(){
            this.finish()
            startActivity(Intent(this,LoginActivity::class.java))
        }

        binding.signupBox.setOnClickListener(){
            this.finish()
            startActivity(Intent(this,SignupActivity::class.java))
        }
    }
}