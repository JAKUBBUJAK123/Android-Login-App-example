package com.example.loginapp

import android.app.Activity
import android.app.usage.UsageEvents.Event
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

class SignIn : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in_layout)
        val database = DatabaseHelper(this)
        val backbutton = findViewById<ImageView>(R.id.back)
        backbutton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val email = findViewById<EditText>(R.id.EmailAdress)
        val password1 = findViewById<EditText>(R.id.Password)
        val password2 = findViewById<EditText>(R.id.secondPassword)
        var error = "";
        var createdAccout = false
        val check = findViewById<Button>(R.id.check)
        val regex = Regex("^(?=.*[A-Z])(?=.*\\d)(?=.*[@#\$%^&+=!]).*$")

        check.setOnClickListener {
            if(email.text.toString().contains("@") && email.text.toString().endsWith(".com")){
                if( password1.text.toString() == password2.text.toString() && regex.matches(password1.text.toString())){
                    showToast("Succesfully created an account")
                    database.addUser(email.text.toString(),password1.text.toString())
                }else{
                    showToast("Your password does not match or don't make its requirements")
                }
            }else{
                showToast("Not proper email.")
            }
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}

