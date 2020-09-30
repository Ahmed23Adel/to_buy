package com.wellmax8.tobuy.DTO;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class sold {

    @PrimaryKey(autoGenerate = true)
    private int id_sold;
    private int id_category;
    private int id_shop_;
    private String name_sold;
    private String description_sold;
    private String extra_sold;
    private double price;

    private String created_at_sold;
    private String last_edit_sold;

    @Ignore
    public static String TIME_BUY_NOT_SPECIFIED="ns";

    @Ignore
    public static int ID_SHOP_NOT_SPECIFIED=-1;


    private boolean isBought;
    private String timeBuying;

    public sold(int id_category, int id_shop_, String name_sold, String description_sold, String extra_sold, double price, String created_at_sold, String last_edit_sold, boolean isBought, String timeBuying) {
        this.id_category = id_category;
        this.id_shop_ = id_shop_;
        this.name_sold = name_sold;
        this.description_sold = description_sold;
        this.extra_sold = extra_sold;
        this.price = price;
        this.created_at_sold = created_at_sold;
        this.last_edit_sold = last_edit_sold;
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

    public String getDescription_sold() {
        return description_sold;
    }

    public String getExtra_sold() {
        return extra_sold;
    }

    public double getPrice() {
        return price;
    }

    public String getCreated_at_sold() {
        return created_at_sold;
    }

    public String getLast_edit_sold() {
        return last_edit_sold;
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
                getDescription_sold().equals(sold.getDescription_sold()) &&
                getExtra_sold().equals(sold.getExtra_sold()) &&
                getCreated_at_sold().equals(sold.getCreated_at_sold()) &&
                getLast_edit_sold().equals(sold.getLast_edit_sold()) &&
                getTimeBuying().equals(sold.getTimeBuying());
    }


}
