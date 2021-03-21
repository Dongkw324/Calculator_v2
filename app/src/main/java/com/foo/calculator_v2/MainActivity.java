package com.foo.calculator_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private boolean firstInput = true; //첫 입력은 무조건 true

    ScrollView scrollView;
    TextView resultTextView, historyTextView;
    Button clearButton, entryClearButton, backSpaceButton, decimalButton;

    Button[] number_btn = new Button[10];
    Button[] operator_btn = new Button[5];

    CalculatorClass calculatorClass = new CalculatorClass(new DecimalFormat("###,###.#############"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView = findViewById(R.id.scroll_View);
        resultTextView = findViewById(R.id.result_textView);
        historyTextView = findViewById(R.id.history_textView);

        clearButton = findViewById(R.id.btn_clear);
        entryClearButton = findViewById(R.id.btn_entry_clear);
        backSpaceButton = findViewById(R.id.btn_back_space);
        decimalButton = findViewById(R.id.btn_decimal);


        for (int i = 0; i < number_btn.length; i++) {
            number_btn[i] = findViewById(R.id.number_btn_0 + i);
        }

        for (int i = 0; i < operator_btn.length; i++) {
            operator_btn[i] = findViewById(R.id.operator_0 + i);
        }

        for(Button button : number_btn){
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    numberClick(view);
                }
            });
        }

        for(Button button: operator_btn){
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    operatorClick(view);
                }
            });
        }

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearClick(view);
            }
        });

        entryClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                entryClearClick(view);
            }
        });

        backSpaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backSpaceClick(view);
            }
        });

        decimalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decimalClick(view);
            }
        });
    }

    private void decimalClick(View view) {
        if(firstInput){
            resultTextView.setTextColor(getResources().getColor(R.color.white));
            resultTextView.setText("0.");
            firstInput = false;
        } else {
            if(resultTextView.getText().toString().contains(".")){
                return;
            } else {
                resultTextView.append(".");
            }
        }
    }

    private void backSpaceClick(View view) {
        if (firstInput && !calculatorClass.getOperatorStr().equals("")) {
            return;
        } else {
            if(resultTextView.getText().toString().length() > 1){
                String getNumber = resultTextView.getText().toString().replace(",", "");
                String subString = getNumber.substring(0, getNumber.length() -1);
                String decimalNumber = calculatorClass.getDecimal(subString);
                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O){
                    resultTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, getSize(decimalNumber));
                }
                resultTextView.setText(decimalNumber);
            } else{
                clearData();
            }
        }
    }

    private void entryClearClick(View view) {
        clearData();
    }

    private void clearClick(View view) {
        calculatorClass.setClear();
        historyTextView.setText(calculatorClass.getOperatorStr());
        clearData();
    }

    private void operatorClick(View view) {
        String getResultNumber = resultTextView.getText().toString();
        String operator = view.getTag().toString();
        String getNumber = calculatorClass.getResult(firstInput, getResultNumber, operator);
        if(getNumber.equals("error")){
            resultTextView.setText("오류");
            for(int i=0;i<operator_btn.length;i++){
                operator_btn[i].setClickable(false);
            }
            decimalButton.setClickable(false);

            for(int i=0;i<number_btn.length;i++){
                number_btn[i].setClickable(false);
            }
            return;
        }
        resultTextView.setText(getNumber);
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O){
            resultTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, getSize(getNumber));
        }
        historyTextView.setText(calculatorClass.getOperatorStr());
        firstInput = true;
    }

    private void numberClick(View view) {
        if(firstInput){
            resultTextView.setTextColor(getResources().getColor(R.color.white));
            resultTextView.setText(view.getTag().toString());
            firstInput = false;
        }
        else{
            String number = resultTextView.getText().toString().replace(",", "");
            if(number.length() > 15){
                return;
            } else{
                number = number + view.getTag().toString();
                String getNumberString = calculatorClass.getDecimal(number);
                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O){
                    resultTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, getSize(getNumberString));
                }
                resultTextView.setText(getNumberString);
            }
        }
    }

    private int getSize(String getNumberString) {
        if(getNumberString.length() > 30){
            return 30;
        } else if(getNumberString.length() > 25){
            return 25;
        } else if(getNumberString.length() > 20){
            return 35;
        } else if(getNumberString.length() > 15){
            return 30;
        }
        return 50;
    }


    private void clearData() {
        firstInput = true;
        resultTextView.setTextColor(getResources().getColor(R.color.gray));
        resultTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
        resultTextView.setText(CalculatorClass.CLEAR_INPUT);
    }

    public TextView getResultView(){
        return resultTextView;
    }
}