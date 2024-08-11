package com.example.intellipickcoaching

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.intellipickcoaching.databinding.ActivitySigninBinding
import java.util.regex.Pattern

class SigninActivity : AppCompatActivity() {

    private val binding by lazy {ActivitySigninBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//       }
        var dbHelper = DBHelper(this, "mydb.db",null,1)
        var database = dbHelper.writableDatabase

        val btnJoin = binding.btnJoin
        var namevalidcheck = false
        var idvalidcheck = false
        var pwvalidcheck = false
        var phonevalidcheck = false
        var emailvalidcheck = false

        val name = binding.etName
        val id = binding.etId
        val pw = binding.etPw
        val phone = binding.etPhone
        val email = binding.etEmail

        val nameValid = binding.tvNameValid
        val idValid = binding.tvIdValid
        val pwvalid = binding.tvPwValid
        val phoneValid = binding.tvPhoneValid
        val emailValid = binding.tvEmailValid

        val nameRegex = """^(?=.*[A-Za-z가-힣\d])[A-Za-z가-힣\d]{1,24}$"""
        val namePattern = Pattern.compile(nameRegex) //대소문자o,숫자o,한글,1~24자만 가능
        name.addTextChangedListener (object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                btnJoin.isEnabled = (emailvalidcheck && namevalidcheck&& idvalidcheck  && pwvalidcheck  && phonevalidcheck)
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(namePattern.matcher(name.text.toString()).matches() ){
                    nameValid.isVisible = false
                    namevalidcheck = true
                } else {
                    nameValid.isVisible = true
                    namevalidcheck = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

        })
        val idRegex = """^(?=.*[A-Za-z\d!@#$%^&_])[A-Za-z\d!#$%^&_]{1,}$"""
        val idPattern = Pattern.compile(idRegex) //대소문자o,숫자o,공백x,특문:!#$%^&_만, 길이제한없음
        id.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                btnJoin.isEnabled = (emailvalidcheck && namevalidcheck&& idvalidcheck  && pwvalidcheck && phonevalidcheck)
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(idPattern.matcher(id.text.toString()).matches() ){
                    idValid.isVisible = false
                    idvalidcheck = true
                } else {
                    idValid.isVisible = true
                    idvalidcheck = false
                }
            }

        })
        val pwregex = """^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&])[A-Za-z\d!@#$%^&]{8,24}$"""
        val pwPattern = Pattern.compile(pwregex ) //대소문자o,숫자o,공백x,특문:!@#$%^&만 가능

        pw.addTextChangedListener (object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                btnJoin.isEnabled = (emailvalidcheck && namevalidcheck&& idvalidcheck  && pwvalidcheck && phonevalidcheck)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(pwPattern.matcher(pw.text.toString()).matches() ){
                    pwvalid.isVisible = false
                    pwvalidcheck = true
                }else {
                    pwvalid.isVisible = true
                    pwvalidcheck = false
                }
            }
        })
       // ^0(\d{1,2})(\d{3,4})(\d{4})$
        val phoneregex = """^01([0|1|6|7|8|9])(\d{3,4})(\d{4})$"""
        val phonePattern = Pattern.compile(phoneregex ) //대소문자o,숫자o,공백x,특문:!@#$%^&만 가능

        phone.addTextChangedListener (object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                btnJoin.isEnabled = (emailvalidcheck && namevalidcheck&& idvalidcheck  && pwvalidcheck && phonevalidcheck)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(phonePattern.matcher(phone.text.toString()).matches() ){
                    phoneValid.isVisible = false
                    phonevalidcheck = true
                }else {
                    phoneValid.isVisible = true
                    phonevalidcheck = false
                }
            }
        })

        val emailRegex = """^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$"""
        val emailPattern = Pattern.compile(emailRegex ) //대소문자o,숫자o,공백x,특문:!@#$%^&만 가능

        email.addTextChangedListener (object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                btnJoin.isEnabled = (emailvalidcheck && namevalidcheck&& idvalidcheck  && pwvalidcheck && phonevalidcheck)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(emailPattern.matcher(phone.text.toString()).matches() ){
                    emailValid.isVisible = false
                    emailvalidcheck = true
                }else {
                    emailValid.isVisible = true
                    emailvalidcheck = true
                }
            }
        })

        btnJoin.setOnClickListener {
            Toast.makeText(this, "회원가입 완료.", Toast.LENGTH_SHORT).show()
            dbHelper.insert(database,name.toString(),pw.toString(),name.toString(),phone.toString(),email.toString())
            //db에 입력
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}