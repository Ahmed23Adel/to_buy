package com.wellmax8.tobuy.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.wellmax8.tobuy.DTO.category;
import com.wellmax8.tobuy.R;
import com.wellmax8.tobuy.View.view_category_details;

public class adapter_categories_smallStyle extends ListAdapter<category, adapter_categories_smallStyle.categoryItem> {

    private Context context;


    public adapter_categories_smallStyle(Context context) {
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
    public adapter_categories_smallStyle.categoryItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_small_style, parent, false);
        return new adapter_categories_smallStyle.categoryItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_categories_smallStyle.categoryItem holder, int position) {
        category category = getItem(position);
        holder.name.setText(category.getName());
        holder.createdAT.setText(category.getCreatedAtReadableJustTime());
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
    }

    public class categoryItem extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView createdAT;
        public LinearLayout textContainer;

        public categoryItem(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_category_name);
            createdAT = itemView.findViewById(R.id.item_category_created_at);
            textContainer = itemView.findViewById(R.id.category_item_background_largeStyle);
        }
    }
}
