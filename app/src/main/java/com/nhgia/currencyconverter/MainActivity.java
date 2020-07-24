package com.nhgia.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView;
import android.widget.Button;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity {

    Country currentCountry = new Country("USD", "us", "US Dollar", "10000", 0.000043);
    String myValueInput = "10000";
    String myValueOutput = "10000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        setContentView(R.layout.act_main_new);

        List<Country> image_details = getListData();
        final CustomListAdapter myAdpt = new CustomListAdapter(this, image_details);
        final ListView listView = (ListView) findViewById(R.id.listView1);
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

        List<Country> image_details2 = getListData2();
        final CustomListAdapter2 myAdpt2 = new CustomListAdapter2(this, image_details2);
        final ListView listView2 = (ListView) findViewById(R.id.listView2);
        listView2.setAdapter(myAdpt2);

        // When the user clicks on the ListItem
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                //
            }
        });

        // TEXT VIEW
        final TextView textView = findViewById(R.id.textView);
        textView.setText(myValueInput);

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
                        myAdpt2.updateList(getListData2());
                        myAdpt2.notifyDataSetChanged();
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

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (data != null) {
//            Toast.makeText(MainActivity.this, "sbvafsbs :" , Toast.LENGTH_LONG).show();
//        }
//    }


    @Override
    protected void onResume() {
        super.onResume();
        final ListView listView = (ListView) findViewById(R.id.listView2);
        final CustomListAdapter2 adpt = (CustomListAdapter2) listView.getAdapter();

        adpt.updateList(getListData2());
        adpt.notifyDataSetChanged();
    }

    private  List<Country> getListData() {
        List<Country> list = new ArrayList<Country>();
        Country vietnam = new Country("VND", "vn", "Vietnamese Dong", myValueInput, 1.0);
        list.add(vietnam);
        //list.add(currentCountry);
        return list;
    }

    private  List<Country> getListData2() {
        CurrenciesList.shared.updateCurrentValue(myValueInput);
        List<Country> list = new ArrayList<Country>(CurrenciesList.shared.listCurrencies);
        return list;
    }

}