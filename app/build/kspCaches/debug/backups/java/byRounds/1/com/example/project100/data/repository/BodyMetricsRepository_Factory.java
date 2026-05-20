package com.example.project100.data.repository;

import com.example.project100.data.local.dao.BodyMetricsDao;
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
public final class BodyMetricsRepository_Factory implements Factory<BodyMetricsRepository> {
  private final Provider<BodyMetricsDao> bodyMetricsDaoProvider;

  public BodyMetricsRepository_Factory(Provider<BodyMetricsDao> bodyMetricsDaoProvider) {
    this.bodyMetricsDaoProvider = bodyMetricsDaoProvider;
  }

  @Override
  public BodyMetricsRepository get() {
    return newInstance(bodyMetricsDaoProvider.get());
  }

  public static BodyMetricsRepository_Factory create(
      Provider<BodyMetricsDao> bodyMetricsDaoProvider) {
    return new BodyMetricsRepository_Factory(bodyMetricsDaoProvider);
  }

  public static BodyMetricsRepository newInstance(BodyMetricsDao bodyMetricsDao) {
    return new BodyMetricsRepository(bodyMetricsDao);
  }
}
