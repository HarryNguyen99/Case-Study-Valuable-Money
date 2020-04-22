package com.example.valuablemoney.model;

public class KhoanChi {
    private int id;
    private String lydochi;
    private String sotienchi;

    public KhoanChi() {
    }

    public KhoanChi(int id, String lydochi, String sotienchi) {
        this.id = id;
        this.lydochi = lydochi;
        this.sotienchi = sotienchi;
    }

    public KhoanChi(String lydochi, String sotienchi) {
        this.lydochi = lydochi;
        this.sotienchi = sotienchi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLydochi() {
        return lydochi;
    }

    public void setLydochi(String lydochi) {
        this.lydochi = lydochi;
    }

    public String getSotienchi() {
        return sotienchi;
    }

    public void setSotienchi(String sotienchi) {
        this.sotienchi = sotienchi;
    }
}
