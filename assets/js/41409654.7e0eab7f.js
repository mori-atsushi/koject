"use strict";(self.webpackChunkdocs=self.webpackChunkdocs||[]).push([[781],{3905:(e,t,o)=>{o.d(t,{Zo:()=>l,kt:()=>u});var n=o(7294);function i(e,t,o){return t in e?Object.defineProperty(e,t,{value:o,enumerable:!0,configurable:!0,writable:!0}):e[t]=o,e}function r(e,t){var o=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),o.push.apply(o,n)}return o}function c(e){for(var t=1;t<arguments.length;t++){var o=null!=arguments[t]?arguments[t]:{};t%2?r(Object(o),!0).forEach((function(t){i(e,t,o[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(o)):r(Object(o)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(o,t))}))}return e}function a(e,t){if(null==e)return{};var o,n,i=function(e,t){if(null==e)return{};var o,n,i={},r=Object.keys(e);for(n=0;n<r.length;n++)o=r[n],t.indexOf(o)>=0||(i[o]=e[o]);return i}(e,t);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);for(n=0;n<r.length;n++)o=r[n],t.indexOf(o)>=0||Object.prototype.propertyIsEnumerable.call(e,o)&&(i[o]=e[o])}return i}var m=n.createContext({}),s=function(e){var t=n.useContext(m),o=t;return e&&(o="function"==typeof e?e(t):c(c({},t),e)),o},l=function(e){var t=s(e.components);return n.createElement(m.Provider,{value:t},e.children)},d="mdxType",p={inlineCode:"code",wrapper:function(e){var t=e.children;return n.createElement(n.Fragment,{},t)}},h=n.forwardRef((function(e,t){var o=e.components,i=e.mdxType,r=e.originalType,m=e.parentName,l=a(e,["components","mdxType","originalType","parentName"]),d=s(o),h=i,u=d["".concat(m,".").concat(h)]||d[h]||p[h]||r;return o?n.createElement(u,c(c({ref:t},l),{},{components:o})):n.createElement(u,c({ref:t},l))}));function u(e,t){var o=arguments,i=t&&t.mdxType;if("string"==typeof e||i){var r=o.length,c=new Array(r);c[0]=h;var a={};for(var m in t)hasOwnProperty.call(t,m)&&(a[m]=t[m]);a.originalType=e,a[d]="string"==typeof e?e:i,c[1]=a;for(var s=2;s<r;s++)c[s]=o[s];return n.createElement.apply(null,c)}return n.createElement.apply(null,o)}h.displayName="MDXCreateElement"},1054:(e,t,o)=>{o.d(t,{BQ:()=>T,E:()=>k,GU:()=>z,Hc:()=>r,IQ:()=>C,Kj:()=>I,N8:()=>K,NR:()=>s,O$:()=>V,Or:()=>y,Pc:()=>h,Q4:()=>A,TD:()=>g,UK:()=>P,Vi:()=>D,Zf:()=>m,_P:()=>l,dK:()=>N,eG:()=>c,eh:()=>M,f3:()=>f,kJ:()=>E,m:()=>p,oX:()=>b,pn:()=>O,pt:()=>j,q:()=>a,q0:()=>S,ql:()=>u,s7:()=>F,tB:()=>d,tQ:()=>v,wn:()=>w,yc:()=>x});var n=o(7294);const i=e=>{let{children:t,href:o}=e;return n.createElement("a",{href:`/koject/api${o}`,target:"_blank"},n.createElement("code",null,t))},r=()=>n.createElement(i,{href:"/koject-core/com.moriatsushi.koject/-provides/index.html"},"@Provides"),c=()=>n.createElement(i,{href:"/koject-core/com.moriatsushi.koject/-singleton/index.html"},"@Singleton"),a=()=>n.createElement(i,{href:"/koject-core/com.moriatsushi.koject/-qualifier/index.html"},"@Qualifier"),m=()=>n.createElement(i,{href:"/koject-core/com.moriatsushi.koject/-named/index.html"},"@Named"),s=()=>n.createElement(i,{href:"/koject-core/com.moriatsushi.koject/-binds/index.html"},"@Binds"),l=()=>n.createElement(i,{href:"/koject-core/com.moriatsushi.koject/start.html"},"Koject.start()"),d=()=>n.createElement(i,{href:"/koject-core/com.moriatsushi.koject/inject.html"},"inject()"),p=()=>n.createElement(i,{href:"/koject-core/com.moriatsushi.koject/lazy-inject.html"},"lazyInject()"),h=()=>n.createElement(i,{href:"/koject-core/com.moriatsushi.koject.extras/-koject-extras/index.html"},"KojectExtras"),u=()=>n.createElement(i,{href:"/koject-core/com.moriatsushi.koject.extras/-koject-extras-message/index.html"},"@KojectExtrasMessage"),j=()=>n.createElement(i,{href:"/koject-core/com.moriatsushi.koject.component/-component-extras/index.html"},"ComponentExtras"),f=()=>n.createElement(i,{href:"/koject-core/com.moriatsushi.koject.error/-missing-extras-exception/index.html"},"MissingExtrasException"),k=()=>n.createElement(i,{href:"/android/koject-android-core/com.moriatsushi.koject.android/application.html"},"application()"),v=()=>n.createElement(i,{href:"/android/koject-android-viewmodel/com.moriatsushi.koject.android.viewmodel/-view-model-component/index.html"},"@ViewModelComponent"),y=()=>n.createElement(i,{href:"/android/koject-android-viewmodel/com.moriatsushi.koject.android.viewmodel/-view-model-coroutine-scope/index.html"},"@ViewModelCoroutineScope"),w=()=>n.createElement(i,{href:"/android/koject-android-activity/com.moriatsushi.koject/inject.html"},"ComponentActivity.inject()"),g=()=>n.createElement(i,{href:"/android/koject-android-activity/com.moriatsushi.koject/lazy-inject.html"},"ComponentActivity.lazyInject()"),x=()=>n.createElement(i,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/-activity-component/index.html"},"@ActivityComponent"),E=()=>n.createElement(i,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/-activity-context/index.html"},"@ActivityContext"),b=()=>n.createElement(i,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/-activity-context/index.html"},"@ActivityCoroutineScope"),C=()=>n.createElement(i,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/lazy-view-models.html"},"ComponentActivity.lazyViewModels()"),M=()=>n.createElement(i,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/inject-view-models.html"},"ComponentActivity.injectViewModels()"),V=()=>n.createElement(i,{href:"/android/koject-android-fragment/com.moriatsushi.koject/inject.html"},"Fragment.inject()"),O=()=>n.createElement(i,{href:"/android/koject-android-fragment/com.moriatsushi.koject/lazy-inject.html"},"Fragment.lazyInject()"),P=()=>n.createElement(i,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/-fragment-component/index.html"},"@FragmentComponent"),N=()=>n.createElement(i,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/-fragment-coroutine-scope/index.html"},"@FragmentCoroutineScope"),T=()=>n.createElement(i,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/-fragment-view-coroutine-scope/index.html"},"@FragmentViewCoroutineScope"),S=()=>n.createElement(i,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/lazy-view-models.html"},"Fragment.lazyViewModels()"),A=()=>n.createElement(i,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/inject-view-models.html"},"Fragment.injectViewModels()"),I=()=>n.createElement(i,{href:"/compose/koject-compose-core/com.moriatsushi.koject.compose/remember-inject.html"},"rememberInject()"),z=()=>n.createElement(i,{href:"/compose/koject-compose-viewmodel/com.moriatsushi.koject.compose.viewmodel/inject-view-model.html"},"injectViewModel()"),D=()=>n.createElement(i,{href:"/compose/koject-compose-core/com.moriatsushi.koject.compose/-compose-component/index.html"},"@ComposeComponent"),F=()=>n.createElement(i,{href:"/compose/koject-compose-core/com.moriatsushi.koject.compose/-compose-coroutine-scope/index.html"},"@ComposeCoroutineScope"),K=()=>n.createElement(i,{href:"/compose/koject-compose-core/com.moriatsushi.koject.compose/-compose-context/index.html"},"@ComposeContext")},7145:(e,t,o)=>{o.r(t),o.d(t,{assets:()=>s,contentTitle:()=>a,default:()=>h,frontMatter:()=>c,metadata:()=>m,toc:()=>l});var n=o(7462),i=(o(7294),o(3905)),r=o(1054);const c={},a="Inject ViewModels into Composable",m={unversionedId:"compose/viewmodel",id:"compose/viewmodel",title:"Inject ViewModels into Composable",description:"Koject can inject Android ViewModels into Composable.",source:"@site/docs/compose/viewmodel.mdx",sourceDirName:"compose",slug:"/compose/viewmodel",permalink:"/koject/docs/compose/viewmodel",draft:!1,editUrl:"https://github.com/Mori-Atsushi/koject/tree/main/docs/docs/compose/viewmodel.mdx",tags:[],version:"current",frontMatter:{},sidebar:"docs",previous:{title:"Inject into Composable",permalink:"/koject/docs/compose/core"}},s={},l=[{value:"Setup to use ViewModels in Compose",id:"setup-to-use-viewmodels-in-compose",level:2},{value:"Using ViewModels in Compose",id:"using-viewmodels-in-compose",level:2}],d={toc:l},p="wrapper";function h(e){let{components:t,...o}=e;return(0,i.kt)(p,(0,n.Z)({},d,o,{components:t,mdxType:"MDXLayout"}),(0,i.kt)("h1",{id:"inject-viewmodels-into-composable"},"Inject ViewModels into Composable"),(0,i.kt)("p",null,"Koject can inject Android ",(0,i.kt)("a",{parentName:"p",href:"https://developer.android.com/reference/androidx/lifecycle/ViewModel"},"ViewModels")," into Composable."),(0,i.kt)("admonition",{title:"LINK",type:"info"},(0,i.kt)("p",{parentName:"admonition"},"Please also refer the ",(0,i.kt)("a",{parentName:"p",href:"/docs/android/viewmodel"},"documentation")," for details on ViewModel, such as how to inject ViewModel into Activity / Fragment.")),(0,i.kt)("h2",{id:"setup-to-use-viewmodels-in-compose"},"Setup to use ViewModels in Compose"),(0,i.kt)("p",null,"To inject ViewModels into Composable, add the following dependencies:"),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-kotlin"},'dependencies {\n    implementation("com.moriatsushi.koject:koject-compose-viewmodel:1.2.0-beta01")\n}\n')),(0,i.kt)("p",null,"Please also refer to the ",(0,i.kt)("a",{parentName:"p",href:"/docs/setup"},"Setup document"),"."),(0,i.kt)("h2",{id:"using-viewmodels-in-compose"},"Using ViewModels in Compose"),(0,i.kt)("p",null,"Specify the ",(0,i.kt)(r.tQ,{mdxType:"ViewModelComponent"})," and ",(0,i.kt)(r.Hc,{mdxType:"Provides"})," annotations when defining a ViewModel.\nAs with other providing types, constructor injection is available."),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-kotlin"},"@Provides\n@ViewModelComponent\nclass TopViewModel(\n    private val userRepository: UserRepository,\n    private val contentRepository: ContentRepository,\n): ViewModel() {\n    /* ... */\n}\n")),(0,i.kt)("p",null,"When using ViewModels, use the ",(0,i.kt)(r.GU,{mdxType:"InjectViewModel"})," Composable function."),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-kotlin"},"@Composable\nfun TopPage(\n    viewModel: TopViewModel = injectViewModel()\n) {\n    /* ... */\n}\n")))}h.isMDXComponent=!0}}]);