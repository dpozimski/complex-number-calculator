package com.dpozimski.complex_number_calculator;

public class ComplexNumberCalculator {
    public ComplexNumber add(ComplexNumber first, ComplexNumber second){
        float digit = first.getDigit() + second.getDigit();
        float complexPart = first.getComplexPart() + second.getComplexPart();

        return new ComplexNumber(digit, complexPart);
    }
    public ComplexNumber odd(ComplexNumber first, ComplexNumber second){
        float digit = first.getDigit() - second.getDigit();
        float complexPart = first.getComplexPart() - second.getComplexPart();

        return new ComplexNumber(digit, complexPart);
    }
}
