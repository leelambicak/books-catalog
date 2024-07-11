package com.project.bookcatalog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class TitleAuthor {

    @EmbeddedId
    private TitleAuthorPk titleAuthorsPk;

    @JsonIgnore
    @ManyToOne
    @MapsId("auId")
    @JoinColumn(name = "au_id")
    private Author author;

    @JsonIgnore
    @ManyToOne
    @MapsId("titleId")
    @JoinColumn(name = "title_id")
    private Title title;

    private int royaltyPct;

    public TitleAuthor() {
    }

    public TitleAuthor(Author author, Title title, int royaltyPct) {
        this.author = author;
        this.title = title;
        this.royaltyPct = royaltyPct;
    }

    public TitleAuthorPk getTitleAuthorsPk() {
        return this.titleAuthorsPk;
    }

    public void setTitleAuthorsPk(TitleAuthorPk titleAuthorsPk) {
        this.titleAuthorsPk = titleAuthorsPk;
    }

    public Author getAuthor() {
        return this.author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Title getTitle() {
        return this.title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public int getRoyaltyPct() {
        return this.royaltyPct;
    }

    public void setRoyaltyPct(int royaltyPct) {
        this.royaltyPct = royaltyPct;
    }

    @Override
    public String toString() {
        return "{" +
                " titleAuthorsPk='" + getTitleAuthorsPk() + "'" +
                ", author='" + getAuthor() + "'" +
                ", title='" + getTitle() + "'" +
                ", royaltyPct='" + getRoyaltyPct() + "'" +
                "}";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((titleAuthorsPk == null) ? 0 : titleAuthorsPk.hashCode());
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + royaltyPct;
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
        TitleAuthor other = (TitleAuthor) obj;
        if (titleAuthorsPk == null) {
            if (other.titleAuthorsPk != null)
                return false;
        } else if (!titleAuthorsPk.equals(other.titleAuthorsPk))
            return false;
        if (author == null) {
            if (other.author != null)
                return false;
        } else if (!author.equals(other.author))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (royaltyPct != other.royaltyPct)
            return false;
        return true;
    }

}