package com.wellmax8.tobuy.DTO;

import androidx.annotation.Nullable;
import androidx.room.Embedded;

import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof sold_large_style)) return false;
        sold_large_style that = (sold_large_style) o;
        return getSold().equals(that.getSold()) &&
                getShop().equals(that.getShop());
    }


}
