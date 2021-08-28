package com.example.ps13303_leminhnhut_asm_gd2.Model;

public class Phanloai {
    private int idPhanLoai;
    private String tenLoai;
    private String trangThai;

    public Phanloai() {
    }

    public Phanloai(int idPhanLoai, String tenLoai, String trangThai) {
        this.idPhanLoai = idPhanLoai;
        this.tenLoai = tenLoai;
        this.trangThai = trangThai;
    }

    public Phanloai(int idPhanLoai, String tenLoai) {
        this.idPhanLoai = idPhanLoai;
        this.tenLoai = tenLoai;
    }

    public Phanloai(String tenLoai, String trangThai) {
        this.tenLoai = tenLoai;
        this.trangThai = trangThai;
    }

    public int getIdPhanLoai() {
        return idPhanLoai;
    }

    public void setIdPhanLoai(int idPhanLoai) {
        this.idPhanLoai = idPhanLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
