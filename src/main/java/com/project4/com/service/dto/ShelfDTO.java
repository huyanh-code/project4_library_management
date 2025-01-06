package com.project4.com.service.dto;

public class ShelfDTO {

    private int MaKeSach;
    private String ViTri;

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

    public ShelfDTO(int maKeSach, String viTri) {
        MaKeSach = maKeSach;
        ViTri = viTri;
    }

    public ShelfDTO() {}
}
