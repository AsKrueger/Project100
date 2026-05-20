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
import com.example.project100.data.local.entity.UserProfileEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class UserProfileDao_Impl implements UserProfileDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UserProfileEntity> __insertionAdapterOfUserProfileEntity;

  public UserProfileDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserProfileEntity = new EntityInsertionAdapter<UserProfileEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `user_profile` (`id`,`username`,`profilePictureUri`,`currentStreak`,`highestStreak`,`currentRank`,`totalPunishmentBurpeesCompleted`,`joinDate`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserProfileEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getUsername());
        if (entity.getProfilePictureUri() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getProfilePictureUri());
        }
        statement.bindLong(4, entity.getCurrentStreak());
        statement.bindLong(5, entity.getHighestStreak());
        statement.bindString(6, entity.getCurrentRank());
        statement.bindLong(7, entity.getTotalPunishmentBurpeesCompleted());
        statement.bindLong(8, entity.getJoinDate());
      }
    };
  }

  @Override
  public Object insertOrUpdateProfile(final UserProfileEntity profile,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUserProfileEntity.insert(profile);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<UserProfileEntity> getUserProfileFlow() {
    final String _sql = "SELECT * FROM user_profile WHERE id = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"user_profile"}, new Callable<UserProfileEntity>() {
      @Override
      @Nullable
      public UserProfileEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
          final int _cursorIndexOfProfilePictureUri = CursorUtil.getColumnIndexOrThrow(_cursor, "profilePictureUri");
          final int _cursorIndexOfCurrentStreak = CursorUtil.getColumnIndexOrThrow(_cursor, "currentStreak");
          final int _cursorIndexOfHighestStreak = CursorUtil.getColumnIndexOrThrow(_cursor, "highestStreak");
          final int _cursorIndexOfCurrentRank = CursorUtil.getColumnIndexOrThrow(_cursor, "currentRank");
          final int _cursorIndexOfTotalPunishmentBurpeesCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "totalPunishmentBurpeesCompleted");
          final int _cursorIndexOfJoinDate = CursorUtil.getColumnIndexOrThrow(_cursor, "joinDate");
          final UserProfileEntity _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpUsername;
            _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
            final String _tmpProfilePictureUri;
            if (_cursor.isNull(_cursorIndexOfProfilePictureUri)) {
              _tmpProfilePictureUri = null;
            } else {
              _tmpProfilePictureUri = _cursor.getString(_cursorIndexOfProfilePictureUri);
            }
            final int _tmpCurrentStreak;
            _tmpCurrentStreak = _cursor.getInt(_cursorIndexOfCurrentStreak);
            final int _tmpHighestStreak;
            _tmpHighestStreak = _cursor.getInt(_cursorIndexOfHighestStreak);
            final String _tmpCurrentRank;
            _tmpCurrentRank = _cursor.getString(_cursorIndexOfCurrentRank);
            final int _tmpTotalPunishmentBurpeesCompleted;
            _tmpTotalPunishmentBurpeesCompleted = _cursor.getInt(_cursorIndexOfTotalPunishmentBurpeesCompleted);
            final long _tmpJoinDate;
            _tmpJoinDate = _cursor.getLong(_cursorIndexOfJoinDate);
            _result = new UserProfileEntity(_tmpId,_tmpUsername,_tmpProfilePictureUri,_tmpCurrentStreak,_tmpHighestStreak,_tmpCurrentRank,_tmpTotalPunishmentBurpeesCompleted,_tmpJoinDate);
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
  public Object getUserProfile(final Continuation<? super UserProfileEntity> $completion) {
    final String _sql = "SELECT * FROM user_profile WHERE id = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UserProfileEntity>() {
      @Override
      @Nullable
      public UserProfileEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
          final int _cursorIndexOfProfilePictureUri = CursorUtil.getColumnIndexOrThrow(_cursor, "profilePictureUri");
          final int _cursorIndexOfCurrentStreak = CursorUtil.getColumnIndexOrThrow(_cursor, "currentStreak");
          final int _cursorIndexOfHighestStreak = CursorUtil.getColumnIndexOrThrow(_cursor, "highestStreak");
          final int _cursorIndexOfCurrentRank = CursorUtil.getColumnIndexOrThrow(_cursor, "currentRank");
          final int _cursorIndexOfTotalPunishmentBurpeesCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "totalPunishmentBurpeesCompleted");
          final int _cursorIndexOfJoinDate = CursorUtil.getColumnIndexOrThrow(_cursor, "joinDate");
          final UserProfileEntity _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpUsername;
            _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
            final String _tmpProfilePictureUri;
            if (_cursor.isNull(_cursorIndexOfProfilePictureUri)) {
              _tmpProfilePictureUri = null;
            } else {
              _tmpProfilePictureUri = _cursor.getString(_cursorIndexOfProfilePictureUri);
            }
            final int _tmpCurrentStreak;
            _tmpCurrentStreak = _cursor.getInt(_cursorIndexOfCurrentStreak);
            final int _tmpHighestStreak;
            _tmpHighestStreak = _cursor.getInt(_cursorIndexOfHighestStreak);
            final String _tmpCurrentRank;
            _tmpCurrentRank = _cursor.getString(_cursorIndexOfCurrentRank);
            final int _tmpTotalPunishmentBurpeesCompleted;
            _tmpTotalPunishmentBurpeesCompleted = _cursor.getInt(_cursorIndexOfTotalPunishmentBurpeesCompleted);
            final long _tmpJoinDate;
            _tmpJoinDate = _cursor.getLong(_cursorIndexOfJoinDate);
            _result = new UserProfileEntity(_tmpId,_tmpUsername,_tmpProfilePictureUri,_tmpCurrentStreak,_tmpHighestStreak,_tmpCurrentRank,_tmpTotalPunishmentBurpeesCompleted,_tmpJoinDate);
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
