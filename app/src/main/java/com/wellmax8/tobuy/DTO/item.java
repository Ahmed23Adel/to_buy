package com.wellmax8.tobuy.DTO;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.wellmax8.tobuy.colors.color;
public abstract class item {

    @PrimaryKey(autoGenerate = true)
    protected int id;
    protected String name;
    protected String description;
    protected String extra;
    protected int chosenColor;
    @Ignore
    protected color color;

    public item(String name, String description, String extra, int chosenColor) {
        this.name = name;
        this.description = description;
        this.extra = extra;
        this.chosenColor = chosenColor;
        this.color = color;
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

    public String getDescription() {
        return description;
    }

    public String getExtra() {
        return extra;
    }

    public int getChosenColor() {
        return chosenColor;
    }
    public color getColor(){
        this.color.setChosenColor(chosenColor);
        return color;
    }
}
