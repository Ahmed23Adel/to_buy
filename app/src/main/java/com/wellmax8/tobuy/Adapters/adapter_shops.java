package com.wellmax8.tobuy.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.wellmax8.tobuy.DTO.shop;
import com.wellmax8.tobuy.R;

import java.util.List;

public class adapter_shops extends ListAdapter<shop, adapter_shops.shopHolder> {

    private Context context;
    public adapter_shops(Context context) {
        super(diffCallback);
        this.context=context;
    }

    private static DiffUtil.ItemCallback<shop> diffCallback=new DiffUtil.ItemCallback<shop>() {
        @Override
        public boolean areItemsTheSame(@NonNull shop oldItem, @NonNull shop newItem) {
            if (oldItem.equals(newItem)){
                return true;
            }
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull shop oldItem, @NonNull shop newItem) {
            if (oldItem.getId_shop()==newItem.getId_shop()){
                return true;
            }
            return false;
        }
    };

    @NonNull
    @Override
    public shopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop, parent, false);
        return new shopHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull shopHolder holder, int position) {
        shop currentShop = getItem(position);
        holder.name.setText(currentShop.getName_shop());
        if (!currentShop.getAddress().isEmpty()) {
            holder.address.setText(currentShop.getAddress());
        } else {
            holder.address.setVisibility(View.GONE);
        }

        if (currentShop.getFacebookLink().isEmpty()) {
            holder.facebookLink.setVisibility(View.GONE);
        }
        holder.facebookLink.setOnClickListener(v -> {
            Intent faceIntent=new Intent(Intent.ACTION_VIEW);
            faceIntent.setData(Uri.parse(currentShop.getFacebookLink()));
            if (faceIntent.resolveActivity(context.getPackageManager())!=null){
                context.startActivity(faceIntent);
            }
        });
    }


    public static class shopHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView address;
        ImageView facebookLink;

        public shopHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_shop_name);
            address = itemView.findViewById(R.id.item_shop_address);
            facebookLink = itemView.findViewById(R.id.item_shop_facebookLink);
        }
    }
}
