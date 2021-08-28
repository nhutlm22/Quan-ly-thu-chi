package com.example.ps13303_leminhnhut_asm_gd2.Model;

public class Giaodich {
    private int maGiaoDich;
    private String tieuDe;
    private String ngay;
    private double tien;
    private String moTa;
    private int maLoai;

    public Giaodich(String tieuDe, String ngay, double tien, String moTa, int maLoai) {
        this.tieuDe = tieuDe;
        this.ngay = ngay;
        this.tien = tien;
        this.moTa = moTa;
        this.maLoai = maLoai;
    }

    public Giaodich() {
    }

    public Giaodich(int maGiaoDich, String tieuDe, String ngay, double tien, String moTa, int maLoai) {
        this.maGiaoDich = maGiaoDich;
        this.tieuDe = tieuDe;
        this.ngay = ngay;
        this.tien = tien;
        this.moTa = moTa;
        this.maLoai = maLoai;
    }

    public int getMaGiaoDich() {
        return maGiaoDich;
    }

    public void setMaGiaoDich(int maGiaoDich) {
        this.maGiaoDich = maGiaoDich;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public double getTien() {
        return tien;
    }

    public void setTien(double tien) {
        this.tien = tien;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }
}
