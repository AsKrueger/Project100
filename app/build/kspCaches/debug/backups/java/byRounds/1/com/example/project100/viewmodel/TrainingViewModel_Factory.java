package com.example.project100.viewmodel;

import com.example.project100.data.repository.WorkoutRepository;
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
public final class TrainingViewModel_Factory implements Factory<TrainingViewModel> {
  private final Provider<WorkoutRepository> repositoryProvider;

  public TrainingViewModel_Factory(Provider<WorkoutRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public TrainingViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static TrainingViewModel_Factory create(Provider<WorkoutRepository> repositoryProvider) {
    return new TrainingViewModel_Factory(repositoryProvider);
  }

  public static TrainingViewModel newInstance(WorkoutRepository repository) {
    return new TrainingViewModel(repository);
  }
}
