package com.example.handyterminal;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "QRInfo",//テーブル名
        indices ={@Index(value = {"breed_name", "pallet_no"}, unique = true)})//UNIQUE設定(重複禁止)

public class QRInfoEntity {

    @PrimaryKey(autoGenerate = true)
    public int id; //id
    public int hour; //時間
    public int minute; //時間
    public String user_name; //作業者名
    public String breed_name; //品種名
    public String pallet_no; //パレットNo.
    public String room_no;//熟成室No.

    public QRInfoEntity(int hour, int minute, String user_name, String breed_name, String pallet_no,String room_no){
        this.hour = hour;//時間を設定
        this.minute = minute;//時間を設定
        this.user_name = user_name;//作業者名を設定
        this.breed_name = breed_name;//品種名を設定
        this.pallet_no = pallet_no;//パレットNo.を設定
        this.room_no = room_no;//熟成室No.を設定
    }

    public int getId(){ return this.id; }//IDを取得
    public int getHour(){
        return this.hour;
    }//時間を取得
    public int getMinute(){
        return  this.minute;
    }//時間をを取得
    public String getUser_name(){
        return  this.user_name;
    }//作業者名を取得
    public String getBreed_name(){
        return this.breed_name;
    }//品種名を取得
    public String getPallet_no(){ return this.pallet_no; }//パレットNo.を取得
    public String getRoom_no(){ return this.room_no; }//熟成室No.を取得
}
