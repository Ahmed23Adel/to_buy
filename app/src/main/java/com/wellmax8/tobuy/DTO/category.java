package com.wellmax8.tobuy.DTO;

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
    private String name_category;

    private String created_at_category;
    private String last_edit_category;

    private String related_to;
    private String description_category;
    private String extra_category;
    private int chosenColor;


    @Ignore
    private color color;

    public category(String name_category, String created_at_category, String last_edit_category, String related_to, String description_category, String extra_category, int chosenColor) {
        this.name_category = name_category;
        this.created_at_category = created_at_category;
        this.last_edit_category = last_edit_category;
        this.related_to = related_to;
        this.description_category = description_category;
        this.extra_category = extra_category;
        this.chosenColor = chosenColor;
        color = new color();
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName_category() {
        return name_category;
    }

    public String getCreated_at_category() {
        return created_at_category;
    }

    public String getLast_edit_category() {
        return last_edit_category;
    }

    public String getRelated_to() {
        return related_to;
    }

    public String getDescription_category() {
        return description_category;
    }

    public String getExtra_category() {
        return extra_category;
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
        Date date = new Date(Long.parseLong(getCreated_at_category()));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM, dd", Locale.getDefault());
        return "Created at: "+simpleDateFormat.format(date).trim();
    }
    public String getCreatedAtDetailedReadable() {
        Date date = new Date(Long.parseLong(getCreated_at_category()));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z", Locale.getDefault());
        return simpleDateFormat.format(date).trim();
    }
    public String getCreatedAtReadableJustTime() {
        Date date = new Date(Long.parseLong(getCreated_at_category()));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM, dd", Locale.getDefault());
        return simpleDateFormat.format(date).trim();
    }

    public String getLastEditReadable() {
        Date date = new Date(Long.parseLong(getLast_edit_category()));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM, dd", Locale.getDefault());
        return "Last edit: "+simpleDateFormat.format(date).trim();
    }

    public String getLastEditDetailedReadable() {
        Date date = new Date(Long.parseLong(getLast_edit_category()));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z", Locale.getDefault());
        return simpleDateFormat.format(date).trim();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        category category = (com.wellmax8.tobuy.DTO.category) obj;
        if (id == category.id && name_category.equals(category.name_category) && created_at_category.equals(category.created_at_category) && last_edit_category.equals(category.last_edit_category)
                && related_to.equals(category.related_to) && description_category.equals(category.description_category)
                && extra_category.equals(category.extra_category) && chosenColor == category.chosenColor
        ) {
            return true;
        } else {
            return false;
        }
    }
}