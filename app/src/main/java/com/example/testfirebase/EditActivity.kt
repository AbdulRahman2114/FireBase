package com.example.testfirebase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EditActivity : AppCompatActivity() {

    private lateinit var etNama: EditText
    private lateinit var etAlamat: EditText
    private lateinit var etJenisKelamin: EditText
    private lateinit var etTempatTanggalLahir: EditText
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    private lateinit var ref: DatabaseReference

    private var mhsId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_all)

        etNama = findViewById(R.id.et_nama)
        etAlamat = findViewById(R.id.et_alamat)
        etJenisKelamin = findViewById(R.id.et_jenis_kelamin)
        etTempatTanggalLahir = findViewById(R.id.et_tempat_tanggal_lahir)
        btnUpdate = findViewById(R.id.btn_save)
        btnDelete = findViewById(R.id.btnDelete)

        ref = FirebaseDatabase.getInstance().getReference("mahasiswa")

        mhsId = intent.getStringExtra("MHS_ID")

        if (mhsId != null) {
            ref.child(mhsId!!).get().addOnSuccessListener {
                val mahasiswa = it.getValue(Mahasiswa::class.java)
                if (mahasiswa != null) {
                    etNama.setText(mahasiswa.nama)
                    etAlamat.setText(mahasiswa.alamat)
                    etJenisKelamin.setText(mahasiswa.jenisKelamin)
                    etTempatTanggalLahir.setText(mahasiswa.tempatTanggalLahir)
                }
            }
        }

        btnUpdate.setOnClickListener {
            updateData()
        }

        btnDelete.setOnClickListener {
            deleteData()
        }
    }

    private fun updateData() {
        val nama = etNama.text.toString().trim()
        val alamat = etAlamat.text.toString().trim()
        val jenisKelamin = etJenisKelamin.text.toString().trim()
        val tempatTanggalLahir = etTempatTanggalLahir.text.toString().trim()

        if (nama.isEmpty()) {
            etNama.error = "Isi Nama!"
            return
        }

        if (alamat.isEmpty()) {
            etAlamat.error = "Isi Alamat!"
            return
        }

        val mhs = Mahasiswa(mhsId, nama, alamat, jenisKelamin, tempatTanggalLahir)
        if (mhsId != null) {
            ref.child(mhsId!!).setValue(mhs).addOnCompleteListener {
                Toast.makeText(applicationContext, "Data berhasil diupdate", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun deleteData() {
        if (mhsId != null) {
            ref.child(mhsId!!).removeValue().addOnCompleteListener {
                Toast.makeText(applicationContext, "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
