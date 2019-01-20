package com.example.temperatureconverter.utils;

public class KelvinConverter {

    private int mTemp;
    private double mKelvin;

    public KelvinConverter(int temp) {
        this.mTemp = temp;
    }

    public double convertCelsiusToKelvin(){
        return mKelvin = mTemp + 273.16;
    }

    public double convertFarenheitToKelvin(){
        return mKelvin = (5/9 * (mTemp - 32) + 273);
    }
}
