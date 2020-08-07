package com.nhgia.currencyconverter;

import java.util.ArrayList;
import java.util.List;

public class HistoryList {

    List<Country> listCurrencies;

    public static HistoryList shared = new HistoryList();
    public HistoryList() {
        this.listCurrencies = new ArrayList<Country>();
    }

    public HistoryList(List<Country> inputList, int currentValue) {
        this.listCurrencies = inputList;
    }

    public void updateCurrentValue(String value) {
        for (Country cou: listCurrencies
        ) {
            cou.setCurrentValue(value);
        }
    }


    @Override
    public void finalize() {
        //Destructor
        this.listCurrencies = null;
    }

}