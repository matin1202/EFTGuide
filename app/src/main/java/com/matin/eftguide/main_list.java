package com.matin.eftguide;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class main_list extends LinearLayout {
    ImageView imageView;
    TextView textView;
    public main_list(Context context) {
        super(context);
        init(context);
    }

    public main_list(Context context, AttributeSet attrs){
        super(context, attrs);
        init(context);
    }

    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.main_list, this, true);

        imageView = findViewById(R.id.main_imageView);
        textView = findViewById(R.id.main_textView);
    }

    public void setImage(int resId){
        imageView.setImageResource(resId);
    }

    public void setText(String text){
        textView.setText(text);
    }
}
