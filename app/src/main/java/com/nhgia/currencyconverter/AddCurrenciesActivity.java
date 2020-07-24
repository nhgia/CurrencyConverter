package com.nhgia.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddCurrenciesActivity extends AppCompatActivity {

    private String myValueInput = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_currencies);

        setTitle(" ");
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000b62")));


        List<Country> image_details = getListData();
        final CurrenciesCustomListAdapter myAdpt = new CurrenciesCustomListAdapter(this, image_details);
        final ListView listView = (ListView) findViewById(R.id.listViewCurrencies);
        listView.setAdapter(myAdpt);
        // When the user clicks on the ListItem
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Country country = (Country) o;
                Toast.makeText(AddCurrenciesActivity.this, "Selected :" + " " + country.getCountryName(), Toast.LENGTH_LONG).show();
                int index = CurrenciesList.shared.listNotChoose.indexOf(country);
                CurrenciesList.shared.listNotChoose.remove(index);
                CurrenciesList.shared.listCurrencies.add(0, country);
                onSupportNavigateUp();
            }
        });
    }

    private  List<Country> getListData() {
        return CurrenciesList.shared.listNotChoose;
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        this.overridePendingTransition(R.anim.left_to_right_back, R.anim.right_to_left_back);
        return true;
    }

    @Override
    public void onBackPressed() {
//        int entryCount = getSupportFragmentManager().getBackStackEntryCount();
//        if (entryCount > 0){
//            super.onBackPressed();
//        }
//        else{
//            finish();
//        }

        super.onBackPressed();
        this.overridePendingTransition(R.anim.left_to_right_back, R.anim.right_to_left_back);
    }
}