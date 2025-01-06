package com.project4.com.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tbl_loai")
public class Category {

    @Id
    @Column(name = "ma_loai")
    public int MaLoai;

    public String TenLoai;

    /**
     * 0: loai  da bi xoa
     * 1: loai chua bi xoa
     */
    @Column(name = "trang_thai")
    private Integer trangThai;

    public int getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(int maLoai) {
        MaLoai = maLoai;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String tenLoai) {
        TenLoai = tenLoai;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public Category() {}

    public Category(int maLoai, String tenLoai, Integer trangThai) {
        MaLoai = maLoai;
        TenLoai = tenLoai;
        this.trangThai = trangThai;
    }
}
