package com.example.project100.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.project100.data.local.Converters;
import com.example.project100.data.local.entity.WorkoutEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.IllegalStateException;
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
public final class WorkoutDao_Impl implements WorkoutDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<WorkoutEntity> __insertionAdapterOfWorkoutEntity;

  private final Converters __converters = new Converters();

  public WorkoutDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWorkoutEntity = new EntityInsertionAdapter<WorkoutEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `workouts` (`date`,`pushUps`,`sitUps`,`squats`,`runningKm`,`waterMl`,`isCompleted`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final WorkoutEntity entity) {
        final String _tmp = __converters.dateToString(entity.getDate());
        if (_tmp == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, _tmp);
        }
        statement.bindLong(2, entity.getPushUps());
        statement.bindLong(3, entity.getSitUps());
        statement.bindLong(4, entity.getSquats());
        statement.bindDouble(5, entity.getRunningKm());
        statement.bindLong(6, entity.getWaterMl());
        final int _tmp_1 = entity.isCompleted() ? 1 : 0;
        statement.bindLong(7, _tmp_1);
      }
    };
  }

  @Override
  public Object insertWorkout(final WorkoutEntity workout,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfWorkoutEntity.insert(workout);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getWorkoutByDate(final LocalDate date,
      final Continuation<? super WorkoutEntity> $completion) {
    final String _sql = "SELECT * FROM workouts WHERE date = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __converters.dateToString(date);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<WorkoutEntity>() {
      @Override
      @Nullable
      public WorkoutEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfPushUps = CursorUtil.getColumnIndexOrThrow(_cursor, "pushUps");
          final int _cursorIndexOfSitUps = CursorUtil.getColumnIndexOrThrow(_cursor, "sitUps");
          final int _cursorIndexOfSquats = CursorUtil.getColumnIndexOrThrow(_cursor, "squats");
          final int _cursorIndexOfRunningKm = CursorUtil.getColumnIndexOrThrow(_cursor, "runningKm");
          final int _cursorIndexOfWaterMl = CursorUtil.getColumnIndexOrThrow(_cursor, "waterMl");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final WorkoutEntity _result;
          if (_cursor.moveToFirst()) {
            final LocalDate _tmpDate;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfDate);
            }
            final LocalDate _tmp_2 = __converters.fromString(_tmp_1);
            if (_tmp_2 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDate', but it was NULL.");
            } else {
              _tmpDate = _tmp_2;
            }
            final int _tmpPushUps;
            _tmpPushUps = _cursor.getInt(_cursorIndexOfPushUps);
            final int _tmpSitUps;
            _tmpSitUps = _cursor.getInt(_cursorIndexOfSitUps);
            final int _tmpSquats;
            _tmpSquats = _cursor.getInt(_cursorIndexOfSquats);
            final double _tmpRunningKm;
            _tmpRunningKm = _cursor.getDouble(_cursorIndexOfRunningKm);
            final int _tmpWaterMl;
            _tmpWaterMl = _cursor.getInt(_cursorIndexOfWaterMl);
            final boolean _tmpIsCompleted;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_3 != 0;
            _result = new WorkoutEntity(_tmpDate,_tmpPushUps,_tmpSitUps,_tmpSquats,_tmpRunningKm,_tmpWaterMl,_tmpIsCompleted);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<WorkoutEntity> getWorkoutFlowByDate(final LocalDate date) {
    final String _sql = "SELECT * FROM workouts WHERE date = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __converters.dateToString(date);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"workouts"}, new Callable<WorkoutEntity>() {
      @Override
      @Nullable
      public WorkoutEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfPushUps = CursorUtil.getColumnIndexOrThrow(_cursor, "pushUps");
          final int _cursorIndexOfSitUps = CursorUtil.getColumnIndexOrThrow(_cursor, "sitUps");
          final int _cursorIndexOfSquats = CursorUtil.getColumnIndexOrThrow(_cursor, "squats");
          final int _cursorIndexOfRunningKm = CursorUtil.getColumnIndexOrThrow(_cursor, "runningKm");
          final int _cursorIndexOfWaterMl = CursorUtil.getColumnIndexOrThrow(_cursor, "waterMl");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final WorkoutEntity _result;
          if (_cursor.moveToFirst()) {
            final LocalDate _tmpDate;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfDate);
            }
            final LocalDate _tmp_2 = __converters.fromString(_tmp_1);
            if (_tmp_2 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDate', but it was NULL.");
            } else {
              _tmpDate = _tmp_2;
            }
            final int _tmpPushUps;
            _tmpPushUps = _cursor.getInt(_cursorIndexOfPushUps);
            final int _tmpSitUps;
            _tmpSitUps = _cursor.getInt(_cursorIndexOfSitUps);
            final int _tmpSquats;
            _tmpSquats = _cursor.getInt(_cursorIndexOfSquats);
            final double _tmpRunningKm;
            _tmpRunningKm = _cursor.getDouble(_cursorIndexOfRunningKm);
            final int _tmpWaterMl;
            _tmpWaterMl = _cursor.getInt(_cursorIndexOfWaterMl);
            final boolean _tmpIsCompleted;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_3 != 0;
            _result = new WorkoutEntity(_tmpDate,_tmpPushUps,_tmpSitUps,_tmpSquats,_tmpRunningKm,_tmpWaterMl,_tmpIsCompleted);
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
  public Flow<List<WorkoutEntity>> getAllWorkouts() {
    final String _sql = "SELECT * FROM workouts ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"workouts"}, new Callable<List<WorkoutEntity>>() {
      @Override
      @NonNull
      public List<WorkoutEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfPushUps = CursorUtil.getColumnIndexOrThrow(_cursor, "pushUps");
          final int _cursorIndexOfSitUps = CursorUtil.getColumnIndexOrThrow(_cursor, "sitUps");
          final int _cursorIndexOfSquats = CursorUtil.getColumnIndexOrThrow(_cursor, "squats");
          final int _cursorIndexOfRunningKm = CursorUtil.getColumnIndexOrThrow(_cursor, "runningKm");
          final int _cursorIndexOfWaterMl = CursorUtil.getColumnIndexOrThrow(_cursor, "waterMl");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final List<WorkoutEntity> _result = new ArrayList<WorkoutEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WorkoutEntity _item;
            final LocalDate _tmpDate;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfDate);
            }
            final LocalDate _tmp_1 = __converters.fromString(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDate', but it was NULL.");
            } else {
              _tmpDate = _tmp_1;
            }
            final int _tmpPushUps;
            _tmpPushUps = _cursor.getInt(_cursorIndexOfPushUps);
            final int _tmpSitUps;
            _tmpSitUps = _cursor.getInt(_cursorIndexOfSitUps);
            final int _tmpSquats;
            _tmpSquats = _cursor.getInt(_cursorIndexOfSquats);
            final double _tmpRunningKm;
            _tmpRunningKm = _cursor.getDouble(_cursorIndexOfRunningKm);
            final int _tmpWaterMl;
            _tmpWaterMl = _cursor.getInt(_cursorIndexOfWaterMl);
            final boolean _tmpIsCompleted;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_2 != 0;
            _item = new WorkoutEntity(_tmpDate,_tmpPushUps,_tmpSitUps,_tmpSquats,_tmpRunningKm,_tmpWaterMl,_tmpIsCompleted);
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
