package com.wellmax8.tobuy.DTO;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.wellmax8.tobuy.Exceptions.colorNotSpecifiedException;
import com.wellmax8.tobuy.colors.color;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Entity()
public class category {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;

    private String created_at;
    private String last_edit;

    private String related_to;
    private String description;
    private String extra;
    private int chosenColor;


    @Ignore
    private color color;

    public category(String name, String created_at, String last_edit, String related_to, String description, String extra, int chosenColor) {
        this.name = name;
        this.created_at = created_at;
        this.last_edit = last_edit;
        this.related_to = related_to;
        this.description = description;
        this.extra = extra;
        this.chosenColor = chosenColor;
        color = new color();
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getLast_edit() {
        return last_edit;
    }

    public String getRelated_to() {
        return related_to;
    }

    public String getDescription() {
        return description;
    }

    public String getExtra() {
        return extra;
    }

    public color getColor() {
        color.setChosenColor(chosenColor);
        return color;
    }

    public int getChosenColor() {
        return chosenColor;

    }

    public int getChosenColorID() {
        try {
            return getColor().getChosenColorID();
        } catch (colorNotSpecifiedException e) {
            color.setChosenColor(color.RED);
            return getChosenColorID();
        }
    }

    public String getCreatedAtReadable() {
        Date date = new Date(Long.parseLong(getCreated_at()));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM, dd", Locale.getDefault());
        return simpleDateFormat.format(date).trim();
    }

    public String getLastEditReadable() {
        Date date = new Date(Long.parseLong(getLast_edit()));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM, dd", Locale.getDefault());
        return simpleDateFormat.format(date).trim();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        category category = (com.wellmax8.tobuy.DTO.category) obj;
        if (id == category.id && name.equals(category.name) && created_at.equals(category.created_at) && last_edit.equals(category.last_edit)
                && related_to.equals(category.related_to) && description.equals(category.description)
                && extra.equals(category.extra) && chosenColor == category.chosenColor
        ) {
            return true;
        } else {
            return false;
        }
    }
}