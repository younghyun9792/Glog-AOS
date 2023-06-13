package com.example.glog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.glog.databinding.ActivityHomeBinding
import com.example.glog.databinding.ActivityMainBinding
import com.example.glog.databinding.ActivitySignupBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    lateinit var db: Glogdb
    lateinit var BoardAdapter: BoardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater);
        setContentView(binding.root)

        db = Room.databaseBuilder(this, Glogdb::class.java, "Glogdb").allowMainThreadQueries()
            .build()
        Glide.with(this).load(R.raw.signup_main_gif).into(binding.mainGif)
        initRecycler()
    }

    private fun initRecycler() {
        val datas = mutableListOf<BoardData>()
        BoardAdapter = BoardAdapter(this)
        binding.block.adapter = BoardAdapter
//        val current_email = curUser?.email.toString()
        db = Room.databaseBuilder(this, Glogdb::class.java, "Glogdb").allowMainThreadQueries()
            .build()
        datas.apply {
            db.boardDao().getBoard().forEach {
                add(
                    BoardData(
                        bid = it.bid,
                        title = it.title,
                        content = it.content,
                        hart = it.hart,
                        hit = it.hit
                    )
                )
            }
            add(BoardData(bid = 1, title="유저 리서치(User Research)란?", content="일상에서 제품을 이해하고 사용하는데 영향을 미치는 사람의 행위와 동기, 니즈를 특정 맥락 안에서 파악합니다.", hart=12345, hit=14516))
            add(BoardData(bid = 2, title="유저 리서치(User Research)란?", content="일상에서 제품을 이해하고 사용하는데 영향을 미치는 사람의 행위와 동기, 니즈를 특정 맥락 안에서 파악합니다.", hart=12345, hit=14516))
            BoardAdapter.datas = datas
            BoardAdapter.notifyDataSetChanged()

        }
    }
}