package com.example.handyterminal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class syukkoActivity extends AppCompatActivity {

    private EditText persontext;
    private EditText codetext;
    private EditText breedtext;
    private EditText roomtext;
    private String pallettext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syukko);

        persontext = findViewById(R.id.editTextTextPersonName);
        codetext = findViewById(R.id.editText);
        breedtext = findViewById(R.id.editTextTextPersonName2);
        roomtext = findViewById(R.id.editText2);
        Button button = findViewById(R.id.button);

//        //ソフトキーボードのエンターを押したとき
//        codetext.setOnEditorActionListener((textView, i, keyEvent) -> {
//            String code = codetext.getText().toString();//現品票コードの取得
//
//            //現品票QRを読んでいた場合
//            if (code.length() != 0) {
//                String str1 = code;
//                code = str1.substring(0, 10);//コード上10桁切り取り(品目コード)
//                breedtext.setText(code);//切り取った品目コードを品種名として表示
//                //コード下2桁切り取り(パレットNo.)
//                pallettext = str1.substring(19,21);
//            }
//            return false;
//        });

        //作業者名半角カナのみ
        InputFilter katakanaFilter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                StringBuilder filteredStringBuilder = new StringBuilder();
                for (int i = start; i < end; i++) {
                    char currentChar = source.charAt(i);
                    if (isHalfWidthKatakana(currentChar)) {
                        filteredStringBuilder.append(currentChar);
                    }
                }
                return filteredStringBuilder.toString();
            }
            private boolean isHalfWidthKatakana(char c) {
                return c >= '\uFF65' && c <= '\uFF9F';
            }
        };
        persontext.setFilters(new InputFilter[]{katakanaFilter});


        //現品票が読み取られたら品種名にフォーカスを移す
        codetext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 20){
                    breedtext.requestFocus();//品種名にフォーカスを移す
                    String code = codetext.getText().toString();//現品票コードの取得
                    //現品票QRを読んでいた場合
                    if (code.length() != 0) {
                        String str1 = code;
                        code = str1.substring(0, 10);//コード上10桁切り取り(品目コード)
                        breedtext.setText(code);//切り取った品目コードを品種名として表示
                        //コード下2桁切り取り(パレットNo.)
                        pallettext = str1.substring(19,21);
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        //品種名が入力されたら熟成室No.にフォーカスを移す
        breedtext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //品種名に入力があったら熟成室No.にフォーカスを移す
                if (charSequence.length() > 9){
                    roomtext.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                //
            }
        });

        //熟成室No.が入力されたらキーボードを隠す
        roomtext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //熟成室No.が読み込まれたらソフトキーボードを隠す
                if (charSequence.length() > 1){
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(roomtext.getWindowToken(),0);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                //
            }
        });

        //設定画面の作業者名取得
        Intent intent = getIntent();
        String getdata = intent.getStringExtra("SEND_DATA");
        //作業者名表示
        persontext.setText(getdata);

        //登録ボタンクリック
        button.setOnClickListener(view -> {
            //未入力なし
            if (persontext.length() !=0 && codetext.length() == 21 && breedtext.length() == 10 && roomtext.length() == 2) {
                //DB
                deletedb();
            }
            //未入力あり
            else{
                ErrorDialog dialog = new ErrorDialog();
                dialog.show(getSupportFragmentManager(),"error_dialog");
            }
        });
    }
    //DB書き込み
    private void deletedb() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {

                try{
                    //DBのインスタンス取得
                    QRInfoDatabase db = Room.databaseBuilder(getApplicationContext(),
                            QRInfoDatabase.class, "QRInfo").build();
                    QRInfoDao dao = db.qrInfoDao();

                    String breed = breedtext.getText().toString();//品種名をセット
                    String room = roomtext.getText().toString();  //熟成室No.をセット
                    String pallet = pallettext;                   //パレットNo.をセット

                    //DBから一致するデータをを削除
                    int rowsDelete = db.qrInfoDao().delete(breed,pallet,room);

                    //削除した行数を判定
                    if (rowsDelete == 0){
                        //削除無し(未入庫)
                        System.out.println("エラー");
                        ErrorDialog3 dialog3 = new ErrorDialog3();
                        dialog3.show(getSupportFragmentManager(),"error_dialog3");
                    }else {
                        //削除あり(出庫)
                        //登録完了ダイアログ
                        myDialogFragment2 myDialogFragment2 = new myDialogFragment2();
                        myDialogFragment2.show(getSupportFragmentManager(), "my_dialog2");
                    }
                } catch (Exception ignored) {

                }
            }
        });
    }
}
