package com.example.project100.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.example.project100.data.local.dao.BodyMetricsDao;
import com.example.project100.data.local.dao.BodyMetricsDao_Impl;
import com.example.project100.data.local.dao.PunishmentDao;
import com.example.project100.data.local.dao.PunishmentDao_Impl;
import com.example.project100.data.local.dao.SettingsDao;
import com.example.project100.data.local.dao.SettingsDao_Impl;
import com.example.project100.data.local.dao.UserProfileDao;
import com.example.project100.data.local.dao.UserProfileDao_Impl;
import com.example.project100.data.local.dao.WorkoutDao;
import com.example.project100.data.local.dao.WorkoutDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile WorkoutDao _workoutDao;

  private volatile PunishmentDao _punishmentDao;

  private volatile UserProfileDao _userProfileDao;

  private volatile BodyMetricsDao _bodyMetricsDao;

  private volatile SettingsDao _settingsDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `workouts` (`date` TEXT NOT NULL, `pushUps` INTEGER NOT NULL, `sitUps` INTEGER NOT NULL, `squats` INTEGER NOT NULL, `runningKm` REAL NOT NULL, `isCompleted` INTEGER NOT NULL, PRIMARY KEY(`date`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `punishments` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `dateGenerated` TEXT NOT NULL, `totalBurpees` INTEGER NOT NULL, `completedBurpees` INTEGER NOT NULL, `isCleared` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `user_profile` (`id` INTEGER NOT NULL, `username` TEXT NOT NULL, `profilePictureUri` TEXT, `currentStreak` INTEGER NOT NULL, `highestStreak` INTEGER NOT NULL, `currentRank` TEXT NOT NULL, `totalPunishmentBurpeesCompleted` INTEGER NOT NULL, `joinDate` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `body_metrics` (`date` TEXT NOT NULL, `weight` REAL NOT NULL, `bodyFat` REAL NOT NULL, `chest` REAL NOT NULL, `waist` REAL NOT NULL, `arms` REAL NOT NULL, `legs` REAL NOT NULL, PRIMARY KEY(`date`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `settings` (`id` INTEGER NOT NULL, `isSoundEnabled` INTEGER NOT NULL, `isNotificationsEnabled` INTEGER NOT NULL, `preferredUnit` TEXT NOT NULL, `lastSyncTimestamp` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'af913c99a3fc69dfb56f9a7afd3d258c')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `workouts`");
        db.execSQL("DROP TABLE IF EXISTS `punishments`");
        db.execSQL("DROP TABLE IF EXISTS `user_profile`");
        db.execSQL("DROP TABLE IF EXISTS `body_metrics`");
        db.execSQL("DROP TABLE IF EXISTS `settings`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsWorkouts = new HashMap<String, TableInfo.Column>(6);
        _columnsWorkouts.put("date", new TableInfo.Column("date", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkouts.put("pushUps", new TableInfo.Column("pushUps", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkouts.put("sitUps", new TableInfo.Column("sitUps", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkouts.put("squats", new TableInfo.Column("squats", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkouts.put("runningKm", new TableInfo.Column("runningKm", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkouts.put("isCompleted", new TableInfo.Column("isCompleted", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysWorkouts = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesWorkouts = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoWorkouts = new TableInfo("workouts", _columnsWorkouts, _foreignKeysWorkouts, _indicesWorkouts);
        final TableInfo _existingWorkouts = TableInfo.read(db, "workouts");
        if (!_infoWorkouts.equals(_existingWorkouts)) {
          return new RoomOpenHelper.ValidationResult(false, "workouts(com.example.project100.data.local.entity.WorkoutEntity).\n"
                  + " Expected:\n" + _infoWorkouts + "\n"
                  + " Found:\n" + _existingWorkouts);
        }
        final HashMap<String, TableInfo.Column> _columnsPunishments = new HashMap<String, TableInfo.Column>(5);
        _columnsPunishments.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPunishments.put("dateGenerated", new TableInfo.Column("dateGenerated", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPunishments.put("totalBurpees", new TableInfo.Column("totalBurpees", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPunishments.put("completedBurpees", new TableInfo.Column("completedBurpees", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPunishments.put("isCleared", new TableInfo.Column("isCleared", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPunishments = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPunishments = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPunishments = new TableInfo("punishments", _columnsPunishments, _foreignKeysPunishments, _indicesPunishments);
        final TableInfo _existingPunishments = TableInfo.read(db, "punishments");
        if (!_infoPunishments.equals(_existingPunishments)) {
          return new RoomOpenHelper.ValidationResult(false, "punishments(com.example.project100.data.local.entity.PunishmentEntity).\n"
                  + " Expected:\n" + _infoPunishments + "\n"
                  + " Found:\n" + _existingPunishments);
        }
        final HashMap<String, TableInfo.Column> _columnsUserProfile = new HashMap<String, TableInfo.Column>(8);
        _columnsUserProfile.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("username", new TableInfo.Column("username", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("profilePictureUri", new TableInfo.Column("profilePictureUri", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("currentStreak", new TableInfo.Column("currentStreak", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("highestStreak", new TableInfo.Column("highestStreak", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("currentRank", new TableInfo.Column("currentRank", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("totalPunishmentBurpeesCompleted", new TableInfo.Column("totalPunishmentBurpeesCompleted", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("joinDate", new TableInfo.Column("joinDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserProfile = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUserProfile = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserProfile = new TableInfo("user_profile", _columnsUserProfile, _foreignKeysUserProfile, _indicesUserProfile);
        final TableInfo _existingUserProfile = TableInfo.read(db, "user_profile");
        if (!_infoUserProfile.equals(_existingUserProfile)) {
          return new RoomOpenHelper.ValidationResult(false, "user_profile(com.example.project100.data.local.entity.UserProfileEntity).\n"
                  + " Expected:\n" + _infoUserProfile + "\n"
                  + " Found:\n" + _existingUserProfile);
        }
        final HashMap<String, TableInfo.Column> _columnsBodyMetrics = new HashMap<String, TableInfo.Column>(7);
        _columnsBodyMetrics.put("date", new TableInfo.Column("date", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBodyMetrics.put("weight", new TableInfo.Column("weight", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBodyMetrics.put("bodyFat", new TableInfo.Column("bodyFat", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBodyMetrics.put("chest", new TableInfo.Column("chest", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBodyMetrics.put("waist", new TableInfo.Column("waist", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBodyMetrics.put("arms", new TableInfo.Column("arms", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBodyMetrics.put("legs", new TableInfo.Column("legs", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBodyMetrics = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBodyMetrics = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoBodyMetrics = new TableInfo("body_metrics", _columnsBodyMetrics, _foreignKeysBodyMetrics, _indicesBodyMetrics);
        final TableInfo _existingBodyMetrics = TableInfo.read(db, "body_metrics");
        if (!_infoBodyMetrics.equals(_existingBodyMetrics)) {
          return new RoomOpenHelper.ValidationResult(false, "body_metrics(com.example.project100.data.local.entity.BodyMetricsEntity).\n"
                  + " Expected:\n" + _infoBodyMetrics + "\n"
                  + " Found:\n" + _existingBodyMetrics);
        }
        final HashMap<String, TableInfo.Column> _columnsSettings = new HashMap<String, TableInfo.Column>(5);
        _columnsSettings.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSettings.put("isSoundEnabled", new TableInfo.Column("isSoundEnabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSettings.put("isNotificationsEnabled", new TableInfo.Column("isNotificationsEnabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSettings.put("preferredUnit", new TableInfo.Column("preferredUnit", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSettings.put("lastSyncTimestamp", new TableInfo.Column("lastSyncTimestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSettings = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSettings = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSettings = new TableInfo("settings", _columnsSettings, _foreignKeysSettings, _indicesSettings);
        final TableInfo _existingSettings = TableInfo.read(db, "settings");
        if (!_infoSettings.equals(_existingSettings)) {
          return new RoomOpenHelper.ValidationResult(false, "settings(com.example.project100.data.local.entity.SettingsEntity).\n"
                  + " Expected:\n" + _infoSettings + "\n"
                  + " Found:\n" + _existingSettings);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "af913c99a3fc69dfb56f9a7afd3d258c", "9b4cc233a1d85bc862b6b53cb58d60dc");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "workouts","punishments","user_profile","body_metrics","settings");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `workouts`");
      _db.execSQL("DELETE FROM `punishments`");
      _db.execSQL("DELETE FROM `user_profile`");
      _db.execSQL("DELETE FROM `body_metrics`");
      _db.execSQL("DELETE FROM `settings`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(WorkoutDao.class, WorkoutDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PunishmentDao.class, PunishmentDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(UserProfileDao.class, UserProfileDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(BodyMetricsDao.class, BodyMetricsDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(SettingsDao.class, SettingsDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public WorkoutDao workoutDao() {
    if (_workoutDao != null) {
      return _workoutDao;
    } else {
      synchronized(this) {
        if(_workoutDao == null) {
          _workoutDao = new WorkoutDao_Impl(this);
        }
        return _workoutDao;
      }
    }
  }

  @Override
  public PunishmentDao punishmentDao() {
    if (_punishmentDao != null) {
      return _punishmentDao;
    } else {
      synchronized(this) {
        if(_punishmentDao == null) {
          _punishmentDao = new PunishmentDao_Impl(this);
        }
        return _punishmentDao;
      }
    }
  }

  @Override
  public UserProfileDao userProfileDao() {
    if (_userProfileDao != null) {
      return _userProfileDao;
    } else {
      synchronized(this) {
        if(_userProfileDao == null) {
          _userProfileDao = new UserProfileDao_Impl(this);
        }
        return _userProfileDao;
      }
    }
  }

  @Override
  public BodyMetricsDao bodyMetricsDao() {
    if (_bodyMetricsDao != null) {
      return _bodyMetricsDao;
    } else {
      synchronized(this) {
        if(_bodyMetricsDao == null) {
          _bodyMetricsDao = new BodyMetricsDao_Impl(this);
        }
        return _bodyMetricsDao;
      }
    }
  }

  @Override
  public SettingsDao settingsDao() {
    if (_settingsDao != null) {
      return _settingsDao;
    } else {
      synchronized(this) {
        if(_settingsDao == null) {
          _settingsDao = new SettingsDao_Impl(this);
        }
        return _settingsDao;
      }
    }
  }
}
