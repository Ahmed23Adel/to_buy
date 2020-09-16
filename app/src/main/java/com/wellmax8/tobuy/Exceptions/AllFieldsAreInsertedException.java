package com.wellmax8.tobuy.Exceptions;

import androidx.annotation.Nullable;

public class AllFieldsAreInsertedException extends Exception {

    public AllFieldsAreInsertedException(String message) {
        super(message);
    }

    @Nullable
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
