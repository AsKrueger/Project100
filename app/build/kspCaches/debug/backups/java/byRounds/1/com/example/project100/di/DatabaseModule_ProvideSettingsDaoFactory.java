package com.example.project100.di;

import com.example.project100.data.local.AppDatabase;
import com.example.project100.data.local.dao.SettingsDao;
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
public final class DatabaseModule_ProvideSettingsDaoFactory implements Factory<SettingsDao> {
  private final Provider<AppDatabase> databaseProvider;

  public DatabaseModule_ProvideSettingsDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public SettingsDao get() {
    return provideSettingsDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideSettingsDaoFactory create(
      Provider<AppDatabase> databaseProvider) {
    return new DatabaseModule_ProvideSettingsDaoFactory(databaseProvider);
  }

  public static SettingsDao provideSettingsDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideSettingsDao(database));
  }
}
