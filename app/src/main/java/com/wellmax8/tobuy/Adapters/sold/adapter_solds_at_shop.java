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
import com.wellmax8.tobuy.DTO.sold;
import com.wellmax8.tobuy.DTO.sold_at_shopId;
import com.wellmax8.tobuy.R;

public class adapter_solds_at_shop extends ListAdapter<sold_at_shopId, adapter_solds_at_shop.soldItemHolder> {

    private Context context;

    public adapter_solds_at_shop(Context context) {
        super(diffCallback);
        this.context = context;
    }

    private static DiffUtil.ItemCallback<sold_at_shopId> diffCallback = new DiffUtil.ItemCallback<sold_at_shopId>() {
        @Override
        public boolean areItemsTheSame(@NonNull sold_at_shopId oldItem, @NonNull sold_at_shopId newItem) {
            if (oldItem.equals(newItem)) {
                return true;
            }
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull sold_at_shopId oldItem, @NonNull sold_at_shopId newItem) {
            if (oldItem.getSold().getId_sold() == newItem.getSold().getId_sold()) {
                return true;
            }
            return false;
        }
    };

    @NonNull
    @Override
    public soldItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sold_at_shop, parent, false);
        return new soldItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull soldItemHolder holder, int position) {
        sold_at_shopId soldAtShopId=getItem(position);
        sold currentSold =soldAtShopId.getSold();
        category currentCategory=soldAtShopId.getCategory();

        holder.name.setText(currentSold.getName_sold());

        if (currentSold.isBought()) {
            holder.checkBox_bought.setChecked(true);
        }

        holder.price.setText(String.valueOf(currentSold.getPrice()) + "$");

        GradientDrawable gradientDrawable = (GradientDrawable) holder.wholeLayout.getBackground();
        gradientDrawable.setColor(ContextCompat.getColor(context, currentCategory.getChosenColorID()));

    }
    

    public static class soldItemHolder extends RecyclerView.ViewHolder {
        TextView name;
        CheckBox checkBox_bought;
        TextView price;
        LinearLayout wholeLayout;


        public soldItemHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_sold_name);
            checkBox_bought = itemView.findViewById(R.id.checkBought_sold);
            price = itemView.findViewById(R.id.item_sold_price);
            wholeLayout = itemView.findViewById(R.id.wholeLayout);
        }
    }
}
