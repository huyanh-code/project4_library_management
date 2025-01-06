package com.project4.com.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tbl_nhaxuatban")
public class Publisher {

    @Id
    @Column(name = "MaNXB")
    private int MaNXB;

    private String TenNXB;
    private String DiaChi;
    private String Sdt;

    /**
     * 0: nha xuat ban da bi xoa
     * 1: nha xuat ban chua bi xoa
     */
    @Column(name = "trang_thai")
    private Integer trangThai;

    public int getMaNXB() {
        return MaNXB;
    }

    public void setMaNXB(int maNXB) {
        MaNXB = maNXB;
    }

    public String getTenNXB() {
        return TenNXB;
    }

    public void setTenNXB(String tenNXB) {
        TenNXB = tenNXB;
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

    public Publisher(int maNXB, String tenNXB, String diaChi, String sdt, Integer trangThai) {
        MaNXB = maNXB;
        TenNXB = tenNXB;
        DiaChi = diaChi;
        Sdt = sdt;
        this.trangThai = trangThai;
    }

    public Publisher() {}
}
