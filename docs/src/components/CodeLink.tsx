import React from 'react';

const CodeLink: React.FC<{
  children: React.ReactNode,
  href: string
}> = ({children, href}) => (
  <a href={`/koject/api${href}`} target="_blank"><code>{children}</code></a>
);

export const Provides: React.FC = () => (
  <CodeLink href="/core/com.moriatsushi.koject/-provides/index.html">
    @Provides
  </CodeLink>
);

export const Singleton: React.FC = () => (
  <CodeLink href="/core/com.moriatsushi.koject/-singleton/index.html">
    @Singleton
  </CodeLink>
);

export const Inject: React.FC = () => (
  <CodeLink href="/core/com.moriatsushi.koject/inject.html">
    inject()
  </CodeLink>
);

export const ViewModelComponent: React.FC = () => (
  <CodeLink href="/android/viewmodel/com.moriatsushi.koject.android.viewmodel/-view-model-component/index.html">
    @ViewModelComponent
  </CodeLink>
);

export const ActivityInjectViewModels: React.FC = () => (
  <CodeLink href="/android/activity/com.moriatsushi.koject.android.activity/inject-view-models.html">
    ComponentActivity.injectViewModels()
  </CodeLink>
);

export const FragmentInjectViewModels: React.FC = () => (
  <CodeLink href="/android/fragment/com.moriatsushi.koject.android.fragment/inject-view-models.html">
    Fragment.injectViewModels()
  </CodeLink>
);

export const RememberInject: React.FC = () => (
  <CodeLink href="/compose/core/com.moriatsushi.koject.compose/remember-inject.html">
    rememberInject()
  </CodeLink>
);
