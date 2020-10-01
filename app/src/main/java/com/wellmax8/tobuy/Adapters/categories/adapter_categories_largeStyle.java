package com.wellmax8.tobuy.Adapters.categories;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.wellmax8.tobuy.DTO.category;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.wellmax8.tobuy.R;
import com.wellmax8.tobuy.View.solds;
import com.wellmax8.tobuy.View.viewDetails.view_category_details;

public class adapter_categories_largeStyle extends ListAdapter<category, adapter_categories_largeStyle.categoryItem> {

    private Context context;
    private int nAdded=0;


    public adapter_categories_largeStyle(Context context) {
        super(diffCallback);
        this.context = context;
    }

    private static final DiffUtil.ItemCallback<category> diffCallback = new DiffUtil.ItemCallback<category>() {
        @Override
        public boolean areItemsTheSame(@NonNull category oldItem, @NonNull category newItem) {
            if (oldItem.getId() == newItem.getId()) {
                return true;
            }
            return false;

        }

        @Override
        public boolean areContentsTheSame(@NonNull category oldItem, @NonNull category newItem) {
            if (oldItem.equals(newItem)) {
                return true;
            }
            return false;

        }
    };

    @NonNull
    @Override
    public categoryItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_large_style, parent, false);
        return new categoryItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull categoryItem holder, int position) {
        category category = getItem(position);
        holder.name.setText(category.getName_category());
        if (category.getRelated_to().isEmpty()) {
            holder.relatedTo.setVisibility(View.GONE);
        }
        holder.relatedTo.setText(category.getRelated_to());
        holder.createdAT.setText(category.getCreatedAtReadable());
        holder.lastEdit.setText(category.getLastEditReadable());
        GradientDrawable gradientDrawable = (GradientDrawable) holder.textContainer.getBackground();
        gradientDrawable.setColor(ContextCompat.getColor(context, category.getChosenColorID()));
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent= new Intent(context, view_category_details.class);
                intent.putExtra(context.getString(R.string.pos),position);
                intent.putExtra(context.getString(R.string.id),category.getId());
                context.startActivity(intent);
                return false;
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, solds.class);
                intent.putExtra(context.getString(R.string.pos),position);
                intent.putExtra(context.getString(R.string.id),category.getId());
                context.startActivity(intent);
            }
        });

    }

    public class categoryItem extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView relatedTo;
        public TextView createdAT;
        public TextView lastEdit;
        public LinearLayout textContainer;
        public int position;

        public categoryItem(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_category_name);
            relatedTo = itemView.findViewById(R.id.item_category_relate_to);
            createdAT = itemView.findViewById(R.id.item_category_created_at);
            lastEdit = itemView.findViewById(R.id.item_category_lastEdit);
            textContainer = itemView.findViewById(R.id.category_item_background_largeStyle);

        }
    }

    public void newItemAdded(){
        nAdded++;
    }

}
