package com.project4.com.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tbl_sach")
@Data
public class Book {

    @Id
    @Column(name = "ma_sach")
    private int maSoSach;

    private String tensach;
    private int matacgia;
    private int maNXB;
    private int maloai;
    private int namxb;
    private int soluong;
    private String ghichu;
    private String hinhanh;

    /**
     * 0: sach da bi xoa
     * 1: sach chua bi xoa
     */
    @Column(name = "trang_thai")
    private Integer trangThai;

    /**
     * Ma ke sach
     */
    @Column(name = "ma_ke")
    private int maKe;

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

    public int getMatacgia() {
        return matacgia;
    }

    public void setMatacgia(int matacgia) {
        this.matacgia = matacgia;
    }

    public int getMaNXB() {
        return maNXB;
    }

    public void setMaNXB(int maNXB) {
        this.maNXB = maNXB;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
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

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public int getMaKe() {
        return maKe;
    }

    public void setMaKe(int maKe) {
        this.maKe = maKe;
    }

    public Book() {}

    public Book(
        int maSoSach,
        String tensach,
        int matacgia,
        int maNXB,
        int maloai,
        int namxb,
        int soluong,
        String ghichu,
        String hinhanh,
        Integer trangThai,
        int maKe
    ) {
        this.maSoSach = maSoSach;
        this.tensach = tensach;
        this.matacgia = matacgia;
        this.maNXB = maNXB;
        this.maloai = maloai;
        this.namxb = namxb;
        this.soluong = soluong;
        this.ghichu = ghichu;
        this.hinhanh = hinhanh;
        this.trangThai = trangThai;
        this.maKe = maKe;
    }
}
