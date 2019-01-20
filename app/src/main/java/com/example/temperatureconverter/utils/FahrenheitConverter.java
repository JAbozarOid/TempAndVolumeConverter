package com.example.temperatureconverter.utils;

public class FahrenheitConverter {

    private int mTemp;
    private double mFarenheit;

    public FahrenheitConverter(int temp) {
        this.mTemp = temp;
    }

    public double convertCelsiusToFarenhet(){
        return mFarenheit = (9.0/5.0) * mTemp + 32;
    }

    public double convertKelvinToFarenheit(){
        return mFarenheit = (((mTemp - 273) * 9/5) + 32);
    }
}
