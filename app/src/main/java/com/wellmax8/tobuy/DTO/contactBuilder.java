package com.wellmax8.tobuy.DTO;

public class contactBuilder {
    private String phoneNumber;
    private String name;
    private String positionOfNameInCorporation;
    private String notes;

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
        return new contact(phoneNumber, name, positionOfNameInCorporation, notes);
    }
}