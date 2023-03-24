package com.example.handyterminal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.content.Intent;

public class setteiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settei);

        //プルダウンリスト
        Spinner spinner = findViewById(R.id.spinner);


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.user_name, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        //spinnerにadapterをセット
        spinner.setAdapter(adapter);

        //インテント作成
        Intent intent = new Intent(getApplication(),nyukoActivity.class);
        Intent intent2 = new Intent(getApplication(), syukkoActivity.class);

        //選択データをセット
        intent.putExtra("SEND_DATA",spinner.getSelectedItem().toString());
        intent2.putExtra("SEND_DATA",spinner.getSelectedItem().toString());

        //入庫登録
//        Button button6 = findViewById(R.id.button6);
//        button6.setOnClickListener(view -> startActivity(intent));

        //出庫登録
//        Button button7 = findViewById(R.id.button7);
//        button7.setOnClickListener(view -> startActivity(intent2));

    }
}