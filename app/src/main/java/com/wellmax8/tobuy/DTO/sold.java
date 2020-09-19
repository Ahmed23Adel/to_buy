package com.wellmax8.tobuy.DTO;

import android.net.Uri;
import android.provider.BaseColumns;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class sold {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int id_category;
    private int id_shop;
    private String name;
    private String description;
    private String extra;
    private double price;


    private boolean isBought;
    private long timeBuying;

    public sold(int id_category, int id_shop, String name, String description, String extra, double price, boolean isBought, long timeBuying) {
        this.id_category = id_category;
        this.id_shop = id_shop;
        this.name = name;
        this.description = description;
        this.extra = extra;
        this.price = price;
        this.isBought = isBought;
        this.timeBuying = timeBuying;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getId_category() {
        return id_category;
    }

    public int getId_shop() {
        return id_shop;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getExtra() {
        return extra;
    }

    public double getPrice() {
        return price;
    }

    public boolean isBought() {
        return isBought;
    }

    public long getTimeBuying() {
        return timeBuying;
    }
}
