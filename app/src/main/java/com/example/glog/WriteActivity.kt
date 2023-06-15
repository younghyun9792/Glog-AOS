package com.example.glog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.glog.databinding.ActivityWriteBinding

class WriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWriteBinding
    lateinit var db: Glogdb
    lateinit var BoardAdapter: BoardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteBinding.inflate(layoutInflater);
        setContentView(binding.root)

        db = Room.databaseBuilder(this, Glogdb::class.java, "Glogdb").allowMainThreadQueries()
            .build()

        binding.backBtn.setOnClickListener(){
            this.finish()
            startActivity(Intent(this,HomeActivity::class.java))
        }

    }

}