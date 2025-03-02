package com.project4.com.service.dto;

public class AuthorDTO {

    private int MaTacGia;
    private String TenTacGia;
    private String NamSinh;
    private String QueQuan;

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

    public AuthorDTO() {}

    public AuthorDTO(int maTacGia, String tenTacGia, String namSinh, String queQuan) {
        MaTacGia = maTacGia;
        TenTacGia = tenTacGia;
        NamSinh = namSinh;
        QueQuan = queQuan;
        System.out.println(queQuan);
    }
}
