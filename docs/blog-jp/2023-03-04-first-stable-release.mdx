---
slug: first-stable-release
title: Koject 1.0.0を公開しました！
authors: atsushi
image: /blog/2023-03-04/ogp.png
---

![](/blog/2023-03-04/banner.png)

この度、Kotlin Multiplatform向けの新しいDIコンテナライブラリ「**Koject**」を公開しました。
この記事では、DIコンテナの役割と「**Koject**」の特徴について紹介します。

<!--truncate-->

[**Read in English →**](/blog/first-stable-release)

## DIコンテナの役割
DIコンテナ（Dependency Injection Container）の役割を理解するためには、まずDependency Injectionについて知る必要があります。
Dependency Injectionは、クラス間の依存関係を外部から注入することで、**テストの容易性やコードの汎用性を向上させる**手法です。

### Dependency Injectionでテスト容易性を上げる
動画アップロードを行うスマホアプリを例に考えてみます。
ローカルにある動画を選択し、サーバーにアップロードが完了した後、OSの通知を表示します。

動画をアップロードする`VideoUploader`クラスと通知を表示する`NotificationManager`クラスを使って、`VideoUploadService`を実装してみます。

```kotlin
class VideoUploadService {
    fun upload(video: Video) {
        val result = VideoUploader().upload(video)
        if (result.isSucceeded) {
            NotificationManager()
                .showNotification("アップロード完了")
        } else {
            NotificationManager()
                .showNotification("アップロード失敗")
        }
    }
}
```

`VideoUploadService`の中で`VideoUploader`と `NotificationManager`をインスタンス化しています。このことを、`VideoUploadService`が`VideoUploader`と`NotificationManager` に直接依存していると言います。

`VideoUploader`はサーバーと通信し、`NotificationManager`はOSの通知を表示します。
このような外部とアクセスするクラスに直接依存すると、テスト時にも外部との通信が発生し、不安定になったり時間がかかるという問題が生じます。

Dependency Injectionは、こういったクラスを**外側から渡してあげる**という考え方です。
先程の例をDependency Injectionを使って書き直すと、以下のようになります。

```kotlin
class VideoUploadService(
    private val videoUploader: VideoUpaloder,
    private val notificationManager: NotificationManager,
) {
    fun upload(video: Video) {
        val result = videoUploader.upload(video)
        if (result.isSucceeded) {
            notificationManager
                .showNotification("アップロード完了")
        } else {
            notificationManager
                .showNotification("アップロード失敗")
        }
    }
}
```

必要なクラスはコンストラクタで渡すようにしました。
これを**コンストラクタ インジェクション**と言います。

こうすることで、`VideoUpaloder`や`NotificationManager`を外部とアクセスが発生しないような偽物に差し替えてテストを書くことができるようになりました。

```kotlin
class VideoUploadServiceTest {
    private val videoUploader = 
        FakeVideoUploader()
    private val notificationManager = 
        FakeNotificationManager()
    private val videoUploadService =
        VideoUploadService(videoUploader, notificationManager)

    @Test
    fun test() {
        val video = Video("test.mp4")
        videoUploadService.upload(video)
        /* ... */
    }
}
```

また、Dependency Injectionの考え方に従うことで、各クラスの関係が明確になります。
今回も`VideoUploadService`が`VideoUpaloder`と`NotificationManager`に関連していることが、コードを全て読まなくてもコンストラクタから理解することができます。

### Dependency Injectionで汎用性を上げる

汎用性の観点からもDependency Injectionは有効です。

先程の動画アップロードのアプリケーションをAndroid/iOSの両方で実装する際、通知の表示方法はAndroidとiOSで異なります。
コンストラクタで `NotificationManager` を渡せるようにした場合、Android向けとiOS向けで `NotificationManager` だけ差し替えればよく、`VideoUploadService`は共通で使うことができます。

```kotlin
// for Android
val videoUploadService = VideoUploadService(
    videoUpaloder = VideoUpaloder(),
    notificationManager = AndroidNotificationManager(),
)
```

```kotlin
// for iOS
val videoUploadService = VideoUploadService(
    videoUpaloder = VideoUpaloder(),
    notificationManager = IOSNotificationManager(),
)
```

このように、Dependency Injectionのパターンに従うことで、プラットフォームや利用ケースに合わせて**同じコードを汎用的に使うことができる**ようになります。

### DIコンテナで依存解決をまとめる
これまで紹介してきたとおり、Dependency Injectionには複数のメリットがあります。
しかし、各クラスは利用時に全ての依存関係を指定する必要があり、依存関係が増えてくるとインスタンスの生成に苦労します。

```kotlin
val storageApi = StorageApi(Dispatchers.IO)
val videoUpaloder = VideoUpaloder(storageApi)
val notificationManager = NotificationManager(context)
val videoUploadService = VideoUploadService(
    videoUpaloder,
    notificationManager,
)
```

これを解決してくれるのがDIコンテナです。
DIコンテナは必要な依存関係を自動的に整理し、インスタンスを生成してくれる機能を持っています。

Kojectでは、クラス宣言時に`@Provides`アノテーションをつけることでDIコンテナに登録でき、`inject()` 関数を使って必要な依存関係を解決したインスタンスを取得できます。

```kotlin
@Provides
class VideoUpaloder

@Provides
class NotificationManager

@Provides
class VideoUploadService(
    private val videoUploader: VideoUpaloder,
    private val notificationManager: NotificationManager,
) {
    /* ... */
}
```

```kotlin
Koject.start()

val videoUploadService = inject<VideoUploadService>()
```

Kojectを使うことで、複雑なコードを書くことなく、Dependency Injectionの恩恵を受けることができます。

## Kojectの特徴
KojectはKotlin Multiplatform向けの新しいDIコンテナライブラリです。
以下に主な特徴を紹介します。

### アノテーションで簡単に配布
Kojectは複数のアノテーションを使って簡単に利用できます。

クラス宣言時に `@Provides` アノテーションをつけ、DIコンテナに登録します。

```kotlin
@Provides
class Repository

@Provides
class Controller(
    private val repository: Repository
)
```

登録したクラスのインスタンスは、`Koject.start()`を呼び出した後、`inject()`メソッドを使用して取得できます。

```kotlin
fun main() {
    Koject.start()

    val controller = inject<Controller>()
}
```

この場合、`inject<Controller>()`を呼び出すことで、Kojectが`Controller`のプライマリコンストラクタと`@Provides`アノテーションがついた依存関係を使用して、`Controller`クラスのインスタンスを作成してくれます。

#### Singletonで配布する

`@Provids`アノテーションと合わせて`@Singleton`アノテーションをつけることで、インスタンスの作成を一度のみに限定し、アプリケーション全体で再利用するようになります。
これは、作成にコストがかかる依存関係や、複数のクラスで共有する必要がある依存関係に特に役立ちます。

```kotlin
@Singleton
@Provides
class Api

@Singleton
@Provids
class Repository(
    private val api: Api,
)
```

この場合、Kojectは`Api`クラスと`Repository`のインスタンスをそれぞれ1つだけ作成し、アプリケーション全体でそれらを再利用することを意味します。

#### スーパータイプとして配布する
DIパターンを実践する際、しばしば実装クラスをインターフェース等のスーパークラスとして使用します。
Kojectでは、`@Binds` アノテーションを使用することで、簡単にスーパータイプとして配布することができます。

以下は、`Repository`インターフェースの実装クラスである`RepositoryImpl`を、`@Binds`アノテーションを使用して`Repository`として提供する例です。

```kotlin
@Binds
@Provides
class RepositoryImpl: Repository

interface Repository
```

#### 詳細な利用方法

より詳細な利用方法については[ドキュメント](/docs/core/basic)を確認してください。

### Kotlin Multiplatformに対応
KojectはKotlin Multiplatformに対応しています。
依存グラフはそれぞれのプラットフォーム毎に作成され、プラットフォームによってクラスを差し替えることも可能です。

各プラットフォームに合わせた[セットアップ方法](/docs/setup)を確認してください。

### コンパイル時の依存グラフの確認
Kojectは[KSP](https://github.com/google/ksp)を使ったコード生成によって動作します。
依存グラフはコンパイル時に作成され、足りない依存関係や重複した配布があった場合はコンパイル時にエラーになります。
コンパイル時に確認できることで、実行時の予期せぬエラーを防ぐことができます。

```kotlin
// @Providesなし
class Repository

@Provides
class Controller(
    private val repository: Repository
)
```

![](/blog/2023-03-04/compile-error.png)

現在、Kojectは`inject()`で呼び出しているクラスが配布されているかは確認しておらず、以下は実行時エラーになることに注意してください。

```kotlin
// @Providesなし
class SampleClass

fun main() {
    Koject.start()

    val controller = inject<SampleClass>() // NotProvidedException!!
}
```

## 今後の予定
Kojectはまだ誕生したばかりです。
より便利なライブラリにするため、今後は以下のサポート強化を予定しています。

* Android向けのサポート強化 [#25](https://github.com/Mori-Atsushi/koject/issues/25) [#52](https://github.com/Mori-Atsushi/koject/issues/52)
* テスト時のサポート強化 [#85](https://github.com/Mori-Atsushi/koject/issues/85)

その他にも気づいたことがあれば、[Issue](https://github.com/Mori-Atsushi/koject/issues) からお気軽にフィードバックください。
