package com.project.bookcatalog.dto;

public class TitleResponseDto {
    private int titleId;
    private String title;
    private double price;
    private double ytdSales;
    private int releaseYear;
    private int pubId;
    public TitleResponseDto() {
        //empty constructor
    }
    public int getTitleId() {
        return titleId;
    }
    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getYtdSales() {
        return ytdSales;
    }
    public void setYtdSales(double ytdSales) {
        this.ytdSales = ytdSales;
    }
    public int getReleaseYear() {
        return releaseYear;
    }
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
    public int getPubId() {
        return pubId;
    }
    public void setPubId(int pubId) {
        this.pubId = pubId;
    }

    
}
