package com.wellmax8.tobuy.DTO;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity()
 public class shop {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String address;
    private String facebookLink;
    private String notes;

    public shop(String name, String address, String facebookLink, String notes) {
        this.name = name;
        this.address = address;
        this.facebookLink = facebookLink;
        this.notes = notes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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
}
