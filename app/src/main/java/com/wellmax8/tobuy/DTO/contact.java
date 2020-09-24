package com.wellmax8.tobuy.DTO;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity()
public class contact {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String phoneNumber;
    private String name;
    private String positionOfNameInCorporation;
    private String notes;

    public contact(String phoneNumber, String name, String positionOfNameInCorporation, String notes) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.positionOfNameInCorporation = positionOfNameInCorporation;
        this.notes = notes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPositionOfNameInCorporation() {
        return positionOfNameInCorporation;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof contact)) return false;
        contact contact = (contact) o;
        return getId() == contact.getId() &&
                getPhoneNumber().equals(contact.getPhoneNumber()) &&
                getName().equals(contact.getName()) &&
                getPositionOfNameInCorporation().equals(contact.getPositionOfNameInCorporation()) &&
                getNotes().equals(contact.getNotes());
    }



    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPositionOfNameInCorporation(String positionOfNameInCorporation) {
        this.positionOfNameInCorporation = positionOfNameInCorporation;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
