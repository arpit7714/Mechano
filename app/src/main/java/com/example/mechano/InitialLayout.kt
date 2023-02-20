package com.example.mechano

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import java.util.logging.Handler
import kotlin.concurrent.schedule

class InitialLayout :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout1)

        Timer().schedule(2000){
            //do something
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
//            Toast.makeText("This message is printed",   Toast.LENGTH_LONG).show()
        }
    }
}