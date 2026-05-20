package com.example.project100.di;

import com.example.project100.data.local.AppDatabase;
import com.example.project100.data.local.dao.BodyMetricsDao;
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
public final class DatabaseModule_ProvideBodyMetricsDaoFactory implements Factory<BodyMetricsDao> {
  private final Provider<AppDatabase> databaseProvider;

  public DatabaseModule_ProvideBodyMetricsDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public BodyMetricsDao get() {
    return provideBodyMetricsDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideBodyMetricsDaoFactory create(
      Provider<AppDatabase> databaseProvider) {
    return new DatabaseModule_ProvideBodyMetricsDaoFactory(databaseProvider);
  }

  public static BodyMetricsDao provideBodyMetricsDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideBodyMetricsDao(database));
  }
}
