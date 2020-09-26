package com.wellmax8.tobuy.Builders;

import com.wellmax8.tobuy.DTO.shop;

public class shopBuilder {
    private String name;
    private String address;
    private String facebookLink;
    private String notes;
    private String createdAt;
    private int id;

    public shopBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public shopBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public shopBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public shopBuilder setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
        return this;
    }

    public shopBuilder setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public shopBuilder setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public shop build() {
        shop shop= new shop(name, address, facebookLink, notes,createdAt);
        shop.setId_shop(id);
        return shop;
    }
}