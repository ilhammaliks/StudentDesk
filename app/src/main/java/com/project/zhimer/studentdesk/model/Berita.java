package com.project.zhimer.studentdesk.model;

public class Berita {

    private String tanggal;
    private String judul;
    private String gambar;
    private String isinotif;
    private String pengirim;

    public String getPengirim() {
        return pengirim;
    }

    public void setPengirim(String pengirim) {
        this.pengirim = pengirim;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getIsinotif() {
        return isinotif;
    }

    public void setIsinotif(String isinotif) {
        this.isinotif = isinotif;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public Berita() {
    }
}
