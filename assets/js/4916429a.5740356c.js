"use strict";(self.webpackChunkdocs=self.webpackChunkdocs||[]).push([[3196],{3905:(e,t,o)=>{o.d(t,{Zo:()=>d,kt:()=>u});var r=o(7294);function n(e,t,o){return t in e?Object.defineProperty(e,t,{value:o,enumerable:!0,configurable:!0,writable:!0}):e[t]=o,e}function i(e,t){var o=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),o.push.apply(o,r)}return o}function c(e){for(var t=1;t<arguments.length;t++){var o=null!=arguments[t]?arguments[t]:{};t%2?i(Object(o),!0).forEach((function(t){n(e,t,o[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(o)):i(Object(o)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(o,t))}))}return e}function a(e,t){if(null==e)return{};var o,r,n=function(e,t){if(null==e)return{};var o,r,n={},i=Object.keys(e);for(r=0;r<i.length;r++)o=i[r],t.indexOf(o)>=0||(n[o]=e[o]);return n}(e,t);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);for(r=0;r<i.length;r++)o=i[r],t.indexOf(o)>=0||Object.prototype.propertyIsEnumerable.call(e,o)&&(n[o]=e[o])}return n}var m=r.createContext({}),s=function(e){var t=r.useContext(m),o=t;return e&&(o="function"==typeof e?e(t):c(c({},t),e)),o},d=function(e){var t=s(e.components);return r.createElement(m.Provider,{value:t},e.children)},l="mdxType",p={inlineCode:"code",wrapper:function(e){var t=e.children;return r.createElement(r.Fragment,{},t)}},h=r.forwardRef((function(e,t){var o=e.components,n=e.mdxType,i=e.originalType,m=e.parentName,d=a(e,["components","mdxType","originalType","parentName"]),l=s(o),h=n,u=l["".concat(m,".").concat(h)]||l[h]||p[h]||i;return o?r.createElement(u,c(c({ref:t},d),{},{components:o})):r.createElement(u,c({ref:t},d))}));function u(e,t){var o=arguments,n=t&&t.mdxType;if("string"==typeof e||n){var i=o.length,c=new Array(i);c[0]=h;var a={};for(var m in t)hasOwnProperty.call(t,m)&&(a[m]=t[m]);a.originalType=e,a[l]="string"==typeof e?e:n,c[1]=a;for(var s=2;s<i;s++)c[s]=o[s];return r.createElement.apply(null,c)}return r.createElement.apply(null,o)}h.displayName="MDXCreateElement"},1054:(e,t,o)=>{o.d(t,{BQ:()=>I,E:()=>k,GU:()=>D,Hc:()=>i,IQ:()=>O,Kj:()=>S,N8:()=>N,NR:()=>s,O$:()=>M,Or:()=>v,Pc:()=>h,Q4:()=>z,TD:()=>E,UK:()=>P,Vi:()=>V,Zf:()=>m,_P:()=>d,dK:()=>K,eG:()=>c,eh:()=>C,f3:()=>f,kJ:()=>b,m:()=>p,oX:()=>w,pn:()=>A,pt:()=>j,q:()=>a,q0:()=>T,ql:()=>u,s7:()=>F,tB:()=>l,tQ:()=>g,wn:()=>y,yc:()=>x});var r=o(7294);const n=e=>{let{children:t,href:o}=e;return r.createElement("a",{href:`/koject/api${o}`,target:"_blank"},r.createElement("code",null,t))},i=()=>r.createElement(n,{href:"/koject-core/com.moriatsushi.koject/-provides/index.html"},"@Provides"),c=()=>r.createElement(n,{href:"/koject-core/com.moriatsushi.koject/-singleton/index.html"},"@Singleton"),a=()=>r.createElement(n,{href:"/koject-core/com.moriatsushi.koject/-qualifier/index.html"},"@Qualifier"),m=()=>r.createElement(n,{href:"/koject-core/com.moriatsushi.koject/-named/index.html"},"@Named"),s=()=>r.createElement(n,{href:"/koject-core/com.moriatsushi.koject/-binds/index.html"},"@Binds"),d=()=>r.createElement(n,{href:"/koject-core/com.moriatsushi.koject/start.html"},"Koject.start()"),l=()=>r.createElement(n,{href:"/koject-core/com.moriatsushi.koject/inject.html"},"inject()"),p=()=>r.createElement(n,{href:"/koject-core/com.moriatsushi.koject/lazy-inject.html"},"lazyInject()"),h=()=>r.createElement(n,{href:"/koject-core/com.moriatsushi.koject.extras/-koject-extras/index.html"},"KojectExtras"),u=()=>r.createElement(n,{href:"/koject-core/com.moriatsushi.koject.extras/-koject-extras-message/index.html"},"@KojectExtrasMessage"),j=()=>r.createElement(n,{href:"/koject-core/com.moriatsushi.koject.component/-component-extras/index.html"},"ComponentExtras"),f=()=>r.createElement(n,{href:"/koject-core/com.moriatsushi.koject.error/-missing-extras-exception/index.html"},"MissingExtrasException"),k=()=>r.createElement(n,{href:"/android/koject-android-core/com.moriatsushi.koject.android/application.html"},"application()"),g=()=>r.createElement(n,{href:"/android/koject-android-viewmodel/com.moriatsushi.koject.android.viewmodel/-view-model-component/index.html"},"@ViewModelComponent"),v=()=>r.createElement(n,{href:"/android/koject-android-viewmodel/com.moriatsushi.koject.android.viewmodel/-view-model-coroutine-scope/index.html"},"@ViewModelCoroutineScope"),y=()=>r.createElement(n,{href:"/android/koject-android-activity/com.moriatsushi.koject/inject.html"},"ComponentActivity.inject()"),E=()=>r.createElement(n,{href:"/android/koject-android-activity/com.moriatsushi.koject/lazy-inject.html"},"ComponentActivity.lazyInject()"),x=()=>r.createElement(n,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/-activity-component/index.html"},"@ActivityComponent"),b=()=>r.createElement(n,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/-activity-context/index.html"},"@ActivityContext"),w=()=>r.createElement(n,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/-activity-context/index.html"},"@ActivityCoroutineScope"),O=()=>r.createElement(n,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/lazy-view-models.html"},"ComponentActivity.lazyViewModels()"),C=()=>r.createElement(n,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/inject-view-models.html"},"ComponentActivity.injectViewModels()"),M=()=>r.createElement(n,{href:"/android/koject-android-fragment/com.moriatsushi.koject/inject.html"},"Fragment.inject()"),A=()=>r.createElement(n,{href:"/android/koject-android-fragment/com.moriatsushi.koject/lazy-inject.html"},"Fragment.lazyInject()"),P=()=>r.createElement(n,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/-fragment-component/index.html"},"@FragmentComponent"),K=()=>r.createElement(n,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/-fragment-coroutine-scope/index.html"},"@FragmentCoroutineScope"),I=()=>r.createElement(n,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/-fragment-view-coroutine-scope/index.html"},"@FragmentViewCoroutineScope"),T=()=>r.createElement(n,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/lazy-view-models.html"},"Fragment.lazyViewModels()"),z=()=>r.createElement(n,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/inject-view-models.html"},"Fragment.injectViewModels()"),S=()=>r.createElement(n,{href:"/compose/koject-compose-core/com.moriatsushi.koject.compose/remember-inject.html"},"rememberInject()"),D=()=>r.createElement(n,{href:"/compose/koject-compose-viewmodel/com.moriatsushi.koject.compose.viewmodel/inject-view-model.html"},"injectViewModel()"),V=()=>r.createElement(n,{href:"/compose/koject-compose-core/com.moriatsushi.koject.compose/-compose-component/index.html"},"@ComposeComponent"),F=()=>r.createElement(n,{href:"/compose/koject-compose-core/com.moriatsushi.koject.compose/-compose-coroutine-scope/index.html"},"@ComposeCoroutineScope"),N=()=>r.createElement(n,{href:"/compose/koject-compose-core/com.moriatsushi.koject.compose/-compose-context/index.html"},"@ComposeContext")},1364:(e,t,o)=>{o.r(t),o.d(t,{assets:()=>m,contentTitle:()=>c,default:()=>p,frontMatter:()=>i,metadata:()=>a,toc:()=>s});var r=o(7462),n=(o(7294),o(3905));o(1054);const i={slug:"android-support",title:"Koject v1.1.0 - Enhanced Android support",authors:"atsushi",image:"/blog/2023-03-11/ogp.png"},c="Koject v1.1.0 - Enhanced Android support",a={permalink:"/koject/blog/android-support",editUrl:"https://github.com/Mori-Atsushi/koject/tree/main/docs/blog/2023-03-11-android-support.mdx",source:"@site/blog/2023-03-11-android-support.mdx",title:"Koject v1.1.0 - Enhanced Android support",description:"I have released Koject v1.1.0, which includes enhanced support for Android application development.",date:"2023-03-11T00:00:00.000Z",formattedDate:"March 11, 2023",tags:[],readingTime:3.305,hasTruncateMarker:!0,authors:[{name:"Mori Atsushi",title:"Koject owner",url:"https://github.com/Mori-Atsushi",imageURL:"https://github.com/mori-atsushi.png",key:"atsushi"}],frontMatter:{slug:"android-support",title:"Koject v1.1.0 - Enhanced Android support",authors:"atsushi",image:"/blog/2023-03-11/ogp.png"},prevItem:{title:"Koject v1.2.0 - What are Android components?",permalink:"/koject/blog/android-components"},nextItem:{title:"Hello, Koject v1.0.0",permalink:"/koject/blog/first-stable-release"}},m={authorsImageUrls:[void 0]},s=[],d={toc:s},l="wrapper";function p(e){let{components:t,...i}=e;return(0,n.kt)(l,(0,r.Z)({},d,i,{components:t,mdxType:"MDXLayout"}),(0,n.kt)("p",null,(0,n.kt)("img",{src:o(6603).Z,width:"1800",height:"630"})),(0,n.kt)("p",null,"I have released ",(0,n.kt)("strong",{parentName:"p"},"Koject v1.1.0"),", which includes enhanced support for Android application development.\nIn this article, I will introduce the new features and the Component feature that enables the features."))}p.isMDXComponent=!0},6603:(e,t,o)=>{o.d(t,{Z:()=>r});const r=o.p+"assets/images/banner-c01476774e20baf3558488f2299086f9.png"}}]);