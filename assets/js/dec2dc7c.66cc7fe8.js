"use strict";(self.webpackChunkdocs=self.webpackChunkdocs||[]).push([[278],{3905:(e,t,o)=>{o.d(t,{Zo:()=>m,kt:()=>h});var n=o(7294);function i(e,t,o){return t in e?Object.defineProperty(e,t,{value:o,enumerable:!0,configurable:!0,writable:!0}):e[t]=o,e}function r(e,t){var o=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),o.push.apply(o,n)}return o}function a(e){for(var t=1;t<arguments.length;t++){var o=null!=arguments[t]?arguments[t]:{};t%2?r(Object(o),!0).forEach((function(t){i(e,t,o[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(o)):r(Object(o)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(o,t))}))}return e}function c(e,t){if(null==e)return{};var o,n,i=function(e,t){if(null==e)return{};var o,n,i={},r=Object.keys(e);for(n=0;n<r.length;n++)o=r[n],t.indexOf(o)>=0||(i[o]=e[o]);return i}(e,t);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);for(n=0;n<r.length;n++)o=r[n],t.indexOf(o)>=0||Object.prototype.propertyIsEnumerable.call(e,o)&&(i[o]=e[o])}return i}var d=n.createContext({}),l=function(e){var t=n.useContext(d),o=t;return e&&(o="function"==typeof e?e(t):a(a({},t),e)),o},m=function(e){var t=l(e.components);return n.createElement(d.Provider,{value:t},e.children)},s="mdxType",p={inlineCode:"code",wrapper:function(e){var t=e.children;return n.createElement(n.Fragment,{},t)}},u=n.forwardRef((function(e,t){var o=e.components,i=e.mdxType,r=e.originalType,d=e.parentName,m=c(e,["components","mdxType","originalType","parentName"]),s=l(o),u=i,h=s["".concat(d,".").concat(u)]||s[u]||p[u]||r;return o?n.createElement(h,a(a({ref:t},m),{},{components:o})):n.createElement(h,a({ref:t},m))}));function h(e,t){var o=arguments,i=t&&t.mdxType;if("string"==typeof e||i){var r=o.length,a=new Array(r);a[0]=u;var c={};for(var d in t)hasOwnProperty.call(t,d)&&(c[d]=t[d]);c.originalType=e,c[s]="string"==typeof e?e:i,a[1]=c;for(var l=2;l<r;l++)a[l]=o[l];return n.createElement.apply(null,a)}return n.createElement.apply(null,o)}u.displayName="MDXCreateElement"},1054:(e,t,o)=>{o.d(t,{BQ:()=>I,E:()=>j,GU:()=>z,Hc:()=>r,IQ:()=>C,Kj:()=>P,N8:()=>H,NR:()=>l,O$:()=>S,Or:()=>w,Pc:()=>u,Q4:()=>A,TD:()=>g,UK:()=>N,Vi:()=>F,Zf:()=>d,_P:()=>m,dK:()=>O,eG:()=>a,eh:()=>E,f3:()=>v,kJ:()=>V,m:()=>p,oX:()=>x,pn:()=>b,pt:()=>k,q:()=>c,q0:()=>T,ql:()=>h,s7:()=>K,tB:()=>s,tQ:()=>f,wn:()=>y,yc:()=>M});var n=o(7294);const i=e=>{let{children:t,href:o}=e;return n.createElement("a",{href:`/koject/api${o}`,target:"_blank"},n.createElement("code",null,t))},r=()=>n.createElement(i,{href:"/koject-core/com.moriatsushi.koject/-provides/index.html"},"@Provides"),a=()=>n.createElement(i,{href:"/koject-core/com.moriatsushi.koject/-singleton/index.html"},"@Singleton"),c=()=>n.createElement(i,{href:"/koject-core/com.moriatsushi.koject/-qualifier/index.html"},"@Qualifier"),d=()=>n.createElement(i,{href:"/koject-core/com.moriatsushi.koject/-named/index.html"},"@Named"),l=()=>n.createElement(i,{href:"/koject-core/com.moriatsushi.koject/-binds/index.html"},"@Binds"),m=()=>n.createElement(i,{href:"/koject-core/com.moriatsushi.koject/start.html"},"Koject.start()"),s=()=>n.createElement(i,{href:"/koject-core/com.moriatsushi.koject/inject.html"},"inject()"),p=()=>n.createElement(i,{href:"/koject-core/com.moriatsushi.koject/lazy-inject.html"},"lazyInject()"),u=()=>n.createElement(i,{href:"/koject-core/com.moriatsushi.koject.extras/-koject-extras/index.html"},"KojectExtras"),h=()=>n.createElement(i,{href:"/koject-core/com.moriatsushi.koject.extras/-koject-extras-message/index.html"},"@KojectExtrasMessage"),k=()=>n.createElement(i,{href:"/koject-core/com.moriatsushi.koject.component/-component-extras/index.html"},"ComponentExtras"),v=()=>n.createElement(i,{href:"/koject-core/com.moriatsushi.koject.error/-missing-extras-exception/index.html"},"MissingExtrasException"),j=()=>n.createElement(i,{href:"/android/koject-android-core/com.moriatsushi.koject.android/application.html"},"application()"),f=()=>n.createElement(i,{href:"/android/koject-android-viewmodel/com.moriatsushi.koject.android.viewmodel/-view-model-component/index.html"},"@ViewModelComponent"),w=()=>n.createElement(i,{href:"/android/koject-android-viewmodel/com.moriatsushi.koject.android.viewmodel/-view-model-coroutine-scope/index.html"},"@ViewModelCoroutineScope"),y=()=>n.createElement(i,{href:"/android/koject-android-activity/com.moriatsushi.koject/inject.html"},"ComponentActivity.inject()"),g=()=>n.createElement(i,{href:"/android/koject-android-activity/com.moriatsushi.koject/lazy-inject.html"},"ComponentActivity.lazyInject()"),M=()=>n.createElement(i,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/-activity-component/index.html"},"@ActivityComponent"),V=()=>n.createElement(i,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/-activity-context/index.html"},"@ActivityContext"),x=()=>n.createElement(i,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/-activity-context/index.html"},"@ActivityCoroutineScope"),C=()=>n.createElement(i,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/lazy-view-models.html"},"ComponentActivity.lazyViewModels()"),E=()=>n.createElement(i,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/inject-view-models.html"},"ComponentActivity.injectViewModels()"),S=()=>n.createElement(i,{href:"/android/koject-android-fragment/com.moriatsushi.koject/inject.html"},"Fragment.inject()"),b=()=>n.createElement(i,{href:"/android/koject-android-fragment/com.moriatsushi.koject/lazy-inject.html"},"Fragment.lazyInject()"),N=()=>n.createElement(i,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/-fragment-component/index.html"},"@FragmentComponent"),O=()=>n.createElement(i,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/-fragment-coroutine-scope/index.html"},"@FragmentCoroutineScope"),I=()=>n.createElement(i,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/-fragment-view-coroutine-scope/index.html"},"@FragmentViewCoroutineScope"),T=()=>n.createElement(i,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/lazy-view-models.html"},"Fragment.lazyViewModels()"),A=()=>n.createElement(i,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/inject-view-models.html"},"Fragment.injectViewModels()"),P=()=>n.createElement(i,{href:"/compose/koject-compose-core/com.moriatsushi.koject.compose/remember-inject.html"},"rememberInject()"),z=()=>n.createElement(i,{href:"/compose/koject-compose-viewmodel/com.moriatsushi.koject.compose.viewmodel/inject-view-model.html"},"injectViewModel()"),F=()=>n.createElement(i,{href:"/compose/koject-compose-core/com.moriatsushi.koject.compose/-compose-component/index.html"},"@ComposeComponent"),K=()=>n.createElement(i,{href:"/compose/koject-compose-core/com.moriatsushi.koject.compose/-compose-coroutine-scope/index.html"},"@ComposeCoroutineScope"),H=()=>n.createElement(i,{href:"/compose/koject-compose-core/com.moriatsushi.koject.compose/-compose-context/index.html"},"@ComposeContext")},2730:(e,t,o)=>{o.r(t),o.d(t,{assets:()=>l,contentTitle:()=>c,default:()=>u,frontMatter:()=>a,metadata:()=>d,toc:()=>m});var n=o(7462),i=(o(7294),o(3905)),r=o(1054);const a={},c="Inject ViewModels",d={unversionedId:"android/viewmodel",id:"android/viewmodel",title:"Inject ViewModels",description:"Koject allows you to easily inject ViewModels into your Android application.",source:"@site/docs/android/viewmodel.mdx",sourceDirName:"android",slug:"/android/viewmodel",permalink:"/koject/docs/android/viewmodel",draft:!1,editUrl:"https://github.com/Mori-Atsushi/koject/tree/main/docs/docs/android/viewmodel.mdx",tags:[],version:"current",frontMatter:{},sidebar:"docs",previous:{title:"Using Koject on Android",permalink:"/koject/docs/android/application"},next:{title:"Inject into Activity",permalink:"/koject/docs/android/activity"}},l={},m=[{value:"Setup for ViewModels",id:"setup-for-viewmodels",level:2},{value:"Using ViewModels",id:"using-viewmodels",level:2},{value:"Working with SavedStateHandle",id:"working-with-savedstatehandle",level:2},{value:"Inject ViewModel&#39;s CoroutineScope",id:"inject-viewmodels-coroutinescope",level:2}],s={toc:m},p="wrapper";function u(e){let{components:t,...o}=e;return(0,i.kt)(p,(0,n.Z)({},s,o,{components:t,mdxType:"MDXLayout"}),(0,i.kt)("h1",{id:"inject-viewmodels"},"Inject ViewModels"),(0,i.kt)("p",null,"Koject allows you to easily inject ",(0,i.kt)("a",{parentName:"p",href:"https://developer.android.com/reference/androidx/lifecycle/ViewModel"},"ViewModels")," into your Android application."),(0,i.kt)("h2",{id:"setup-for-viewmodels"},"Setup for ViewModels"),(0,i.kt)("p",null,"Add the appropriate dependencies for the injection target:"),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-kotlin"},'dependencies {\n    // Inject ViewModel into Activity\n    implementation("com.moriatsushi.koject:koject-android-activity:1.2.0-beta01")\n    // Inject ViewModel into Fragment\n    implementation("com.moriatsushi.koject:koject-android-fragment:1.2.0-beta01")\n    // ViewModelFactory only\n    implementation("com.moriatsushi.koject:koject-android-viewmodel:1.2.0-beta01")\n}\n')),(0,i.kt)("p",null,"Please also refer to the ",(0,i.kt)("a",{parentName:"p",href:"/docs/setup"},"Setup document"),"."),(0,i.kt)("h2",{id:"using-viewmodels"},"Using ViewModels"),(0,i.kt)("p",null,"To define a ",(0,i.kt)("inlineCode",{parentName:"p"},"ViewModel"),", specify the ",(0,i.kt)(r.tQ,{mdxType:"ViewModelComponent"})," and ",(0,i.kt)(r.Hc,{mdxType:"Provides"})," annotations.\nYou can use constructor injection as with other types:"),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-kotlin"},"@Provides\n@ViewModelComponent\nclass TopViewModel(\n    private val userRepository: UserRepository,\n    private val contentRepository: ContentRepository,\n): ViewModel() {\n    /* ... */\n}\n")),(0,i.kt)("p",null,"When using ViewModels, use the ",(0,i.kt)(r.IQ,{mdxType:"ActivityLazyViewModels"})," function:"),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-kotlin"},"class TopActivity : ComponentActivity() {\n    private val viewModel: TopViewModel by lazyViewModels()\n\n    override fun onCreate(savedInstanceState: Bundle?) {\n        super.onCreate(savedInstanceState)\n        /* ... */\n    }\n}\n")),(0,i.kt)("p",null,"For fragments, use the ",(0,i.kt)(r.q0,{mdxType:"FragmentLazyViewModels"})," function:"),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-kotlin"},"class TopFragment : Fragment() {\n    private val viewModel: TopViewModel by lazyViewModels()\n\n    override fun onCreateView(\n        inflater: LayoutInflater,\n        container: ViewGroup?,\n        savedInstanceState: Bundle?,\n    ): View? {\n        /* ... */\n    }\n}\n")),(0,i.kt)("admonition",{title:"LINK",type:"info"},(0,i.kt)("p",{parentName:"admonition"},"Refer to the ",(0,i.kt)("a",{parentName:"p",href:"/docs/compose/viewmodel"},"documentation")," to inject ViewModels in Jetpack Compose.")),(0,i.kt)("admonition",{title:"MIGRATION (VERSION 1.2.0)",type:"note"},(0,i.kt)("p",{parentName:"admonition"},"As of version v1.2.0, ",(0,i.kt)("inlineCode",{parentName:"p"},"injectViewModels()")," has been renamed to ",(0,i.kt)("inlineCode",{parentName:"p"},"lazyViewModels()"),":"),(0,i.kt)("pre",{parentName:"admonition"},(0,i.kt)("code",{parentName:"pre",className:"language-kotlin"},"// Until v1.1.0\nprivate val viewModel: TopViewModel by injectViewModels()\n\n// Since v1.2.0\nprivate val viewModel: TopViewModel by lazyViewModels()\n"))),(0,i.kt)("h2",{id:"working-with-savedstatehandle"},"Working with SavedStateHandle"),(0,i.kt)("p",null,(0,i.kt)("a",{parentName:"p",href:"https://developer.android.com/reference/androidx/lifecycle/SavedStateHandle"},"SavedStateHandle")," is used to save ViewModel state.\nKoject allows you to inject ",(0,i.kt)("inlineCode",{parentName:"p"},"SavedStateHandle")," out of the box."),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-kotlin"},"@Provides\n@ViewModelComponent\nclass SavedStateViewModel(\n    private val savedStateHandle: SavedStateHandle\n) : ViewModel() {\n    /* ... */\n}\n")),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-kotlin"},"class TopActivity : ComponentActivity() {\n    private val viewModel: SavedStateViewModel by lazyViewModels()\n\n    override fun onCreate(savedInstanceState: Bundle?) {\n        super.onCreate(savedInstanceState)\n        /* ... */\n    }\n}\n")),(0,i.kt)("h2",{id:"inject-viewmodels-coroutinescope"},"Inject ViewModel's CoroutineScope"),(0,i.kt)("p",null,"Types with ",(0,i.kt)("inlineCode",{parentName:"p"},"ViewModelComponent")," annotations can only be injected into ",(0,i.kt)("inlineCode",{parentName:"p"},"ViewModel")," and can inject ViewModel's ",(0,i.kt)("inlineCode",{parentName:"p"},"CoroutineScope"),".\nThe ",(0,i.kt)(r.Or,{mdxType:"ViewModelCoroutineScope"})," qualifier is required to use the ",(0,i.kt)("inlineCode",{parentName:"p"},"CoroutineScope"),"."),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-kotlin"},"@Provides\n@ViewModelComponent\nclass ViewModelHelper(\n    @ViewModelCoroutineScope\n    val scope: CoroutineScope // same as ViewModel.viewModelScope\n) {\n    /* ... */\n}\n\n@Provides\n@ViewModelComponent\nclass SomeViewModel(\n    val helper: ViewModelHelper\n): ViewModel() {\n    /* ... */\n}\n")),(0,i.kt)("admonition",{title:"LINK",type:"info"},(0,i.kt)("p",{parentName:"admonition"},"Check the ",(0,i.kt)("a",{parentName:"p",href:"/docs/android/components"},"Android components documentation")," for all available components for Android.")))}u.isMDXComponent=!0}}]);