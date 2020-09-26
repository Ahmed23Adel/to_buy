package com.wellmax8.tobuy.Builders;

import com.wellmax8.tobuy.DTO.contact;

public class contactBuilder {

    private int id;
    private String phoneNumber;
    private String name;
    private String positionOfNameInCorporation;
    private String notes;

    public contactBuilder setId(String id) {
        this.id = Integer.parseInt(id);
        return this;
    }

    public contactBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public contactBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public contactBuilder setPositionOfNameInCorporation(String positionOfNameInCorporation) {
        this.positionOfNameInCorporation = positionOfNameInCorporation;
        return this;
    }

    public contactBuilder setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public contact build() {
        contact contact=new contact(phoneNumber, name, positionOfNameInCorporation, notes);
        contact.setId(id);
        return contact;
    }
}