"use strict";(self.webpackChunkdocs=self.webpackChunkdocs||[]).push([[3053],{3905:(e,t,n)=>{n.d(t,{Zo:()=>s,kt:()=>k});var o=n(7294);function r(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function a(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);t&&(o=o.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,o)}return n}function i(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?a(Object(n),!0).forEach((function(t){r(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):a(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function c(e,t){if(null==e)return{};var n,o,r=function(e,t){if(null==e)return{};var n,o,r={},a=Object.keys(e);for(o=0;o<a.length;o++)n=a[o],t.indexOf(n)>=0||(r[n]=e[n]);return r}(e,t);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);for(o=0;o<a.length;o++)n=a[o],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(r[n]=e[n])}return r}var m=o.createContext({}),l=function(e){var t=o.useContext(m),n=t;return e&&(n="function"==typeof e?e(t):i(i({},t),e)),n},s=function(e){var t=l(e.components);return o.createElement(m.Provider,{value:t},e.children)},d="mdxType",p={inlineCode:"code",wrapper:function(e){var t=e.children;return o.createElement(o.Fragment,{},t)}},u=o.forwardRef((function(e,t){var n=e.components,r=e.mdxType,a=e.originalType,m=e.parentName,s=c(e,["components","mdxType","originalType","parentName"]),d=l(n),u=r,k=d["".concat(m,".").concat(u)]||d[u]||p[u]||a;return n?o.createElement(k,i(i({ref:t},s),{},{components:n})):o.createElement(k,i({ref:t},s))}));function k(e,t){var n=arguments,r=t&&t.mdxType;if("string"==typeof e||r){var a=n.length,i=new Array(a);i[0]=u;var c={};for(var m in t)hasOwnProperty.call(t,m)&&(c[m]=t[m]);c.originalType=e,c[d]="string"==typeof e?e:r,i[1]=c;for(var l=2;l<a;l++)i[l]=n[l];return o.createElement.apply(null,i)}return o.createElement.apply(null,n)}u.displayName="MDXCreateElement"},1054:(e,t,n)=>{n.d(t,{$h:()=>f,BQ:()=>K,E:()=>x,GU:()=>q,Hc:()=>a,IQ:()=>S,Kj:()=>H,N8:()=>Q,NR:()=>l,O$:()=>I,Or:()=>F,Pc:()=>k,Q4:()=>V,TD:()=>b,UK:()=>P,Uf:()=>v,Vi:()=>D,Vn:()=>y,Zf:()=>m,_P:()=>s,dK:()=>M,eG:()=>i,eh:()=>A,f3:()=>g,kJ:()=>w,m:()=>u,oX:()=>O,pn:()=>T,pt:()=>j,q:()=>c,q0:()=>z,qK:()=>d,ql:()=>h,s7:()=>B,tB:()=>p,tQ:()=>C,wn:()=>E,yc:()=>N});var o=n(7294);const r=e=>{let{children:t,href:n}=e;return o.createElement("a",{href:`/koject/api${n}`,target:"_blank"},o.createElement("code",null,t))},a=()=>o.createElement(r,{href:"/koject-core/com.moriatsushi.koject/-provides/index.html"},"@Provides"),i=()=>o.createElement(r,{href:"/koject-core/com.moriatsushi.koject/-singleton/index.html"},"@Singleton"),c=()=>o.createElement(r,{href:"/koject-core/com.moriatsushi.koject/-qualifier/index.html"},"@Qualifier"),m=()=>o.createElement(r,{href:"/koject-core/com.moriatsushi.koject/-named/index.html"},"@Named"),l=()=>o.createElement(r,{href:"/koject-core/com.moriatsushi.koject/-binds/index.html"},"@Binds"),s=()=>o.createElement(r,{href:"/koject-core/com.moriatsushi.koject/start.html"},"Koject.start()"),d=()=>o.createElement(r,{href:"/koject-core/com.moriatsushi.koject/-koject/stop.html"},"Koject.stop()"),p=()=>o.createElement(r,{href:"/koject-core/com.moriatsushi.koject/inject.html"},"inject()"),u=()=>o.createElement(r,{href:"/koject-core/com.moriatsushi.koject/lazy-inject.html"},"lazyInject()"),k=()=>o.createElement(r,{href:"/koject-core/com.moriatsushi.koject.extras/-koject-extras/index.html"},"KojectExtras"),h=()=>o.createElement(r,{href:"/koject-core/com.moriatsushi.koject.extras/-koject-extras-message/index.html"},"@KojectExtrasMessage"),j=()=>o.createElement(r,{href:"/koject-core/com.moriatsushi.koject.component/-component-extras/index.html"},"ComponentExtras"),g=()=>o.createElement(r,{href:"/koject-core/com.moriatsushi.koject.error/-missing-extras-exception/index.html"},"MissingExtrasException"),f=()=>o.createElement(r,{href:"/koject-test/com.moriatsushi.koject.test/run-test.html"},"Koject.runTest()"),y=()=>o.createElement(r,{href:"/koject-test/com.moriatsushi.koject.test/start-test.html"},"Koject.startTest()"),v=()=>o.createElement(r,{href:"/koject-test/com.moriatsushi.koject.test/-test-provides/index.html"},"@TestProvides"),x=()=>o.createElement(r,{href:"/android/koject-android-core/com.moriatsushi.koject.android/application.html"},"application()"),C=()=>o.createElement(r,{href:"/android/koject-android-viewmodel/com.moriatsushi.koject.android.viewmodel/-view-model-component/index.html"},"@ViewModelComponent"),F=()=>o.createElement(r,{href:"/android/koject-android-viewmodel/com.moriatsushi.koject.android.viewmodel/-view-model-coroutine-scope/index.html"},"@ViewModelCoroutineScope"),E=()=>o.createElement(r,{href:"/android/koject-android-activity/com.moriatsushi.koject/inject.html"},"ComponentActivity.inject()"),b=()=>o.createElement(r,{href:"/android/koject-android-activity/com.moriatsushi.koject/lazy-inject.html"},"ComponentActivity.lazyInject()"),N=()=>o.createElement(r,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/-activity-component/index.html"},"@ActivityComponent"),w=()=>o.createElement(r,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/-activity-context/index.html"},"@ActivityContext"),O=()=>o.createElement(r,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/-activity-context/index.html"},"@ActivityCoroutineScope"),S=()=>o.createElement(r,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/lazy-view-models.html"},"ComponentActivity.lazyViewModels()"),A=()=>o.createElement(r,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/inject-view-models.html"},"ComponentActivity.injectViewModels()"),I=()=>o.createElement(r,{href:"/android/koject-android-fragment/com.moriatsushi.koject/inject.html"},"Fragment.inject()"),T=()=>o.createElement(r,{href:"/android/koject-android-fragment/com.moriatsushi.koject/lazy-inject.html"},"Fragment.lazyInject()"),P=()=>o.createElement(r,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/-fragment-component/index.html"},"@FragmentComponent"),M=()=>o.createElement(r,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/-fragment-coroutine-scope/index.html"},"@FragmentCoroutineScope"),K=()=>o.createElement(r,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/-fragment-view-coroutine-scope/index.html"},"@FragmentViewCoroutineScope"),z=()=>o.createElement(r,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/lazy-view-models.html"},"Fragment.lazyViewModels()"),V=()=>o.createElement(r,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/inject-view-models.html"},"Fragment.injectViewModels()"),H=()=>o.createElement(r,{href:"/compose/koject-compose-core/com.moriatsushi.koject.compose/remember-inject.html"},"rememberInject()"),q=()=>o.createElement(r,{href:"/compose/koject-compose-viewmodel/com.moriatsushi.koject.compose.viewmodel/inject-view-model.html"},"injectViewModel()"),D=()=>o.createElement(r,{href:"/compose/koject-compose-core/com.moriatsushi.koject.compose/-compose-component/index.html"},"@ComposeComponent"),B=()=>o.createElement(r,{href:"/compose/koject-compose-core/com.moriatsushi.koject.compose/-compose-coroutine-scope/index.html"},"@ComposeCoroutineScope"),Q=()=>o.createElement(r,{href:"/compose/koject-compose-core/com.moriatsushi.koject.compose/-compose-context/index.html"},"@ComposeContext")},2388:(e,t,n)=>{n.r(t),n.d(t,{assets:()=>l,contentTitle:()=>c,default:()=>u,frontMatter:()=>i,metadata:()=>m,toc:()=>s});var o=n(7462),r=(n(7294),n(3905)),a=n(1054);const i={},c="Inject into Fragment",m={unversionedId:"android/fragment",id:"android/fragment",title:"Inject into Fragment",description:"Koject provides additional support for injecting into Fragments.",source:"@site/docs/android/fragment.mdx",sourceDirName:"android",slug:"/android/fragment",permalink:"/koject/docs/android/fragment",draft:!1,editUrl:"https://github.com/Mori-Atsushi/koject/tree/main/docs/docs/android/fragment.mdx",tags:[],version:"current",frontMatter:{},sidebar:"docs",previous:{title:"Inject into Activity",permalink:"/koject/docs/android/activity"},next:{title:"Android components",permalink:"/koject/docs/android/components"}},l={},s=[{value:"Setup for Fragment",id:"setup-for-fragment",level:2},{value:"Basic usage",id:"basic-usage",level:2},{value:"Inject Fragment into other types",id:"inject-fragment-into-other-types",level:2},{value:"Inject Activity / Context",id:"inject-activity--context",level:2},{value:"Inject Fragment&#39;s CoroutineScope",id:"inject-fragments-coroutinescope",level:2}],d={toc:s},p="wrapper";function u(e){let{components:t,...n}=e;return(0,r.kt)(p,(0,o.Z)({},d,n,{components:t,mdxType:"MDXLayout"}),(0,r.kt)("h1",{id:"inject-into-fragment"},"Inject into Fragment"),(0,r.kt)("p",null,"Koject provides additional support for injecting into Fragments."),(0,r.kt)("h2",{id:"setup-for-fragment"},"Setup for Fragment"),(0,r.kt)("p",null,"The following dependency is required to take advantage of the additional support."),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-kotlin"},'dependencies {\n    implementation("com.moriatsushi.koject:koject-android-fragment:1.3.0-beta01")\n}\n')),(0,r.kt)("p",null,"Please also refer to the ",(0,r.kt)("a",{parentName:"p",href:"/docs/setup"},"Setup document"),"."),(0,r.kt)("h2",{id:"basic-usage"},"Basic usage"),(0,r.kt)("p",null,"You can use the ",(0,r.kt)(a.O$,{mdxType:"FragmentInject"})," or ",(0,r.kt)(a.pn,{mdxType:"FragmentLazyInject"})," methods to inject any provided type."),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-kotlin"},"@Provides\n@Singleton\nclass Repository\n\n@Provides\nclass Controller(\n    private val repository: Repository\n)\n")),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-kotlin"},"class MyFragment: Fragment {\n    val controller: Controller by lazyInject()\n}\n")),(0,r.kt)("h2",{id:"inject-fragment-into-other-types"},"Inject Fragment into other types"),(0,r.kt)("p",null,"Koject defines the ",(0,r.kt)(a.UK,{mdxType:"FragmentComponent"})," and adding this annotation restricts it to be injectable only to ",(0,r.kt)("inlineCode",{parentName:"p"},"Fragment")," or ",(0,r.kt)("inlineCode",{parentName:"p"},"FragmentComponent")," types."),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-kotlin"},"@FragmentComponent\nclass FragmentHelper\n")),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-kotlin"},"@FragmentComponent\nclass FragmentHelperHolder(\n    val helper: FragmentHelper // can be injected\n)\n")),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-kotlin"},"class MyFragment: Fragment {\n    val helper: FragmentHelper by lazyInject() // can be injected\n}\n")),(0,r.kt)("p",null,"You can inject ",(0,r.kt)("inlineCode",{parentName:"p"},"Fragment")," into ",(0,r.kt)("inlineCode",{parentName:"p"},"FragmentComponent")," types."),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-kotlin"},"@FragmentComponent\nclass FragmentHelper(\n    val fragment: Fragment // can be injected\n)\n")),(0,r.kt)("h2",{id:"inject-activity--context"},"Inject Activity / Context"),(0,r.kt)("p",null,"The following three types of Activities are also available for ",(0,r.kt)("inlineCode",{parentName:"p"},"FragmentComponent")," types."),(0,r.kt)("ul",null,(0,r.kt)("li",{parentName:"ul"},(0,r.kt)("inlineCode",{parentName:"li"},"androidx.fragment.app.FragmentActivity")),(0,r.kt)("li",{parentName:"ul"},(0,r.kt)("inlineCode",{parentName:"li"},"androidx.activity.ComponentActivity")),(0,r.kt)("li",{parentName:"ul"},(0,r.kt)("inlineCode",{parentName:"li"},"android.app.Activity"))),(0,r.kt)("p",null,"You can use them like this:"),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-kotlin"},"@FragmentComponent\nclass FragmentHelper(\n    val activity: ComponentActivity // can be injected\n)\n")),(0,r.kt)("p",null,"When using Activity, be careful to inject after the Fragment is attached."),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-kotlin"},"class MyFragment: Fragment() {\n    val helper: Fragment = inject() // error!\n    val helper: Fragment by lazyInject() // can be injected\n\n    override fun onAttach(context: Context) {\n        super.onAttach(context)\n        \n        val helper: Fragment = inject() // can be injected\n    }\n}\n")),(0,r.kt)("p",null,"You can also inject ",(0,r.kt)("inlineCode",{parentName:"p"},"android.content.Context")," by adding an ",(0,r.kt)(a.kJ,{mdxType:"ActivityContext"})," qualifier."),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-kotlin"},"@FragmentComponent\nclass FragmentHelper(\n    @ActivityContext\n    val context: Context // activity's context\n)\n")),(0,r.kt)("h2",{id:"inject-fragments-coroutinescope"},"Inject Fragment's CoroutineScope"),(0,r.kt)("p",null,"The ",(0,r.kt)("inlineCode",{parentName:"p"},"FragmentComponent")," type can also inject an fragment-scoped ",(0,r.kt)("inlineCode",{parentName:"p"},"CoroutineScope")," and fragment-view-scoped ",(0,r.kt)("inlineCode",{parentName:"p"},"CoroutineScope"),".\nUse ",(0,r.kt)(a.dK,{mdxType:"FragmentCoroutineScope"})," qualifier and ",(0,r.kt)(a.BQ,{mdxType:"FragmentViewCoroutineScope"})," qualifier respectively."),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-kotlin"},"@FragmentComponent\nclass FragmentHelper(\n    @FragmentCoroutineScope\n    val scope: CoroutineScope, // same as Fragment.lifecycleScope\n    @FragmentViewCoroutineScope\n    val viewScope: CoroutineScope, // same as Fragment.viewLifecycleOwner.lifecycleScope\n)\n")),(0,r.kt)("admonition",{title:"LINK",type:"info"},(0,r.kt)("p",{parentName:"admonition"},"Check the ",(0,r.kt)("a",{parentName:"p",href:"/docs/android/components"},"Android components documentation")," for all available components for Android.")))}u.isMDXComponent=!0}}]);