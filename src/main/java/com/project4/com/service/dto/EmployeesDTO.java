package com.project4.com.service.dto;

import lombok.Getter;
import lombok.Setter;

public class EmployeesDTO {

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

    public EmployeesDTO(int maNV, String tenNV, String namSinh, String gioiTinh, String diaChi, String sdt) {
        MaNV = maNV;
        TenNV = tenNV;
        NamSinh = namSinh;
        GioiTinh = gioiTinh;
        DiaChi = diaChi;
        Sdt = sdt;
    }

    public EmployeesDTO() {}
}
