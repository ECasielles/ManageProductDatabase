package com.example.usuario.manageproductsdatabase.activity;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.example.usuario.manageproductsdatabase.R;

public class AccountSettingsActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.account_settings);
    }
}
