package com.example.handyterminal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //画面遷移
        //入庫登録
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(view -> {
            Intent intent = new Intent(getApplication(),nyukoActivity.class);
            startActivity(intent);
        });

        //出庫登録
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(view -> {
            Intent intent = new Intent(getApplication(), syukkoActivity.class);
            startActivity(intent);
        });

        //登録状況
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(view -> {
            Intent intent = new Intent(getApplication(),tourokuActivity.class);
            startActivity(intent);
        });

        //入庫状況モニタ
        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(view -> {
            Intent intent = new Intent(getApplication(),monitorActivity.class);
            startActivity(intent);
        });

        //設定画面
        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(view -> {
            Intent intent = new Intent(getApplication(),setteiActivity.class);
            startActivity(intent);
        });
    }
    //アプリ終了
    private boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "もう一度戻るボタンを押すとアプリが終了します", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(() -> doubleBackToExitPressedOnce=false, 2000);
    }
}
