package com.wellmax8.tobuy.Time;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.wellmax8.tobuy.DTO.sold;
import com.wellmax8.tobuy.Exceptions.DateNotSpecifiedException;

import java.util.Calendar;
import java.util.Date;

public class DatePicker extends DialogFragment {

    private int FIELD_NOT_SET=-1;

    private int lastChosenYear =FIELD_NOT_SET;
    private int lastChosenMonth =FIELD_NOT_SET;
    private int lastChosenDayOfMonth =FIELD_NOT_SET;

    private CheckBox isBought;

    public DatePicker(CheckBox isBought) {
        this.isBought = isBought;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();

        return new DatePickerDialog(getActivity(), onDateSetListener, calendar.get(calendar.YEAR), calendar.get(calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

      private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
            lastChosenYear = year;
            lastChosenMonth = month;
            lastChosenDayOfMonth = dayOfMonth;
        }
    };

    public String getDate(){
        if (isBought.isSelected()){
            try {
                Date date=getDateChosen();
                return getDateInUnix(date);
            } catch (DateNotSpecifiedException e) {
                return getCurrentTime();
            }
        }else{
            return sold.TIME_BUY_NOT_SPECIFIED;
        }

    }



    private Date getDateChosen() throws DateNotSpecifiedException {
        if (lastChosenYear==FIELD_NOT_SET){
            throw new DateNotSpecifiedException();
        }
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.YEAR,lastChosenYear);
        calendar.set(Calendar.MONTH,lastChosenMonth);
        calendar.set(Calendar.DAY_OF_MONTH,lastChosenDayOfMonth);
        return calendar.getTime();
    }

    private String getDateInUnix(Date date){
        return String.valueOf(date.getTime());
    }

    private String getCurrentTime(){
        Long currentTime=System.currentTimeMillis();
        return currentTime.toString();
    }



}
