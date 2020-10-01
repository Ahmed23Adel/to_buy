package com.wellmax8.tobuy.ViewModel.shops;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.wellmax8.tobuy.DTO.contact;
import com.wellmax8.tobuy.DTO.shop;
import com.wellmax8.tobuy.DTO.sold_at_shopId;
import com.wellmax8.tobuy.ROOM.contact.repo_contact;
import com.wellmax8.tobuy.ROOM.shop.repo_shop;
import com.wellmax8.tobuy.ROOM.shop_contact.repo_shop_contact;
import com.wellmax8.tobuy.ROOM.sold.repo_sold;

import java.util.List;

public class VM_shop_details extends ViewModel {

    private Context context;
    private repo_shop repo_shop;
    private repo_sold repo_sold;
    private repo_contact repo_contact;
    private repo_shop_contact repo_shop_contact;

    public VM_shop_details() {
    }

    public void setContext(Context context) {
        this.context = context;
        repo_sold=new repo_sold(context);
        repo_shop=new repo_shop(context);
        repo_contact=new repo_contact(context);
        repo_shop_contact=new repo_shop_contact(context);
    }

    public LiveData<List<shop>> getShopAtId(int id_shp){
        return repo_shop.getShopAtID(id_shp);
    }

    public LiveData<List<sold_at_shopId>>getSoldsAtIdShop(int id_shop){
        return repo_sold.getSoldsAtIdShop(id_shop);
    }

    public LiveData<List<contact>> getContactsAtShopId(int id_shop){
        return repo_contact.getContactAtShopId(id_shop);
    }


    public void deleteCurrentShop(int shop_id) {
        repo_shop.deleteAtId(shop_id);
        repo_shop_contact.deleteShop_contactAtShopId(shop_id);
        repo_sold.updateSoldShopIdAtShopId(shop_id);
    }
}
