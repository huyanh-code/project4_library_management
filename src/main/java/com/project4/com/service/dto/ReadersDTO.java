package com.project4.com.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReadersDTO {

    private Integer MaDocGia;
    private String TenDocGia;
    private String GioiTinh;
    private String diachi;
    private String sdt;

    public ReadersDTO(Integer maDocGia, String tenDocGia, String gioiTinh, String diachi, String sdt) {
        MaDocGia = maDocGia;
        TenDocGia = tenDocGia;
        GioiTinh = gioiTinh;
        this.diachi = diachi;
        this.sdt = sdt;
    }

    public Integer getMaDocGia() {
        return MaDocGia;
    }

    public void setMaDocGia(Integer maDocGia) {
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

    public ReadersDTO() {}
}
