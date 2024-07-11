package com.project.bookcatalog.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Title {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int titleId;
    @Column(nullable = false,unique = true)
    private String title;
    private double price;
    private double ytdSales;
    private int releaseYear;
    
    @ManyToOne
    @JoinColumn(name="pub_id")
    private Publisher publishers;

    @JsonIgnore
    @OneToMany(mappedBy = "title", cascade = CascadeType.ALL)
    private List<TitleAuthor> titleAuthors;

    @JsonIgnore
    @OneToMany(mappedBy = "titles",cascade = CascadeType.ALL)
    private List<Sale> sales;


    public Title() {
    }

    public Title(String title, double price, double ytdSales, int releaseYear, Publisher publishers, List<TitleAuthor> titleAuthors, List<Sale> sales) {
        this.title = title;
        this.price = price;
        this.ytdSales = ytdSales;
        this.releaseYear = releaseYear;
        this.publishers = publishers;
        this.titleAuthors = titleAuthors;
        this.sales = sales;
    }
    



    public int getTitleId() {
        return this.titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
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

    public void setYtdSales(double ytdSales) {
        this.ytdSales = ytdSales;
    }

    public int getReleaseYear() {
        return this.releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Publisher getPublishers() {
        return this.publishers;
    }

    public void setPublishers(Publisher publishers) {
        this.publishers = publishers;
    }

    public List<TitleAuthor> getTitleAuthors() {
        return this.titleAuthors;
    }

    public void setTitleAuthors(List<TitleAuthor> titleAuthors) {
        this.titleAuthors = titleAuthors;
    }

    public List<Sale> getSales() {
        return this.sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    @Override
    public String toString() {
        return "{" +
            " titleId='" + getTitleId() + "'" +
            ", title='" + getTitle() + "'" +
            ", price='" + getPrice() + "'" +
            ", ytdSales='" + getYtdSales() + "'" +
            ", releaseYear='" + getReleaseYear() + "'" +
            ", publishers='" + getPublishers() + "'" +
            ", titleAuthors='" + getTitleAuthors() + "'" +
            ", sales='" + getSales() + "'" +
            "}";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + titleId;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        long temp;
        temp = Double.doubleToLongBits(price);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(ytdSales);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + releaseYear;
        result = prime * result + ((publishers == null) ? 0 : publishers.hashCode());
        result = prime * result + ((titleAuthors == null) ? 0 : titleAuthors.hashCode());
        result = prime * result + ((sales == null) ? 0 : sales.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Title other = (Title) obj;
        if (titleId != other.titleId)
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
            return false;
        if (Double.doubleToLongBits(ytdSales) != Double.doubleToLongBits(other.ytdSales))
            return false;
        if (releaseYear != other.releaseYear)
            return false;
        if (publishers == null) {
            if (other.publishers != null)
                return false;
        } else if (!publishers.equals(other.publishers))
            return false;
        if (titleAuthors == null) {
            if (other.titleAuthors != null)
                return false;
        } else if (!titleAuthors.equals(other.titleAuthors))
            return false;
        if (sales == null) {
            if (other.sales != null)
                return false;
        } else if (!sales.equals(other.sales))
            return false;
        return true;
    }

}