package com.wellmax8.tobuy.DTO;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity()
public class shop_contact {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int id_shop;
    private int id_contact;

    public shop_contact(int id_shop, int id_contact) {
        this.id_shop = id_shop;
        this.id_contact = id_contact;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getId_shop() {
        return id_shop;
    }

    public int getId_contact() {
        return id_contact;
    }
}
