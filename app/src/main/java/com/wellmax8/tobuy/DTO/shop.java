package com.wellmax8.tobuy.DTO;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity()
 public class shop {
    @PrimaryKey(autoGenerate = true)
    private int id_shop;
    private String name_shop;
    private String address;
    private String facebookLink;
    private String notes;

    public shop(String name_shop, String address, String facebookLink, String notes) {
        this.name_shop = name_shop;
        this.address = address;
        this.facebookLink = facebookLink;
        this.notes = notes;
    }

    public void setId_shop(int id_shop) {
        this.id_shop = id_shop;
    }

    public int getId_shop() {
        return id_shop;
    }

    public String getName_shop() {
        return name_shop;
    }

    public String getAddress() {
        return address;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof shop)) return false;
        shop shop = (shop) o;
        return getId_shop() == shop.getId_shop() &&
                getName_shop().equals(shop.getName_shop()) &&
                getAddress().equals(shop.getAddress()) &&
                getFacebookLink().equals(shop.getFacebookLink()) &&
                getNotes().equals(shop.getNotes());
    }

}
