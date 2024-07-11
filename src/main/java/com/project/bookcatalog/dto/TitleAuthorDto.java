package com.project.bookcatalog.dto;

public class TitleAuthorDto {
    private String title;
    private double price;
    private double ytdSales;
    private String auName;
    private String pubName;


    public TitleAuthorDto() {
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getYtdSales() {
        return this.ytdSales;
    }

    public void setYtdSales(double d) {
        this.ytdSales = d;
    }

    public String getAuName() {
        return this.auName;
    }

    public void setAuName(String auName) {
        this.auName = auName;
    }

    public String getPubName() {
        return this.pubName;
    }

    public void setPubName(String pubName) {
        this.pubName = pubName;
    }


}
