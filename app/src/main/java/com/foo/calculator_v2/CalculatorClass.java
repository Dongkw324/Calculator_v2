package com.foo.calculator_v2;

import java.text.DecimalFormat;

public class CalculatorClass {

    public static final String CLEAR_INPUT = "0";

    private double resultNumber = 0;
    private double lastNumber = 0;
    private String operator = "+";
    private String operatorStr = "";

    DecimalFormat decimalFormat;


    public CalculatorClass(DecimalFormat decimalFormat) {
        this.decimalFormat = decimalFormat;
    }

    public CalculatorClass(){
        decimalFormat = new DecimalFormat("###,###.#####");
    }

    public String getDecimal(String number){
        String temp = number.replace(",", "");
        return decimalFormat.format(Double.parseDouble(temp));
    }

    public String getDecimal(Double number){
        return decimalFormat.format(number);
    }

    public String getOperatorStr() {
        return operatorStr;
    }


    public void setClear(){
         resultNumber = 0;
         lastNumber = 0;
         operator = "+";
         operatorStr = "";
    }
    public double doubleResultNumber(double result, double lastInputNumber, String operator){
        switch(operator){
            case "+":
                result += lastInputNumber;
                break;
            case "-":
                result -= lastInputNumber;
                break;
            case "*":
                result *= lastInputNumber;
                break;
            case "/":
                result /= lastInputNumber;
                break;
        }
        return result;
    }

    public String getResult(boolean firstInput, String getResultNumber, String lastOperator) {
        if (firstInput) {
            if(lastOperator.equals("=")){
                resultNumber = doubleResultNumber(resultNumber, lastNumber, operator);
                if(lastNumber == 0 && operator.equals("/")){
                    return "error";
                }
                operatorStr="";
            } else {
                operator = lastOperator;
                if(operatorStr.equals("")){
                    operatorStr = getResultNumber + " " + lastOperator;
                }
                else{
                    operatorStr = operatorStr.substring(0, operatorStr.length() - 1);
                    operatorStr = operatorStr + lastOperator;
                }
            }

        } else {
            lastNumber = Double.parseDouble(getResultNumber.replace(",", ""));
            resultNumber = doubleResultNumber(resultNumber, lastNumber, operator);
            if(lastNumber == 0 && operator.equals("/")){
                return "error";
            }
            if(lastOperator.equals("=")){
                operatorStr = "";
            }
            else{
                operatorStr = operatorStr + " " + getResultNumber + " " + lastOperator;
                operator = lastOperator;
            }
        }

        return getDecimal(resultNumber);
    }
}
