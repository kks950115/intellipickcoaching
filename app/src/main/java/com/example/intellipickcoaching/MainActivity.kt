package com.example.intellipickcoaching

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.intellipickcoaching.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(binding.root)

        var dbHelper = DBHelper(this, "mydb.db",null,1)
        var database = dbHelper.writableDatabase

        val id = binding.etId
        val pw = binding.etPw

        binding.btnLogin.setOnClickListener {
            var result = dbHelper.select(database,id.toString(),pw.toString())
            if(result != null){
                val intent = Intent(this, LoginActivity::class.java )
                intent.putExtra("id",binding.etId.toString())
                intent.putExtra("pw",binding.etPw.toString())
                startActivity(intent)
            } else {
                Toast.makeText(this, "아이디나 비밀번호가 잘못되었습니다.", Toast.LENGTH_SHORT).show()
                id.text.clear()
                pw.text.clear()
            }

        }



        binding.tvJoin.setOnClickListener {
            val intent = Intent(this,SigninActivity::class.java)
            startActivity(intent)
        }
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }
}