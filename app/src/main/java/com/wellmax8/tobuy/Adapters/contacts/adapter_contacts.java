package com.wellmax8.tobuy.Adapters.contacts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.wellmax8.tobuy.DTO.contact;
import com.wellmax8.tobuy.R;

public class adapter_contacts extends ListAdapter<contact, adapter_contacts.contactItem> {

    public  adapter_contacts() {
        super(diffCallback);
    }
    private onLongItemClicked onLongItemClicked;
    public static  DiffUtil.ItemCallback<contact> diffCallback=new DiffUtil.ItemCallback<contact>() {
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
    public contactItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact_small_style, parent, false);
        return new contactItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull contactItem holder, int position) {
        contact currentContact = getItem(position);
        holder.wholeTextView.setText(getText(currentContact));
        holder.itemView.setOnClickListener(v -> onLongItemClicked.onClick(position));
    }

    private String getText(contact contact) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!contact.getPositionOfNameInCorporation().isEmpty()) {
            stringBuilder.append("("+contact.getPositionOfNameInCorporation()+")");
        }
        stringBuilder.append(" " + contact.getName());
        stringBuilder.append(" (" + contact.getPhoneNumber() + ")");
        return stringBuilder.toString();
    }


    public class contactItem extends RecyclerView.ViewHolder {
        public TextView wholeTextView;

        public contactItem(@NonNull View itemView) {
            super(itemView);
            wholeTextView = itemView.findViewById(R.id.wholeText);
        }
    }

    public interface onLongItemClicked{
        void onClick(int position);
    }

    public void setOnLongItemClicked(adapter_contacts.onLongItemClicked onLongItemClicked) {
        this.onLongItemClicked = onLongItemClicked;
    }
}
