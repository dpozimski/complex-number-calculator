package com.dpozimski.complex_number_calculator;

import com.jjoe64.graphview.series.DataPoint;

import java.text.DecimalFormat;


public class ComplexNumber {
    private float digit;
    private float complexPart;

    ComplexNumber(float digit, float complexPart){
        this.digit = digit;
        this.complexPart = complexPart;
    }

    public float getDigit() {
        return digit;
    }

    public float getComplexPart() {
        return complexPart;
    }

    public boolean isGreaterThanOrEqualZero(){
        return digit >= 0;
    }

    public DataPoint toDataPoint() {
        return new DataPoint(digit, complexPart);
    }

    public String toString(){
        DecimalFormat leadingSignFormatt = new DecimalFormat("+#,##0.00;-#");
        return String.format("%.2f", digit) + leadingSignFormatt.format(complexPart);
    }
}
