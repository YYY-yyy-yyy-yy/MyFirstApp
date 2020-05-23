package com.swufe.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    TextView tv;
    EditText inp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv = findViewById(R.id.textView2);
        tv.setText("swufe");
        inp = findViewById(R.id.editText2);
        String str = inp.getText().toString();

        Button btn = findViewById(R.id.button);
//        btn.setOnClickListener(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("click","onclick......");
                String str = inp.getText().toString();
                tv.setText("Hello"+str);
            }
        });
    }

    @Override
    public void onClick(View v) {
        Log.i("click","onclick123......");
        String str = inp.getText().toString();
        tv.setText("Hello"+str);
    }
    public void btnClick(View v){
        Log.i("click","onclick333......");
    }
}






















