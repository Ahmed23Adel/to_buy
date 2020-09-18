package com.wellmax8.tobuy.SharedPreference;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.wellmax8.tobuy.R;

public class sharedPref extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.com_wellmax8_tobuy,rootKey);
    }


}
