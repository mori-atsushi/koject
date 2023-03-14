import React from 'react';

const CodeLink: React.FC<{
  children: React.ReactNode,
  href: string
}> = ({children, href}) => (
  <a href={`/koject/api${href}`} target="_blank"><code>{children}</code></a>
);

export const Provides: React.FC = () => (
  <CodeLink href="/koject-core/com.moriatsushi.koject/-provides/index.html">
    @Provides
  </CodeLink>
);

export const Singleton: React.FC = () => (
  <CodeLink href="/koject-core/com.moriatsushi.koject/-singleton/index.html">
    @Singleton
  </CodeLink>
);

export const Qualifier: React.FC = () => (
  <CodeLink href="/koject-core/com.moriatsushi.koject/-qualifier/index.html">
    @Qualifier
  </CodeLink>
);

export const Named: React.FC = () => (
  <CodeLink href="/koject-core/com.moriatsushi.koject/-named/index.html">
    @Named
  </CodeLink>
);

export const Binds: React.FC = () => (
  <CodeLink href="/koject-core/com.moriatsushi.koject/-binds/index.html">
    @Binds
  </CodeLink>
);

export const KojectStart: React.FC = () => (
  <CodeLink href="/koject-core/com.moriatsushi.koject/start.html">
    Koject.start()
  </CodeLink>
);

export const Inject: React.FC = () => (
  <CodeLink href="/koject-core/com.moriatsushi.koject/inject.html">
    inject()
  </CodeLink>
);

export const LazyInject: React.FC = () => (
  <CodeLink href="/koject-core/com.moriatsushi.koject/lazy-inject.html">
    lazyInject()
  </CodeLink>
);

export const KojectExtras: React.FC = () => (
  <CodeLink href="/koject-core/com.moriatsushi.koject.extras/-koject-extras/index.html">
    KojectExtras
  </CodeLink>
);

export const KojectExtrasMessage: React.FC = () => (
  <CodeLink href="/koject-core/com.moriatsushi.koject.extras/-koject-extras-message/index.html">
    @KojectExtrasMessage
  </CodeLink>
);

export const Component: React.FC = () => (
  <CodeLink href="/koject-core/com.moriatsushi.koject.component/-component/index.html">
    @Component
  </CodeLink>
);

export const ComponentExtras: React.FC = () => (
  <CodeLink href="/koject-core/com.moriatsushi.koject.component/-component-extras/index.html">
    ComponentExtras
  </CodeLink>
);

export const MissingExtrasException: React.FC = () => (
  <CodeLink href="/koject-core/com.moriatsushi.koject.error/-missing-extras-exception/index.html">
    MissingExtrasException
  </CodeLink>
);

export const AndroidApplication: React.FC = () => (
  <CodeLink href="/android/koject-android-core/com.moriatsushi.koject.android/application.html">
    application()
  </CodeLink>
);

export const ViewModelComponent: React.FC = () => (
  <CodeLink href="/android/koject-android-viewmodel/com.moriatsushi.koject.android.viewmodel/-view-model-component/index.html">
    @ViewModelComponent
  </CodeLink>
);

export const ViewModelCoroutineScope: React.FC = () => (
  <CodeLink href="/android/koject-android-viewmodel/com.moriatsushi.koject.android.viewmodel/-view-model-coroutine-scope/index.html">
    @ViewModelCoroutineScope
  </CodeLink>
);

export const ActivityInject: React.FC = () => (
  <CodeLink href="/android/koject-android-activity/com.moriatsushi.koject/inject.html">
    ComponentActivity.inject()
  </CodeLink>
);

export const ActivityLazyInject: React.FC = () => (
  <CodeLink href="/android/koject-android-activity/com.moriatsushi.koject/lazy-inject.html">
    ComponentActivity.lazyInject()
  </CodeLink>
);

export const ActivityComponent: React.FC = () => (
  <CodeLink href="/android/koject-android-activity/com.moriatsushi.koject.android.activity/-activity-component/index.html">
    @ActivityComponent
  </CodeLink>
);

export const ActivityContext: React.FC = () => (
  <CodeLink href="/android/koject-android-activity/com.moriatsushi.koject.android.activity/-activity-context/index.html">
    @ActivityContext
  </CodeLink>
);

export const ActivityCoroutineScope: React.FC = () => (
  <CodeLink href="/android/koject-android-activity/com.moriatsushi.koject.android.activity/-activity-context/index.html">
    @ActivityCoroutineScope
  </CodeLink>
);

export const ActivityLazyViewModels: React.FC = () => (
  <CodeLink href="/android/koject-android-activity/com.moriatsushi.koject.android.activity/lazy-view-models.html">
    ComponentActivity.lazyViewModels()
  </CodeLink>
);

export const ActivityInjectViewModels: React.FC = () => (
  <CodeLink href="/android/koject-android-activity/com.moriatsushi.koject.android.activity/inject-view-models.html">
    ComponentActivity.injectViewModels()
  </CodeLink>
);

export const FragmentInject: React.FC = () => (
  <CodeLink href="/android/koject-android-fragment/com.moriatsushi.koject/inject.html">
    Fragment.inject()
  </CodeLink>
);

export const FragmentLazyInject: React.FC = () => (
  <CodeLink href="/android/koject-android-fragment/com.moriatsushi.koject/lazy-inject.html">
    Fragment.lazyInject()
  </CodeLink>
);

export const FragmentComponent: React.FC = () => (
  <CodeLink href="/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/-fragment-component/index.html">
    @FragmentComponent
  </CodeLink>
);

export const FragmentCoroutineScope: React.FC = () => (
  <CodeLink href="/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/-fragment-coroutine-scope/index.html">
    @FragmentCoroutineScope
  </CodeLink>
);

export const FragmentViewCoroutineScope: React.FC = () => (
  <CodeLink href="/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/-fragment-view-coroutine-scope/index.html">
    @FragmentViewCoroutineScope
  </CodeLink>
);

export const FragmentLazyViewModels: React.FC = () => (
  <CodeLink href="/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/lazy-view-models.html">
    Fragment.lazyViewModels()
  </CodeLink>
);

export const FragmentInjectViewModels: React.FC = () => (
  <CodeLink href="/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/inject-view-models.html">
    Fragment.injectViewModels()
  </CodeLink>
);

export const RememberInject: React.FC = () => (
  <CodeLink href="/compose/koject-compose-core/com.moriatsushi.koject.compose/remember-inject.html">
    rememberInject()
  </CodeLink>
);

export const InjectViewModel: React.FC = () => (
  <CodeLink href="/compose/koject-compose-viewmodel/com.moriatsushi.koject.compose.viewmodel/inject-view-model.html">
    injectViewModel()
  </CodeLink>
);

export const ComposeComponent: React.FC = () => (
  <CodeLink href="/compose/koject-compose-core/com.moriatsushi.koject.compose/-compose-component/index.html">
    @ComposeComponent
  </CodeLink>
);

export const ComposeCoroutineScope: React.FC = () => (
  <CodeLink href="/compose/koject-compose-core/com.moriatsushi.koject.compose/-compose-coroutine-scope/index.html">
    @ComposeCoroutineScope
  </CodeLink>
);

export const ComposeContext: React.FC = () => (
  <CodeLink href="/compose/koject-compose-core/com.moriatsushi.koject.compose/-compose-context/index.html">
    @ComposeContext
  </CodeLink>
);
