package com.example.asus.myapplication;

import com.google.firebase.database.DatabaseReference;

public class matakuliah {
    private String nama;
    private String kode;
    private String absen;
    private String nim;
    private String nik;

    public matakuliah() {

    }

    public matakuliah(String nama, String kode, String absen , String nim , String nik) {
        this.nama = nama;
        this.kode = kode;
        this.absen = absen;
        this.nim = nim;
        this.nik = nik;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public void setAbsen(String absen) {
        this.absen = absen;
    }

    public String getNama() {

        return nama;
    }

    public String getKode() {
        return kode;
    }

    public String getAbsen() {
        return absen;
    }
}
