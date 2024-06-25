package com.example.testfirebase

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class AdapterMahasiswa(
    private val context: Context,
    private val resource: Int,
    private val mhsList: List<Mahasiswa>
) : ArrayAdapter<Mahasiswa>(context, resource, mhsList) {

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(resource, null)

        val namaTextView = view.findViewById<TextView>(R.id.tv_nama)
        val alamatTextView = view.findViewById<TextView>(R.id.tv_alamat)
        val jenisKelaminTextView = view.findViewById<TextView>(R.id.tv_jenis_kelamin)
        val tempatTanggalLahirTextView = view.findViewById<TextView>(R.id.tv_tempat_tanggal_lahir)
        val btnEdit = view.findViewById<Button>(R.id.btn_edit_all)

        val mahasiswa = mhsList[position]

        namaTextView.text = "Nama: ${mahasiswa.nama}"
        alamatTextView.text = "Alamat: ${mahasiswa.alamat}"
        jenisKelaminTextView.text = "Jenis Kelamin: ${mahasiswa.jenisKelamin}"
        tempatTanggalLahirTextView.text = "Tempat/Tanggal Lahir: ${mahasiswa.tempatTanggalLahir}"

        btnEdit.setOnClickListener {
            val intent = Intent(context, EditActivity::class.java).apply {
                putExtra("MHS_ID", mahasiswa.id)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        }

        return view
    }
}
