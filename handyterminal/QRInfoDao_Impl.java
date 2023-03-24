package com.example.handyterminal;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class QRInfoDao_Impl implements QRInfoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<QRInfoEntity> __insertionAdapterOfQRInfoEntity;

  private final EntityDeletionOrUpdateAdapter<QRInfoEntity> __deletionAdapterOfQRInfoEntity;

  private final EntityDeletionOrUpdateAdapter<QRInfoEntity> __updateAdapterOfQRInfoEntity;

  private final SharedSQLiteStatement __preparedStmtOfDelete;

  public QRInfoDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfQRInfoEntity = new EntityInsertionAdapter<QRInfoEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `QRInfo` (`id`,`hour`,`minute`,`user_name`,`breed_name`,`pallet_no`,`room_no`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, QRInfoEntity value) {
        stmt.bindLong(1, value.id);
        stmt.bindLong(2, value.hour);
        stmt.bindLong(3, value.minute);
        if (value.user_name == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.user_name);
        }
        if (value.breed_name == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.breed_name);
        }
        if (value.pallet_no == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.pallet_no);
        }
        if (value.room_no == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.room_no);
        }
      }
    };
    this.__deletionAdapterOfQRInfoEntity = new EntityDeletionOrUpdateAdapter<QRInfoEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `QRInfo` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, QRInfoEntity value) {
        stmt.bindLong(1, value.id);
      }
    };
    this.__updateAdapterOfQRInfoEntity = new EntityDeletionOrUpdateAdapter<QRInfoEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `QRInfo` SET `id` = ?,`hour` = ?,`minute` = ?,`user_name` = ?,`breed_name` = ?,`pallet_no` = ?,`room_no` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, QRInfoEntity value) {
        stmt.bindLong(1, value.id);
        stmt.bindLong(2, value.hour);
        stmt.bindLong(3, value.minute);
        if (value.user_name == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.user_name);
        }
        if (value.breed_name == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.breed_name);
        }
        if (value.pallet_no == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.pallet_no);
        }
        if (value.room_no == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.room_no);
        }
        stmt.bindLong(8, value.id);
      }
    };
    this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM QRInfo wHERE breed_name = ? AND pallet_no = ? AND room_no = ?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final QRInfoEntity qrInfoEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfQRInfoEntity.insert(qrInfoEntity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final QRInfoEntity qrInfoEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfQRInfoEntity.handle(qrInfoEntity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final QRInfoEntity qrInfoEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfQRInfoEntity.handle(qrInfoEntity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int delete(final String breed, final String pallet, final String room) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDelete.acquire();
    int _argIndex = 1;
    if (breed == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, breed);
    }
    _argIndex = 2;
    if (pallet == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, pallet);
    }
    _argIndex = 3;
    if (room == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, room);
    }
    __db.beginTransaction();
    try {
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfDelete.release(_stmt);
    }
  }

  @Override
  public List<QRInfoEntity> getdesc() {
    final String _sql = "SELECT * FROM QRInfo ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfHour = CursorUtil.getColumnIndexOrThrow(_cursor, "hour");
      final int _cursorIndexOfMinute = CursorUtil.getColumnIndexOrThrow(_cursor, "minute");
      final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "user_name");
      final int _cursorIndexOfBreedName = CursorUtil.getColumnIndexOrThrow(_cursor, "breed_name");
      final int _cursorIndexOfPalletNo = CursorUtil.getColumnIndexOrThrow(_cursor, "pallet_no");
      final int _cursorIndexOfRoomNo = CursorUtil.getColumnIndexOrThrow(_cursor, "room_no");
      final List<QRInfoEntity> _result = new ArrayList<QRInfoEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final QRInfoEntity _item;
        final int _tmpHour;
        _tmpHour = _cursor.getInt(_cursorIndexOfHour);
        final int _tmpMinute;
        _tmpMinute = _cursor.getInt(_cursorIndexOfMinute);
        final String _tmpUser_name;
        if (_cursor.isNull(_cursorIndexOfUserName)) {
          _tmpUser_name = null;
        } else {
          _tmpUser_name = _cursor.getString(_cursorIndexOfUserName);
        }
        final String _tmpBreed_name;
        if (_cursor.isNull(_cursorIndexOfBreedName)) {
          _tmpBreed_name = null;
        } else {
          _tmpBreed_name = _cursor.getString(_cursorIndexOfBreedName);
        }
        final String _tmpPallet_no;
        if (_cursor.isNull(_cursorIndexOfPalletNo)) {
          _tmpPallet_no = null;
        } else {
          _tmpPallet_no = _cursor.getString(_cursorIndexOfPalletNo);
        }
        final String _tmpRoom_no;
        if (_cursor.isNull(_cursorIndexOfRoomNo)) {
          _tmpRoom_no = null;
        } else {
          _tmpRoom_no = _cursor.getString(_cursorIndexOfRoomNo);
        }
        _item = new QRInfoEntity(_tmpHour,_tmpMinute,_tmpUser_name,_tmpBreed_name,_tmpPallet_no,_tmpRoom_no);
        _item.id = _cursor.getInt(_cursorIndexOfId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<QRInfoEntity> getnon() {
    final String _sql = "SELECT * FROM QRInfo";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfHour = CursorUtil.getColumnIndexOrThrow(_cursor, "hour");
      final int _cursorIndexOfMinute = CursorUtil.getColumnIndexOrThrow(_cursor, "minute");
      final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "user_name");
      final int _cursorIndexOfBreedName = CursorUtil.getColumnIndexOrThrow(_cursor, "breed_name");
      final int _cursorIndexOfPalletNo = CursorUtil.getColumnIndexOrThrow(_cursor, "pallet_no");
      final int _cursorIndexOfRoomNo = CursorUtil.getColumnIndexOrThrow(_cursor, "room_no");
      final List<QRInfoEntity> _result = new ArrayList<QRInfoEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final QRInfoEntity _item;
        final int _tmpHour;
        _tmpHour = _cursor.getInt(_cursorIndexOfHour);
        final int _tmpMinute;
        _tmpMinute = _cursor.getInt(_cursorIndexOfMinute);
        final String _tmpUser_name;
        if (_cursor.isNull(_cursorIndexOfUserName)) {
          _tmpUser_name = null;
        } else {
          _tmpUser_name = _cursor.getString(_cursorIndexOfUserName);
        }
        final String _tmpBreed_name;
        if (_cursor.isNull(_cursorIndexOfBreedName)) {
          _tmpBreed_name = null;
        } else {
          _tmpBreed_name = _cursor.getString(_cursorIndexOfBreedName);
        }
        final String _tmpPallet_no;
        if (_cursor.isNull(_cursorIndexOfPalletNo)) {
          _tmpPallet_no = null;
        } else {
          _tmpPallet_no = _cursor.getString(_cursorIndexOfPalletNo);
        }
        final String _tmpRoom_no;
        if (_cursor.isNull(_cursorIndexOfRoomNo)) {
          _tmpRoom_no = null;
        } else {
          _tmpRoom_no = _cursor.getString(_cursorIndexOfRoomNo);
        }
        _item = new QRInfoEntity(_tmpHour,_tmpMinute,_tmpUser_name,_tmpBreed_name,_tmpPallet_no,_tmpRoom_no);
        _item.id = _cursor.getInt(_cursorIndexOfId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<QRInfoEntity> get05() {
    final String _sql = "SELECT * FROM QRInfo WHERE room_no = '05' ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfHour = CursorUtil.getColumnIndexOrThrow(_cursor, "hour");
      final int _cursorIndexOfMinute = CursorUtil.getColumnIndexOrThrow(_cursor, "minute");
      final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "user_name");
      final int _cursorIndexOfBreedName = CursorUtil.getColumnIndexOrThrow(_cursor, "breed_name");
      final int _cursorIndexOfPalletNo = CursorUtil.getColumnIndexOrThrow(_cursor, "pallet_no");
      final int _cursorIndexOfRoomNo = CursorUtil.getColumnIndexOrThrow(_cursor, "room_no");
      final List<QRInfoEntity> _result = new ArrayList<QRInfoEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final QRInfoEntity _item;
        final int _tmpHour;
        _tmpHour = _cursor.getInt(_cursorIndexOfHour);
        final int _tmpMinute;
        _tmpMinute = _cursor.getInt(_cursorIndexOfMinute);
        final String _tmpUser_name;
        if (_cursor.isNull(_cursorIndexOfUserName)) {
          _tmpUser_name = null;
        } else {
          _tmpUser_name = _cursor.getString(_cursorIndexOfUserName);
        }
        final String _tmpBreed_name;
        if (_cursor.isNull(_cursorIndexOfBreedName)) {
          _tmpBreed_name = null;
        } else {
          _tmpBreed_name = _cursor.getString(_cursorIndexOfBreedName);
        }
        final String _tmpPallet_no;
        if (_cursor.isNull(_cursorIndexOfPalletNo)) {
          _tmpPallet_no = null;
        } else {
          _tmpPallet_no = _cursor.getString(_cursorIndexOfPalletNo);
        }
        final String _tmpRoom_no;
        if (_cursor.isNull(_cursorIndexOfRoomNo)) {
          _tmpRoom_no = null;
        } else {
          _tmpRoom_no = _cursor.getString(_cursorIndexOfRoomNo);
        }
        _item = new QRInfoEntity(_tmpHour,_tmpMinute,_tmpUser_name,_tmpBreed_name,_tmpPallet_no,_tmpRoom_no);
        _item.id = _cursor.getInt(_cursorIndexOfId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<QRInfoEntity> get06() {
    final String _sql = "SELECT * FROM QRInfo WHERE room_no = '06' ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfHour = CursorUtil.getColumnIndexOrThrow(_cursor, "hour");
      final int _cursorIndexOfMinute = CursorUtil.getColumnIndexOrThrow(_cursor, "minute");
      final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "user_name");
      final int _cursorIndexOfBreedName = CursorUtil.getColumnIndexOrThrow(_cursor, "breed_name");
      final int _cursorIndexOfPalletNo = CursorUtil.getColumnIndexOrThrow(_cursor, "pallet_no");
      final int _cursorIndexOfRoomNo = CursorUtil.getColumnIndexOrThrow(_cursor, "room_no");
      final List<QRInfoEntity> _result = new ArrayList<QRInfoEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final QRInfoEntity _item;
        final int _tmpHour;
        _tmpHour = _cursor.getInt(_cursorIndexOfHour);
        final int _tmpMinute;
        _tmpMinute = _cursor.getInt(_cursorIndexOfMinute);
        final String _tmpUser_name;
        if (_cursor.isNull(_cursorIndexOfUserName)) {
          _tmpUser_name = null;
        } else {
          _tmpUser_name = _cursor.getString(_cursorIndexOfUserName);
        }
        final String _tmpBreed_name;
        if (_cursor.isNull(_cursorIndexOfBreedName)) {
          _tmpBreed_name = null;
        } else {
          _tmpBreed_name = _cursor.getString(_cursorIndexOfBreedName);
        }
        final String _tmpPallet_no;
        if (_cursor.isNull(_cursorIndexOfPalletNo)) {
          _tmpPallet_no = null;
        } else {
          _tmpPallet_no = _cursor.getString(_cursorIndexOfPalletNo);
        }
        final String _tmpRoom_no;
        if (_cursor.isNull(_cursorIndexOfRoomNo)) {
          _tmpRoom_no = null;
        } else {
          _tmpRoom_no = _cursor.getString(_cursorIndexOfRoomNo);
        }
        _item = new QRInfoEntity(_tmpHour,_tmpMinute,_tmpUser_name,_tmpBreed_name,_tmpPallet_no,_tmpRoom_no);
        _item.id = _cursor.getInt(_cursorIndexOfId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<QRInfoEntity> get07() {
    final String _sql = "SELECT * FROM QRInfo WHERE room_no = '07' ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfHour = CursorUtil.getColumnIndexOrThrow(_cursor, "hour");
      final int _cursorIndexOfMinute = CursorUtil.getColumnIndexOrThrow(_cursor, "minute");
      final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "user_name");
      final int _cursorIndexOfBreedName = CursorUtil.getColumnIndexOrThrow(_cursor, "breed_name");
      final int _cursorIndexOfPalletNo = CursorUtil.getColumnIndexOrThrow(_cursor, "pallet_no");
      final int _cursorIndexOfRoomNo = CursorUtil.getColumnIndexOrThrow(_cursor, "room_no");
      final List<QRInfoEntity> _result = new ArrayList<QRInfoEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final QRInfoEntity _item;
        final int _tmpHour;
        _tmpHour = _cursor.getInt(_cursorIndexOfHour);
        final int _tmpMinute;
        _tmpMinute = _cursor.getInt(_cursorIndexOfMinute);
        final String _tmpUser_name;
        if (_cursor.isNull(_cursorIndexOfUserName)) {
          _tmpUser_name = null;
        } else {
          _tmpUser_name = _cursor.getString(_cursorIndexOfUserName);
        }
        final String _tmpBreed_name;
        if (_cursor.isNull(_cursorIndexOfBreedName)) {
          _tmpBreed_name = null;
        } else {
          _tmpBreed_name = _cursor.getString(_cursorIndexOfBreedName);
        }
        final String _tmpPallet_no;
        if (_cursor.isNull(_cursorIndexOfPalletNo)) {
          _tmpPallet_no = null;
        } else {
          _tmpPallet_no = _cursor.getString(_cursorIndexOfPalletNo);
        }
        final String _tmpRoom_no;
        if (_cursor.isNull(_cursorIndexOfRoomNo)) {
          _tmpRoom_no = null;
        } else {
          _tmpRoom_no = _cursor.getString(_cursorIndexOfRoomNo);
        }
        _item = new QRInfoEntity(_tmpHour,_tmpMinute,_tmpUser_name,_tmpBreed_name,_tmpPallet_no,_tmpRoom_no);
        _item.id = _cursor.getInt(_cursorIndexOfId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<QRInfoEntity> get08() {
    final String _sql = "SELECT * FROM QRInfo WHERE room_no = '08' ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfHour = CursorUtil.getColumnIndexOrThrow(_cursor, "hour");
      final int _cursorIndexOfMinute = CursorUtil.getColumnIndexOrThrow(_cursor, "minute");
      final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "user_name");
      final int _cursorIndexOfBreedName = CursorUtil.getColumnIndexOrThrow(_cursor, "breed_name");
      final int _cursorIndexOfPalletNo = CursorUtil.getColumnIndexOrThrow(_cursor, "pallet_no");
      final int _cursorIndexOfRoomNo = CursorUtil.getColumnIndexOrThrow(_cursor, "room_no");
      final List<QRInfoEntity> _result = new ArrayList<QRInfoEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final QRInfoEntity _item;
        final int _tmpHour;
        _tmpHour = _cursor.getInt(_cursorIndexOfHour);
        final int _tmpMinute;
        _tmpMinute = _cursor.getInt(_cursorIndexOfMinute);
        final String _tmpUser_name;
        if (_cursor.isNull(_cursorIndexOfUserName)) {
          _tmpUser_name = null;
        } else {
          _tmpUser_name = _cursor.getString(_cursorIndexOfUserName);
        }
        final String _tmpBreed_name;
        if (_cursor.isNull(_cursorIndexOfBreedName)) {
          _tmpBreed_name = null;
        } else {
          _tmpBreed_name = _cursor.getString(_cursorIndexOfBreedName);
        }
        final String _tmpPallet_no;
        if (_cursor.isNull(_cursorIndexOfPalletNo)) {
          _tmpPallet_no = null;
        } else {
          _tmpPallet_no = _cursor.getString(_cursorIndexOfPalletNo);
        }
        final String _tmpRoom_no;
        if (_cursor.isNull(_cursorIndexOfRoomNo)) {
          _tmpRoom_no = null;
        } else {
          _tmpRoom_no = _cursor.getString(_cursorIndexOfRoomNo);
        }
        _item = new QRInfoEntity(_tmpHour,_tmpMinute,_tmpUser_name,_tmpBreed_name,_tmpPallet_no,_tmpRoom_no);
        _item.id = _cursor.getInt(_cursorIndexOfId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<QRInfoEntity> get09() {
    final String _sql = "SELECT * FROM QRInfo WHERE room_no = '09' ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfHour = CursorUtil.getColumnIndexOrThrow(_cursor, "hour");
      final int _cursorIndexOfMinute = CursorUtil.getColumnIndexOrThrow(_cursor, "minute");
      final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "user_name");
      final int _cursorIndexOfBreedName = CursorUtil.getColumnIndexOrThrow(_cursor, "breed_name");
      final int _cursorIndexOfPalletNo = CursorUtil.getColumnIndexOrThrow(_cursor, "pallet_no");
      final int _cursorIndexOfRoomNo = CursorUtil.getColumnIndexOrThrow(_cursor, "room_no");
      final List<QRInfoEntity> _result = new ArrayList<QRInfoEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final QRInfoEntity _item;
        final int _tmpHour;
        _tmpHour = _cursor.getInt(_cursorIndexOfHour);
        final int _tmpMinute;
        _tmpMinute = _cursor.getInt(_cursorIndexOfMinute);
        final String _tmpUser_name;
        if (_cursor.isNull(_cursorIndexOfUserName)) {
          _tmpUser_name = null;
        } else {
          _tmpUser_name = _cursor.getString(_cursorIndexOfUserName);
        }
        final String _tmpBreed_name;
        if (_cursor.isNull(_cursorIndexOfBreedName)) {
          _tmpBreed_name = null;
        } else {
          _tmpBreed_name = _cursor.getString(_cursorIndexOfBreedName);
        }
        final String _tmpPallet_no;
        if (_cursor.isNull(_cursorIndexOfPalletNo)) {
          _tmpPallet_no = null;
        } else {
          _tmpPallet_no = _cursor.getString(_cursorIndexOfPalletNo);
        }
        final String _tmpRoom_no;
        if (_cursor.isNull(_cursorIndexOfRoomNo)) {
          _tmpRoom_no = null;
        } else {
          _tmpRoom_no = _cursor.getString(_cursorIndexOfRoomNo);
        }
        _item = new QRInfoEntity(_tmpHour,_tmpMinute,_tmpUser_name,_tmpBreed_name,_tmpPallet_no,_tmpRoom_no);
        _item.id = _cursor.getInt(_cursorIndexOfId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<QRInfoEntity> get10() {
    final String _sql = "SELECT * FROM QRInfo WHERE room_no = '10' ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfHour = CursorUtil.getColumnIndexOrThrow(_cursor, "hour");
      final int _cursorIndexOfMinute = CursorUtil.getColumnIndexOrThrow(_cursor, "minute");
      final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "user_name");
      final int _cursorIndexOfBreedName = CursorUtil.getColumnIndexOrThrow(_cursor, "breed_name");
      final int _cursorIndexOfPalletNo = CursorUtil.getColumnIndexOrThrow(_cursor, "pallet_no");
      final int _cursorIndexOfRoomNo = CursorUtil.getColumnIndexOrThrow(_cursor, "room_no");
      final List<QRInfoEntity> _result = new ArrayList<QRInfoEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final QRInfoEntity _item;
        final int _tmpHour;
        _tmpHour = _cursor.getInt(_cursorIndexOfHour);
        final int _tmpMinute;
        _tmpMinute = _cursor.getInt(_cursorIndexOfMinute);
        final String _tmpUser_name;
        if (_cursor.isNull(_cursorIndexOfUserName)) {
          _tmpUser_name = null;
        } else {
          _tmpUser_name = _cursor.getString(_cursorIndexOfUserName);
        }
        final String _tmpBreed_name;
        if (_cursor.isNull(_cursorIndexOfBreedName)) {
          _tmpBreed_name = null;
        } else {
          _tmpBreed_name = _cursor.getString(_cursorIndexOfBreedName);
        }
        final String _tmpPallet_no;
        if (_cursor.isNull(_cursorIndexOfPalletNo)) {
          _tmpPallet_no = null;
        } else {
          _tmpPallet_no = _cursor.getString(_cursorIndexOfPalletNo);
        }
        final String _tmpRoom_no;
        if (_cursor.isNull(_cursorIndexOfRoomNo)) {
          _tmpRoom_no = null;
        } else {
          _tmpRoom_no = _cursor.getString(_cursorIndexOfRoomNo);
        }
        _item = new QRInfoEntity(_tmpHour,_tmpMinute,_tmpUser_name,_tmpBreed_name,_tmpPallet_no,_tmpRoom_no);
        _item.id = _cursor.getInt(_cursorIndexOfId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
