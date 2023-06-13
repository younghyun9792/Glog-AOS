package com.example.glog

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.glog.databinding.ActivitySignupBinding
import java.util.regex.Pattern


class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    lateinit var db: Glogdb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater);
        setContentView(binding.root)
        db = Room.databaseBuilder(this, Glogdb::class.java, "Glogdb").allowMainThreadQueries()
            .build()
        Glide.with(this).load(R.raw.signup_main_gif).into(binding.mainGif)

        var id = ""
        var password = ""
        var nickname = ""

        binding.backBtn.setOnClickListener(){
            this.finish()
            startActivity(Intent(this,MainActivity::class.java))
        }

        binding.nextBox.setOnClickListener(){
            if(db.userDao().getId(binding.idInputText.text.toString()) == null){
                Log.d("signup","id is null! 안겹침")
                binding.idInputBox.visibility = View.GONE
                binding.idInputBoxIcon.visibility = View.GONE
                binding.idInputText.visibility = View.GONE
                binding.nextBox.visibility = View.GONE
                binding.nextText.visibility = View.GONE
                binding.failId.visibility = View.GONE

                binding.pwInputBox.visibility = View.VISIBLE
                binding.pwInputBoxIcon.visibility = View.VISIBLE
                binding.pwInputText.visibility = View.VISIBLE
                binding.pwReInputBox.visibility = View.VISIBLE
                binding.pwReInputText.visibility = View.VISIBLE
                binding.pwReInputBoxIcon.visibility = View.VISIBLE
                binding.nextBox2.visibility = View.VISIBLE
                binding.next2Text.visibility = View.VISIBLE
                binding.featPwRole.visibility = View.VISIBLE

                id = binding.idInputText.text.toString()
            }
            else{
                Log.d("signup", "id is not null! 겹침")
                binding.idInputBox.setBackgroundResource(R.drawable.fail_box)
                binding.failId.visibility = View.VISIBLE
            }
        }

        binding.nextBox2.setOnClickListener(){
            val pattern: Pattern = Pattern.compile("[!@#$%^&*(),.?\":{}|<>]")
            if(binding.pwInputText.text.toString().equals(binding.pwReInputText.text.toString()) == false){
                Log.d("signup", "비밀번호 재입력 틀림")
                Log.d("signup",binding.pwInputText.text.toString()+" and "+binding.pwReInputText.text.toString())
                binding.pwInputBox.setBackgroundResource(R.drawable.fail_box)
                binding.pwReInputBox.setBackgroundResource(R.drawable.fail_box)
                binding.featPwRole.text = "입력하신 비밀번호가 일치하지 않습니다. 다시 확인해 주세요."
                binding.featPwRole.setTextColor(Color.parseColor("#E83F28"))
            }
            else if(binding.pwInputText.text.length < 8 ){
                Log.d("signup", "비밀번호 길이가 8보다 짧음")
                binding.pwInputBox.setBackgroundResource(R.drawable.fail_box)
                binding.pwReInputBox.setBackgroundResource(R.drawable.fail_box)
                binding.featPwRole.text = "비밀번호는 8자리 이상 및 기호를 포함해주세요."
                binding.featPwRole.setTextColor(Color.parseColor("#E83F28"))
            }
            else if(pattern.matcher(binding.pwInputText.text).find() == false){
                Log.d("signup", "비밀번호 특수문자 포함 안함")
                binding.pwInputBox.setBackgroundResource(R.drawable.fail_box)
                binding.pwReInputBox.setBackgroundResource(R.drawable.fail_box)
                binding.featPwRole.text = "비밀번호는 8자리 이상 및 기호를 포함해주세요."
                binding.featPwRole.setTextColor(Color.parseColor("#E83F28"))
            }
            else{
                password = binding.pwInputText.text.toString()

                binding.pwInputBox.visibility = View.GONE
                binding.pwInputText.visibility = View.GONE
                binding.pwInputBoxIcon.visibility = View.GONE
                binding.pwReInputBox.visibility = View.GONE
                binding.pwReInputText.visibility = View.GONE
                binding.pwReInputBoxIcon.visibility = View.GONE
                binding.nextBox2.visibility = View.GONE
                binding.next2Text.visibility = View.GONE
                binding.featPwRole.visibility = View.GONE


                binding.nicknameInputBox.visibility = View.VISIBLE
                binding.nicknameInputBoxIcon.visibility = View.VISIBLE
                binding.nicknameInputText.visibility = View.VISIBLE
                binding.nextBox3.visibility = View.VISIBLE
                binding.nextText3.visibility = View.VISIBLE
            }
        }

        binding.nextBox3.setOnClickListener(){
            Log.d("signup","nickname input")
            if(db.userDao().getNickname(binding.nicknameInputText.text.toString()) == null){
                Log.d("signup","닉네임 안겹침! 유저 추가")
                val new_user = UserInfo(0, id, password, nickname)
                db.userDao().insertUser(new_user)
                val auto = getSharedPreferences("autoLogin", MODE_PRIVATE)
                val autoLoginEdit = auto.edit()
                autoLoginEdit.putString("Id", id)
                autoLoginEdit.putString("Nickname", nickname)
                autoLoginEdit.commit()
                this.finish()
                startActivity(Intent(this,HomeActivity::class.java))
            }
            else{
                Log.d("signup", "닉네임 겹침")
                binding.nicknameInputBox.setBackgroundResource(R.drawable.fail_box)
                binding.failNickname.visibility = View.VISIBLE
            }
        }
//        binding.loginBox.setOnClickListener(){
//            this.finish()
//            startActivity(Intent(this,LoginActivity::class.java))
//        }
    }
}