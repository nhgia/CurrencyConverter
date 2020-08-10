package com.nhgia.currencyconverter;

import java.util.ArrayList;
import java.util.List;

public class CountryList {

    List<Country> listCurrencies;

    public static CountryList shared = new CountryList();
    public CountryList() {
        this.listCurrencies = new ArrayList<Country>();
    }

    public CountryList(List<Country> inputList, int currentValue) {
        this.listCurrencies = inputList;
    }

    public void updateCurrentValue(String value) {
        for (Country cou: listCurrencies
        ) {
            cou.setCurrentValue(value);
        }
    }

    public void updateConvertRate(List<String> myLsString) {
        // WARNING - HARD CODE BELOW

    }


    @Override
    public void finalize() {
        //Destructor
        this.listCurrencies = null;
    }

}
