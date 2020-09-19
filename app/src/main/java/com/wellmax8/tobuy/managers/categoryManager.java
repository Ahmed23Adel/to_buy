package com.wellmax8.tobuy.managers;

import android.content.Context;
import android.widget.TextView;

import androidx.core.text.HtmlCompat;

import com.wellmax8.tobuy.DTO.category;
import com.wellmax8.tobuy.R;

public class categoryManager {

    private category category;
    private TextView textView;
    private Context context;

    public categoryManager(com.wellmax8.tobuy.DTO.category category, TextView textView, Context context) {
        this.category = category;
        this.textView = textView;
        this.context = context;
    }

    public void showText() {
        textView.setText(HtmlCompat.fromHtml(getTextInHTML(), HtmlCompat.FROM_HTML_MODE_LEGACY));
    }

    public String getTextInHTML() {
        return getName() + getRelatedTo() + getDescription() + getExtra()+getCreatedAt()+getLastEdit();
    }

    private String getName() {
        return getTextLargeAndSmall(context.getString(R.string.name), category.getName());
    }

    private String getRelatedTo() {
        return getTextLargeAndSmall(context.getString(R.string.related_to), category.getRelated_to());
    }

    private String getDescription() {
        if (!category.getDescription().isEmpty()) {
            return getTextLargeAndSmall(context.getString(R.string.description), category.getDescription());
        } else {
            return "";
        }
    }

    private String getCreatedAt() {
        return getTextLargeAndSmall(context.getString(R.string.created_at), category.getCreatedAtDetailedReadable());
    }
    private String getLastEdit() {
        return getTextLargeAndSmall(context.getString(R.string.last_edit), category.getLastEditDetailedReadable());
    }

    private String getExtra() {
        if (!category.getExtra().isEmpty()) {
            return getTextLargeAndSmall(context.getString(R.string.extra), category.getExtra());
        } else {
            return "";
        }
    }

    private String getTextLargeAndSmall(String large, String small) {
        return "<b> " + large + " " + "</b> " + "<small> " + small + " </small> " + "<br/>" + "<br/>";
    }


}
