package com.project4.com.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tbl_kesach")
public class Shelf {

    @Id
    @Column(name = "Make")
    private int MaKeSach;

    private String ViTri;

    /**
     * 0: tac gia da bi xoa
     * 1: tac gia chua bi xoa
     */
    @Column(name = "TrangThai")
    private Integer trangThai;

    public int getMaKeSach() {
        return MaKeSach;
    }

    public void setMaKeSach(int maKeSach) {
        MaKeSach = maKeSach;
    }

    public String getViTri() {
        return ViTri;
    }

    public void setViTri(String viTri) {
        ViTri = viTri;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public Shelf() {}

    public Shelf(int maKeSach, String viTri, Integer trangThai) {
        MaKeSach = maKeSach;
        ViTri = viTri;
        this.trangThai = trangThai;
    }
}
