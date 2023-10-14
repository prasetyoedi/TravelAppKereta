package com.example.travelappkereta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.travelappkereta.databinding.ActivityFiveBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class FiveActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFiveBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFiveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengambil harga dari ekstra intent (ThirdActivity)
        val hargaTiket = intent.getIntExtra(Third_Activity.EXTRA_HARGA, 0)

        val tanggal1 = intent.getLongExtra(Third_Activity.EXTRA_TANGGAL, 0)
        val asal1 = intent.getStringExtra(Third_Activity.EXTRA_ASAL) ?: ""
        val tujuan1 = intent.getStringExtra(Third_Activity.EXTRA_TUJUAN) ?: ""
        val kelas1 = intent.getStringExtra(Third_Activity.EXTRA_KELAS) ?: ""
        val penumpang1 = intent.getStringExtra(Third_Activity.EXTRA_PENUMPANG) ?: ""
        val paket1 = intent.getStringExtra(Third_Activity.EXTRA_PESANPAKET) ?: ""

        // Setel TextView untuk menampilkan harga tiket yang diambil dari ThirdActivity
        val hargaTextView = binding.harga
        hargaTextView.text = "Harga Tiket: Rp. $hargaTiket"

        with(binding) {
            tanggal.text = convertLongToDateString(tanggal1)
            asal.text = asal1
            tujuan.text = tujuan1
            kelas.text = kelas1
            penumpang.text = penumpang1
            paketPerjalanan.text = "Paket: $paket1"

            // Inisialisasi CalendarView
            val calendarView = calendarView

            // Tambahkan listener untuk menangani perubahan tanggal di CalendarView
            calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
                val calendar = Calendar.getInstance()
                calendar.set(year, month, dayOfMonth)

                val isTravelPlanAvailable = checkIfTravelPlanAvailable(calendar)
                if (isTravelPlanAvailable) {
                    Toast.makeText(
                        this@FiveActivity,
                        "Tersedia rencana perjalanan pada tanggal ini.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@FiveActivity,
                        "Tidak ada rencana perjalanan pada tanggal ini.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            btnInputRencana.setOnClickListener {
                val intentFive = Intent(this@FiveActivity, Third_Activity::class.java)
                startActivity(intentFive)
            }
        }
    }

    private fun convertLongToDateString(timeInMillis: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeInMillis
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        return "$day/$month/$year"
    }

    private fun checkIfTravelPlanAvailable(calendar: Calendar): Boolean {
        // Di sini, Anda dapat menggantikan logika ini dengan logika sebenarnya yang sesuai dengan aplikasi Anda.
        // Sebagai contoh, kita akan membuat daftar tanggal rencana perjalanan yang tersedia.
        val availableTravelDates = setOf(
            Calendar.getInstance(), // Hari ini
            Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, 1) }, // Besok
            Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, 2) } // Lusa
        )

        // Ubah format tanggal dari calendar menjadi bentuk yang sesuai dengan entri di setOf
        val dateString = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time)

        // Periksa apakah tanggal yang dipilih ada dalam daftar tanggal rencana perjalanan yang tersedia
        return availableTravelDates.any { it.get(Calendar.DAY_OF_MONTH).toString() == dateString}

    }
}