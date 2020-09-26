package com.wellmax8.tobuy.DTO;

public class shopBuilder {
    private String name;
    private String address;
    private String facebookLink;
    private String notes;
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

    public shop build() {
        shop shop= new shop(name, address, facebookLink, notes);
        shop.setId_shop(id);
        return shop;
    }
}