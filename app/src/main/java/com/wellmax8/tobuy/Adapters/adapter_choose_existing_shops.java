package com.wellmax8.tobuy.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.wellmax8.tobuy.DTO.shop;
import com.wellmax8.tobuy.R;


public class adapter_choose_existing_shops extends ListAdapter<shop, adapter_choose_existing_shops.shopHolder> {


    private Context context;
    private OnItemClick onItemClick;
    public adapter_choose_existing_shops(Context context) {
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
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_choose_existing_shop, parent, false);
        return new shopHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull shopHolder holder, int position) {
        shop currentShop=getItem(position);
        holder.name.setText(currentShop.getName_shop());

        if (!currentShop.getAddress().isEmpty()) {
            holder.address.setText(currentShop.getAddress());
        } else {
            holder.address.setVisibility(View.GONE);
        }
        if (currentShop.getFacebookLink().isEmpty()) {
            holder.facebookLink.setVisibility(View.GONE);
        }else {
            holder.facebookLink.setOnClickListener(v -> {
                Intent faceIntent = new Intent(Intent.ACTION_VIEW);
                faceIntent.setData(Uri.parse(currentShop.getFacebookLink()));
                if (faceIntent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(faceIntent);
                }
            });
        }

        holder.viewUsage.setOnClickListener(v -> {
            Toast.makeText(context, "View usage", Toast.LENGTH_SHORT).show();
        });

        holder.itemView.setOnClickListener(v -> {
           onItemClick.onClick(currentShop.getId_shop(),currentShop.getName_shop(),currentShop.getAddress());
        });

    }

    public static class shopHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView address;
        ImageView facebookLink;
        TextView viewUsage;

        public shopHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_existing_shop_name);
            address = itemView.findViewById(R.id.item_existing_shop_address);
            facebookLink = itemView.findViewById(R.id.item_existing_shop_facebookLink);
            viewUsage=itemView.findViewById(R.id.item_existing_shop_viewUsage);
        }
    }

    public interface OnItemClick{
        void onClick(int id_shop,String name,String address);
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }
}
