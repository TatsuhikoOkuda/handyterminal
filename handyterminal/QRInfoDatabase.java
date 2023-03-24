package com.example.handyterminal;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {QRInfoEntity.class},version = 1,exportSchema = false)
public abstract class QRInfoDatabase extends RoomDatabase {


    public abstract QRInfoDao qrInfoDao();
}
