package com.example.travelappkereta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.travelappkereta.databinding.ActivitySecondBinding

class Second_Activity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val extraUsername = intent.getStringExtra("EXTRA_USERNAME")
        val extraPassword = intent.getStringExtra("EXTRA_PASSWORD")

        with(binding) {
            // Inisialisasi elemen UI menggunakan View Binding
            val edtLoginUsername = username
            val edtLoginPassword = pass

            loginBtn.setOnClickListener {
                val username = edtLoginUsername.text.toString()
                val password = edtLoginPassword.text.toString()

                if (username == extraUsername && password == extraPassword) {

                    val fourthActivity = Intent(this@Second_Activity, FiveActivity::class.java)
                    startActivity(fourthActivity)
                } else {

                    Toast.makeText(this@Second_Activity, "Login gagal. Periksa username dan password.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}