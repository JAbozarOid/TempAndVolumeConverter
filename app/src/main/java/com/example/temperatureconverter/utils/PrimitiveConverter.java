package com.example.temperatureconverter.utils;

import android.util.Log;

public class PrimitiveConverter {

    private String mText;

    public PrimitiveConverter() {
    }

    public PrimitiveConverter(String mText) {
        this.mText = mText;
    }

    public int convertStringToInt() {
        int num = 0;
        if (mText.isEmpty()) {
            return 0;
        } else {
            try {
                num = Integer.parseInt(mText);
            } catch (NumberFormatException e) {
                Log.e("convertStringToInt: ", e.getMessage());
            }
            return num;
        }

    }

    public String convertDoubleToString(double num) {
        String sDoubleToString = "";
        if (num == 0) {
            return "";
        } else {
            try {
                sDoubleToString = String.valueOf(num);
            } catch (NumberFormatException e) {
                Log.e("convertDoubleToString: ", e.getMessage());
            }
        }
        return sDoubleToString;
    }
}
