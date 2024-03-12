package com.ucaru.ShortLink.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "shortlink")
public class ShortLink {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "actual_link")
    private String actualLink;

    @Column(name = "short_link")
    private String shortLink;

    //empty constructor
    public ShortLink() {}

    //all args constructor
    public ShortLink(int id, String actualLink, String shortLink) {
        this.id = id;
        this.actualLink = actualLink;
        this.shortLink = shortLink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActualLink() {
        return actualLink;
    }

    public void setActualLink(String actualLink) {
        this.actualLink = actualLink;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }
}
