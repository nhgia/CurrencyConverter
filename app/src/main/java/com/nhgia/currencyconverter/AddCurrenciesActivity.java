package com.nhgia.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class AddCurrenciesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_currencies);
    }
}