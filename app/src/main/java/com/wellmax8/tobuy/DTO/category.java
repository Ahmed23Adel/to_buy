package com.wellmax8.tobuy.DTO;

import android.app.AlertDialog.Builder;
import android.net.Uri;
import android.provider.BaseColumns;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.wellmax8.tobuy.colors.color;

@Entity()
public class category {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;

    private int created_at;
    private int last_edit;

    private String related_to;
    private String description;
    private String extra;
    private int chosenColor;


    @Ignore
    private color color;

    public category(String name, int created_at, int last_edit, String related_to, String description, String extra, int chosenColor) {
        this.name = name;
        this.created_at = created_at;
        this.last_edit = last_edit;
        this.related_to = related_to;
        this.description = description;
        this.extra = extra;
        this.chosenColor = chosenColor;
        color =new color();
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

    public int getCreated_at() {
        return created_at;
    }

    public int getLast_edit() {
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
}
