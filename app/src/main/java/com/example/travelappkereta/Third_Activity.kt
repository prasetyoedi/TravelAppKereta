package com.example.travelappkereta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import com.example.travelappkereta.databinding.ActivityThirdBinding
import java.util.Calendar

class Third_Activity : AppCompatActivity() {
    private val TAG = "MainActivityLifecycle"
    private lateinit var binding: ActivityThirdBinding

    companion object {
        const val EXTRA_TANGGAL = "extra_tanggal"
        const val EXTRA_ASAL = "extra_asal"
        const val EXTRA_TUJUAN= "extra_tujuan"
        const val EXTRA_KELAS = "extra_kelas"
        const val EXTRA_PENUMPANG = "extra_penumpang"
        const val EXTRA_PESANPAKET = "extra_pesanpaket"
        const val EXTRA_HARGA = "extra_harga"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val category = arrayOf(
            "Economy",
            "Premium Economy",
            "Business",
        )

        val stasiun_tujuan = arrayOf(
            "Pasar Senen",
            "Surabaya Gubeng",
            "Solo Balapan",
        )

        val stasiun_asal = arrayOf(
            "Pasar Senen",
            "Surabaya Gubeng",
            "Solo Balapan",
        )

        val penumpang = arrayOf(
            "1 penumpang",
            "2 penumpang",
            "3 penumpang"
        )

        with(binding) {
            val categoryAdapter = ArrayAdapter(this@Third_Activity, android.R.layout.simple_spinner_dropdown_item, category)
            categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinCategory.adapter = categoryAdapter

            val stasiunTujuanAdapter = ArrayAdapter(this@Third_Activity, android.R.layout.simple_spinner_dropdown_item, stasiun_tujuan)
            stasiunTujuanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinStasiunTujuan.adapter= stasiunTujuanAdapter

            val stasiunAsalAdapter = ArrayAdapter(this@Third_Activity, android.R.layout.simple_spinner_dropdown_item, stasiun_asal)
            stasiunAsalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinStasiunAsal.adapter= stasiunAsalAdapter

            val penumpangAdapter = ArrayAdapter(this@Third_Activity, android.R.layout.simple_spinner_dropdown_item, penumpang)
            penumpangAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinPenumpang.adapter= penumpangAdapter

            datePickerTanggal.init(datePickerTanggal.year, datePickerTanggal.month, datePickerTanggal.dayOfMonth) { _, year, month, day ->
                val selectedDate = "$day/${month + 1}/$year"
                Toast.makeText(this@Third_Activity, selectedDate, Toast.LENGTH_SHORT).show()
            }

            val hargaTextView = tvHarga
            val pesanButton = pesanButton
            var ticketBefore: Int = 0

            val basePrice = 100 // Harga dasar
            var totalPrice = 0

            val toggleButtons = listOf(
                toggleMakanPagi, toggleMakanSiang, toggleMakanMalam,
                togglePinggirJendela, toggleDudukTengah, togglePalingBelakang, togglePalingDepan
            )

            spinStasiunAsal.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    val asal = spinStasiunAsal.selectedItem.toString()
                    val tujuan = spinStasiunTujuan.selectedItem.toString()
                    val kelas = spinCategory.selectedItem.toString()
                    val penumpang = spinPenumpang.selectedItem.toString()

                    val hargaTiket = calculateTicketPrice(asal, tujuan, kelas, penumpang)
                    if(ticketBefore == 0) {
                        ticketBefore = hargaTiket
                    } else {
                        totalPrice -= ticketBefore
                        ticketBefore = hargaTiket
                    }
                    totalPrice += ticketBefore
                    updateHarga(totalPrice, hargaTextView)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                }
            }

            spinStasiunTujuan.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    val asal = spinStasiunAsal.selectedItem.toString()
                    val tujuan = spinStasiunTujuan.selectedItem.toString()
                    val kelas = spinCategory.selectedItem.toString()
                    val penumpang = spinPenumpang.selectedItem.toString()

                    val hargaTiket = calculateTicketPrice(asal, tujuan, kelas, penumpang)
                    if(ticketBefore == 0) {
                        ticketBefore = hargaTiket
                    } else {
                        totalPrice -= ticketBefore
                        ticketBefore = hargaTiket
                    }
                    totalPrice += ticketBefore
                    updateHarga(totalPrice, hargaTextView)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                }
            }
            spinCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    val asal = spinStasiunAsal.selectedItem.toString()
                    val tujuan = spinStasiunTujuan.selectedItem.toString()
                    val kelas = spinCategory.selectedItem.toString()
                    val penumpang = spinPenumpang.selectedItem.toString()

                    val hargaTiket = calculateTicketPrice(asal, tujuan, kelas, penumpang)
                    if(ticketBefore == 0) {
                        ticketBefore = hargaTiket
                    } else {
                        totalPrice -= ticketBefore
                        ticketBefore = hargaTiket
                    }
                    totalPrice += ticketBefore
                    updateHarga(totalPrice, hargaTextView)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                }
            }

            spinPenumpang.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    val asal = spinStasiunAsal.selectedItem.toString()
                    val tujuan = spinStasiunTujuan.selectedItem.toString()
                    val kelas = spinCategory.selectedItem.toString()
                    val penumpang = spinPenumpang.selectedItem.toString()

                    val hargaTiket = calculateTicketPrice(asal, tujuan, kelas, penumpang)

                    if(ticketBefore == 0) {
                        ticketBefore = hargaTiket
                    } else {
                        totalPrice -= ticketBefore
                        ticketBefore = hargaTiket
                    }

                    totalPrice += ticketBefore
                    updateHarga(totalPrice, hargaTextView)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                }
            }

            toggleButtons.forEach { toggleButton ->
                toggleButton.setOnCheckedChangeListener { _, isChecked ->
                    val priceChange = when (toggleButton) {
                        toggleMakanPagi -> 15000
                        toggleMakanSiang -> 25000
                        toggleMakanMalam -> 40000
                        togglePinggirJendela -> 50000
                        toggleDudukTengah -> 80000
                        togglePalingBelakang -> 100000
                        togglePalingDepan -> 150000
                        else -> 0
                    }

                    if (isChecked) {
                        totalPrice += priceChange
                    } else {
                        totalPrice -= priceChange
                    }
                    updateHarga(totalPrice, hargaTextView)
                }
            }

            pesanButton.setOnClickListener {
                val day = datePickerTanggal.dayOfMonth
                val month = datePickerTanggal.month + 1
                val year = datePickerTanggal.year

                val calendar = Calendar.getInstance()
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, day)

                val asal = spinStasiunAsal.selectedItem.toString()
                val tujuan = spinStasiunTujuan.selectedItem.toString()
                val kelas = spinCategory.selectedItem.toString()
                val penumpang = spinPenumpang.selectedItem.toString()

                val hargaTiket = calculateTicketPrice(asal, tujuan, kelas, penumpang)
                val hargaToggle = toggleButtons.sumBy { toggleButton ->
                    val priceChange = when (toggleButton) {
                        toggleMakanPagi -> 15000
                        toggleMakanSiang -> 25000
                        toggleMakanMalam -> 40000
                        togglePinggirJendela -> 50000
                        toggleDudukTengah -> 80000
                        togglePalingBelakang -> 100000
                        togglePalingDepan -> 150000
                        else -> 0
                    }
                    if (toggleButton.isChecked) priceChange else 0
                }

                val totalHarga = hargaTiket + hargaToggle
                updateHarga(totalHarga, hargaTextView)

                val intentFourth = Intent(this@Third_Activity, FiveActivity::class.java)
                intentFourth.putExtra(Third_Activity.EXTRA_TANGGAL, calendar.timeInMillis)
                intentFourth.putExtra(Third_Activity.EXTRA_ASAL, asal)
                intentFourth.putExtra(Third_Activity.EXTRA_TUJUAN, tujuan)
                intentFourth.putExtra(Third_Activity.EXTRA_KELAS, kelas)
                intentFourth.putExtra(Third_Activity.EXTRA_PENUMPANG, penumpang)
                intentFourth.putExtra(Third_Activity.EXTRA_HARGA, totalHarga) // Menggunakan totalHarga yang sudah dihitung
                intentFourth.putExtra(Third_Activity.EXTRA_PESANPAKET, "")

                val toastMessage = "Tiket Kereta Pada Tanggal ${day}/${month}/${year}, Berhasil Dipesan"
                Toast.makeText(this@Third_Activity, toastMessage, Toast.LENGTH_SHORT).show()
                startActivity(intentFourth)
            }
        }
    }
    private fun updateHarga(price: Int, hargaTextView: TextView) {
        hargaTextView.text = "Harga: Rp. $price"
    }

    fun calculateTicketPrice(asal: String, tujuan: String, kelas: String, penumpang: String): Int {
        val basePrice = when (kelas) {
            "Economy" -> 100000
            "Premium Economy" -> 200000
            "Business" -> 300000
            else -> 0 // Harga dasar jika kelas tidak sesuai
        }

        val distancePrice = when (asal to tujuan) {
            "Pasar Senen" to "Surabaya Gubeng" -> 100000
            "Surabaya Gubeng" to "Pasar Senen" -> 100000
            "Pasar Senen" to "Solo Balapan" -> 200000
            "Solo Balapan" to "Pasar Senen" -> 200000
            "Surabaya Gubeng" to "Solo Balapan" -> 150000
            "Solo Balapan" to "Surabaya Gubeng" -> 150000
            else -> 0 // Harga jarak jika tujuan tidak sesuai
        }

        val passengerMultiplier = when (penumpang) {
            "1 penumpang" -> 1
            "2 penumpang" -> 2
            "3 penumpang" -> 3
            else -> 1 // Jika tidak ada penumpang yang dipilih, defaultnya 1 penumpang
        }

        return basePrice + distancePrice * passengerMultiplier
    }
}