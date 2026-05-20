package com.example.project100.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.project100.data.local.Converters;
import com.example.project100.data.local.entity.PunishmentEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.IllegalStateException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class PunishmentDao_Impl implements PunishmentDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PunishmentEntity> __insertionAdapterOfPunishmentEntity;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<PunishmentEntity> __updateAdapterOfPunishmentEntity;

  public PunishmentDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPunishmentEntity = new EntityInsertionAdapter<PunishmentEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `punishments` (`id`,`dateGenerated`,`totalBurpees`,`completedBurpees`,`isCleared`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PunishmentEntity entity) {
        statement.bindLong(1, entity.getId());
        final String _tmp = __converters.dateToString(entity.getDateGenerated());
        if (_tmp == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, _tmp);
        }
        statement.bindLong(3, entity.getTotalBurpees());
        statement.bindLong(4, entity.getCompletedBurpees());
        final int _tmp_1 = entity.isCleared() ? 1 : 0;
        statement.bindLong(5, _tmp_1);
      }
    };
    this.__updateAdapterOfPunishmentEntity = new EntityDeletionOrUpdateAdapter<PunishmentEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `punishments` SET `id` = ?,`dateGenerated` = ?,`totalBurpees` = ?,`completedBurpees` = ?,`isCleared` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PunishmentEntity entity) {
        statement.bindLong(1, entity.getId());
        final String _tmp = __converters.dateToString(entity.getDateGenerated());
        if (_tmp == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, _tmp);
        }
        statement.bindLong(3, entity.getTotalBurpees());
        statement.bindLong(4, entity.getCompletedBurpees());
        final int _tmp_1 = entity.isCleared() ? 1 : 0;
        statement.bindLong(5, _tmp_1);
        statement.bindLong(6, entity.getId());
      }
    };
  }

  @Override
  public Object insertPunishment(final PunishmentEntity punishment,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPunishmentEntity.insert(punishment);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updatePunishment(final PunishmentEntity punishment,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfPunishmentEntity.handle(punishment);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<PunishmentEntity>> getActivePunishments() {
    final String _sql = "SELECT * FROM punishments WHERE isCleared = 0 ORDER BY dateGenerated ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"punishments"}, new Callable<List<PunishmentEntity>>() {
      @Override
      @NonNull
      public List<PunishmentEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDateGenerated = CursorUtil.getColumnIndexOrThrow(_cursor, "dateGenerated");
          final int _cursorIndexOfTotalBurpees = CursorUtil.getColumnIndexOrThrow(_cursor, "totalBurpees");
          final int _cursorIndexOfCompletedBurpees = CursorUtil.getColumnIndexOrThrow(_cursor, "completedBurpees");
          final int _cursorIndexOfIsCleared = CursorUtil.getColumnIndexOrThrow(_cursor, "isCleared");
          final List<PunishmentEntity> _result = new ArrayList<PunishmentEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PunishmentEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final LocalDate _tmpDateGenerated;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfDateGenerated)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfDateGenerated);
            }
            final LocalDate _tmp_1 = __converters.fromString(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDate', but it was NULL.");
            } else {
              _tmpDateGenerated = _tmp_1;
            }
            final int _tmpTotalBurpees;
            _tmpTotalBurpees = _cursor.getInt(_cursorIndexOfTotalBurpees);
            final int _tmpCompletedBurpees;
            _tmpCompletedBurpees = _cursor.getInt(_cursorIndexOfCompletedBurpees);
            final boolean _tmpIsCleared;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsCleared);
            _tmpIsCleared = _tmp_2 != 0;
            _item = new PunishmentEntity(_tmpId,_tmpDateGenerated,_tmpTotalBurpees,_tmpCompletedBurpees,_tmpIsCleared);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<Integer> getTotalDebtFlow() {
    final String _sql = "SELECT SUM(totalBurpees - completedBurpees) FROM punishments WHERE isCleared = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"punishments"}, new Callable<Integer>() {
      @Override
      @Nullable
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<PunishmentEntity>> getAllPunishments() {
    final String _sql = "SELECT * FROM punishments ORDER BY dateGenerated DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"punishments"}, new Callable<List<PunishmentEntity>>() {
      @Override
      @NonNull
      public List<PunishmentEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDateGenerated = CursorUtil.getColumnIndexOrThrow(_cursor, "dateGenerated");
          final int _cursorIndexOfTotalBurpees = CursorUtil.getColumnIndexOrThrow(_cursor, "totalBurpees");
          final int _cursorIndexOfCompletedBurpees = CursorUtil.getColumnIndexOrThrow(_cursor, "completedBurpees");
          final int _cursorIndexOfIsCleared = CursorUtil.getColumnIndexOrThrow(_cursor, "isCleared");
          final List<PunishmentEntity> _result = new ArrayList<PunishmentEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PunishmentEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final LocalDate _tmpDateGenerated;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfDateGenerated)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfDateGenerated);
            }
            final LocalDate _tmp_1 = __converters.fromString(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDate', but it was NULL.");
            } else {
              _tmpDateGenerated = _tmp_1;
            }
            final int _tmpTotalBurpees;
            _tmpTotalBurpees = _cursor.getInt(_cursorIndexOfTotalBurpees);
            final int _tmpCompletedBurpees;
            _tmpCompletedBurpees = _cursor.getInt(_cursorIndexOfCompletedBurpees);
            final boolean _tmpIsCleared;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsCleared);
            _tmpIsCleared = _tmp_2 != 0;
            _item = new PunishmentEntity(_tmpId,_tmpDateGenerated,_tmpTotalBurpees,_tmpCompletedBurpees,_tmpIsCleared);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
