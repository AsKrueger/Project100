package com.example.project100;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.hilt.work.HiltWorkerFactory;
import androidx.hilt.work.WorkerAssistedFactory;
import androidx.hilt.work.WorkerFactoryModule_ProvideFactoryFactory;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import com.example.project100.data.local.AppDatabase;
import com.example.project100.data.local.dao.BodyMetricsDao;
import com.example.project100.data.local.dao.PunishmentDao;
import com.example.project100.data.local.dao.UserProfileDao;
import com.example.project100.data.local.dao.WorkoutDao;
import com.example.project100.data.repository.BodyMetricsRepository;
import com.example.project100.data.repository.WorkoutRepository;
import com.example.project100.di.DatabaseModule_ProvideBodyMetricsDaoFactory;
import com.example.project100.di.DatabaseModule_ProvideDatabaseFactory;
import com.example.project100.di.DatabaseModule_ProvidePunishmentDaoFactory;
import com.example.project100.di.DatabaseModule_ProvideUserProfileDaoFactory;
import com.example.project100.di.DatabaseModule_ProvideWorkoutDaoFactory;
import com.example.project100.system.DailyResetWorker;
import com.example.project100.system.DailyResetWorker_AssistedFactory;
import com.example.project100.system.SystemManager;
import com.example.project100.viewmodel.BodyMetricsViewModel;
import com.example.project100.viewmodel.BodyMetricsViewModel_HiltModules;
import com.example.project100.viewmodel.HistoryViewModel;
import com.example.project100.viewmodel.HistoryViewModel_HiltModules;
import com.example.project100.viewmodel.MainViewModel;
import com.example.project100.viewmodel.MainViewModel_HiltModules;
import com.example.project100.viewmodel.PunishmentViewModel;
import com.example.project100.viewmodel.PunishmentViewModel_HiltModules;
import com.example.project100.viewmodel.TrainingViewModel;
import com.example.project100.viewmodel.TrainingViewModel_HiltModules;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.IdentifierNameString;
import dagger.internal.KeepFieldType;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.SingleCheck;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

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
public final class DaggerProject100App_HiltComponents_SingletonC {
  private DaggerProject100App_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public Project100App_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements Project100App_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public Project100App_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements Project100App_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public Project100App_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements Project100App_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public Project100App_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements Project100App_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public Project100App_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements Project100App_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public Project100App_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements Project100App_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public Project100App_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements Project100App_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public Project100App_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends Project100App_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends Project100App_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends Project100App_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends Project100App_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(MapBuilder.<String, Boolean>newMapBuilder(5).put(LazyClassKeyProvider.com_example_project100_viewmodel_BodyMetricsViewModel, BodyMetricsViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_example_project100_viewmodel_HistoryViewModel, HistoryViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_example_project100_viewmodel_MainViewModel, MainViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_example_project100_viewmodel_PunishmentViewModel, PunishmentViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_example_project100_viewmodel_TrainingViewModel, TrainingViewModel_HiltModules.KeyModule.provide()).build());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_example_project100_viewmodel_MainViewModel = "com.example.project100.viewmodel.MainViewModel";

      static String com_example_project100_viewmodel_PunishmentViewModel = "com.example.project100.viewmodel.PunishmentViewModel";

      static String com_example_project100_viewmodel_HistoryViewModel = "com.example.project100.viewmodel.HistoryViewModel";

      static String com_example_project100_viewmodel_TrainingViewModel = "com.example.project100.viewmodel.TrainingViewModel";

      static String com_example_project100_viewmodel_BodyMetricsViewModel = "com.example.project100.viewmodel.BodyMetricsViewModel";

      @KeepFieldType
      MainViewModel com_example_project100_viewmodel_MainViewModel2;

      @KeepFieldType
      PunishmentViewModel com_example_project100_viewmodel_PunishmentViewModel2;

      @KeepFieldType
      HistoryViewModel com_example_project100_viewmodel_HistoryViewModel2;

      @KeepFieldType
      TrainingViewModel com_example_project100_viewmodel_TrainingViewModel2;

      @KeepFieldType
      BodyMetricsViewModel com_example_project100_viewmodel_BodyMetricsViewModel2;
    }
  }

  private static final class ViewModelCImpl extends Project100App_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<BodyMetricsViewModel> bodyMetricsViewModelProvider;

    private Provider<HistoryViewModel> historyViewModelProvider;

    private Provider<MainViewModel> mainViewModelProvider;

    private Provider<PunishmentViewModel> punishmentViewModelProvider;

    private Provider<TrainingViewModel> trainingViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.bodyMetricsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.historyViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.mainViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.punishmentViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.trainingViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(MapBuilder.<String, javax.inject.Provider<ViewModel>>newMapBuilder(5).put(LazyClassKeyProvider.com_example_project100_viewmodel_BodyMetricsViewModel, ((Provider) bodyMetricsViewModelProvider)).put(LazyClassKeyProvider.com_example_project100_viewmodel_HistoryViewModel, ((Provider) historyViewModelProvider)).put(LazyClassKeyProvider.com_example_project100_viewmodel_MainViewModel, ((Provider) mainViewModelProvider)).put(LazyClassKeyProvider.com_example_project100_viewmodel_PunishmentViewModel, ((Provider) punishmentViewModelProvider)).put(LazyClassKeyProvider.com_example_project100_viewmodel_TrainingViewModel, ((Provider) trainingViewModelProvider)).build());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return Collections.<Class<?>, Object>emptyMap();
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_example_project100_viewmodel_MainViewModel = "com.example.project100.viewmodel.MainViewModel";

      static String com_example_project100_viewmodel_PunishmentViewModel = "com.example.project100.viewmodel.PunishmentViewModel";

      static String com_example_project100_viewmodel_BodyMetricsViewModel = "com.example.project100.viewmodel.BodyMetricsViewModel";

      static String com_example_project100_viewmodel_HistoryViewModel = "com.example.project100.viewmodel.HistoryViewModel";

      static String com_example_project100_viewmodel_TrainingViewModel = "com.example.project100.viewmodel.TrainingViewModel";

      @KeepFieldType
      MainViewModel com_example_project100_viewmodel_MainViewModel2;

      @KeepFieldType
      PunishmentViewModel com_example_project100_viewmodel_PunishmentViewModel2;

      @KeepFieldType
      BodyMetricsViewModel com_example_project100_viewmodel_BodyMetricsViewModel2;

      @KeepFieldType
      HistoryViewModel com_example_project100_viewmodel_HistoryViewModel2;

      @KeepFieldType
      TrainingViewModel com_example_project100_viewmodel_TrainingViewModel2;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.example.project100.viewmodel.BodyMetricsViewModel 
          return (T) new BodyMetricsViewModel(singletonCImpl.bodyMetricsRepositoryProvider.get());

          case 1: // com.example.project100.viewmodel.HistoryViewModel 
          return (T) new HistoryViewModel(singletonCImpl.workoutRepositoryProvider.get());

          case 2: // com.example.project100.viewmodel.MainViewModel 
          return (T) new MainViewModel(singletonCImpl.workoutRepositoryProvider.get());

          case 3: // com.example.project100.viewmodel.PunishmentViewModel 
          return (T) new PunishmentViewModel(singletonCImpl.workoutRepositoryProvider.get());

          case 4: // com.example.project100.viewmodel.TrainingViewModel 
          return (T) new TrainingViewModel(singletonCImpl.workoutRepositoryProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends Project100App_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends Project100App_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends Project100App_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<AppDatabase> provideDatabaseProvider;

    private Provider<DailyResetWorker_AssistedFactory> dailyResetWorker_AssistedFactoryProvider;

    private Provider<SystemManager> systemManagerProvider;

    private Provider<BodyMetricsRepository> bodyMetricsRepositoryProvider;

    private Provider<WorkoutRepository> workoutRepositoryProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    private WorkoutDao workoutDao() {
      return DatabaseModule_ProvideWorkoutDaoFactory.provideWorkoutDao(provideDatabaseProvider.get());
    }

    private PunishmentDao punishmentDao() {
      return DatabaseModule_ProvidePunishmentDaoFactory.providePunishmentDao(provideDatabaseProvider.get());
    }

    private UserProfileDao userProfileDao() {
      return DatabaseModule_ProvideUserProfileDaoFactory.provideUserProfileDao(provideDatabaseProvider.get());
    }

    private Map<String, javax.inject.Provider<WorkerAssistedFactory<? extends ListenableWorker>>> mapOfStringAndProviderOfWorkerAssistedFactoryOf(
        ) {
      return Collections.<String, javax.inject.Provider<WorkerAssistedFactory<? extends ListenableWorker>>>singletonMap("com.example.project100.system.DailyResetWorker", ((Provider) dailyResetWorker_AssistedFactoryProvider));
    }

    private HiltWorkerFactory hiltWorkerFactory() {
      return WorkerFactoryModule_ProvideFactoryFactory.provideFactory(mapOfStringAndProviderOfWorkerAssistedFactoryOf());
    }

    private BodyMetricsDao bodyMetricsDao() {
      return DatabaseModule_ProvideBodyMetricsDaoFactory.provideBodyMetricsDao(provideDatabaseProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<AppDatabase>(singletonCImpl, 1));
      this.dailyResetWorker_AssistedFactoryProvider = SingleCheck.provider(new SwitchingProvider<DailyResetWorker_AssistedFactory>(singletonCImpl, 0));
      this.systemManagerProvider = DoubleCheck.provider(new SwitchingProvider<SystemManager>(singletonCImpl, 2));
      this.bodyMetricsRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<BodyMetricsRepository>(singletonCImpl, 3));
      this.workoutRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<WorkoutRepository>(singletonCImpl, 4));
    }

    @Override
    public void injectProject100App(Project100App project100App) {
      injectProject100App2(project100App);
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private Project100App injectProject100App2(Project100App instance) {
      Project100App_MembersInjector.injectWorkerFactory(instance, hiltWorkerFactory());
      Project100App_MembersInjector.injectSystemManager(instance, systemManagerProvider.get());
      return instance;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.example.project100.system.DailyResetWorker_AssistedFactory 
          return (T) new DailyResetWorker_AssistedFactory() {
            @Override
            public DailyResetWorker create(Context context, WorkerParameters workerParams) {
              return new DailyResetWorker(context, workerParams, singletonCImpl.workoutDao(), singletonCImpl.punishmentDao(), singletonCImpl.userProfileDao());
            }
          };

          case 1: // com.example.project100.data.local.AppDatabase 
          return (T) DatabaseModule_ProvideDatabaseFactory.provideDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 2: // com.example.project100.system.SystemManager 
          return (T) new SystemManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 3: // com.example.project100.data.repository.BodyMetricsRepository 
          return (T) new BodyMetricsRepository(singletonCImpl.bodyMetricsDao());

          case 4: // com.example.project100.data.repository.WorkoutRepository 
          return (T) new WorkoutRepository(singletonCImpl.workoutDao(), singletonCImpl.punishmentDao(), singletonCImpl.userProfileDao());

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
