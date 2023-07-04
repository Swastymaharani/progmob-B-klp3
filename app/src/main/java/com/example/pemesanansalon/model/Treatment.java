package com.example.pemesanansalon.model;

import java.io.Serializable;

public class Treatment implements Serializable {
    String idTreatment;
    String judulTreatment;
    String linkgambarTreatment;
    String deskripsiTreatment;
    int durasiJamTreatment;
    String jenisTreatment;

    public String getIdTreatment() {
        return idTreatment;
    }

    public void setIdTreatment(String idTreatment) {
        this.idTreatment = idTreatment;
    }

    public Treatment(String idTreatment, String judulTreatment, String linkgambarTreatment, String deskripsiTreatment, int durasiMenitTreatment, String jenisTreatment) {
        this.idTreatment = idTreatment;
        this.judulTreatment = judulTreatment;
        this.linkgambarTreatment = linkgambarTreatment;
        this.deskripsiTreatment = deskripsiTreatment;
        this.durasiJamTreatment = durasiMenitTreatment;
        this.jenisTreatment = jenisTreatment;
    }

    public String getJudulTreatment() {
        return judulTreatment;
    }

    public void setJudulTreatment(String judulTreatment) {
        this.judulTreatment = judulTreatment;
    }

    public String getLinkgambarTreatment() {
        return linkgambarTreatment;
    }

    public void setLinkgambarTreatment(String linkgambarTreatment) {
        this.linkgambarTreatment = linkgambarTreatment;
    }

    public String getDeskripsiTreatment() {
        return deskripsiTreatment;
    }

    public void setDeskripsiTreatment(String deskripsiTreatment) {
        this.deskripsiTreatment = deskripsiTreatment;
    }

    public int getDurasiJamTreatment() {
        return durasiJamTreatment;
    }

    public void setDurasiJamTreatment(int durasiJamTreatment) {
        this.durasiJamTreatment = durasiJamTreatment;
    }

    public String getJenisTreatment() {
        return jenisTreatment;
    }

    public void setJenisTreatment(String jenisTreatment) {
        this.jenisTreatment = jenisTreatment;
    }
}
