package com.samuelokello.chatwiseassignment.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.samuelokello.chatwiseassignment.R.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(layout.activity_main)

        findViewById<Button>(id.btnViewProducts).setOnClickListener {
            startActivity(Intent(this, ProductListActivity::class.java))
        }
    }
}