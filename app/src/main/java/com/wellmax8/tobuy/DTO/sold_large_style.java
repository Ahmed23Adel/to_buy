package com.wellmax8.tobuy.DTO;

import androidx.room.Embedded;

public class sold_large_style {
    @Embedded
    private sold sold;

    @Embedded
    private shop shop;

    public sold_large_style(sold sold,shop shop) {
        this.sold = sold;
        this.shop = shop;
    }

    public com.wellmax8.tobuy.DTO.sold getSold() {
        return sold;
    }

    public com.wellmax8.tobuy.DTO.shop getShop() {
        return shop;
    }
}
