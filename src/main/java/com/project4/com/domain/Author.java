package com.project4.com.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tbl_tacgia")
public class Author {

    @Id
    @Column(name = "ma_tac_gia")
    private int MaTacGia;

    private String TenTacGia;
    private String NamSinh;
    private String QueQuan;

    /**
     * 0: tac gia da bi xoa
     * 1: tac gia chua bi xoa
     */
    @Column(name = "trang_thai")
    private Integer trangThai;

    public int getMaTacGia() {
        return MaTacGia;
    }

    public void setMaTacGia(int maTacGia) {
        MaTacGia = maTacGia;
    }

    public String getTenTacGia() {
        return TenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        TenTacGia = tenTacGia;
    }

    public String getNamSinh() {
        return NamSinh;
    }

    public void setNamSinh(String namSinh) {
        NamSinh = namSinh;
    }

    public String getQueQuan() {
        return QueQuan;
    }

    public void setQueQuan(String queQuan) {
        QueQuan = queQuan;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public Author(int maTacGia, String tenTacGia, String namSinh, String queQuan, Integer trangThai) {
        MaTacGia = maTacGia;
        TenTacGia = tenTacGia;
        NamSinh = namSinh;
        QueQuan = queQuan;
        this.trangThai = trangThai;
    }

    public Author() {}
}
