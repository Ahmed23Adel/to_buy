package com.wellmax8.tobuy.Observable;

import com.wellmax8.tobuy.Observers.Observer_viewQuilt;

public interface Observable_viewQuilt {
    void subscribe(Observer_viewQuilt o);
    void unSubscribe(Observer_viewQuilt o);
    void updateCategoriesStyle();
}
