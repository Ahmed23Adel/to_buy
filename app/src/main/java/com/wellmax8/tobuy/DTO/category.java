package com.wellmax8.tobuy.DTO;

import android.app.AlertDialog.Builder;
import android.net.Uri;
import android.provider.BaseColumns;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

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

    private boolean should_be_reminded;
    private int date_of_reminder;
    private boolean remind_every_week;
    private boolean remind_every_month;

    public category(String name, int created_at, int last_edit, String related_to, String description, String extra, int chosenColor, boolean should_be_reminded, int date_of_reminder, boolean remind_every_week, boolean remind_every_month) {
        this.name = name;
        this.created_at = created_at;
        this.last_edit = last_edit;
        this.related_to = related_to;
        this.description = description;
        this.extra = extra;
        this.chosenColor = chosenColor;
        this.should_be_reminded = should_be_reminded;
        this.date_of_reminder = date_of_reminder;
        this.remind_every_week = remind_every_week;
        this.remind_every_month = remind_every_month;
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

    public int getChosenColor() {
        return chosenColor;
    }

    public boolean isShould_be_reminded() {
        return should_be_reminded;
    }

    public int getDate_of_reminder() {
        return date_of_reminder;
    }

    public boolean isRemind_every_week() {
        return remind_every_week;
    }

    public boolean isRemind_every_month() {
        return remind_every_month;
    }



}
