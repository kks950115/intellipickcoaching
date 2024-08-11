package com.example.intellipickcoaching

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.intellipickcoaching.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        val intent = intent
        val id = intent.getStringExtra("id")
        val pw = intent.getStringExtra("pw")


        var dbHelper = DBHelper(this, "mydb.db",null,1)
        var database = dbHelper.writableDatabase

        var result = dbHelper.select(database,id.toString(),pw.toString())


        binding.textView.text = result.toString()
    }
}