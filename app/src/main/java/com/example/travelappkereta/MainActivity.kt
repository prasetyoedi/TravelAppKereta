package com.example.travelappkereta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import com.example.travelappkereta.databinding.ActivityMainBinding
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        with(binding) {
            // Inisialisasi elemen UI
            val edtEmail = edtEmail
            val edtUsername = edtUsername
            val edtPass = edtPass
            val edtPhone = edtPhone

            regisBtn.setOnClickListener {
                val email = edtEmail.text.toString()
                val username = edtUsername.text.toString()
                val password = edtPass.text.toString()
                val phone = edtPhone.text.toString()

                val datePicker = datePicker
                val year = datePicker.year
                val month = datePicker.month
                val day = datePicker.dayOfMonth

                val birthCalendar = Calendar.getInstance()
                birthCalendar.set(year, month, day)

                val currentDate = Calendar.getInstance()
                val age = currentDate.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR)

                if (age < 15) {
                    Toast.makeText(this@MainActivity, "Anda harus berusia minimal 15 tahun", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (email.isEmpty() || username.isEmpty() || password.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(this@MainActivity, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                Toast.makeText(this@MainActivity, "Registrasi berhasil", Toast.LENGTH_SHORT).show()

                // Navigasi ke SecondActivity
                val intent = Intent(this@MainActivity, Second_Activity::class.java)
                intent.putExtra("EXTRA_USERNAME", username)
                intent.putExtra("EXTRA_PASSWORD", password)
                startActivity(intent)
            }

            loginlink.setOnClickListener {
                // Navigasi ke SecondActivity jika pengguna sudah memiliki akun
                val intent = Intent(this@MainActivity, Second_Activity::class.java)
                startActivity(intent)
            }
        }
    }
}