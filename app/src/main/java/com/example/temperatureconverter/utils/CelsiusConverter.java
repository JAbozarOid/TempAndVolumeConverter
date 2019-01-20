package com.example.temperatureconverter.utils;

public class CelsiusConverter {

    private int mTemp;
    private double mCelsius;

    public CelsiusConverter(int temp) {
        this.mTemp = temp;
    }

    public double convertKelvinToCelsius(){
        return mCelsius = mTemp - 273.16;
    }

    public double convertFahrenheitToCelsius(){
        return mCelsius = (mTemp - 32) * 5.0/9.0;
    }
}
