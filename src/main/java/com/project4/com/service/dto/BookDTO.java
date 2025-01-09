package com.project4.com.service.dto;

import lombok.Data;

@Data
public class BookDTO {

    private int maSoSach;
    private String tensach;
    private int idTacgia;
    private String tenTacGia;
    private int maNXB;
    private String tenNXB;
    private int maLoai;
    private String tenLoai;
    private int Make;
    private String viTri;
    private int namxb;
    private int soluong;
    private String ghichu;
    private String hinhanh;

    public int getMaSoSach() {
        return maSoSach;
    }

    public void setMaSoSach(int maSoSach) {
        this.maSoSach = maSoSach;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public int getIdTacgia() {
        return idTacgia;
    }

    public void setIdTacgia(int idTacgia) {
        this.idTacgia = idTacgia;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public int getMaNXB() {
        return maNXB;
    }

    public void setMaNXB(int maNXB) {
        this.maNXB = maNXB;
    }

    public String getTenNXB() {
        return tenNXB;
    }

    public void setTenNXB(String tenNXB) {
        this.tenNXB = tenNXB;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public int getMake() {
        return Make;
    }

    public void setMake(int make) {
        Make = make;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    public int getNamxb() {
        return namxb;
    }

    public void setNamxb(int namxb) {
        this.namxb = namxb;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public BookDTO(
        int maSoSach,
        String tensach,
        int idTacgia,
        String tenTacGia,
        int maNXB,
        String tenNXB,
        int maLoai,
        String tenLoai,
        int make,
        String viTri,
        int namxb,
        int soluong,
        String ghichu,
        String hinhanh
    ) {
        this.maSoSach = maSoSach;
        this.tensach = tensach;
        this.idTacgia = idTacgia;
        this.tenTacGia = tenTacGia;
        this.maNXB = maNXB;
        this.tenNXB = tenNXB;
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
        this.Make = make;
        this.viTri = viTri;
        this.namxb = namxb;
        this.soluong = soluong;
        this.ghichu = ghichu;
        this.hinhanh = hinhanh;
    }

    public BookDTO() {}
}
