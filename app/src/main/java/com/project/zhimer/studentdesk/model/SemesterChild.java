package com.project.zhimer.studentdesk.model;

public class SemesterChild {

    private String kodeMk, namaMk, nilaiHuruf;
    private Integer sks, totalSks;

    public SemesterChild(String kodeMk, String namaMk, String nilaiHuruf, Integer sks) {
        this.kodeMk = kodeMk;
        this.namaMk = namaMk;
        this.nilaiHuruf = nilaiHuruf;
        this.sks = sks;
    }

    public String getKodeMk() {
        return kodeMk;
    }

    public void setKodeMk(String kodeMk) {
        this.kodeMk = kodeMk;
    }

    public String getNamaMk() {
        return namaMk;
    }

    public void setNamaMk(String namaMk) {
        this.namaMk = namaMk;
    }

    public String getNilaiHuruf() {
        return nilaiHuruf;
    }

    public void setNilaiHuruf(String nilaiHuruf) {
        this.nilaiHuruf = nilaiHuruf;
    }

    public Integer getSks() {
        return sks;
    }

    public void setSks(Integer sks) {
        this.sks = sks;
    }

    public Integer getTotalSks() {
        return totalSks;
    }

    public void setTotalSks(Integer totalSks) {
        this.totalSks = totalSks;
    }
}
