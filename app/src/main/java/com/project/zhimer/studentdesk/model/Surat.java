package com.project.zhimer.studentdesk.model;

public class Surat {

    private String nim;
    private String prodi;
    private String ttl;
    private String phone;
    private String email;
    private String alamat;
    private String jenisSurat;
    private String bahasa;
    private String keterangan;

    private String namaPerusahaan;
    private String namaPenerimaSurat; //ditujukan kepada
    private String jabatanPenerimaSurat;
    private String divisi;

    private String tanggalBuat;
    private String jenis;
    private String tanggalJadi;

    private String tanggalLulus;


    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenisSurat() {
        return jenisSurat;
    }

    public void setJenisSurat(String jenisSurat) {
        this.jenisSurat = jenisSurat;
    }

    public String getBahasa() {
        return bahasa;
    }

    public void setBahasa(String bahasa) {
        this.bahasa = bahasa;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNamaPerusahaan() {
        return namaPerusahaan;
    }

    public void setNamaPerusahaan(String namaPerusahaan) {
        this.namaPerusahaan = namaPerusahaan;
    }

    public String getNamaPenerimaSurat() {
        return namaPenerimaSurat;
    }

    public void setNamaPenerimaSurat(String namaPenerimaSurat) {
        this.namaPenerimaSurat = namaPenerimaSurat;
    }

    public String getJabatanPenerimaSurat() {
        return jabatanPenerimaSurat;
    }

    public void setJabatanPenerimaSurat(String jabatanPenerimaSurat) {
        this.jabatanPenerimaSurat = jabatanPenerimaSurat;
    }

    public String getDivisi() {
        return divisi;
    }

    public void setDivisi(String divisi) {
        this.divisi = divisi;
    }

    public String getTanggalBuat() {
        return tanggalBuat;
    }

    public void setTanggalBuat(String tanggalBuat) {
        this.tanggalBuat = tanggalBuat;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getTanggalJadi() {
        return tanggalJadi;
    }

    public void setTanggalJadi(String tanggalJadi) {
        this.tanggalJadi = tanggalJadi;
    }

    public String getTanggalLulus() {
        return tanggalLulus;
    }

    public void setTanggalLulus(String tanggalLulus) {
        this.tanggalLulus = tanggalLulus;
    }

    public Surat(String tanggalBuat, String jenis, String tanggalJadi)
    {
        this.tanggalBuat = tanggalBuat;
        this.jenis = jenis;
        this.tanggalJadi= tanggalJadi;
    }
}
