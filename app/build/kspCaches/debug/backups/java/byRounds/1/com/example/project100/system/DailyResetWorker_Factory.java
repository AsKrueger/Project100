package com.example.project100.system;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.example.project100.data.local.dao.PunishmentDao;
import com.example.project100.data.local.dao.UserProfileDao;
import com.example.project100.data.local.dao.WorkoutDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class DailyResetWorker_Factory {
  private final Provider<WorkoutDao> workoutDaoProvider;

  private final Provider<PunishmentDao> punishmentDaoProvider;

  private final Provider<UserProfileDao> userProfileDaoProvider;

  public DailyResetWorker_Factory(Provider<WorkoutDao> workoutDaoProvider,
      Provider<PunishmentDao> punishmentDaoProvider,
      Provider<UserProfileDao> userProfileDaoProvider) {
    this.workoutDaoProvider = workoutDaoProvider;
    this.punishmentDaoProvider = punishmentDaoProvider;
    this.userProfileDaoProvider = userProfileDaoProvider;
  }

  public DailyResetWorker get(Context context, WorkerParameters workerParams) {
    return newInstance(context, workerParams, workoutDaoProvider.get(), punishmentDaoProvider.get(), userProfileDaoProvider.get());
  }

  public static DailyResetWorker_Factory create(Provider<WorkoutDao> workoutDaoProvider,
      Provider<PunishmentDao> punishmentDaoProvider,
      Provider<UserProfileDao> userProfileDaoProvider) {
    return new DailyResetWorker_Factory(workoutDaoProvider, punishmentDaoProvider, userProfileDaoProvider);
  }

  public static DailyResetWorker newInstance(Context context, WorkerParameters workerParams,
      WorkoutDao workoutDao, PunishmentDao punishmentDao, UserProfileDao userProfileDao) {
    return new DailyResetWorker(context, workerParams, workoutDao, punishmentDao, userProfileDao);
  }
}
