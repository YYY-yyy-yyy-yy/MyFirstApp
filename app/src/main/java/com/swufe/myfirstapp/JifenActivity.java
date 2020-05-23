package com.swufe.myfirstapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class JifenActivity extends AppCompatActivity {

    TextView score;
    TextView scoreb;
    private final String TAG = "Jifen";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jifen);
        score = findViewById(R.id.score);
        scoreb = findViewById(R.id.scoreb);
        Log.i(TAG,"onCreate:");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        String scorea = ((TextView)findViewById(R.id.score)).getText().toString();
        String scoreb = ((TextView)findViewById(R.id.scoreb)).getText().toString();
        outState.putString("teama_score",scorea);
        outState.putString("teamb_score",scoreb);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String scorea = savedInstanceState.getString("teama_score");
        String scoreb = savedInstanceState.getString("teamb_score");
        ((TextView)findViewById(R.id.score)).setText(scorea);
        ((TextView)findViewById(R.id.scoreb)).setText(scoreb);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart:");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onResume:");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"onRestart:");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause:");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onStop:");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy:");
    }

    public void btnAdd1(View btn){
        if(btn.getId()==R.id.btna1){
            showScore(1);
        }else{
            showScore2(1);
        }
    }
    public void btnAdd2(View btn){
        if(btn.getId()==R.id.btna2){
            showScore(2);
        }else{
            showScore2(2);
        }
    }
    public void btnAdd3(View btn){
        if(btn.getId()==R.id.btna3){
            showScore(3);
        }else{
            showScore2(3);
        }
    }
    public void btnReset(View btn){
        score.setText("0");
        scoreb.setText("0");
    }
    private void showScore(int inc){
        Log.i("show","inc"+inc);
        String oldScore = (String) score.getText();
        int newScore = Integer.parseInt(oldScore)+inc;
        score.setText(""+newScore);
    }
    private void showScore2(int inc){
        Log.i("show","inc"+inc);
        String oldScore = (String) scoreb.getText();
        int newScore = Integer.parseInt(oldScore)+inc;
        scoreb.setText(""+newScore);
    }

}
