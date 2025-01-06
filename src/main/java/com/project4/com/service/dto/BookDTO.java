package com.project4.com.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class BookDTO {

    private int maSoSach;
    private String tensach;
    private int idTacgia;

    public String getTenTacgia() {
        return tenTacgia;
    }

    public void setTenTacgia(String tenTacgia) {
        this.tenTacgia = tenTacgia;
    }

    public int getIdTacgia() {
        return idTacgia;
    }

    public void setIdTacgia(int idTacgia) {
        this.idTacgia = idTacgia;
    }

    private String tenTacgia;
    private int maNXB;

    public String getTenNXB() {
        return tenNXB;
    }

    public void setTenNXB(String tenNXB) {
        this.tenNXB = tenNXB;
    }

    private String tenNXB;
    private int maloai;
    private String tenLoai;

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    private int namxb;
    private int soluong;
    private String ghichu;
    private String hinhanh;
    private int Make;

    public BookDTO() {}

    public BookDTO(
        int maSoSach,
        String tensach,
        int matacgia,
        String tenTacgia,
        int maNXB, // 5
        String tenNXB,
        int maloai,
        String tenLoai,
        int namxb,
        int soluong, // 10
        String hinhanh,
        String ghichu,
        int make
    ) {
        this.maSoSach = maSoSach;
        this.tensach = tensach;
        this.idTacgia = matacgia;
        this.tenTacgia = tenTacgia;
        this.maNXB = maNXB;
        this.tenNXB = tenNXB;
        this.maloai = maloai;
        this.tenLoai = tenLoai;
        this.namxb = namxb;
        this.soluong = soluong;
        this.ghichu = ghichu;
        this.hinhanh = hinhanh;
        Make = make;
    }

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

    public int getMake() {
        return Make;
    }

    public void setMake(int make) {
        Make = make;
    }
}
