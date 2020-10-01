package com.wellmax8.tobuy.Adapters.contacts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.wellmax8.tobuy.DTO.contact;
import com.wellmax8.tobuy.R;

public class adapter_contacts_large_style extends ListAdapter<contact, adapter_contacts_large_style.contactHolder> {

    public adapter_contacts_large_style() {
        super(diffCallback);
    }

    private static DiffUtil.ItemCallback<contact> diffCallback=new DiffUtil.ItemCallback<contact>() {
        @Override
        public boolean areItemsTheSame(@NonNull contact oldItem, @NonNull contact newItem) {
            if (oldItem.equals(newItem)){
                return true;
            }
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull contact oldItem, @NonNull contact newItem) {
            if (oldItem.getId()==newItem.getId()){
                return true;
            }
            return false;
        }
    };
    @NonNull
    @Override
    public contactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new contactHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull contactHolder holder, int position) {
        contact currentContact=getItem(position);
        holder.name.setText(currentContact.getName());
        if (!currentContact.getPositionOfNameInCorporation().isEmpty()){
            holder.position.setText(currentContact.getPositionOfNameInCorporation());
        }else{
            holder.position.setVisibility(View.GONE);
        }
    }

    public static class contactHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView position;
        ImageView callNow;
        public contactHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.item_contact_name);
            position=itemView.findViewById(R.id.item_contact_position);
            callNow=itemView.findViewById(R.id.callNow);
        }
    }
}
