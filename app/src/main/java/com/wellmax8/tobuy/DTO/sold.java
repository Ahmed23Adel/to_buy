package com.wellmax8.tobuy.DTO;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class sold {

    @PrimaryKey(autoGenerate = true)
    private int id_sold;
    private int id_category;
    private int id_shop_;
    private String name_sold;
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

    public sold(int id_category, int id_shop_, String name_sold, String description, String extra, double price, String created_at, String last_edit, boolean isBought, String timeBuying) {
        this.id_category = id_category;
        this.id_shop_ = id_shop_;
        this.name_sold = name_sold;
        this.description = description;
        this.extra = extra;
        this.price = price;
        this.created_at = created_at;
        this.last_edit = last_edit;
        this.isBought = isBought;
        this.timeBuying = timeBuying;
    }

    public void setId_sold(int id_sold) {
        this.id_sold = id_sold;
    }

    public int getId_sold() {
        return id_sold;
    }

    public int getId_category() {
        return id_category;
    }

    public int getId_shop_() {
        return id_shop_;
    }

    public String getName_sold() {
        return name_sold;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof sold)) return false;
        sold sold = (sold) o;
        return getId_sold() == sold.getId_sold() &&
                getId_category() == sold.getId_category() &&
                getId_shop_() == sold.getId_shop_() &&
                Double.compare(sold.getPrice(), getPrice()) == 0 &&
                isBought() == sold.isBought() &&
                getName_sold().equals(sold.getName_sold()) &&
                getDescription().equals(sold.getDescription()) &&
                getExtra().equals(sold.getExtra()) &&
                getCreated_at().equals(sold.getCreated_at()) &&
                getLast_edit().equals(sold.getLast_edit()) &&
                getTimeBuying().equals(sold.getTimeBuying());
    }


}
