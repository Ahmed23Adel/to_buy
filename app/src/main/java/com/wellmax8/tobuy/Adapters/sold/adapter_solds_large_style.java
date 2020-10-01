package com.wellmax8.tobuy.Adapters.sold;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.wellmax8.tobuy.DTO.category;
import com.wellmax8.tobuy.DTO.shop;
import com.wellmax8.tobuy.DTO.sold;
import com.wellmax8.tobuy.DTO.sold_large_style;
import com.wellmax8.tobuy.R;

public class adapter_solds_large_style extends ListAdapter<sold_large_style,adapter_solds_large_style.soldItemHolder> {

    private category currentCategory;
    private Context context;
    public adapter_solds_large_style(Context context,category currentCategory) {
        super(diffCallback);
        this.currentCategory=currentCategory;
        this.context=context;
    }

    private static DiffUtil.ItemCallback<sold_large_style> diffCallback =new DiffUtil.ItemCallback<sold_large_style>() {
        @Override
        public boolean areItemsTheSame(@NonNull sold_large_style oldItem, @NonNull sold_large_style newItem) {
            if (oldItem.equals(newItem)){
                return true;
            }
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull sold_large_style oldItem, @NonNull sold_large_style newItem) {
            if (oldItem.getSold().getId_sold()==newItem.getSold().getId_sold()){
                return true;
            }
            return false;
        }
    };

    @NonNull
    @Override
    public soldItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sold_large_style, parent, false);
        return new soldItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull soldItemHolder holder, int position) {
        sold_large_style soldLargeStyle=getItem(position);
        sold sold=soldLargeStyle.getSold();
        shop shop=soldLargeStyle.getShop();

        holder.name.setText(sold.getName_sold());
        if (writeFrom(sold)){
            holder.from.setText("From: "+shop.getName_shop());
        }else{
            holder.from.setVisibility(View.GONE);
        }
        if (sold.isBought()){
            holder.checkBox_bought.setChecked(true);
        }
        holder.price.setText(String.valueOf(sold.getPrice())+"$");

        GradientDrawable gradientDrawable= (GradientDrawable) holder.wholeLayout.getBackground();
        gradientDrawable.setColor(ContextCompat.getColor(context,currentCategory.getChosenColorID()));

    }

    private boolean writeFrom(sold sold){
        if (sold.getId_shop_()== com.wellmax8.tobuy.DTO.sold.ID_SHOP_NOT_SPECIFIED){
            return false;
        }
        return true;
    }

    public static class soldItemHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView from;
        CheckBox checkBox_bought;
        TextView price;
        LinearLayout wholeLayout;


        public soldItemHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.item_sold_name);
            from =itemView.findViewById(R.id.item_sold_from);
            checkBox_bought =itemView.findViewById(R.id.checkBought_sold);
            price=itemView.findViewById(R.id.item_sold_price);
            wholeLayout=itemView.findViewById(R.id.wholeLayout);
        }
    }
}
