package com.example.glog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.glog.databinding.ActivityLoginBinding
import com.example.glog.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater);
        setContentView(binding.root)

        binding.backBtn.setOnClickListener(){
            this.finish()
            startActivity(Intent(this,MainActivity::class.java))
        }
//        binding.loginBox.setOnClickListener(){
//            this.finish()
//            startActivity(Intent(this,LoginActivity::class.java))
//        }
    }
}