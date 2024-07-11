package com.project.bookcatalog.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class TitleAuthorPk implements Serializable{

    private int auId;
    private int titleId;


    public TitleAuthorPk() {
    }

    public TitleAuthorPk(int titleId, int auId) {
        this.auId = auId;
        this.titleId = titleId;
    }


    public int getAuId() {
        return this.auId;
    }

    public void setAuId(int auId) {
        this.auId = auId;
    }

    public int getTitleId() {
        return this.titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

}