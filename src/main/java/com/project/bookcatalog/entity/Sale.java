package com.project.bookcatalog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class Sale {

    @EmbeddedId
    private SalePk salesPk;

    @JsonIgnore
    @ManyToOne
    @MapsId("titleId")
    @JoinColumn(name = "title_id")
    private Title titles;

    @JsonIgnore
    @ManyToOne
    @MapsId("storeId")
    @JoinColumn(name = "store_id")
    private Store stores;

    private int qtySold;

    public Sale() {
    }

    public Sale(Title titles, Store stores, int qtySold) {
        this.titles = titles;
        this.stores = stores;
        this.qtySold = qtySold;
    }

    public SalePk getSalesPk() {
        return this.salesPk;
    }

    public void setSalesPk(SalePk salesPk) {
        this.salesPk = salesPk;
    }

    public Title getTitles() {
        return this.titles;
    }

    public void setTitles(Title titles) {
        this.titles = titles;
    }

    public Store getStores() {
        return this.stores;
    }

    public void setStores(Store stores) {
        this.stores = stores;
    }

    public int getQtySold() {
        return this.qtySold;
    }

    public void setQtySold(int qtySold) {
        this.qtySold = qtySold;
    }

    @Override
    public String toString() {
        return "{" +
                " salesPk='" + getSalesPk() + "'" +
                ", titles='" + getTitles() + "'" +
                ", stores='" + getStores() + "'" +
                ", qtySold='" + getQtySold() + "'" +
                "}";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((salesPk == null) ? 0 : salesPk.hashCode());
        result = prime * result + ((titles == null) ? 0 : titles.hashCode());
        result = prime * result + ((stores == null) ? 0 : stores.hashCode());
        result = prime * result + qtySold;
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
        Sale other = (Sale) obj;
        if (salesPk == null) {
            if (other.salesPk != null)
                return false;
        } else if (!salesPk.equals(other.salesPk))
            return false;
        if (titles == null) {
            if (other.titles != null)
                return false;
        } else if (!titles.equals(other.titles))
            return false;
        if (stores == null) {
            if (other.stores != null)
                return false;
        } else if (!stores.equals(other.stores))
            return false;
        if (qtySold != other.qtySold)
            return false;
        return true;
    }

}