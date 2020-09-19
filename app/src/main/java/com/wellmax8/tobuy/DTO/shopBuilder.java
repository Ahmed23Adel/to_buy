package com.wellmax8.tobuy.DTO;

public class shopBuilder {
    private String name;
    private String address;
    private String facebookLink;
    private String notes;

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
        return new shop(name, address, facebookLink, notes);
    }
}