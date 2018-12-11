package com.project.zhimer.studentdesk.model;

public class Kuliah {

    private String kelas;
    private String mataKuliah;
    private Integer sks;
    private String hari;
    private String mulai;
    private String kelar;
    private String dosen;
    private String ruang;
    private Integer jumlahSKS;

    //kehadiran
    private Integer hadir;
    private Integer sakit;
    private Integer izin;
    private Integer alpa;
    private String persentase;

    //pengganti
    private String tanggalBerhalangan;
    private String tanggalPengganti;


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

    public Integer getSks() {
        return sks;
    }

    public void setSks(Integer sks) {
        this.sks = sks;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getMulai() {
        return mulai;
    }

    public void setMulai(String mulai) {
        this.mulai = mulai;
    }

    public String getKelar() {
        return kelar;
    }

    public void setKelar(String kelar) {
        this.kelar = kelar;
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

    public Integer getJumlahSKS() {
        return jumlahSKS;
    }

    public void setJumlahSKS(Integer jumlahSKS) {
        this.jumlahSKS = jumlahSKS;
    }

    public Integer getHadir() {
        return hadir;
    }

    public void setHadir(Integer hadir) {
        this.hadir = hadir;
    }

    public Integer getSakit() {
        return sakit;
    }

    public void setSakit(Integer sakit) {
        this.sakit = sakit;
    }

    public Integer getIzin() {
        return izin;
    }

    public void setIzin(Integer izin) {
        this.izin = izin;
    }

    public Integer getAlpa() {
        return alpa;
    }

    public void setAlpa(Integer alpa) {
        this.alpa = alpa;
    }

    public String getPersentase() {
        return persentase;
    }

    public void setPersentase(String persentase) {
        this.persentase = persentase;
    }

    public String getTanggalBerhalangan() {
        return tanggalBerhalangan;
    }

    public void setTanggalBerhalangan(String tanggalBerhalangan) {
        this.tanggalBerhalangan = tanggalBerhalangan;
    }

    public String getTanggalPengganti() {
        return tanggalPengganti;
    }

    public void setTanggalPengganti(String tanggalPengganti) {
        this.tanggalPengganti = tanggalPengganti;
    }
}
