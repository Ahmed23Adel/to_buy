package com.wellmax8.tobuy.DTO;

import com.wellmax8.tobuy.ViewModel.VM_solds;

import java.util.ArrayList;

public class contact_saved_or_addedTo_shopContact {

    //it will send either state=new id=-1(ID_NOT_SPECIFIED) then it will insert New id ;


    private int id;
    private String phoneNumber;
    private String name;
    private String positionOfNameInCorporation;
    private String notes;

    public  static int STATE_OLD=1;
    public static int STATE_NEW=2;
    private int state=-1;
    public static int ID_NOT_SPECIFIED=-1;

    private VM_solds VM;

    public contact_saved_or_addedTo_shopContact(VM_solds VM) {
        this.VM=VM;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setSTATE_OLD(){
        state=STATE_OLD;
    }

    public void setSTATE_NEW() {
        state=STATE_NEW;
    }

    public int getState() {
        return state;
    }

    public boolean isNew(int state){
        if (state==STATE_NEW){
            return true;
        }
        return false;
    }
    public void saveToDB(){
        if (state==STATE_NEW){
            ArrayList<contact> contacts=new ArrayList<>();
            contact contact=new contactBuilder()
                    .setName(name)
                    .setPhoneNumber(phoneNumber)
                    .setPositionOfNameInCorporation(positionOfNameInCorporation)
                    .setNotes(notes)
                    .build();
            contacts.add(contact);
            VM.insertContacts(contacts);
        }
    }
}
