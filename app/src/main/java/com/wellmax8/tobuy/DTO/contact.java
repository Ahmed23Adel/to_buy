package com.wellmax8.tobuy.DTO;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

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
    public boolean equals(@Nullable Object obj) {
        contact contact=(com.wellmax8.tobuy.DTO.contact)obj;
        if(id==contact.getId()&&phoneNumber.equals(contact.getPhoneNumber())&&name.equals(contact.getName())&&positionOfNameInCorporation.equals(contact.getPositionOfNameInCorporation())&&notes.equals(contact.getNotes())){
            return true;
        }
        return false;
    }
}
