"use strict";(self.webpackChunkdocs=self.webpackChunkdocs||[]).push([[4298],{3905:(e,t,o)=>{o.d(t,{Zo:()=>m,kt:()=>h});var n=o(7294);function a(e,t,o){return t in e?Object.defineProperty(e,t,{value:o,enumerable:!0,configurable:!0,writable:!0}):e[t]=o,e}function r(e,t){var o=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),o.push.apply(o,n)}return o}function i(e){for(var t=1;t<arguments.length;t++){var o=null!=arguments[t]?arguments[t]:{};t%2?r(Object(o),!0).forEach((function(t){a(e,t,o[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(o)):r(Object(o)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(o,t))}))}return e}function c(e,t){if(null==e)return{};var o,n,a=function(e,t){if(null==e)return{};var o,n,a={},r=Object.keys(e);for(n=0;n<r.length;n++)o=r[n],t.indexOf(o)>=0||(a[o]=e[o]);return a}(e,t);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);for(n=0;n<r.length;n++)o=r[n],t.indexOf(o)>=0||Object.prototype.propertyIsEnumerable.call(e,o)&&(a[o]=e[o])}return a}var l=n.createContext({}),s=function(e){var t=n.useContext(l),o=t;return e&&(o="function"==typeof e?e(t):i(i({},t),e)),o},m=function(e){var t=s(e.components);return n.createElement(l.Provider,{value:t},e.children)},d="mdxType",p={inlineCode:"code",wrapper:function(e){var t=e.children;return n.createElement(n.Fragment,{},t)}},u=n.forwardRef((function(e,t){var o=e.components,a=e.mdxType,r=e.originalType,l=e.parentName,m=c(e,["components","mdxType","originalType","parentName"]),d=s(o),u=a,h=d["".concat(l,".").concat(u)]||d[u]||p[u]||r;return o?n.createElement(h,i(i({ref:t},m),{},{components:o})):n.createElement(h,i({ref:t},m))}));function h(e,t){var o=arguments,a=t&&t.mdxType;if("string"==typeof e||a){var r=o.length,i=new Array(r);i[0]=u;var c={};for(var l in t)hasOwnProperty.call(t,l)&&(c[l]=t[l]);c.originalType=e,c[d]="string"==typeof e?e:a,i[1]=c;for(var s=2;s<r;s++)i[s]=o[s];return n.createElement.apply(null,i)}return n.createElement.apply(null,o)}u.displayName="MDXCreateElement"},1054:(e,t,o)=>{o.d(t,{$h:()=>v,BQ:()=>P,E:()=>w,GU:()=>z,Hc:()=>r,IQ:()=>I,Kj:()=>D,N8:()=>Z,NR:()=>s,O$:()=>T,Or:()=>C,Pc:()=>h,Q4:()=>R,TD:()=>E,UK:()=>A,Uf:()=>y,Vi:()=>H,Vn:()=>g,Zf:()=>l,_P:()=>m,dK:()=>K,eG:()=>i,eh:()=>S,f3:()=>j,kJ:()=>N,m:()=>u,oX:()=>V,pn:()=>O,pt:()=>f,q:()=>c,q0:()=>F,qK:()=>d,ql:()=>k,s7:()=>U,tB:()=>p,tQ:()=>b,wn:()=>x,yc:()=>M});var n=o(7294);const a=e=>{let{children:t,href:o}=e;return n.createElement("a",{href:`/koject/api${o}`,target:"_blank"},n.createElement("code",null,t))},r=()=>n.createElement(a,{href:"/koject-core/com.moriatsushi.koject/-provides/index.html"},"@Provides"),i=()=>n.createElement(a,{href:"/koject-core/com.moriatsushi.koject/-singleton/index.html"},"@Singleton"),c=()=>n.createElement(a,{href:"/koject-core/com.moriatsushi.koject/-qualifier/index.html"},"@Qualifier"),l=()=>n.createElement(a,{href:"/koject-core/com.moriatsushi.koject/-named/index.html"},"@Named"),s=()=>n.createElement(a,{href:"/koject-core/com.moriatsushi.koject/-binds/index.html"},"@Binds"),m=()=>n.createElement(a,{href:"/koject-core/com.moriatsushi.koject/start.html"},"Koject.start()"),d=()=>n.createElement(a,{href:"/koject-core/com.moriatsushi.koject/-koject/stop.html"},"Koject.stop()"),p=()=>n.createElement(a,{href:"/koject-core/com.moriatsushi.koject/inject.html"},"inject()"),u=()=>n.createElement(a,{href:"/koject-core/com.moriatsushi.koject/lazy-inject.html"},"lazyInject()"),h=()=>n.createElement(a,{href:"/koject-core/com.moriatsushi.koject.extras/-koject-extras/index.html"},"KojectExtras"),k=()=>n.createElement(a,{href:"/koject-core/com.moriatsushi.koject.extras/-koject-extras-message/index.html"},"@KojectExtrasMessage"),f=()=>n.createElement(a,{href:"/koject-core/com.moriatsushi.koject.component/-component-extras/index.html"},"ComponentExtras"),j=()=>n.createElement(a,{href:"/koject-core/com.moriatsushi.koject.error/-missing-extras-exception/index.html"},"MissingExtrasException"),v=()=>n.createElement(a,{href:"/koject-test/com.moriatsushi.koject.test/run-test.html"},"Koject.runTest()"),g=()=>n.createElement(a,{href:"/koject-test/com.moriatsushi.koject.test/start-test.html"},"Koject.startTest()"),y=()=>n.createElement(a,{href:"/koject-test/com.moriatsushi.koject.test/-test-provides/index.html"},"@TestProvides"),w=()=>n.createElement(a,{href:"/android/koject-android-core/com.moriatsushi.koject.android/application.html"},"application()"),b=()=>n.createElement(a,{href:"/android/koject-android-viewmodel/com.moriatsushi.koject.android.viewmodel/-view-model-component/index.html"},"@ViewModelComponent"),C=()=>n.createElement(a,{href:"/android/koject-android-viewmodel/com.moriatsushi.koject.android.viewmodel/-view-model-coroutine-scope/index.html"},"@ViewModelCoroutineScope"),x=()=>n.createElement(a,{href:"/android/koject-android-activity/com.moriatsushi.koject/inject.html"},"ComponentActivity.inject()"),E=()=>n.createElement(a,{href:"/android/koject-android-activity/com.moriatsushi.koject/lazy-inject.html"},"ComponentActivity.lazyInject()"),M=()=>n.createElement(a,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/-activity-component/index.html"},"@ActivityComponent"),N=()=>n.createElement(a,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/-activity-context/index.html"},"@ActivityContext"),V=()=>n.createElement(a,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/-activity-context/index.html"},"@ActivityCoroutineScope"),I=()=>n.createElement(a,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/lazy-view-models.html"},"ComponentActivity.lazyViewModels()"),S=()=>n.createElement(a,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/inject-view-models.html"},"ComponentActivity.injectViewModels()"),T=()=>n.createElement(a,{href:"/android/koject-android-fragment/com.moriatsushi.koject/inject.html"},"Fragment.inject()"),O=()=>n.createElement(a,{href:"/android/koject-android-fragment/com.moriatsushi.koject/lazy-inject.html"},"Fragment.lazyInject()"),A=()=>n.createElement(a,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/-fragment-component/index.html"},"@FragmentComponent"),K=()=>n.createElement(a,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/-fragment-coroutine-scope/index.html"},"@FragmentCoroutineScope"),P=()=>n.createElement(a,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/-fragment-view-coroutine-scope/index.html"},"@FragmentViewCoroutineScope"),F=()=>n.createElement(a,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/lazy-view-models.html"},"Fragment.lazyViewModels()"),R=()=>n.createElement(a,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/inject-view-models.html"},"Fragment.injectViewModels()"),D=()=>n.createElement(a,{href:"/compose/koject-compose-core/com.moriatsushi.koject.compose/remember-inject.html"},"rememberInject()"),z=()=>n.createElement(a,{href:"/compose/koject-compose-viewmodel/com.moriatsushi.koject.compose.viewmodel/inject-view-model.html"},"injectViewModel()"),H=()=>n.createElement(a,{href:"/compose/koject-compose-core/com.moriatsushi.koject.compose/-compose-component/index.html"},"@ComposeComponent"),U=()=>n.createElement(a,{href:"/compose/koject-compose-core/com.moriatsushi.koject.compose/-compose-coroutine-scope/index.html"},"@ComposeCoroutineScope"),Z=()=>n.createElement(a,{href:"/compose/koject-compose-core/com.moriatsushi.koject.compose/-compose-context/index.html"},"@ComposeContext")},3428:(e,t,o)=>{o.r(t),o.d(t,{assets:()=>s,contentTitle:()=>c,default:()=>u,frontMatter:()=>i,metadata:()=>l,toc:()=>m});var n=o(7462),a=(o(7294),o(3905)),r=o(1054);const i={slug:"android-support",title:"Koject v1.1.0 - Enhanced Android support",authors:"atsushi",image:"/blog/2023-03-11/ogp.png"},c="Koject v1.1.0 - Enhanced Android support",l={permalink:"/koject/blog/android-support",editUrl:"https://github.com/Mori-Atsushi/koject/tree/main/docs/blog/2023-03-11-android-support.mdx",source:"@site/blog/2023-03-11-android-support.mdx",title:"Koject v1.1.0 - Enhanced Android support",description:"I have released Koject v1.1.0, which includes enhanced support for Android application development.",date:"2023-03-11T00:00:00.000Z",formattedDate:"March 11, 2023",tags:[],readingTime:3.305,hasTruncateMarker:!0,authors:[{name:"Mori Atsushi",title:"Koject owner",url:"https://github.com/Mori-Atsushi",imageURL:"https://github.com/mori-atsushi.png",key:"atsushi"}],frontMatter:{slug:"android-support",title:"Koject v1.1.0 - Enhanced Android support",authors:"atsushi",image:"/blog/2023-03-11/ogp.png"},prevItem:{title:"Koject v1.2.0 - What are Android components?",permalink:"/koject/blog/android-components"},nextItem:{title:"Hello, Koject v1.0.0",permalink:"/koject/blog/first-stable-release"}},s={authorsImageUrls:[void 0]},m=[{value:"Inject ViewModels",id:"inject-viewmodels",level:2},{value:"Inject into Composable",id:"inject-into-composable",level:2},{value:"Component feature",id:"component-feature",level:2},{value:"Koject is evolving",id:"koject-is-evolving",level:2}],d={toc:m},p="wrapper";function u(e){let{components:t,...i}=e;return(0,a.kt)(p,(0,n.Z)({},d,i,{components:t,mdxType:"MDXLayout"}),(0,a.kt)("p",null,(0,a.kt)("img",{src:o(6603).Z,width:"1800",height:"630"})),(0,a.kt)("p",null,"I have released ",(0,a.kt)("strong",{parentName:"p"},"Koject v1.1.0"),", which includes enhanced support for Android application development.\nIn this article, I will introduce the new features and the Component feature that enables the features."),(0,a.kt)("p",null,(0,a.kt)("a",{parentName:"p",href:"/blog/jp/android-support"},(0,a.kt)("strong",{parentName:"a"},"\u65e5\u672c\u8a9e\u3067\u8aad\u3080 \u2192"))),(0,a.kt)("h2",{id:"inject-viewmodels"},"Inject ViewModels"),(0,a.kt)("p",null,"In Android application development, it is common to add dependencies such as Repository to AndroidX's ",(0,a.kt)("a",{parentName:"p",href:"https://developer.android.com/reference/androidx/lifecycle/ViewModel"},"ViewModel")," and use it from Activity or Fragment. With Koject v1.1.0, you can automatically inject ViewModel dependencies."),(0,a.kt)("p",null,"First, add the ",(0,a.kt)(r.Hc,{mdxType:"Provides"})," annotation to the class used in ViewModel and register it with the DI container.\nBy adding ",(0,a.kt)(r.eG,{mdxType:"Singleton"}),", it can register as a singleton."),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},"@Singleton\n@Provides\nclass UserRepository {\n    /* ... */\n}\n\n@Singleton\n@Provides\nclass ContentRepository {\n    /* ... */\n}\n")),(0,a.kt)("p",null,"Next, define the ViewModel.\nYou can inject the classes registered with the DI container in the constructor. Add ",(0,a.kt)("inlineCode",{parentName:"p"},"@Provides")," annotation and ",(0,a.kt)(r.tQ,{mdxType:"ViewModelComponent"})," annotation to ViewModel."),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},"@Provides\n@ViewModelComponent\nclass TopViewModel(\n    private val userRepository: UserRepository,\n    private val contentRepository: ContentRepository,\n): ViewModel() {\n    /* ... */\n}\n")),(0,a.kt)("p",null,"Finally, use the ",(0,a.kt)(r.eh,{mdxType:"ActivityInjectViewModels"})," function to obtain the ViewModel."),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},"class TopActivity : ComponentActivity() {\n    private val viewModel: TopViewModel by injectViewModels()\n\n    override fun onCreate(savedInstanceState: Bundle?) {\n        super.onCreate(savedInstanceState)\n        /* ... */\n    }\n}\n")),(0,a.kt)("p",null,"For Fragment, please use the ",(0,a.kt)(r.Q4,{mdxType:"FragmentInjectViewModels"})," function instead."),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},"class TopFragment : Fragment() {\n    private val viewModel: TopViewModel by injectViewModels()\n\n    override fun onCreateView(\n        inflater: LayoutInflater,\n        container: ViewGroup?,\n        savedInstanceState: Bundle?,\n    ): View? {\n        /* ... */\n    }\n}\n")),(0,a.kt)("p",null,"Additional dependencies are required to use this feature. For more details, please refer to the ",(0,a.kt)("a",{parentName:"p",href:"/docs/android/viewmodel"},"documentation"),"."),(0,a.kt)("admonition",{title:"Updated (2023/03/14)",type:"note"},(0,a.kt)("p",{parentName:"admonition"},(0,a.kt)("inlineCode",{parentName:"p"},"injectViewModels")," has been renamed to ",(0,a.kt)("inlineCode",{parentName:"p"},"lazyViewModels")," in v1.2.0."),(0,a.kt)("pre",{parentName:"admonition"},(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},"// Until v1.1.0\nprivate val viewModel: TopViewModel by injectViewModels()\n\n// Since v1.2.0\nprivate val viewModel: TopViewModel by lazyViewModels()\n"))),(0,a.kt)("h2",{id:"inject-into-composable"},"Inject into Composable"),(0,a.kt)("p",null,"Koject 1.1.0 also added support for ",(0,a.kt)("a",{parentName:"p",href:"https://developer.android.com/jetpack/compose"},"Jetpack Compose")," and ",(0,a.kt)("a",{parentName:"p",href:"https://www.jetbrains.com/lp/compose-mpp/"},"Compose Multiplatform"),"."),(0,a.kt)("p",null,"You can use the ",(0,a.kt)(r.Kj,{mdxType:"RememberInject"})," Composable function to use the provided class in a Composable function."),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},"@Composable\nfun Sample(\n    controller: SampleController = rememberInject()\n) {\n    /* ... */\n}\n")),(0,a.kt)("p",null,(0,a.kt)("inlineCode",{parentName:"p"},"rememberInject()")," can be called inside Composable functions, but it is recommended to ",(0,a.kt)("strong",{parentName:"p"},"use it as a default argument"),".\nThis will make it easier to replace during testing."),(0,a.kt)("p",null,"Please ",(0,a.kt)("strong",{parentName:"p"},"do not to use the ",(0,a.kt)(r.tB,{mdxType:"Inject"})," function inside Composable functions"),". Creating an instance for each rendering or performing inject processing multiple times may cause performance degradation."),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},"@Composable\nfun Sample(\n    controller: SampleController = inject() // DO NOT!!\n) {\n    /* ... */\n}\n")),(0,a.kt)("p",null,"For the setup method, please refer to the ",(0,a.kt)("a",{parentName:"p",href:"/docs/compose/core"},"documentation"),"."),(0,a.kt)("h2",{id:"component-feature"},"Component feature"),(0,a.kt)("p",null,"In ViewModel, you can inject a ",(0,a.kt)("a",{parentName:"p",href:"https://developer.android.com/reference/androidx/lifecycle/SavedStateHandle"},(0,a.kt)("inlineCode",{parentName:"a"},"SavedStateHandle"))," to save the state:"),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},"@Provides\n@ViewModelComponent\nclass SavedStateViewModel(\n    private val savedStateHandle: SavedStateHandle\n) : ViewModel() {\n    /* ... */\n}\n")),(0,a.kt)("p",null,"Koject has added the ",(0,a.kt)("strong",{parentName:"p"},"Component feature")," to provide instances created by the platform, such as ",(0,a.kt)("inlineCode",{parentName:"p"},"SavedStateHandle"),"."),(0,a.kt)("p",null,"A Component is a grouping of types registered with the DI container.\nBy default, the all types are registered in the root component.\nBy annotating with a Component annotation, they can be registered in another component."),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},"// Registered in RootComponent\n@Provides\nclass NormalClass\n\n// Registered in SomeComponent\n@SomeComponent\n@Provides\nclass ComponentClass\n")),(0,a.kt)("p",null,"Types registered in a component can only reference types within the same component and types in the root component."),(0,a.kt)("p",null,(0,a.kt)("img",{src:o(2915).Z,width:"1800",height:"771"})),(0,a.kt)("p",null,"You can also provide additional dependencies to a component."),(0,a.kt)("p",null,(0,a.kt)("img",{src:o(4608).Z,width:"1800",height:"771"})),(0,a.kt)("p",null,"Koject defines ",(0,a.kt)("inlineCode",{parentName:"p"},"@ViewModelComponent")," by default and adds ",(0,a.kt)("inlineCode",{parentName:"p"},"SavedStateHandle")," to ",(0,a.kt)("inlineCode",{parentName:"p"},"@ViewModelComponent"),".\nTherefore, be aware that you cannot inject ",(0,a.kt)("inlineCode",{parentName:"p"},"SavedStateHandle")," into a component other than ",(0,a.kt)("inlineCode",{parentName:"p"},"@ViewModelComponent"),"."),(0,a.kt)("p",null,"You can learn how to create a custom component in the ",(0,a.kt)("a",{parentName:"p",href:"/docs/core/component"},"documentation"),"."),(0,a.kt)("admonition",{title:"Experimental",type:"caution"},(0,a.kt)("p",{parentName:"admonition"},"The Component feature is an ",(0,a.kt)("strong",{parentName:"p"},"experimental")," API. It may change in the future.")),(0,a.kt)("h2",{id:"koject-is-evolving"},"Koject is evolving"),(0,a.kt)("p",null,"I believe that Koject v1.1.0 will provide even stronger support for Android application development.\nIn order to make the library even easier to use, I plan to add even more features in the future."),(0,a.kt)("p",null,"If you notice anything, please feel free to provide feedback on the ",(0,a.kt)("a",{parentName:"p",href:"https://github.com/Mori-Atsushi/koject/issues"},"Issue")," page."))}u.isMDXComponent=!0},6603:(e,t,o)=>{o.d(t,{Z:()=>n});const n=o.p+"assets/images/banner-c01476774e20baf3558488f2299086f9.png"},4608:(e,t,o)=>{o.d(t,{Z:()=>n});const n=o.p+"assets/images/component-extras-622353a1c4d12d8544bc2cebc2f336b9.png"},2915:(e,t,o)=>{o.d(t,{Z:()=>n});const n=o.p+"assets/images/component-ba80f974b7d2d3e17d29047c89549a4d.png"}}]);