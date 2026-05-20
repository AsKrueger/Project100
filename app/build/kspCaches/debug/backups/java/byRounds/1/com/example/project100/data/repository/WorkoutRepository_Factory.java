package com.example.project100.data.repository;

import com.example.project100.data.local.dao.PunishmentDao;
import com.example.project100.data.local.dao.UserProfileDao;
import com.example.project100.data.local.dao.WorkoutDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class WorkoutRepository_Factory implements Factory<WorkoutRepository> {
  private final Provider<WorkoutDao> workoutDaoProvider;

  private final Provider<PunishmentDao> punishmentDaoProvider;

  private final Provider<UserProfileDao> userProfileDaoProvider;

  public WorkoutRepository_Factory(Provider<WorkoutDao> workoutDaoProvider,
      Provider<PunishmentDao> punishmentDaoProvider,
      Provider<UserProfileDao> userProfileDaoProvider) {
    this.workoutDaoProvider = workoutDaoProvider;
    this.punishmentDaoProvider = punishmentDaoProvider;
    this.userProfileDaoProvider = userProfileDaoProvider;
  }

  @Override
  public WorkoutRepository get() {
    return newInstance(workoutDaoProvider.get(), punishmentDaoProvider.get(), userProfileDaoProvider.get());
  }

  public static WorkoutRepository_Factory create(Provider<WorkoutDao> workoutDaoProvider,
      Provider<PunishmentDao> punishmentDaoProvider,
      Provider<UserProfileDao> userProfileDaoProvider) {
    return new WorkoutRepository_Factory(workoutDaoProvider, punishmentDaoProvider, userProfileDaoProvider);
  }

  public static WorkoutRepository newInstance(WorkoutDao workoutDao, PunishmentDao punishmentDao,
      UserProfileDao userProfileDao) {
    return new WorkoutRepository(workoutDao, punishmentDao, userProfileDao);
  }
}
