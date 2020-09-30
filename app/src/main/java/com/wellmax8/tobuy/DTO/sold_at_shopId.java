package com.wellmax8.tobuy.DTO;

import androidx.room.Embedded;

public class sold_at_shopId {

    @Embedded
    private sold sold;

    @Embedded
    private category category;

    public sold_at_shopId(com.wellmax8.tobuy.DTO.sold sold, com.wellmax8.tobuy.DTO.category category) {
        this.sold = sold;
        this.category = category;
    }

    public com.wellmax8.tobuy.DTO.sold getSold() {
        return sold;
    }

    public com.wellmax8.tobuy.DTO.category getCategory() {
        return category;
    }
}
