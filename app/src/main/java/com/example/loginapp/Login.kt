package com.example.loginapp

import android.app.Activity
import android.app.usage.UsageEvents.Event
import android.content.Context
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.os.Message
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.loginapp.ui.theme.LoginAppTheme

class Login: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)
        val db = DatabaseHelper(this)
        val loginButton = findViewById<Button>(R.id.Login)
        loginButton.setOnClickListener {
            val email = findViewById<EditText>(R.id.emailAddress).text.toString()
            val password = findViewById<EditText>(R.id.Password).text.toString()
            if(db.isValidUser(email,password)){
                Toast.makeText(this,"Succesfully logged in" , Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Invalid email or password",Toast.LENGTH_SHORT).show()
            }

        }

    }
}