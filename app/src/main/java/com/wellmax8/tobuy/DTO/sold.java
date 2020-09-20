package com.wellmax8.tobuy.DTO;

import android.net.Uri;
import android.provider.BaseColumns;

import androidx.room.Entity;
import androidx.room.Ignore;
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

    private String created_at;
    private String last_edit;

    @Ignore
    public static String TIME_BUY_NOT_SPECIFIED="ns";

    @Ignore
    public static int ID_SHOP_NOT_SPECIFIED=-1;


    private boolean isBought;
    private String timeBuying;

    public sold(int id_category, int id_shop, String name, String description, String extra, double price, String created_at, String last_edit, boolean isBought, String timeBuying) {
        this.id_category = id_category;
        this.id_shop = id_shop;
        this.name = name;
        this.description = description;
        this.extra = extra;
        this.price = price;
        this.created_at = created_at;
        this.last_edit = last_edit;
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

    public String getCreated_at() {
        return created_at;
    }

    public String getLast_edit() {
        return last_edit;
    }

    public boolean isBought() {
        return isBought;
    }

    public String getTimeBuying() {
        return timeBuying;
    }
}
