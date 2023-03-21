"use strict";(self.webpackChunkdocs=self.webpackChunkdocs||[]).push([[4412],{3905:(e,t,n)=>{n.d(t,{Zo:()=>d,kt:()=>h});var o=n(7294);function i(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function a(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);t&&(o=o.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,o)}return n}function r(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?a(Object(n),!0).forEach((function(t){i(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):a(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function c(e,t){if(null==e)return{};var n,o,i=function(e,t){if(null==e)return{};var n,o,i={},a=Object.keys(e);for(o=0;o<a.length;o++)n=a[o],t.indexOf(n)>=0||(i[n]=e[n]);return i}(e,t);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);for(o=0;o<a.length;o++)n=a[o],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(i[n]=e[n])}return i}var l=o.createContext({}),s=function(e){var t=o.useContext(l),n=t;return e&&(n="function"==typeof e?e(t):r(r({},t),e)),n},d=function(e){var t=s(e.components);return o.createElement(l.Provider,{value:t},e.children)},p="mdxType",m={inlineCode:"code",wrapper:function(e){var t=e.children;return o.createElement(o.Fragment,{},t)}},u=o.forwardRef((function(e,t){var n=e.components,i=e.mdxType,a=e.originalType,l=e.parentName,d=c(e,["components","mdxType","originalType","parentName"]),p=s(n),u=i,h=p["".concat(l,".").concat(u)]||p[u]||m[u]||a;return n?o.createElement(h,r(r({ref:t},d),{},{components:n})):o.createElement(h,r({ref:t},d))}));function h(e,t){var n=arguments,i=t&&t.mdxType;if("string"==typeof e||i){var a=n.length,r=new Array(a);r[0]=u;var c={};for(var l in t)hasOwnProperty.call(t,l)&&(c[l]=t[l]);c.originalType=e,c[p]="string"==typeof e?e:i,r[1]=c;for(var s=2;s<a;s++)r[s]=n[s];return o.createElement.apply(null,r)}return o.createElement.apply(null,n)}u.displayName="MDXCreateElement"},1054:(e,t,n)=>{n.d(t,{$h:()=>f,BQ:()=>I,E:()=>w,GU:()=>F,Hc:()=>a,IQ:()=>V,Kj:()=>H,N8:()=>U,NR:()=>s,O$:()=>T,Or:()=>b,Pc:()=>h,Q4:()=>z,TD:()=>x,UK:()=>P,Uf:()=>g,Vi:()=>W,Vn:()=>y,Zf:()=>l,_P:()=>d,dK:()=>K,eG:()=>r,eh:()=>S,f3:()=>j,kJ:()=>A,m:()=>u,oX:()=>E,pn:()=>O,pt:()=>v,q:()=>c,q0:()=>D,qK:()=>p,ql:()=>k,s7:()=>B,tB:()=>m,tQ:()=>C,wn:()=>M,yc:()=>N});var o=n(7294);const i=e=>{let{children:t,href:n}=e;return o.createElement("a",{href:`/koject/api${n}`,target:"_blank"},o.createElement("code",null,t))},a=()=>o.createElement(i,{href:"/koject-core/com.moriatsushi.koject/-provides/index.html"},"@Provides"),r=()=>o.createElement(i,{href:"/koject-core/com.moriatsushi.koject/-singleton/index.html"},"@Singleton"),c=()=>o.createElement(i,{href:"/koject-core/com.moriatsushi.koject/-qualifier/index.html"},"@Qualifier"),l=()=>o.createElement(i,{href:"/koject-core/com.moriatsushi.koject/-named/index.html"},"@Named"),s=()=>o.createElement(i,{href:"/koject-core/com.moriatsushi.koject/-binds/index.html"},"@Binds"),d=()=>o.createElement(i,{href:"/koject-core/com.moriatsushi.koject/start.html"},"Koject.start()"),p=()=>o.createElement(i,{href:"/koject-core/com.moriatsushi.koject/-koject/stop.html"},"Koject.stop()"),m=()=>o.createElement(i,{href:"/koject-core/com.moriatsushi.koject/inject.html"},"inject()"),u=()=>o.createElement(i,{href:"/koject-core/com.moriatsushi.koject/lazy-inject.html"},"lazyInject()"),h=()=>o.createElement(i,{href:"/koject-core/com.moriatsushi.koject.extras/-koject-extras/index.html"},"KojectExtras"),k=()=>o.createElement(i,{href:"/koject-core/com.moriatsushi.koject.extras/-koject-extras-message/index.html"},"@KojectExtrasMessage"),v=()=>o.createElement(i,{href:"/koject-core/com.moriatsushi.koject.component/-component-extras/index.html"},"ComponentExtras"),j=()=>o.createElement(i,{href:"/koject-core/com.moriatsushi.koject.error/-missing-extras-exception/index.html"},"MissingExtrasException"),f=()=>o.createElement(i,{href:"/koject-test/com.moriatsushi.koject.test/run-test.html"},"Koject.runTest()"),y=()=>o.createElement(i,{href:"/koject-test/com.moriatsushi.koject.test/start-test.html"},"Koject.startTest()"),g=()=>o.createElement(i,{href:"/koject-test/com.moriatsushi.koject.test/-test-provides/index.html"},"@TestProvides"),w=()=>o.createElement(i,{href:"/android/koject-android-core/com.moriatsushi.koject.android/application.html"},"application()"),C=()=>o.createElement(i,{href:"/android/koject-android-viewmodel/com.moriatsushi.koject.android.viewmodel/-view-model-component/index.html"},"@ViewModelComponent"),b=()=>o.createElement(i,{href:"/android/koject-android-viewmodel/com.moriatsushi.koject.android.viewmodel/-view-model-coroutine-scope/index.html"},"@ViewModelCoroutineScope"),M=()=>o.createElement(i,{href:"/android/koject-android-activity/com.moriatsushi.koject/inject.html"},"ComponentActivity.inject()"),x=()=>o.createElement(i,{href:"/android/koject-android-activity/com.moriatsushi.koject/lazy-inject.html"},"ComponentActivity.lazyInject()"),N=()=>o.createElement(i,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/-activity-component/index.html"},"@ActivityComponent"),A=()=>o.createElement(i,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/-activity-context/index.html"},"@ActivityContext"),E=()=>o.createElement(i,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/-activity-context/index.html"},"@ActivityCoroutineScope"),V=()=>o.createElement(i,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/lazy-view-models.html"},"ComponentActivity.lazyViewModels()"),S=()=>o.createElement(i,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/inject-view-models.html"},"ComponentActivity.injectViewModels()"),T=()=>o.createElement(i,{href:"/android/koject-android-fragment/com.moriatsushi.koject/inject.html"},"Fragment.inject()"),O=()=>o.createElement(i,{href:"/android/koject-android-fragment/com.moriatsushi.koject/lazy-inject.html"},"Fragment.lazyInject()"),P=()=>o.createElement(i,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/-fragment-component/index.html"},"@FragmentComponent"),K=()=>o.createElement(i,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/-fragment-coroutine-scope/index.html"},"@FragmentCoroutineScope"),I=()=>o.createElement(i,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/-fragment-view-coroutine-scope/index.html"},"@FragmentViewCoroutineScope"),D=()=>o.createElement(i,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/lazy-view-models.html"},"Fragment.lazyViewModels()"),z=()=>o.createElement(i,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/inject-view-models.html"},"Fragment.injectViewModels()"),H=()=>o.createElement(i,{href:"/compose/koject-compose-core/com.moriatsushi.koject.compose/remember-inject.html"},"rememberInject()"),F=()=>o.createElement(i,{href:"/compose/koject-compose-viewmodel/com.moriatsushi.koject.compose.viewmodel/inject-view-model.html"},"injectViewModel()"),W=()=>o.createElement(i,{href:"/compose/koject-compose-core/com.moriatsushi.koject.compose/-compose-component/index.html"},"@ComposeComponent"),B=()=>o.createElement(i,{href:"/compose/koject-compose-core/com.moriatsushi.koject.compose/-compose-coroutine-scope/index.html"},"@ComposeCoroutineScope"),U=()=>o.createElement(i,{href:"/compose/koject-compose-core/com.moriatsushi.koject.compose/-compose-context/index.html"},"@ComposeContext")},1255:(e,t,n)=>{n.r(t),n.d(t,{assets:()=>s,contentTitle:()=>c,default:()=>u,frontMatter:()=>r,metadata:()=>l,toc:()=>d});var o=n(7462),i=(n(7294),n(3905)),a=n(1054);const r={slug:"android-components",title:"Koject v1.2.0 - What are Android components?",authors:"atsushi",image:"/blog/2023-03-18/ogp.png"},c="Koject v1.2.0 - What are Android components?",l={permalink:"/koject/blog/android-components",editUrl:"https://github.com/Mori-Atsushi/koject/tree/main/docs/blog/2023-03-18-android-components.mdx",source:"@site/blog/2023-03-18-android-components.mdx",title:"Koject v1.2.0 - What are Android components?",description:"While Android support was recently strengthened in Koject 1.1.0, several more features have been added in Koject 1.2.0.",date:"2023-03-18T00:00:00.000Z",formattedDate:"March 18, 2023",tags:[],readingTime:4.415,hasTruncateMarker:!0,authors:[{name:"Mori Atsushi",title:"Koject owner",url:"https://github.com/Mori-Atsushi",imageURL:"https://github.com/mori-atsushi.png",key:"atsushi"}],frontMatter:{slug:"android-components",title:"Koject v1.2.0 - What are Android components?",authors:"atsushi",image:"/blog/2023-03-18/ogp.png"},nextItem:{title:"Koject v1.1.0 - Enhanced Android support",permalink:"/koject/blog/android-support"}},s={authorsImageUrls:[void 0]},d=[{value:"Migrating from v1.1.0",id:"migrating-from-v110",level:2},{value:"Providing Android Applications",id:"providing-android-applications",level:2},{value:"Inject ViewModel&#39;s CoroutineScope",id:"inject-viewmodels-coroutinescope",level:2},{value:"Added Android components",id:"added-android-components",level:2},{value:"What more do you need?",id:"what-more-do-you-need",level:2}],p={toc:d},m="wrapper";function u(e){let{components:t,...r}=e;return(0,i.kt)(m,(0,o.Z)({},p,r,{components:t,mdxType:"MDXLayout"}),(0,i.kt)("p",null,(0,i.kt)("img",{src:n(7566).Z,width:"1800",height:"630"})),(0,i.kt)("p",null,"While ",(0,i.kt)("a",{parentName:"p",href:"/blog/android-support"},"Android support was recently strengthened in Koject 1.1.0"),", several more features have been added in Koject 1.2.0.\nIn this article, I'll introduce how to use v1.2.0 and the main features that have been added."),(0,i.kt)("p",null,(0,i.kt)("a",{parentName:"p",href:"/blog/jp/android-components"},(0,i.kt)("strong",{parentName:"a"},"\u65e5\u672c\u8a9e\u3067\u8aad\u3080 \u2192"))),(0,i.kt)("h2",{id:"migrating-from-v110"},"Migrating from v1.1.0"),(0,i.kt)("p",null,"Some APIs have been changed from v1.1.0 to v1.2.0, which may require your attention."),(0,i.kt)("details",null,(0,i.kt)("summary",null,"[core] The inject() API for Named has changed (#148)"),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-kotlin"},'// Until v1.1.0\nval db1 = inject<DB>("db1")\nval db2 = inject<DB>("db2")\n\n// Since v1.2.0\nval db1 = inject<DB>(Named("db1"))\nval db2 = inject<DB>(Named("db2"))\n'))),(0,i.kt)("details",null,(0,i.kt)("summary",null,"[core] The ComponentExtras API has changed. (#157)"),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-kotlin"},"// Until v1.1.0\n@ExperimentalKojectApi\n@ComponentExtras(CustomComponent::class)\nclass CustomComponentExtras(\n    val extra: ExtraClass\n)\n\n// Since v1.2.0\n@ExperimentalKojectApi\nclass CustomComponentExtras(\n    val extra: ExtraClass\n): ComponentExtras<CustomComponent>\n"))),(0,i.kt)("details",null,(0,i.kt)("summary",null,"[android] injectViewModels() has been renamed to lazyViewModels() (#149)"),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-kotlin"},"// Until v1.1.0\nprivate val viewModel: TopViewModel by injectViewModels()\n\n// Since v1.2.0\nprivate val viewModel: TopViewModel by lazyViewModels()\n"))),(0,i.kt)("h2",{id:"providing-android-applications"},"Providing Android Applications"),(0,i.kt)("p",null,"With Koject 1.2.0, it's now possible to provide the Android ",(0,i.kt)("inlineCode",{parentName:"p"},"Application")," class.\nThis can be done by adding the ",(0,i.kt)("inlineCode",{parentName:"p"},"koject-android-core")," package."),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-kotlin"},'dependencies {\n    implementation("com.moriatsushi.koject:koject-android-core:1.2.0")\n}\n')),(0,i.kt)("p",null,"To use it, you need to pass an ",(0,i.kt)("inlineCode",{parentName:"p"},"Application")," using the ",(0,i.kt)(a.E,{mdxType:"AndroidApplication"})," method when calling ",(0,i.kt)(a._P,{mdxType:"KojectStart"}),"."),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-kotlin"},"class MyApplication : Application() {\n    override fun onCreate() {\n        super.onCreate()\n\n        Koject.start {\n            application(this@MyApplication)\n        }\n    }\n}\n")),(0,i.kt)("p",null,"This allows the ",(0,i.kt)("inlineCode",{parentName:"p"},"Application")," class and the ",(0,i.kt)("inlineCode",{parentName:"p"},"Context")," class in the Application scope to be provided.\nThe provided classes can be used like this:"),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-kotlin"},'@Provides\n@Singleton\nfun provideAppDatabase(\n    applicationContext: Context // can be injected\n): AppDatabase {\n    return Room.databaseBuilder(\n        applicationContext,\n        AppDatabase::class.java,\n        "database-name"\n    ).build()\n}\n')),(0,i.kt)("h2",{id:"inject-viewmodels-coroutinescope"},"Inject ViewModel's CoroutineScope"),(0,i.kt)("p",null,"In the Android architecture using ViewModels, it is common for the ViewModel class to become bloated with code. One solution is to extract some of the code into a separate class, as shown below:"),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-kotlin"},"class MyViewModel: ViewModel() {\n    private val helper = TopViewModelHelper(viewModelScope)\n\n    fun update() {\n        helper.update()\n    }\n}\n")),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-kotlin"},"class ViewModelHelper(\n    private val coroutineScope: CoroutineScope\n) {\n    fun update() {\n        coroutineScope.launch {\n            /* ... */\n        }\n    }\n}\n")),(0,i.kt)("p",null,"When attempting to provide this ",(0,i.kt)("inlineCode",{parentName:"p"},"ViewModelHelper")," using a DI container, it becomes difficult to pass the ",(0,i.kt)("inlineCode",{parentName:"p"},"CoroutineScope")," of the ",(0,i.kt)("inlineCode",{parentName:"p"},"ViewModel")," to the ",(0,i.kt)("inlineCode",{parentName:"p"},"ViewModelHelper"),"."),(0,i.kt)("p",null,"One possible solution is to pass it through a ",(0,i.kt)("inlineCode",{parentName:"p"},"setup")," function, as shown below.\nHowever, there is a risk of forgetting to call the ",(0,i.kt)("inlineCode",{parentName:"p"},"setup")," function, making this approach less safe."),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-kotlin"},"@Provides\nclass ViewModelHelper {\n    private lateinit var coroutineScope: CoroutineScope\n\n    fun setup(coroutineScope: CoroutineScope) {\n        this.coroutineScope = coroutineScope\n    }\n\n    fun update() {\n        coroutineScope.launch {\n            /* ... */\n        }\n    }\n}\n")),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-kotlin"},"@Provides\nclass MyViewModel(\n    private val helper: TopViewModelHelper\n): ViewModel() {\n    init {\n        helper.setup(viewModelScope)\n    }\n\n    fun update() {\n        helper.update()\n    }\n}\n")),(0,i.kt)("p",null,"Another possible solution is to provide a ",(0,i.kt)("inlineCode",{parentName:"p"},"Factory")," class."),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-kotlin"},"class ViewModelHelper(\n    private val coroutineScope: CoroutineScope\n) {\n    fun update() {\n        coroutineScope.launch {\n            /* ... */\n        }\n    }\n\n    @Provides\n    class Factory {\n        fun create(coroutineScope: CoroutineScope): ViewModelHelper {\n            return ViewModelHelper(coroutineScope)\n        }\n    }\n}\n")),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-kotlin"},"@Provides\nclass MyViewModel(\n    helperFactory: TopViewModelHelper.Factory\n): ViewModel() {\n    private val helper = helperFactory.create(viewMdoelScope)\n\n    fun update() {\n        helper.update()\n    }\n}\n")),(0,i.kt)("p",null,"Both methods work, but there is still redundancy."),(0,i.kt)("p",null,"With Koject, this can be solved simply by using the ",(0,i.kt)("inlineCode",{parentName:"p"},"ViewModelComponent"),".\nWhen the ",(0,i.kt)("inlineCode",{parentName:"p"},"@Provides")," annotation is accompanied by the ",(0,i.kt)(a.tQ,{mdxType:"ViewModelComponent"})," annotation, the ",(0,i.kt)("inlineCode",{parentName:"p"},"CoroutineScope")," of the ViewModel Scope can be injected using the ",(0,i.kt)(a.Or,{mdxType:"ViewModelCoroutineScope"})," annotation."),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-kotlin"},"@ViewModelComponent\n@Provides\nclass MyViewModel(\n    private val helper: ViewModelHelper\n): ViewModel() {\n    fun update() {\n        helper.update()\n    }\n}\n")),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-kotlin"},"@ViewModelComponent\n@Provides\nclass ViewModelHelper(\n    @ViewModelCoroutineScope\n    private val coroutineScope: CoroutineScope // same as ViewModel.viewModelScope\n) {\n    fun update() {\n        coroutineScope.launch {\n            /* ... */\n        }\n    }\n}\n")),(0,i.kt)("p",null,"Note that types with the ",(0,i.kt)("inlineCode",{parentName:"p"},"@ViewModelComponent")," annotation can only be injected into other types with the same annotation.\nThe ",(0,i.kt)("inlineCode",{parentName:"p"},"MyViewModel")," class must also have the ",(0,i.kt)("inlineCode",{parentName:"p"},"@ViewModelComponent")," annotation."),(0,i.kt)("p",null,"To use ViewModel, use the ",(0,i.kt)(a.IQ,{mdxType:"ActivityLazyViewModels"})," function."),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-kotlin"},"class TopActivity : ComponentActivity() {\n    private val viewModel: TopViewModel by lazyViewModels()\n\n    override fun onCreate(savedInstanceState: Bundle?) {\n        super.onCreate(savedInstanceState)\n        /* ... */\n    }\n}\n")),(0,i.kt)("p",null,"This functionality is useful not only for delegating work to other classes, but also for consolidating parts of ViewModel code."),(0,i.kt)("p",null,"For more information about ViewModels, please refer to the ",(0,i.kt)("a",{parentName:"p",href:"/docs/android/viewmodel"},"Inject ViewModels documentation"),"."),(0,i.kt)("h2",{id:"added-android-components"},"Added Android components"),(0,i.kt)("p",null,"Android components have been added to Koject 1.2.0, which now includes ",(0,i.kt)(a.yc,{mdxType:"ActivityComponent"}),", ",(0,i.kt)(a.UK,{mdxType:"FragmentComponent"}),", and ",(0,i.kt)(a.Vi,{mdxType:"ComposeComponent"}),", in addition to ",(0,i.kt)("inlineCode",{parentName:"p"},"ViewModelComponent"),"."),(0,i.kt)("p",null,"Types annotated with ",(0,i.kt)("inlineCode",{parentName:"p"},"@ActivityComponent")," can only be injected into an ",(0,i.kt)("inlineCode",{parentName:"p"},"Activity")," or a type with the same annotation.\n",(0,i.kt)("inlineCode",{parentName:"p"},"@ActivityComponent")," allows you to use ",(0,i.kt)("inlineCode",{parentName:"p"},"ComponentActivity"),", activity scope ",(0,i.kt)("inlineCode",{parentName:"p"},"Context"),", ",(0,i.kt)("inlineCode",{parentName:"p"},"CoroutineScope"),", and more."),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-kotlin"},"@ActivityComponent\nclass ActivityHelper(\n    val activity: ComponentActivity, // can be injected\n    @ActivityContext\n    val context: Context, // activity's context\n    @ActivityCoroutineScope\n    val coroutineScope: CoroutineScope // same as ComponentActivity.lifecycleScope\n)\n")),(0,i.kt)("p",null,"When using it in an activity, please use ",(0,i.kt)(a.wn,{mdxType:"ActivityInject"})," or ",(0,i.kt)(a.TD,{mdxType:"ActivityLazyInject"}),"."),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-kotlin"},"class MyActivity: ComponentActivity {\n    val helper: ActivityHelper by lazyInject() // can be injected\n}\n")),(0,i.kt)("p",null,"Similar to ViewModel, this function can be useful for refactoring such as class division."),(0,i.kt)("p",null,"You can check all components and the types they can be used with in the ",(0,i.kt)("a",{parentName:"p",href:"/docs/android/components"},"Android components documentation"),"."),(0,i.kt)("h2",{id:"what-more-do-you-need"},"What more do you need?"),(0,i.kt)("p",null,"Koject aims to keep its basic functions simple while adding various features.\nIf you find any missing functionality, please let us know via ",(0,i.kt)("a",{parentName:"p",href:"https://github.com/mori-atsushi/koject/issues"},"Issues"),"."))}u.isMDXComponent=!0},7566:(e,t,n)=>{n.d(t,{Z:()=>o});const o=n.p+"assets/images/banner-ad7697f1a86ee926742691349d6e917e.png"}}]);