package com.sell.utils;

public class MathUtil {

    private static final double MONEY_RANGE=0.01;

    public static boolean equals(Double d1,Double d2){
        Double result=Math.abs(d1-d2);
        if (result<MONEY_RANGE){
            return true;
        }else{
            return false;
        }
    }
}
