package com.example.testfirebase

class Mahasiswa (
    val id : String?,
    val nama : String,
    val alamat : String,
    val jenisKelamin : String,
    val tempatTanggalLahir: String
) {
    constructor(): this("","","","","")
}