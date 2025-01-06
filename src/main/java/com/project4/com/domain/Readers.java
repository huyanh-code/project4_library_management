package com.project4.com.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tbl_docgia")
@Data
public class Readers {

    @Id
    @Column(name = "madocgia")
    private int MaDocGia;

    private String TenDocGia;
    private String GioiTinh;
    private String diachi;
    private String sdt;

    /**
     * 0: doc gia da bi xoa
     * 1: doc gia chua bi xoa
     */
    @Column(name = "trang_thai")
    private Integer trangThai;

    public Readers(int maDocGia, String tenDocGia, String gioiTinh, String diachi, String sdt, Integer trangThai) {
        MaDocGia = maDocGia;
        TenDocGia = tenDocGia;
        GioiTinh = gioiTinh;
        this.diachi = diachi;
        this.sdt = sdt;
        this.trangThai = trangThai;
    }

    public int getMaDocGia() {
        return MaDocGia;
    }

    public void setMaDocGia(int maDocGia) {
        MaDocGia = maDocGia;
    }

    public String getTenDocGia() {
        return TenDocGia;
    }

    public void setTenDocGia(String tenDocGia) {
        TenDocGia = tenDocGia;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public Readers() {}
}
