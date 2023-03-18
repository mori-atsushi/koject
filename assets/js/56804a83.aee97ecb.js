"use strict";(self.webpackChunkdocs=self.webpackChunkdocs||[]).push([[2390],{3905:(e,t,o)=>{o.d(t,{Zo:()=>l,kt:()=>u});var n=o(7294);function r(e,t,o){return t in e?Object.defineProperty(e,t,{value:o,enumerable:!0,configurable:!0,writable:!0}):e[t]=o,e}function i(e,t){var o=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),o.push.apply(o,n)}return o}function c(e){for(var t=1;t<arguments.length;t++){var o=null!=arguments[t]?arguments[t]:{};t%2?i(Object(o),!0).forEach((function(t){r(e,t,o[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(o)):i(Object(o)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(o,t))}))}return e}function a(e,t){if(null==e)return{};var o,n,r=function(e,t){if(null==e)return{};var o,n,r={},i=Object.keys(e);for(n=0;n<i.length;n++)o=i[n],t.indexOf(o)>=0||(r[o]=e[o]);return r}(e,t);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);for(n=0;n<i.length;n++)o=i[n],t.indexOf(o)>=0||Object.prototype.propertyIsEnumerable.call(e,o)&&(r[o]=e[o])}return r}var m=n.createContext({}),d=function(e){var t=n.useContext(m),o=t;return e&&(o="function"==typeof e?e(t):c(c({},t),e)),o},l=function(e){var t=d(e.components);return n.createElement(m.Provider,{value:t},e.children)},s="mdxType",p={inlineCode:"code",wrapper:function(e){var t=e.children;return n.createElement(n.Fragment,{},t)}},h=n.forwardRef((function(e,t){var o=e.components,r=e.mdxType,i=e.originalType,m=e.parentName,l=a(e,["components","mdxType","originalType","parentName"]),s=d(o),h=r,u=s["".concat(m,".").concat(h)]||s[h]||p[h]||i;return o?n.createElement(u,c(c({ref:t},l),{},{components:o})):n.createElement(u,c({ref:t},l))}));function u(e,t){var o=arguments,r=t&&t.mdxType;if("string"==typeof e||r){var i=o.length,c=new Array(i);c[0]=h;var a={};for(var m in t)hasOwnProperty.call(t,m)&&(a[m]=t[m]);a.originalType=e,a[s]="string"==typeof e?e:r,c[1]=a;for(var d=2;d<i;d++)c[d]=o[d];return n.createElement.apply(null,c)}return n.createElement.apply(null,o)}h.displayName="MDXCreateElement"},1054:(e,t,o)=>{o.d(t,{BQ:()=>T,E:()=>f,GU:()=>K,Hc:()=>i,IQ:()=>C,Kj:()=>I,N8:()=>F,NR:()=>d,O$:()=>O,Or:()=>v,Pc:()=>h,Q4:()=>D,TD:()=>x,UK:()=>N,Vi:()=>z,Zf:()=>m,_P:()=>l,dK:()=>P,eG:()=>c,eh:()=>A,f3:()=>k,kJ:()=>b,m:()=>p,oX:()=>w,pn:()=>M,pt:()=>j,q:()=>a,q0:()=>S,ql:()=>u,s7:()=>V,tB:()=>s,tQ:()=>y,wn:()=>g,yc:()=>E});var n=o(7294);const r=e=>{let{children:t,href:o}=e;return n.createElement("a",{href:`/koject/api${o}`,target:"_blank"},n.createElement("code",null,t))},i=()=>n.createElement(r,{href:"/koject-core/com.moriatsushi.koject/-provides/index.html"},"@Provides"),c=()=>n.createElement(r,{href:"/koject-core/com.moriatsushi.koject/-singleton/index.html"},"@Singleton"),a=()=>n.createElement(r,{href:"/koject-core/com.moriatsushi.koject/-qualifier/index.html"},"@Qualifier"),m=()=>n.createElement(r,{href:"/koject-core/com.moriatsushi.koject/-named/index.html"},"@Named"),d=()=>n.createElement(r,{href:"/koject-core/com.moriatsushi.koject/-binds/index.html"},"@Binds"),l=()=>n.createElement(r,{href:"/koject-core/com.moriatsushi.koject/start.html"},"Koject.start()"),s=()=>n.createElement(r,{href:"/koject-core/com.moriatsushi.koject/inject.html"},"inject()"),p=()=>n.createElement(r,{href:"/koject-core/com.moriatsushi.koject/lazy-inject.html"},"lazyInject()"),h=()=>n.createElement(r,{href:"/koject-core/com.moriatsushi.koject.extras/-koject-extras/index.html"},"KojectExtras"),u=()=>n.createElement(r,{href:"/koject-core/com.moriatsushi.koject.extras/-koject-extras-message/index.html"},"@KojectExtrasMessage"),j=()=>n.createElement(r,{href:"/koject-core/com.moriatsushi.koject.component/-component-extras/index.html"},"ComponentExtras"),k=()=>n.createElement(r,{href:"/koject-core/com.moriatsushi.koject.error/-missing-extras-exception/index.html"},"MissingExtrasException"),f=()=>n.createElement(r,{href:"/android/koject-android-core/com.moriatsushi.koject.android/application.html"},"application()"),y=()=>n.createElement(r,{href:"/android/koject-android-viewmodel/com.moriatsushi.koject.android.viewmodel/-view-model-component/index.html"},"@ViewModelComponent"),v=()=>n.createElement(r,{href:"/android/koject-android-viewmodel/com.moriatsushi.koject.android.viewmodel/-view-model-coroutine-scope/index.html"},"@ViewModelCoroutineScope"),g=()=>n.createElement(r,{href:"/android/koject-android-activity/com.moriatsushi.koject/inject.html"},"ComponentActivity.inject()"),x=()=>n.createElement(r,{href:"/android/koject-android-activity/com.moriatsushi.koject/lazy-inject.html"},"ComponentActivity.lazyInject()"),E=()=>n.createElement(r,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/-activity-component/index.html"},"@ActivityComponent"),b=()=>n.createElement(r,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/-activity-context/index.html"},"@ActivityContext"),w=()=>n.createElement(r,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/-activity-context/index.html"},"@ActivityCoroutineScope"),C=()=>n.createElement(r,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/lazy-view-models.html"},"ComponentActivity.lazyViewModels()"),A=()=>n.createElement(r,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/inject-view-models.html"},"ComponentActivity.injectViewModels()"),O=()=>n.createElement(r,{href:"/android/koject-android-fragment/com.moriatsushi.koject/inject.html"},"Fragment.inject()"),M=()=>n.createElement(r,{href:"/android/koject-android-fragment/com.moriatsushi.koject/lazy-inject.html"},"Fragment.lazyInject()"),N=()=>n.createElement(r,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/-fragment-component/index.html"},"@FragmentComponent"),P=()=>n.createElement(r,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/-fragment-coroutine-scope/index.html"},"@FragmentCoroutineScope"),T=()=>n.createElement(r,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/-fragment-view-coroutine-scope/index.html"},"@FragmentViewCoroutineScope"),S=()=>n.createElement(r,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/lazy-view-models.html"},"Fragment.lazyViewModels()"),D=()=>n.createElement(r,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/inject-view-models.html"},"Fragment.injectViewModels()"),I=()=>n.createElement(r,{href:"/compose/koject-compose-core/com.moriatsushi.koject.compose/remember-inject.html"},"rememberInject()"),K=()=>n.createElement(r,{href:"/compose/koject-compose-viewmodel/com.moriatsushi.koject.compose.viewmodel/inject-view-model.html"},"injectViewModel()"),z=()=>n.createElement(r,{href:"/compose/koject-compose-core/com.moriatsushi.koject.compose/-compose-component/index.html"},"@ComposeComponent"),V=()=>n.createElement(r,{href:"/compose/koject-compose-core/com.moriatsushi.koject.compose/-compose-coroutine-scope/index.html"},"@ComposeCoroutineScope"),F=()=>n.createElement(r,{href:"/compose/koject-compose-core/com.moriatsushi.koject.compose/-compose-context/index.html"},"@ComposeContext")},2164:(e,t,o)=>{o.r(t),o.d(t,{assets:()=>d,contentTitle:()=>a,default:()=>h,frontMatter:()=>c,metadata:()=>m,toc:()=>l});var n=o(7462),r=(o(7294),o(3905)),i=o(1054);const c={},a="Using Koject on Android",m={unversionedId:"android/application",id:"android/application",title:"Using Koject on Android",description:"Integrating Koject into your Android application is easy.",source:"@site/docs/android/application.mdx",sourceDirName:"android",slug:"/android/application",permalink:"/koject/docs/android/application",draft:!1,editUrl:"https://github.com/Mori-Atsushi/koject/tree/main/docs/docs/android/application.mdx",tags:[],version:"current",frontMatter:{},sidebar:"docs",previous:{title:"Android",permalink:"/koject/docs/android/"},next:{title:"Inject ViewModels",permalink:"/koject/docs/android/viewmodel"}},d={},l=[{value:"Getting Started",id:"getting-started",level:2}],s={toc:l},p="wrapper";function h(e){let{components:t,...o}=e;return(0,r.kt)(p,(0,n.Z)({},s,o,{components:t,mdxType:"MDXLayout"}),(0,r.kt)("h1",{id:"using-koject-on-android"},"Using Koject on Android"),(0,r.kt)("p",null,"Integrating Koject into your Android application is easy."),(0,r.kt)("h2",{id:"getting-started"},"Getting Started"),(0,r.kt)("p",null,"To get started, add the following dependencies for Android:"),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-kotlin"},'dependencies {\n    implementation("com.moriatsushi.koject:koject-android-core:1.2.0")\n}\n')),(0,r.kt)("p",null,"Also, make sure to check the ",(0,r.kt)("a",{parentName:"p",href:"/docs/setup"},"Setup document"),"."),(0,r.kt)("p",null,"Call ",(0,r.kt)(i._P,{mdxType:"KojectStart"})," in the ",(0,r.kt)("inlineCode",{parentName:"p"},"Application")," class, and be sure to call ",(0,r.kt)(i.E,{mdxType:"AndroidApplication"}),"."),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-kotlin"},"class MyApplication : Application() {\n    override fun onCreate() {\n        super.onCreate()\n\n        Koject.start {\n            application(this@MyApplication)\n        }\n    }\n}\n")),(0,r.kt)("p",null,"To provide the necessary dependencies, use the ",(0,r.kt)(i.Hc,{mdxType:"Provides"})," annotation.\nYou can inject the ",(0,r.kt)("inlineCode",{parentName:"p"},"Application")," and the application's ",(0,r.kt)("inlineCode",{parentName:"p"},"Context"),"."),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-kotlin"},'@Provides\n@Singleton\nfun provideAppDatabase(\n    applicationContext: Context\n): AppDatabase {\n    return Room.databaseBuilder(\n        applicationContext,\n        AppDatabase::class.java,\n        "database-name"\n    ).build()\n}\n\n@Provides\nclass MyController(\n    val db: AppDatabase\n) {\n    /* ... */\n}\n')),(0,r.kt)("p",null,"To inject into an ",(0,r.kt)("inlineCode",{parentName:"p"},"Activity"),", etc., use the ",(0,r.kt)(i.tB,{mdxType:"Inject"})," or ",(0,r.kt)(i.m,{mdxType:"LazyInject"})," method."),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-kotlin"},"class MyActivity: Activity {\n    val controller: MyController by lazyInject()\n\n    /* ... */\n}\n")))}h.isMDXComponent=!0}}]);