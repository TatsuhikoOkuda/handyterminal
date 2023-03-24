package com.example.handyterminal;


import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class tourokuActivity extends AppCompatActivity {

    private String hourtext;
    private String minutetext;
    private String selected;
    private List<QRInfoEntity> list;
    private TextView textView3;
    private TextView textView10;
    private TextView textView11;
    private TextView textView12;

    public tourokuActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touroku);

        //プルダウンリスト
        Spinner spinner = findViewById(R.id.spinner);

        new Thread(() -> runOnUiThread(() -> {
            //字列配列とデフォルトのスピナーレイアウトを使用した ArrayAdapter を作成
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(tourokuActivity.this,
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
                            loaddb();
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

    //DB読み込み
    private void loaddb(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {

            textView3 = findViewById(R.id.textView3);   //時間
            textView10 = findViewById(R.id.textView10); //作業者名
            textView11 = findViewById(R.id.textView11); //品種名
            textView12 = findViewById(R.id.textView12); //パレットNo.

            //リストの行
            final int[] r = {0};

            //可変長文字列
            StringBuilder stringBuilder1 = new StringBuilder();
            StringBuilder stringBuilder2 = new StringBuilder();
            StringBuilder stringBuilder3 = new StringBuilder();
            StringBuilder stringBuilder4 = new StringBuilder();

            try {
                //DBのインスタンス取得
                QRInfoDatabase db = Room.databaseBuilder(getApplicationContext(),
                    QRInfoDatabase.class,"QRInfo").build();
                QRInfoDao dao = db.qrInfoDao();

                //各部屋のデータを取得
                switch (selected) {
                    case " ":
                        list = dao.getnon();
                        break;
                    case "5号熟成室":
                        list = dao.get05();
                        break;
                    case "6号熟成室":
                        list = dao.get06();
                        break;
                    case "7号熟成室":
                        list = dao.get07();
                        break;
                    case "8号熟成室":
                        list = dao.get08();
                        break;
                    case "9号熟成室":
                        list = dao.get09();
                        break;
                    case "10号熟成室":
                        list = dao.get10();
                        break;
                }
            }catch (Exception ignored){

            }

                //UIスレッド以外のスレッドからビューを更新する
                runOnUiThread(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        if (list.size() == 0){
                            textView3.setText("");
                            textView10.setText("");
                            textView11.setText("");
                            textView12.setText("");
                        }else {
                            //表示データ作成
                            for (int i = 0; i < list.size(); i++) {
                                list.size();
                                QRInfoEntity entity = list.get(r[0]);        //テーブルの行番号
                                int hour = entity.getHour();                 //時
                                //0埋め
                                if (hour < 10) {
                                    hourtext = "0" + hour;
                                } else {
                                    hourtext = "" + hour;
                                }
                                int minute = entity.getMinute();            //分
                                //0埋め
                                if (minute < 10) {
                                    minutetext = "0" + minute;
                                } else {
                                    minutetext = "" + minute;
                                }
                                String user_name = entity.getUser_name();   //作業者名
                                String breed_name = entity.getBreed_name(); //品種名
                                String pallet_no = entity.getPallet_no();   //パレットNo.

                                //表示する文字列を作成
                                stringBuilder1.append(" ").append(hourtext).append(":").append(minutetext).append("\n");
                                stringBuilder2.append(" ").append(user_name).append("\n");
                                stringBuilder3.append(" ").append(breed_name).append("\n");
                                stringBuilder4.append(" ").append(pallet_no).append("\n");

                                //TextViewにセット
                                textView3.setText(stringBuilder1);
                                textView10.setText(stringBuilder2);
                                textView11.setText(stringBuilder3);
                                textView12.setText(stringBuilder4);

                                //テーブルの次の行へ
                                r[0]++;
                            }


                        }
                    }
                });
        });
    }
}