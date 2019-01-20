package com.example.temperatureconverter;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.temperatureconverter.utils.CelsiusConverter;

import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class CelsiusConverterTest {

    public static final String TAG = "Junit";
    private CelsiusConverter celsiusConverter;

    @Test
    public void testCelcius(){
        celsiusConverter = new CelsiusConverter(20);
        Log.i(TAG, "20 farenheit is equal to : " + celsiusConverter.convertFahrenheitToCelsius() + " " + "Celsius");
    }
}
