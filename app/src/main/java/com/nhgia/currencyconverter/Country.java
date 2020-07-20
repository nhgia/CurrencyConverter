package com.nhgia.currencyconverter;


public class Country {

    private String countryName;

    // Image name (Without extension)
    private String flagName;
    private String currency;
    private double convertRate;
    private String currentValue;


    public Country(String countryName, String flagName, String currency, String currentValue, double convertRate) {
        this.countryName = countryName;
        this.flagName = flagName;
        this.currency = currency;
        this.currentValue = currentValue;
        this.convertRate = convertRate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(String currentValue) {
        this.currentValue = currentValue;
    }

    public double getConvertRate() {
        return convertRate;
    }

    public void setConvertRate(double convertRate) {
        this.convertRate = convertRate;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getFlagName() {
        return flagName;
    }

    public void setFlagName(String flagName) {
        this.flagName = flagName;
    }

    @Override
    public String toString()  {
        return "";
    }
}