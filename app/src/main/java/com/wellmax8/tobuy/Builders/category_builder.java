package com.wellmax8.tobuy.Builders;

import com.wellmax8.tobuy.DTO.category;

public class category_builder {
    private int id;
    private String name;

    private String created_at;
    private String last_edit;

    private String related_to;
    private String description;
    private String extra;
    private int chosenColor;



    public category_builder setName(String name) {
        this.name = name;
        return this;
    }

    public category_builder setCreated_at(String created_at) {
        this.created_at = created_at;
        return this;
    }

    public category_builder setLast_edit(String last_edit) {
        this.last_edit = last_edit;
        return this;
    }

    public category_builder setRelated_to(String related_to) {
        this.related_to = related_to;
        return this;
    }

    public category_builder setDescription(String description) {
        this.description = description;
        return this;
    }

    public category_builder setExtra(String extra) {
        this.extra = extra;
        return this;
    }

    public category_builder setChosenColor(int chosenColor) {
        this.chosenColor = chosenColor;
        return this;
    }



    public category build() {
        category category = new category(name, created_at, last_edit, related_to, description, extra, chosenColor);
        return category;
    }

}
