package com.example.valuablemoney.model;

public class KhoanThu {
    private int id;
    private String nguonthu;
    private String sotien;

    public KhoanThu() {
    }

    public KhoanThu(int id, String nguonthu, String sotien) {
        this.id = id;
        this.nguonthu = nguonthu;
        this.sotien = sotien;
    }

    public KhoanThu(String nguonthu, String sotien) {
        this.nguonthu = nguonthu;
        this.sotien = sotien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNguonthu() {
        return nguonthu;
    }

    public void setNguonthu(String nguonthu) {
        this.nguonthu = nguonthu;
    }

    public String getSotien() {
        return sotien;
    }

    public void setSotien(String sotien) {
        this.sotien = sotien;
    }
}
