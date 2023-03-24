package com.example.handyterminal;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface QRInfoDao {

    //降順(最新が上)
    @Query("SELECT * FROM QRInfo ORDER BY id DESC")
    List<QRInfoEntity> getdesc();

    @Query("SELECT * FROM QRInfo")
    List<QRInfoEntity> getnon();

    //熟成室No.5のデータを降順で取得
    @Query("SELECT * FROM QRInfo WHERE room_no = '05' ORDER BY id DESC")
    List<QRInfoEntity> get05();

    //熟成室No.6のデータを降順で取得
    @Query("SELECT * FROM QRInfo WHERE room_no = '06' ORDER BY id DESC")
    List<QRInfoEntity> get06();

    //熟成室No.7のデータを降順で取得
    @Query("SELECT * FROM QRInfo WHERE room_no = '07' ORDER BY id DESC")
    List<QRInfoEntity> get07();

    //熟成室No.8のデータを降順で取得
    @Query("SELECT * FROM QRInfo WHERE room_no = '08' ORDER BY id DESC")
    List<QRInfoEntity> get08();

    //熟成室No.9のデータを降順で取得
    @Query("SELECT * FROM QRInfo WHERE room_no = '09' ORDER BY id DESC")
    List<QRInfoEntity> get09();

    //熟成室No.10のデータを降順で取得
    @Query("SELECT * FROM QRInfo WHERE room_no = '10' ORDER BY id DESC")
    List<QRInfoEntity> get10();
    @Query("DELETE FROM QRInfo wHERE breed_name = :breed AND pallet_no = :pallet AND room_no = :room")
    int delete(String breed,String pallet,String room);

    @Delete()
    void delete(QRInfoEntity qrInfoEntity);

    @Insert
    void insert(QRInfoEntity qrInfoEntity);
    @Update
    void update(QRInfoEntity qrInfoEntity);

}
