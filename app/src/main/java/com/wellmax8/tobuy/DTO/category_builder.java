package com.wellmax8.tobuy.DTO;

public class category_builder {
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

    public category_builder setName(String name) {
        this.name = name;
        return this;
    }

    public category_builder setCreated_at(int created_at) {
        this.created_at = created_at;
        return this;
    }

    public category_builder setLast_edit(int last_edit) {
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

    public category_builder setShould_be_reminded(boolean should_be_reminded) {
        this.should_be_reminded = should_be_reminded;
        return this;
    }

    public category_builder setDate_of_reminder(int date_of_reminder) {
        this.date_of_reminder = date_of_reminder;
        return this;
    }

    public category_builder setRemind_every_week(boolean remind_every_week) {
        this.remind_every_week = remind_every_week;
        return this;
    }

    public category_builder setRemind_every_month(boolean remind_every_month) {
        this.remind_every_month = remind_every_month;
        return this;
    }

    public category build() {
        category category = new category(name, created_at, last_edit, related_to, description, extra, chosenColor, should_be_reminded, date_of_reminder, remind_every_week, remind_every_month);
        return category;
    }

}
