package com.example.handyterminal;

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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class nyukoActivity extends AppCompatActivity {
    private EditText persontext;
    private EditText codetext;
    private EditText breedtext;
    private EditText roomtext;
    private String pallettext;

    //レイアウトが読み込まれたとき
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nyuko);

        //レイアウトのedittextをjavaで使えるようにするやつ
        persontext = findViewById(R.id.editTextTextPersonName);//作業者名
        codetext = findViewById(R.id.editText);                //現品票コード
        breedtext = findViewById(R.id.editTextTextPersonName2);//品種名
        roomtext = findViewById(R.id.editText2);               //熟成室No.
        Button button = findViewById(R.id.button);             //登録ボタン

//        persontext.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                //何もしない
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                //作業者名に入力があったら現品票読取フォーカスを移す
//                if (charSequence.length() > 0){
//                    codetext.requestFocus();
//                }
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                //何もしない
//            }
//        });

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
                //DB書き込み
                writedb();
            }
            //未入力あり
            else{
                //エラーダイアログ
                ErrorDialog dialog = new ErrorDialog();
                dialog.show(getSupportFragmentManager(),"error_dialog");
            }
        });
    }

    //DB書き込み
    private void writedb() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                //DBのインスタンス取得v
                QRInfoDatabase db = Room.databaseBuilder(getApplicationContext(),
                        QRInfoDatabase.class, "QRInfo").build();

                QRInfoDao dao = db.qrInfoDao();

                Calendar calendar = Calendar.getInstance();//カレンダー
                int hour = calendar.get(Calendar.HOUR_OF_DAY);//時セット
                int minute = calendar.get(Calendar.MINUTE);//分セット
                String user_name = persontext.getText().toString();//作業者名セット
                String breed_name = breedtext.getText().toString();//品種名セット
                String pallet_no = pallettext;//パレットNo.セット
                String room_no = roomtext.getText().toString();//熟成室No.セット

                //DB書き込み
                QRInfoEntity entity = new QRInfoEntity(hour, minute, user_name, breed_name, pallet_no, room_no);
                dao.insert(entity);

                //登録完了ダイアログ
                myDialogFragment myDialogFragment = new myDialogFragment();
                myDialogFragment.show(getSupportFragmentManager(),"my_dialog");

            } catch (Exception e) {
                //データ重複ダイアログ
                System.out.println("エラー");
                ErrorDialog2 dialog2 = new ErrorDialog2();
                dialog2.show(getSupportFragmentManager(),"error_dialog2");
            }
        });
    }


}
