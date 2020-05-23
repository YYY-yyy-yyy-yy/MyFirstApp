package com.swufe.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WenduActivity extends AppCompatActivity implements View.OnClickListener {

    TextView out;
    EditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wendu);

        out = findViewById(R.id.showText);
        edit =(EditText)findViewById(R.id.inputTest);
        Button btn =(Button)findViewById(R.id.btn2);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String str = edit.getText().toString();
        if (str != null) {
            Double num = Double.parseDouble(str);
            if(num < -273.16){
                out.setText("结果为："+"输入温度不存在");
            }else{
                double end = num*1.8+32;
                out.setText("结果为："+end);
            }
        }else {
            out.setText("结果为："+"还未输入温度");
        }


    }
}
