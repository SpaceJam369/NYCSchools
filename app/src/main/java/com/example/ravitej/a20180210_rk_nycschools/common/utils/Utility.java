package com.example.ravitej.a20180210_rk_nycschools.common.utils;

public class Utility {

    public static String convertStringToInt(String toConvert){
        String convertedValue = null;
        try{
            convertedValue = String.valueOf((Integer.parseInt(toConvert)));
        }catch (NumberFormatException e){}

        return convertedValue;
    }
}
