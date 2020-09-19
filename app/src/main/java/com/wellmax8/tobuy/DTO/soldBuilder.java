package com.wellmax8.tobuy.DTO;

public class soldBuilder {
    private int id_category;
    private int id_shop;
    private String name;
    private String description;
    private String extra;
    private double price;
    private boolean isBought;
    private long timeBuying;

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

    public soldBuilder setIsBought(boolean isBought) {
        this.isBought = isBought;
        return this;
    }

    public soldBuilder setTimeBuying(long timeBuying) {
        this.timeBuying = timeBuying;
        return this;
    }

    public sold build() {
        return new sold(id_category, id_shop, name, description, extra, price, isBought, timeBuying);
    }
}