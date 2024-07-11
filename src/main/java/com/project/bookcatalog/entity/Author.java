package com.project.bookcatalog.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authorId_seq")
    @SequenceGenerator(name = "authorId_seq", sequenceName = "authorId_sequence", allocationSize = 1, initialValue = 101)
    private int auId;
    @Column(nullable = false)
    private String auName;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false,unique = true)
    private String mobile;
    private String city;
    private String country;

    @JsonIgnore
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<TitleAuthor> titleAuthors;


    public Author() {
    }

    public Author(String auName, String email, String mobile, String city, String country, List<TitleAuthor> titleAuthors) {
        this.auName = auName;
        this.email = email;
        this.mobile = mobile;
        this.city = city;
        this.country = country;
        this.titleAuthors = titleAuthors;
    }

    public int getAuId() {
        return this.auId;
    }

    public void setAuId(int auId) {
        this.auId = auId;
    }

    public String getAuName() {
        return this.auName;
    }

    public void setAuName(String auName) {
        this.auName = auName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<TitleAuthor> getTitleAuthors() {
        return this.titleAuthors;
    }

    public void setTitleAuthors(List<TitleAuthor> titleAuthors) {
        this.titleAuthors = titleAuthors;
    }

    @Override
    public String toString() {
        return "{" +
            " auId='" + getAuId() + "'" +
            ", auName='" + getAuName() + "'" +
            ", email='" + getEmail() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", city='" + getCity() + "'" +
            ", country='" + getCountry() + "'" +
            ", titleAuthors='" + getTitleAuthors() + "'" +
            "}";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + auId;
        result = prime * result + ((auName == null) ? 0 : auName.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((titleAuthors == null) ? 0 : titleAuthors.hashCode());
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
        Author other = (Author) obj;
        if (auId != other.auId)
            return false;
        if (auName == null) {
            if (other.auName != null)
                return false;
        } else if (!auName.equals(other.auName))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (mobile == null) {
            if (other.mobile != null)
                return false;
        } else if (!mobile.equals(other.mobile))
            return false;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (country == null) {
            if (other.country != null)
                return false;
        } else if (!country.equals(other.country))
            return false;
        if (titleAuthors == null) {
            if (other.titleAuthors != null)
                return false;
        } else if (!titleAuthors.equals(other.titleAuthors))
            return false;
        return true;
    }

}