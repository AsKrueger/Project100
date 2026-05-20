package com.example.project100.viewmodel;

import com.example.project100.data.repository.BodyMetricsRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class BodyMetricsViewModel_Factory implements Factory<BodyMetricsViewModel> {
  private final Provider<BodyMetricsRepository> repositoryProvider;

  public BodyMetricsViewModel_Factory(Provider<BodyMetricsRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public BodyMetricsViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static BodyMetricsViewModel_Factory create(
      Provider<BodyMetricsRepository> repositoryProvider) {
    return new BodyMetricsViewModel_Factory(repositoryProvider);
  }

  public static BodyMetricsViewModel newInstance(BodyMetricsRepository repository) {
    return new BodyMetricsViewModel(repository);
  }
}
