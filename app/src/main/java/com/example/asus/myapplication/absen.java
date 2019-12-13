package com.example.asus.myapplication;

public class absen {
    private String nik;
    private String matakuliah;

    public absen() {
    }

    public absen(String nik, String matakuliah) {
        this.nik = nik;
        this.matakuliah = matakuliah;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nama) {
        this.nik = nama;
    }

    public String getMatakuliah() {
        return matakuliah;
    }

    public void setMatakuliah(String matakuliah) {
        this.matakuliah = matakuliah;
    }
}
