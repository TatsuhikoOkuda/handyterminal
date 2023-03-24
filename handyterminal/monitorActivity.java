package com.example.handyterminal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class monitorActivity extends AppCompatActivity {
    private  String selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);

        //プルダウンリスト
        Spinner spinner = findViewById(R.id.spinner);

        new Thread(() -> runOnUiThread(() -> {
            //字列配列とデフォルトのスピナーレイアウトを使用した ArrayAdapter を作成
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(monitorActivity.this,
                    R.array.room_No, android.R.layout.simple_spinner_item);
            //選択肢のリストが表示されたときに使用するレイアウトを指定する
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setFocusable(false);
            //アダプターをスピナーにセットする
            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    selected = parent.getItemAtPosition(position).toString();

                    if (!spinner.isFocusable()){
                        spinner.setFocusable(true);
                        return;
                    }
                    switch (selected) {
                        case "5号熟成室":
                        case "6号熟成室":
                        case "7号熟成室":
                        case "8号熟成室":
                        case "9号熟成室":
                        case "10号熟成室":
                            Log.d("Spinner", "onItemSelected: " + selected);
                            break;
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        })).start();



    }
}