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
import com.example.project100.data.local.entity.BodyMetricsEntity;
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
public final class BodyMetricsDao_Impl implements BodyMetricsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<BodyMetricsEntity> __insertionAdapterOfBodyMetricsEntity;

  private final Converters __converters = new Converters();

  public BodyMetricsDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBodyMetricsEntity = new EntityInsertionAdapter<BodyMetricsEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `body_metrics` (`date`,`weight`,`bodyFat`,`chest`,`waist`,`arms`,`legs`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final BodyMetricsEntity entity) {
        final String _tmp = __converters.dateToString(entity.getDate());
        if (_tmp == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, _tmp);
        }
        statement.bindDouble(2, entity.getWeight());
        statement.bindDouble(3, entity.getBodyFat());
        statement.bindDouble(4, entity.getChest());
        statement.bindDouble(5, entity.getWaist());
        statement.bindDouble(6, entity.getArms());
        statement.bindDouble(7, entity.getLegs());
      }
    };
  }

  @Override
  public Object insertMetrics(final BodyMetricsEntity metrics,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfBodyMetricsEntity.insert(metrics);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<BodyMetricsEntity>> getAllMetrics() {
    final String _sql = "SELECT * FROM body_metrics ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"body_metrics"}, new Callable<List<BodyMetricsEntity>>() {
      @Override
      @NonNull
      public List<BodyMetricsEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
          final int _cursorIndexOfBodyFat = CursorUtil.getColumnIndexOrThrow(_cursor, "bodyFat");
          final int _cursorIndexOfChest = CursorUtil.getColumnIndexOrThrow(_cursor, "chest");
          final int _cursorIndexOfWaist = CursorUtil.getColumnIndexOrThrow(_cursor, "waist");
          final int _cursorIndexOfArms = CursorUtil.getColumnIndexOrThrow(_cursor, "arms");
          final int _cursorIndexOfLegs = CursorUtil.getColumnIndexOrThrow(_cursor, "legs");
          final List<BodyMetricsEntity> _result = new ArrayList<BodyMetricsEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final BodyMetricsEntity _item;
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
            final double _tmpWeight;
            _tmpWeight = _cursor.getDouble(_cursorIndexOfWeight);
            final double _tmpBodyFat;
            _tmpBodyFat = _cursor.getDouble(_cursorIndexOfBodyFat);
            final double _tmpChest;
            _tmpChest = _cursor.getDouble(_cursorIndexOfChest);
            final double _tmpWaist;
            _tmpWaist = _cursor.getDouble(_cursorIndexOfWaist);
            final double _tmpArms;
            _tmpArms = _cursor.getDouble(_cursorIndexOfArms);
            final double _tmpLegs;
            _tmpLegs = _cursor.getDouble(_cursorIndexOfLegs);
            _item = new BodyMetricsEntity(_tmpDate,_tmpWeight,_tmpBodyFat,_tmpChest,_tmpWaist,_tmpArms,_tmpLegs);
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
  public Object getMetricsByDate(final LocalDate date,
      final Continuation<? super BodyMetricsEntity> $completion) {
    final String _sql = "SELECT * FROM body_metrics WHERE date = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __converters.dateToString(date);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<BodyMetricsEntity>() {
      @Override
      @Nullable
      public BodyMetricsEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
          final int _cursorIndexOfBodyFat = CursorUtil.getColumnIndexOrThrow(_cursor, "bodyFat");
          final int _cursorIndexOfChest = CursorUtil.getColumnIndexOrThrow(_cursor, "chest");
          final int _cursorIndexOfWaist = CursorUtil.getColumnIndexOrThrow(_cursor, "waist");
          final int _cursorIndexOfArms = CursorUtil.getColumnIndexOrThrow(_cursor, "arms");
          final int _cursorIndexOfLegs = CursorUtil.getColumnIndexOrThrow(_cursor, "legs");
          final BodyMetricsEntity _result;
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
            final double _tmpWeight;
            _tmpWeight = _cursor.getDouble(_cursorIndexOfWeight);
            final double _tmpBodyFat;
            _tmpBodyFat = _cursor.getDouble(_cursorIndexOfBodyFat);
            final double _tmpChest;
            _tmpChest = _cursor.getDouble(_cursorIndexOfChest);
            final double _tmpWaist;
            _tmpWaist = _cursor.getDouble(_cursorIndexOfWaist);
            final double _tmpArms;
            _tmpArms = _cursor.getDouble(_cursorIndexOfArms);
            final double _tmpLegs;
            _tmpLegs = _cursor.getDouble(_cursorIndexOfLegs);
            _result = new BodyMetricsEntity(_tmpDate,_tmpWeight,_tmpBodyFat,_tmpChest,_tmpWaist,_tmpArms,_tmpLegs);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
