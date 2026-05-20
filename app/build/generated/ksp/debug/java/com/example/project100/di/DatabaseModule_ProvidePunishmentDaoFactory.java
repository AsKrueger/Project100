package com.example.project100.di;

import com.example.project100.data.local.AppDatabase;
import com.example.project100.data.local.dao.PunishmentDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvidePunishmentDaoFactory implements Factory<PunishmentDao> {
  private final Provider<AppDatabase> databaseProvider;

  public DatabaseModule_ProvidePunishmentDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public PunishmentDao get() {
    return providePunishmentDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvidePunishmentDaoFactory create(
      Provider<AppDatabase> databaseProvider) {
    return new DatabaseModule_ProvidePunishmentDaoFactory(databaseProvider);
  }

  public static PunishmentDao providePunishmentDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.providePunishmentDao(database));
  }
}
