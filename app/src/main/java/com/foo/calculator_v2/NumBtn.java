package com.foo.calculator_v2;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

public class NumBtn extends AppCompatButton {

    public NumBtn(@NonNull Context context) {
        super(context);
    }

    public NumBtn(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NumBtn(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
