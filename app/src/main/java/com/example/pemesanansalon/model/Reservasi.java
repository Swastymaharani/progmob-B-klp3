package com.example.pemesanansalon.model;

import java.io.Serializable;

public class Reservasi implements Serializable {
    String email;
    String nama;
    String telepon;
    int waktuMulaiReservasi;
    String tanggalReservasi;
    String tipeTreatment;
    int durasiTreatment;

    public void setWaktuMulaiReservasi(int waktuMulaiReservasi) {
        this.waktuMulaiReservasi = waktuMulaiReservasi;
    }

    public int getDurasiTreatment() {
        return durasiTreatment;
    }

    public void setDurasiTreatment(int durasiTreatment) {
        this.durasiTreatment = durasiTreatment;
    }

    public String getTipeTreatment() {
        return tipeTreatment;
    }

    public void setTipeTreatment(String tipeTreatment) {
        this.tipeTreatment = tipeTreatment;
    }

    public Reservasi(String email, String nama, String telepon, int waktuMulaiReservasi, String tanggalReservasi, String tipeTreatment,int durasiTreatment) {
        this.email = email;
        this.nama = nama;
        this.telepon = telepon;
        this.waktuMulaiReservasi = waktuMulaiReservasi;
        this.tanggalReservasi = tanggalReservasi;
        this.tipeTreatment = tipeTreatment;
        this.durasiTreatment = durasiTreatment;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public int getWaktuMulaiReservasi() {
        return waktuMulaiReservasi;
    }

    public void setWaktuReservasi(int waktuMulaiReservasi){
        this.waktuMulaiReservasi = waktuMulaiReservasi;
    }

    public String getTanggalReservasi() {
        return tanggalReservasi;
    }

    public void setTanggalReservasi(String tanggalReservasi) {
        this.tanggalReservasi = tanggalReservasi;
    }
}

