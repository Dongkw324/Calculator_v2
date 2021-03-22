package com.foo.calculator_v2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

public class NumBtn extends AppCompatButton {

    int num_btn_default = R.drawable.number_button_default;
    int num_btn_click = R.drawable.number_button_click;

    public NumBtn(@NonNull Context context) { //MainActivity에서 버튼을 인스턴스 할 때의 생성자
        super(context);
        setBackground(getResources().getDrawable(num_btn_default));
        setTextColor(getResources().getColor(R.color.white));

    }

    public NumBtn(@NonNull Context context, @Nullable AttributeSet attrs) { //xml에서 버튼을 만들었을때 그 버튼을 전달해오는 생성자
        super(context, attrs);
        setBackground(getResources().getDrawable(num_btn_default));
        setTextColor(getResources().getColor(R.color.white));
    }

    public NumBtn(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackground(getResources().getDrawable(num_btn_default));
        setTextColor(getResources().getColor(R.color.white));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) { //숫자 버튼 눌릴 때 발생하는 이벤트
        super.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                setBackground(getResources().getDrawable(num_btn_click));
                setTextColor(getResources().getColor(R.color.gray));
                break;
            case MotionEvent.ACTION_UP:
                setBackground(getResources().getDrawable(num_btn_default));
                setTextColor(getResources().getColor(R.color.white));
                break;

        }
        return true;
    }

}
