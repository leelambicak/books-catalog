package com.project.bookcatalog.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class SalePk implements Serializable{

    private int storeId;
    private int titleId;


    public SalePk() {
    }

    public SalePk(int titleId, int storeId) {
        this.storeId = storeId;
        this.titleId = titleId;
    }

    public int getStoreId() {
        return this.storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getTitleId() {
        return this.titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + storeId;
        result = prime * result + titleId;
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
        SalePk other = (SalePk) obj;
        if (storeId != other.storeId)
            return false;
        if (titleId != other.titleId)
            return false;
        return true;
    }

}
