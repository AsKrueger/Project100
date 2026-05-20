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
public final class PunishmentViewModel_Factory implements Factory<PunishmentViewModel> {
  private final Provider<WorkoutRepository> repositoryProvider;

  public PunishmentViewModel_Factory(Provider<WorkoutRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public PunishmentViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static PunishmentViewModel_Factory create(Provider<WorkoutRepository> repositoryProvider) {
    return new PunishmentViewModel_Factory(repositoryProvider);
  }

  public static PunishmentViewModel newInstance(WorkoutRepository repository) {
    return new PunishmentViewModel(repository);
  }
}
