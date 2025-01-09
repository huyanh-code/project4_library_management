package com.project4.com.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tbl_nhanvien")
public class Employees {

    @Id
    @Column(name = "MaNV")
    private int MaNV;

    private String TenNV;
    private String NamSinh;
    private String GioiTinh;
    private String DiaChi;
    private String Sdt;

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int maNV) {
        MaNV = maNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String tenNV) {
        TenNV = tenNV;
    }

    public String getNamSinh() {
        return NamSinh;
    }

    public void setNamSinh(String namSinh) {
        NamSinh = namSinh;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String sdt) {
        Sdt = sdt;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    /**
     * 0: loai  da bi xoa
     * 1: loai chua bi xoa
     */
    @Column(name = "TrangThai")
    private Integer trangThai;

    public Employees(int maNV, String tenNV, String namSinh, String gioiTinh, String diaChi, String sdt, Integer trangThai) {
        MaNV = maNV;
        TenNV = tenNV;
        NamSinh = namSinh;
        GioiTinh = gioiTinh;
        DiaChi = diaChi;
        Sdt = sdt;
        this.trangThai = trangThai;
    }

    public Employees() {}
}
