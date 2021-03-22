package com.foo.calculator_v2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;


public class OptBtn extends AppCompatButton {
    int optButtonDefault = R.drawable.operator_button_default;
    int optButtonClick = R.drawable.operator_button_click;

    public OptBtn(@NonNull Context context) { // MainActivity에서 연산자 버튼 인스턴스 받아옴
        super(context);
        setBackground(getResources().getDrawable(optButtonDefault));
        setTextColor(getResources().getColor(R.color.white));
    }

    public OptBtn(@NonNull Context context, @Nullable AttributeSet attrs) { // xml에서 연산자 버튼 인스턴스 받아옴
        super(context, attrs);
        setBackground(getResources().getDrawable(optButtonDefault));
        setTextColor(getResources().getColor(R.color.white));
    }

    public OptBtn(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackground(getResources().getDrawable(optButtonDefault));
        setTextColor(getResources().getColor(R.color.white));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) { //연산자 버튼 누를때 발생하는 이벤트
        super.onTouchEvent(event);
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                setBackground(getResources().getDrawable(optButtonClick));
                setTextColor(getResources().getColor(R.color.darkGray));
                break;
            case MotionEvent.ACTION_UP:
                setBackground(getResources().getDrawable(optButtonDefault));
                setTextColor(getResources().getColor(R.color.white));
                break;
        }
        return true;
    }
}
