package com.example.project100;

import androidx.hilt.work.HiltWorkerFactory;
import com.example.project100.system.SystemManager;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class Project100App_MembersInjector implements MembersInjector<Project100App> {
  private final Provider<HiltWorkerFactory> workerFactoryProvider;

  private final Provider<SystemManager> systemManagerProvider;

  public Project100App_MembersInjector(Provider<HiltWorkerFactory> workerFactoryProvider,
      Provider<SystemManager> systemManagerProvider) {
    this.workerFactoryProvider = workerFactoryProvider;
    this.systemManagerProvider = systemManagerProvider;
  }

  public static MembersInjector<Project100App> create(
      Provider<HiltWorkerFactory> workerFactoryProvider,
      Provider<SystemManager> systemManagerProvider) {
    return new Project100App_MembersInjector(workerFactoryProvider, systemManagerProvider);
  }

  @Override
  public void injectMembers(Project100App instance) {
    injectWorkerFactory(instance, workerFactoryProvider.get());
    injectSystemManager(instance, systemManagerProvider.get());
  }

  @InjectedFieldSignature("com.example.project100.Project100App.workerFactory")
  public static void injectWorkerFactory(Project100App instance, HiltWorkerFactory workerFactory) {
    instance.workerFactory = workerFactory;
  }

  @InjectedFieldSignature("com.example.project100.Project100App.systemManager")
  public static void injectSystemManager(Project100App instance, SystemManager systemManager) {
    instance.systemManager = systemManager;
  }
}
