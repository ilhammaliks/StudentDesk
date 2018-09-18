package com.project.zhimer.studentdesk.model;

public class Kuliah {

    private String kelas;
    private String mataKuliah;
    private String sks;
    private String waktu;
    private String dosen;
    private String ruang;


    public Kuliah(String kelas, String mataKuliah, String sks, String waktu, String dosen, String ruang) {
        this.kelas = kelas;
        this.mataKuliah = mataKuliah;
        this.sks = sks;
        this.waktu = waktu;
        this.dosen = dosen;
        this.ruang = ruang;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getMataKuliah() {
        return mataKuliah;
    }

    public void setMataKuliah(String mataKuliah) {
        this.mataKuliah = mataKuliah;
    }

    public String getSks() {
        return sks;
    }

    public void setSks(String sks) {
        this.sks = sks;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getDosen() {
        return dosen;
    }

    public void setDosen(String dosen) {
        this.dosen = dosen;
    }

    public String getRuang() {
        return ruang;
    }

    public void setRuang(String ruang) {
        this.ruang = ruang;
    }
}
