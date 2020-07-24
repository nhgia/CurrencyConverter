package com.nhgia.currencyconverter;

import java.util.ArrayList;
import java.util.List;

public class CurrenciesList {

    List<Country> listCurrencies;
    List<Country> listNotChoose;

    public static CurrenciesList shared = new CurrenciesList();
    public CurrenciesList() {
        List<Country> myLs = new ArrayList<Country>();
        Country addBtnLs = new Country("add", "ru", "_", "0", 1);
        myLs.add(addBtnLs);
        this.listCurrencies = myLs;
        this.listNotChoose = getListInit();
    }

    public CurrenciesList(List<Country> inputList) {
        this.listCurrencies = inputList;
        this.listNotChoose = inputList;
    }

    public void updateCurrentValue(String value) {
        for (Country cou: listCurrencies
             ) {
            cou.setCurrentValue(value);
        }
        for (Country cou: listNotChoose
             ) {
            cou.setCurrentValue(value);
        }
    }

    private List<Country> getListInit() {
        List<Country> list = new ArrayList<Country>();
        Country ru = new Country("RUB", "ru", "Russian Rouble", "0", 0.0031);
        Country usa = new Country("USD", "us", "US Dollar", "0", 0.000043);
        Country th = new Country("THB", "th", "Thai Bath", "0", 0.001359);
        Country hkd = new Country("HKD", "hk", "Hong Kong Dollar", "0", 0.000334);
        Country hkd2 = new Country("GBP", "gb", "Pound Sterling", "0", 0.000034);
        Country hkd3 = new Country("GNF", "gn", "Guinean Franc", "0", 0.42);

        list.add(ru);
        list.add(usa);
        list.add(th);
        list.add(hkd);
        list.add(hkd2);
        list.add(hkd3);
        return list;
    }


    @Override
    public void finalize() {
        //Destructor
        this.listCurrencies = null;
        this.listNotChoose = null;
    }

}