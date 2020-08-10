package com.nhgia.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView;
import android.widget.Button;
import android.content.Intent;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.tools.jsc.Main;

public class MainActivity extends AppCompatActivity {

    Country currentCountry = new Country("USD", "us", "US Dollar", "10000", 0.000043);
    String myValueInput = "10000";
    String myValueOutput = "10000";

    ListView listView;
    ListView listView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        setContentView(R.layout.act_main_new);

        listView = (ListView) findViewById(R.id.listView1);
        listView2 = (ListView) findViewById(R.id.listView2);

        List<Country> image_details = getListData();
        final CustomListAdapter myAdpt = new CustomListAdapter(this, image_details);
        listView.setAdapter(myAdpt);
        // When the user clicks on the ListItem
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Country country = (Country) o;
                Toast.makeText(MainActivity.this, "Selected :" + " " + country.getCountryName(), Toast.LENGTH_LONG).show();
            }
        });

        CountryList.shared.listCurrencies = getListData2();
        listView2.setAdapter(new CustomListAdapter2(this, CountryList.shared.listCurrencies));

        // When the user clicks on the ListItem
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView2.getItemAtPosition(position);
                Country country = (Country) o;
//                Toast.makeText(MainActivity.this, "Selected :" + " " + country.getCountryName(), Toast.LENGTH_LONG).show();
                currentCountry = country;
                currentCountry.setCurrentValue(myValueInput);
                myAdpt.updateList(getListData());
                myAdpt.notifyDataSetChanged();
                HistoryList.shared.listCurrencies.add(currentCountry);
            }
        });

        // TEXT VIEW
        final TextView textView = findViewById(R.id.textView);
        textView.setText(myValueInput);

        sendAndRequestResponse();

        //BUTTON HANDLER
        final Button button = findViewById(R.id.buttonEqual);
        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                Context rhino = Context.enter();
                rhino.setOptimizationLevel(-1);

                String evaluation = myValueInput;
                try {
                    Scriptable scope = rhino.initStandardObjects();
                    myValueInput = rhino.evaluateString(scope, evaluation, "JavaScript", 1, null).toString();
                    boolean checkChar = myValueInput.matches("^[a-zA-Z]*$");
                    if (myValueInput.equals("Infinity")) {
                        textView.setText("Divide by 0");
                    }
                    else if (checkChar) {
                        textView.setText("Bad expression");
                    }
                    else {
                        textView.setText(myValueInput);
                        currentCountry.setCurrentValue(myValueInput);
                        myAdpt.updateList(getListData());
                        myAdpt.notifyDataSetChanged();
                        HistoryList.shared.listCurrencies.add(currentCountry);
                    }
                }
                catch (Exception e) {
                    textView.setText("Bad expression");
                }
                finally {
                    Context.exit();
                }

            }
        });

        final Button buttonHist = findViewById(R.id.buttonHist);
        buttonHist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, HistoryActivity.class);
                String value = myIntent.getStringExtra("historyActivity");
                myIntent.putExtra("historyActivity",value); //Optional parameters
                MainActivity.this.startActivity(myIntent);
            }
        });

        final Button button0 = findViewById(R.id.button0);
        button0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myValueInput += "0";
                textView.setText(myValueInput);

            }
        });

        final Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myValueInput += "1";
                textView.setText(myValueInput);
            }
        });

        final Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myValueInput += "2";
                textView.setText(myValueInput);
            }
        });

        final Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myValueInput += "3";
                textView.setText(myValueInput);
            }
        });

        final Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myValueInput += "4";
                textView.setText(myValueInput);
            }
        });

        final Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myValueInput += "5";
                textView.setText(myValueInput);
            }
        });

        final Button button6 = findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myValueInput += "6";
                textView.setText(myValueInput);
            }
        });

        final Button button7 = findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myValueInput += "7";
                textView.setText(myValueInput);
            }
        });

        final Button button8 = findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myValueInput += "8";
                textView.setText(myValueInput);
            }
        });

        final Button button9 = findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myValueInput += "9";
                textView.setText(myValueInput);
            }
        });

        final Button buttonClear = findViewById(R.id.button14);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (myValueInput.length() > 0) {
                    myValueInput = myValueInput.substring(0, myValueInput.length() - 1);
                }
                textView.setText(myValueInput);
            }
        });

        final Button buttonDot = findViewById(R.id.buttonDot);
        buttonDot.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myValueInput += ".";
                textView.setText(myValueInput);
            }
        });

        final Button buttonPlus = findViewById(R.id.buttonPlus);
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myValueInput += "+";
                textView.setText(myValueInput);
            }
        });

        final Button buttonMinus = findViewById(R.id.buttonSubtract);
        buttonMinus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myValueInput += "-";
                textView.setText(myValueInput);
            }
        });

        final Button buttonMul = findViewById(R.id.buttonMultiply);
        buttonMul.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myValueInput += "*";
                textView.setText(myValueInput);
            }
        });

        final Button buttonDiv = findViewById(R.id.buttonDevide);
        buttonDiv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myValueInput += "/";
                textView.setText(myValueInput);
            }
        });

    }

    private void sendAndRequestResponse() {

        String url = "http://data.fixer.io/api/latest?access_key=b901367a307a445358cf2a07a15052bc&symbols=VND,RUB,USD,THB,HKD,GBP,GNF";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    Toast.makeText(MainActivity.this, "Data updated, date: " + response.getString("date"), Toast.LENGTH_LONG).show();
                    Double vnRate = response.getJSONObject("rates").getDouble("VND");

                    //Warning - Bad HARD CODE below
                    Double ruRate = response.getJSONObject("rates").getDouble("RUB") / vnRate;
                    Double usRate = response.getJSONObject("rates").getDouble("USD") / vnRate;
                    Double thRate = response.getJSONObject("rates").getDouble("THB") / vnRate;
                    Double hkRate = response.getJSONObject("rates").getDouble("HKD") / vnRate;
                    Double gbRate = response.getJSONObject("rates").getDouble("GBP") / vnRate;
                    Double gnRate = response.getJSONObject("rates").getDouble("GNF") / vnRate;
                    CountryList.shared.listCurrencies.get(0).setConvertRate(ruRate);
                    CountryList.shared.listCurrencies.get(1).setConvertRate(usRate);
                    CountryList.shared.listCurrencies.get(2).setConvertRate(thRate);
                    CountryList.shared.listCurrencies.get(3).setConvertRate(hkRate);
                    CountryList.shared.listCurrencies.get(4).setConvertRate(gbRate);
                    CountryList.shared.listCurrencies.get(5).setConvertRate(gnRate);

                    currentCountry = CountryList.shared.listCurrencies.get(1);

                    CustomListAdapter myAdpt = (CustomListAdapter) listView.getAdapter();
                    myAdpt.updateList(getListData());
                    myAdpt.notifyDataSetChanged();

                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this, "Update error: Bad JSON" , Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Update error: " + error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    private  List<Country> getListData() {
        List<Country> list = new ArrayList<Country>();
        Country vietnam = new Country("VND", "vn", "Vietnamese Dong", myValueInput, 1.0);
        list.add(vietnam);
        list.add(currentCountry);
        return list;
    }

    private  List<Country> getListData2() {
        List<Country> list = new ArrayList<Country>();
        Country ru = new Country("RUB", "ru", "Russian Rouble", myValueInput, 0.0031);
        Country usa = new Country("USD", "us", "US Dollar", myValueInput, 0.000043);
        Country th = new Country("THB", "th", "Thai Bath", myValueInput, 0.001359);
        Country hkd = new Country("HKD", "hk", "Hong Kong Dollar", myValueInput, 0.000334);
        Country hkd2 = new Country("GBP", "gb", "Pound Sterling", myValueInput, 0.000034);
        Country hkd3 = new Country("GNF", "gn", "Guinean Franc", myValueInput, 0.42);

        list.add(ru);
        list.add(usa);
        list.add(th);
        list.add(hkd);
        list.add(hkd2);
        list.add(hkd3);
        return list;
    }
}