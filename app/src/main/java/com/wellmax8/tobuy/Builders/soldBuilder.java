package com.wellmax8.tobuy.Builders;

import com.wellmax8.tobuy.DTO.sold;

public class soldBuilder {
    private int id_category;
    private int id_shop;
    private String name;
    private String description;
    private String extra;
    private double price;
    private String created_at;
    private String last_edit;
    private boolean isBought;
    private String timeBuying;

    public soldBuilder setId_category(int id_category) {
        this.id_category = id_category;
        return this;
    }

    public soldBuilder setId_shop(int id_shop) {
        this.id_shop = id_shop;
        return this;
    }

    public soldBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public soldBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public soldBuilder setExtra(String extra) {
        this.extra = extra;
        return this;
    }

    public soldBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public soldBuilder setCreated_at(String created_at) {
        this.created_at = created_at;
        return this;
    }

    public soldBuilder setLast_edit(String last_edit) {
        this.last_edit = last_edit;
        return this;
    }

    public soldBuilder setIsBought(boolean isBought) {
        this.isBought = isBought;
        return this;
    }

    public soldBuilder setTimeBuying(String timeBuying) {
        this.timeBuying = timeBuying;
        return this;
    }

    public sold build() {
        return new sold(id_category, id_shop, name, description, extra, price, created_at, last_edit, isBought, timeBuying);
    }
}