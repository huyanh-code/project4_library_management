package com.project4.com.service.dto;

public class PublisherDTO {

    private int MaNXB;

    private String TenNXB;
    private String DiaChi;
    private String Sdt;

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

    public PublisherDTO(int maNXB, String tenNXB, String diaChi, String sdt) {
        MaNXB = maNXB;
        TenNXB = tenNXB;
        DiaChi = diaChi;
        Sdt = sdt;
    }

    public PublisherDTO() {}
}
