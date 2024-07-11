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
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publisherId_seq")
    @SequenceGenerator(name = "publisherId_seq", sequenceName = "publisherId_sequence", allocationSize = 1, initialValue = 1001)
    private int pubId;
    @Column(nullable = false,length = 25)
    private String pubName;
    @Column(nullable = false,unique = true)
    private String email;
    private String city;
    private String country;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "publishers")
    private List<Title> titles;


    public Publisher() {
    }
    

    public Publisher(int pubId) {
        this.pubId = pubId;
    }


    public Publisher(String pubName, String email, String city, String country, List<Title> titles) {
        this.pubName = pubName;
        this.email = email;
        this.city = city;
        this.country = country;
        this.titles = titles;
    }
    
    public int getPubId() {
        return this.pubId;
    }

    public void setPubId(int pubId) {
        this.pubId = pubId;
    }

    public String getPubName() {
        return this.pubName;
    }

    public void setPubName(String pubName) {
        this.pubName = pubName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<Title> getTitles() {
        return this.titles;
    }

    public void setTitles(List<Title> titles) {
        this.titles = titles;
    }

    @Override
    public String toString() {
        return "{" +
            " pubId='" + getPubId() + "'" +
            ", pubName='" + getPubName() + "'" +
            ", email='" + getEmail() + "'" +
            ", city='" + getCity() + "'" +
            ", country='" + getCountry() + "'" +
            ", titles='" + getTitles() + "'" +
            "}";
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + pubId;
        result = prime * result + ((pubName == null) ? 0 : pubName.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((titles == null) ? 0 : titles.hashCode());
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
        Publisher other = (Publisher) obj;
        if (pubId != other.pubId)
            return false;
        if (pubName == null) {
            if (other.pubName != null)
                return false;
        } else if (!pubName.equals(other.pubName))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
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
        if (titles == null) {
            if (other.titles != null)
                return false;
        } else if (!titles.equals(other.titles))
            return false;
        return true;
    }

}
