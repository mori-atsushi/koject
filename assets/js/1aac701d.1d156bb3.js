"use strict";(self.webpackChunkdocs=self.webpackChunkdocs||[]).push([[5650],{3905:(e,t,n)=>{n.d(t,{Zo:()=>d,kt:()=>k});var o=n(7294);function a(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function i(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);t&&(o=o.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,o)}return n}function r(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?i(Object(n),!0).forEach((function(t){a(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):i(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function l(e,t){if(null==e)return{};var n,o,a=function(e,t){if(null==e)return{};var n,o,a={},i=Object.keys(e);for(o=0;o<i.length;o++)n=i[o],t.indexOf(n)>=0||(a[n]=e[n]);return a}(e,t);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);for(o=0;o<i.length;o++)n=i[o],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(a[n]=e[n])}return a}var s=o.createContext({}),c=function(e){var t=o.useContext(s),n=t;return e&&(n="function"==typeof e?e(t):r(r({},t),e)),n},d=function(e){var t=c(e.components);return o.createElement(s.Provider,{value:t},e.children)},p="mdxType",m={inlineCode:"code",wrapper:function(e){var t=e.children;return o.createElement(o.Fragment,{},t)}},u=o.forwardRef((function(e,t){var n=e.components,a=e.mdxType,i=e.originalType,s=e.parentName,d=l(e,["components","mdxType","originalType","parentName"]),p=c(n),u=a,k=p["".concat(s,".").concat(u)]||p[u]||m[u]||i;return n?o.createElement(k,r(r({ref:t},d),{},{components:n})):o.createElement(k,r({ref:t},d))}));function k(e,t){var n=arguments,a=t&&t.mdxType;if("string"==typeof e||a){var i=n.length,r=new Array(i);r[0]=u;var l={};for(var s in t)hasOwnProperty.call(t,s)&&(l[s]=t[s]);l.originalType=e,l[p]="string"==typeof e?e:a,r[1]=l;for(var c=2;c<i;c++)r[c]=n[c];return o.createElement.apply(null,r)}return o.createElement.apply(null,n)}u.displayName="MDXCreateElement"},5162:(e,t,n)=>{n.d(t,{Z:()=>r});var o=n(7294),a=n(6010);const i={tabItem:"tabItem_Ymn6"};function r(e){let{children:t,hidden:n,className:r}=e;return o.createElement("div",{role:"tabpanel",className:(0,a.Z)(i.tabItem,r),hidden:n},t)}},4866:(e,t,n)=>{n.d(t,{Z:()=>j});var o=n(7462),a=n(7294),i=n(6010),r=n(2466),l=n(6550),s=n(1980),c=n(7392),d=n(12);function p(e){return function(e){return a.Children.map(e,(e=>{if(!e||(0,a.isValidElement)(e)&&function(e){const{props:t}=e;return!!t&&"object"==typeof t&&"value"in t}(e))return e;throw new Error(`Docusaurus error: Bad <Tabs> child <${"string"==typeof e.type?e.type:e.type.name}>: all children of the <Tabs> component should be <TabItem>, and every <TabItem> should have a unique "value" prop.`)}))?.filter(Boolean)??[]}(e).map((e=>{let{props:{value:t,label:n,attributes:o,default:a}}=e;return{value:t,label:n,attributes:o,default:a}}))}function m(e){const{values:t,children:n}=e;return(0,a.useMemo)((()=>{const e=t??p(n);return function(e){const t=(0,c.l)(e,((e,t)=>e.value===t.value));if(t.length>0)throw new Error(`Docusaurus error: Duplicate values "${t.map((e=>e.value)).join(", ")}" found in <Tabs>. Every value needs to be unique.`)}(e),e}),[t,n])}function u(e){let{value:t,tabValues:n}=e;return n.some((e=>e.value===t))}function k(e){let{queryString:t=!1,groupId:n}=e;const o=(0,l.k6)(),i=function(e){let{queryString:t=!1,groupId:n}=e;if("string"==typeof t)return t;if(!1===t)return null;if(!0===t&&!n)throw new Error('Docusaurus error: The <Tabs> component groupId prop is required if queryString=true, because this value is used as the search param name. You can also provide an explicit value such as queryString="my-search-param".');return n??null}({queryString:t,groupId:n});return[(0,s._X)(i),(0,a.useCallback)((e=>{if(!i)return;const t=new URLSearchParams(o.location.search);t.set(i,e),o.replace({...o.location,search:t.toString()})}),[i,o])]}function h(e){const{defaultValue:t,queryString:n=!1,groupId:o}=e,i=m(e),[r,l]=(0,a.useState)((()=>function(e){let{defaultValue:t,tabValues:n}=e;if(0===n.length)throw new Error("Docusaurus error: the <Tabs> component requires at least one <TabItem> children component");if(t){if(!u({value:t,tabValues:n}))throw new Error(`Docusaurus error: The <Tabs> has a defaultValue "${t}" but none of its children has the corresponding value. Available values are: ${n.map((e=>e.value)).join(", ")}. If you intend to show no default tab, use defaultValue={null} instead.`);return t}const o=n.find((e=>e.default))??n[0];if(!o)throw new Error("Unexpected error: 0 tabValues");return o.value}({defaultValue:t,tabValues:i}))),[s,c]=k({queryString:n,groupId:o}),[p,h]=function(e){let{groupId:t}=e;const n=function(e){return e?`docusaurus.tab.${e}`:null}(t),[o,i]=(0,d.Nk)(n);return[o,(0,a.useCallback)((e=>{n&&i.set(e)}),[n,i])]}({groupId:o}),g=(()=>{const e=s??p;return u({value:e,tabValues:i})?e:null})();(0,a.useLayoutEffect)((()=>{g&&l(g)}),[g]);return{selectedValue:r,selectValue:(0,a.useCallback)((e=>{if(!u({value:e,tabValues:i}))throw new Error(`Can't select invalid tab value=${e}`);l(e),c(e),h(e)}),[c,h,i]),tabValues:i}}var g=n(2389);const f={tabList:"tabList__CuJ",tabItem:"tabItem_LNqP"};function v(e){let{className:t,block:n,selectedValue:l,selectValue:s,tabValues:c}=e;const d=[],{blockElementScrollPositionUntilNextRender:p}=(0,r.o5)(),m=e=>{const t=e.currentTarget,n=d.indexOf(t),o=c[n].value;o!==l&&(p(t),s(o))},u=e=>{let t=null;switch(e.key){case"Enter":m(e);break;case"ArrowRight":{const n=d.indexOf(e.currentTarget)+1;t=d[n]??d[0];break}case"ArrowLeft":{const n=d.indexOf(e.currentTarget)-1;t=d[n]??d[d.length-1];break}}t?.focus()};return a.createElement("ul",{role:"tablist","aria-orientation":"horizontal",className:(0,i.Z)("tabs",{"tabs--block":n},t)},c.map((e=>{let{value:t,label:n,attributes:r}=e;return a.createElement("li",(0,o.Z)({role:"tab",tabIndex:l===t?0:-1,"aria-selected":l===t,key:t,ref:e=>d.push(e),onKeyDown:u,onClick:m},r,{className:(0,i.Z)("tabs__item",f.tabItem,r?.className,{"tabs__item--active":l===t})}),n??t)})))}function y(e){let{lazy:t,children:n,selectedValue:o}=e;const i=(Array.isArray(n)?n:[n]).filter(Boolean);if(t){const e=i.find((e=>e.props.value===o));return e?(0,a.cloneElement)(e,{className:"margin-top--md"}):null}return a.createElement("div",{className:"margin-top--md"},i.map(((e,t)=>(0,a.cloneElement)(e,{key:t,hidden:e.props.value!==o}))))}function b(e){const t=h(e);return a.createElement("div",{className:(0,i.Z)("tabs-container",f.tabList)},a.createElement(v,(0,o.Z)({},e,t)),a.createElement(y,(0,o.Z)({},e,t)))}function j(e){const t=(0,g.Z)();return a.createElement(b,(0,o.Z)({key:String(t)},e))}},1054:(e,t,n)=>{n.d(t,{$h:()=>v,BQ:()=>V,E:()=>j,GU:()=>R,Hc:()=>i,IQ:()=>E,Kj:()=>O,N8:()=>J,NR:()=>c,O$:()=>S,Or:()=>N,Pc:()=>k,Q4:()=>H,TD:()=>T,UK:()=>P,Uf:()=>b,Vi:()=>Z,Vn:()=>y,Zf:()=>s,_P:()=>d,dK:()=>L,eG:()=>r,eh:()=>M,f3:()=>f,kJ:()=>x,m:()=>u,oX:()=>A,pn:()=>K,pt:()=>g,q:()=>l,q0:()=>D,qK:()=>p,ql:()=>h,s7:()=>F,tB:()=>m,tQ:()=>w,wn:()=>C,yc:()=>I});var o=n(7294);const a=e=>{let{children:t,href:n}=e;return o.createElement("a",{href:`/koject/api${n}`,target:"_blank"},o.createElement("code",null,t))},i=()=>o.createElement(a,{href:"/koject-core/com.moriatsushi.koject/-provides/index.html"},"@Provides"),r=()=>o.createElement(a,{href:"/koject-core/com.moriatsushi.koject/-singleton/index.html"},"@Singleton"),l=()=>o.createElement(a,{href:"/koject-core/com.moriatsushi.koject/-qualifier/index.html"},"@Qualifier"),s=()=>o.createElement(a,{href:"/koject-core/com.moriatsushi.koject/-named/index.html"},"@Named"),c=()=>o.createElement(a,{href:"/koject-core/com.moriatsushi.koject/-binds/index.html"},"@Binds"),d=()=>o.createElement(a,{href:"/koject-core/com.moriatsushi.koject/start.html"},"Koject.start()"),p=()=>o.createElement(a,{href:"/koject-core/com.moriatsushi.koject/-koject/stop.html"},"Koject.stop()"),m=()=>o.createElement(a,{href:"/koject-core/com.moriatsushi.koject/inject.html"},"inject()"),u=()=>o.createElement(a,{href:"/koject-core/com.moriatsushi.koject/lazy-inject.html"},"lazyInject()"),k=()=>o.createElement(a,{href:"/koject-core/com.moriatsushi.koject.extras/-koject-extras/index.html"},"KojectExtras"),h=()=>o.createElement(a,{href:"/koject-core/com.moriatsushi.koject.extras/-koject-extras-message/index.html"},"@KojectExtrasMessage"),g=()=>o.createElement(a,{href:"/koject-core/com.moriatsushi.koject.component/-component-extras/index.html"},"ComponentExtras"),f=()=>o.createElement(a,{href:"/koject-core/com.moriatsushi.koject.error/-missing-extras-exception/index.html"},"MissingExtrasException"),v=()=>o.createElement(a,{href:"/koject-test/com.moriatsushi.koject.test/run-test.html"},"Koject.runTest()"),y=()=>o.createElement(a,{href:"/koject-test/com.moriatsushi.koject.test/start-test.html"},"Koject.startTest()"),b=()=>o.createElement(a,{href:"/koject-test/com.moriatsushi.koject.test/-test-provides/index.html"},"@TestProvides"),j=()=>o.createElement(a,{href:"/android/koject-android-core/com.moriatsushi.koject.android/application.html"},"application()"),w=()=>o.createElement(a,{href:"/android/koject-android-viewmodel/com.moriatsushi.koject.android.viewmodel/-view-model-component/index.html"},"@ViewModelComponent"),N=()=>o.createElement(a,{href:"/android/koject-android-viewmodel/com.moriatsushi.koject.android.viewmodel/-view-model-coroutine-scope/index.html"},"@ViewModelCoroutineScope"),C=()=>o.createElement(a,{href:"/android/koject-android-activity/com.moriatsushi.koject/inject.html"},"ComponentActivity.inject()"),T=()=>o.createElement(a,{href:"/android/koject-android-activity/com.moriatsushi.koject/lazy-inject.html"},"ComponentActivity.lazyInject()"),I=()=>o.createElement(a,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/-activity-component/index.html"},"@ActivityComponent"),x=()=>o.createElement(a,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/-activity-context/index.html"},"@ActivityContext"),A=()=>o.createElement(a,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/-activity-context/index.html"},"@ActivityCoroutineScope"),E=()=>o.createElement(a,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/lazy-view-models.html"},"ComponentActivity.lazyViewModels()"),M=()=>o.createElement(a,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/inject-view-models.html"},"ComponentActivity.injectViewModels()"),S=()=>o.createElement(a,{href:"/android/koject-android-fragment/com.moriatsushi.koject/inject.html"},"Fragment.inject()"),K=()=>o.createElement(a,{href:"/android/koject-android-fragment/com.moriatsushi.koject/lazy-inject.html"},"Fragment.lazyInject()"),P=()=>o.createElement(a,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/-fragment-component/index.html"},"@FragmentComponent"),L=()=>o.createElement(a,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/-fragment-coroutine-scope/index.html"},"@FragmentCoroutineScope"),V=()=>o.createElement(a,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/-fragment-view-coroutine-scope/index.html"},"@FragmentViewCoroutineScope"),D=()=>o.createElement(a,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/lazy-view-models.html"},"Fragment.lazyViewModels()"),H=()=>o.createElement(a,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/inject-view-models.html"},"Fragment.injectViewModels()"),O=()=>o.createElement(a,{href:"/compose/koject-compose-core/com.moriatsushi.koject.compose/remember-inject.html"},"rememberInject()"),R=()=>o.createElement(a,{href:"/compose/koject-compose-viewmodel/com.moriatsushi.koject.compose.viewmodel/inject-view-model.html"},"injectViewModel()"),Z=()=>o.createElement(a,{href:"/compose/koject-compose-core/com.moriatsushi.koject.compose/-compose-component/index.html"},"@ComposeComponent"),F=()=>o.createElement(a,{href:"/compose/koject-compose-core/com.moriatsushi.koject.compose/-compose-coroutine-scope/index.html"},"@ComposeCoroutineScope"),J=()=>o.createElement(a,{href:"/compose/koject-compose-core/com.moriatsushi.koject.compose/-compose-context/index.html"},"@ComposeContext")},7860:(e,t,n)=>{n.r(t),n.d(t,{assets:()=>p,contentTitle:()=>c,default:()=>h,frontMatter:()=>s,metadata:()=>d,toc:()=>m});var o=n(7462),a=(n(7294),n(3905)),i=n(4866),r=n(5162),l=n(1054);const s={slug:"migrating-from-hilt",title:"Migrating from Hilt to Koject",authors:"atsushi",image:"/blog/2023-04-03/ogp.png"},c="Migrating from Hilt to Koject",d={permalink:"/koject/blog/migrating-from-hilt",editUrl:"https://github.com/Mori-Atsushi/koject/tree/main/docs/blog/2023-04-03-migrating-from-hilt.mdx",source:"@site/blog/2023-04-03-migrating-from-hilt.mdx",title:"Migrating from Hilt to Koject",description:"Koject is a new dependency injection (DI) container library for Kotlin.",date:"2023-04-03T00:00:00.000Z",formattedDate:"April 3, 2023",tags:[],readingTime:8.225,hasTruncateMarker:!0,authors:[{name:"Mori Atsushi",title:"Koject owner",url:"https://github.com/Mori-Atsushi",imageURL:"https://github.com/mori-atsushi.png",key:"atsushi"}],frontMatter:{slug:"migrating-from-hilt",title:"Migrating from Hilt to Koject",authors:"atsushi",image:"/blog/2023-04-03/ogp.png"},nextItem:{title:"Koject v1.3.0 - Writing Test Code with DI Containers",permalink:"/koject/blog/test-test-test"}},p={authorsImageUrls:[void 0]},m=[{value:"Hilt or Koject",id:"hilt-or-koject",level:2},{value:"Getting Started with Koject",id:"getting-started-with-koject",level:2},{value:"Provide ImageLoader",id:"provide-imageloader",level:2},{value:"Provides Repositories",id:"provides-repositories",level:2},{value:"Provids ViewModel",id:"provids-viewmodel",level:2},{value:"Provides JankStats",id:"provides-jankstats",level:2},{value:"Review all changes",id:"review-all-changes",level:2}],u={toc:m},k="wrapper";function h(e){let{components:t,...s}=e;return(0,a.kt)(k,(0,o.Z)({},u,s,{components:t,mdxType:"MDXLayout"}),(0,a.kt)("p",null,(0,a.kt)("img",{src:n(1988).Z,width:"2400",height:"840"})),(0,a.kt)("p",null,"Koject is a new dependency injection (DI) container library for Kotlin.\nIn this article, I'll discuss how to migrate from ",(0,a.kt)("a",{parentName:"p",href:"https://dagger.dev/hilt/"},"Hilt"),' to Koject using the Android sample app "',(0,a.kt)("a",{parentName:"p",href:"https://github.com/android/nowinandroid"},"Now in Android"),'" as an example.'),(0,a.kt)("p",null,(0,a.kt)("a",{parentName:"p",href:"/blog/jp/migrating-from-hilt"},(0,a.kt)("strong",{parentName:"a"},"\u65e5\u672c\u8a9e\u3067\u8aad\u3080 \u2192"))),(0,a.kt)("h2",{id:"hilt-or-koject"},"Hilt or Koject"),(0,a.kt)("p",null,"Koject is compatible with multiple platforms, and if you want to share code between Android and other platforms such as iOS, you can use Koject to also share your DI container.\nHilt and Dagger do not work with Kotlin/Native or Kotlin/JS."),(0,a.kt)("p",null,"If you're developing for Android only, Hilt is still a valid option.\nHilt is optimized for Android and offers more support for Android components.\nAdditionally, custom scopes are not provided by Koject."),(0,a.kt)("p",null,"On the other hand, Koject is simpler and easier to understand as it has fewer features.\nIt also offers standard DI container functionality that is more than enough for most use cases."),(0,a.kt)("p",null,"Furthermore, Koject works on ",(0,a.kt)("a",{parentName:"p",href:"https://github.com/google/ksp"},"KSP"),", which tends to reduce compilation time compared to Hilt, which works on ",(0,a.kt)("a",{parentName:"p",href:"https://kotlinlang.org/docs/kapt.html"},"kapt"),".\nWhile Dagger is also planning to ",(0,a.kt)("a",{parentName:"p",href:"https://github.com/google/dagger/issues/2349"},"migrate to KSP"),", it will take some time."),(0,a.kt)("p",null,"When comparing Koject and Hilt, consider the pros and cons listed in the table below and choose the one that suits you best."),(0,a.kt)("table",null,(0,a.kt)("thead",{parentName:"table"},(0,a.kt)("tr",{parentName:"thead"},(0,a.kt)("th",{parentName:"tr",align:"left"},"Library"),(0,a.kt)("th",{parentName:"tr",align:"center"},"Koject"),(0,a.kt)("th",{parentName:"tr",align:"center"},"Dagger"))),(0,a.kt)("tbody",{parentName:"table"},(0,a.kt)("tr",{parentName:"tbody"},(0,a.kt)("td",{parentName:"tr",align:"left"},"Multiplatform"),(0,a.kt)("td",{parentName:"tr",align:"center"},"\u25cb"),(0,a.kt)("td",{parentName:"tr",align:"center"},"\u2717")),(0,a.kt)("tr",{parentName:"tbody"},(0,a.kt)("td",{parentName:"tr",align:"left"},"Android Support"),(0,a.kt)("td",{parentName:"tr",align:"center"},"\u25cb"),(0,a.kt)("td",{parentName:"tr",align:"center"},"\u25ce")),(0,a.kt)("tr",{parentName:"tbody"},(0,a.kt)("td",{parentName:"tr",align:"left"},"Custom Scopes"),(0,a.kt)("td",{parentName:"tr",align:"center"},"\u2717"),(0,a.kt)("td",{parentName:"tr",align:"center"},"\u25cb")),(0,a.kt)("tr",{parentName:"tbody"},(0,a.kt)("td",{parentName:"tr",align:"left"},"Code Generation"),(0,a.kt)("td",{parentName:"tr",align:"center"},"\u25cb(KSP)"),(0,a.kt)("td",{parentName:"tr",align:"center"},"\u25b3(kapt)")),(0,a.kt)("tr",{parentName:"tbody"},(0,a.kt)("td",{parentName:"tr",align:"left"},"Multi-module"),(0,a.kt)("td",{parentName:"tr",align:"center"},"\u25cb"),(0,a.kt)("td",{parentName:"tr",align:"center"},"\u25cb")))),(0,a.kt)("h2",{id:"getting-started-with-koject"},"Getting Started with Koject"),(0,a.kt)("p",null,"Let's try using Koject in Now in Android. Please also check out ",(0,a.kt)("a",{parentName:"p",href:"https://github.com/mori-atsushi/nowinandroid"},"my GitHub repository"),"."),(0,a.kt)("p",null,"First, add the Koject dependency to the ",(0,a.kt)("inlineCode",{parentName:"p"},"build.gradle.kts")," file in the application module as follows:"),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin",metastring:'title="app/build.gradle.kts"',title:'"app/build.gradle.kts"'},'plugins {\n    id("nowinandroid.android.application")\n\n    /* ... */\n\n    id("com.google.devtools.ksp") version "1.8.0-1.0.9"\n}\n\ndependencies {\n    /* ... */\n\n    implementation("com.moriatsushi.koject:koject-android-core:1.3.0")\n    implementation("com.moriatsushi.koject:koject-android-activity:1.3.0")\n    ksp("com.moriatsushi.koject:koject-processor-app:1.3.0")\n}\n\n/* ... */\n')),(0,a.kt)("p",null,"In the library module, use ",(0,a.kt)("inlineCode",{parentName:"p"},"koject-processor-lib")," instead of ",(0,a.kt)("inlineCode",{parentName:"p"},"koject-processor-app"),".\nAlso, don't forget to set the ",(0,a.kt)("inlineCode",{parentName:"p"},"moduleName"),"."),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin",metastring:'title="{lib}/build.gradle.kts"',title:'"{lib}/build.gradle.kts"'},'plugins {\n    id("nowinandroid.android.library")\n\n    /* ... */\n\n    id("com.google.devtools.ksp") version "1.8.0-1.0.9"\n}\n\ndependencies {\n    /* ... */\n\n    implementation("com.moriatsushi.koject:koject-core:1.3.0")\n    ksp("com.moriatsushi.koject:koject-processor-lib:1.3.0")\n}\n\nksp {\n    arg("moduleName", project.name)\n}\n\n/* ... */\n')),(0,a.kt)("p",null,"Now in Android has shared setup code in the ",(0,a.kt)("inlineCode",{parentName:"p"},"build-logic")," directory.\nWe can also group the Koject setup with plugins as follows:"),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin",metastring:'title="build-logic/conversation/src/main/kotlin/AndroidLibraryKojectConventionPlugin.kt"',title:'"build-logic/conversation/src/main/kotlin/AndroidLibraryKojectConventionPlugin.kt"'},'import com.google.devtools.ksp.gradle.KspExtension\nimport org.gradle.api.Plugin\nimport org.gradle.api.Project\nimport org.gradle.api.artifacts.VersionCatalogsExtension\nimport org.gradle.kotlin.dsl.configure\nimport org.gradle.kotlin.dsl.dependencies\nimport org.gradle.kotlin.dsl.getByType\n\nclass AndroidLibraryKojectConventionPlugin : Plugin<Project> {\n    override fun apply(target: Project) {\n        with(target) {\n            with(pluginManager) {\n                apply("com.google.devtools.ksp")\n                apply("com.android.library")\n            }\n\n            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")\n            dependencies {\n                "implementation"(libs.findLibrary("koject.core").get())\n                "ksp"(libs.findLibrary("koject.processor.lib").get())\n            }\n\n            extensions.configure<KspExtension> {\n                arg("moduleName", name)\n                allowSourcesFromOtherPlugins = true\n            }\n        }\n    }\n}\n')),(0,a.kt)("p",null,"Next, modify the application class.\nRemove the ",(0,a.kt)("inlineCode",{parentName:"p"},"@HiltAndroidApp")," annotation and call ",(0,a.kt)(l._P,{mdxType:"KojectStart"})," in ",(0,a.kt)("inlineCode",{parentName:"p"},"onCreate"),"."),(0,a.kt)(i.Z,{mdxType:"Tabs"},(0,a.kt)(r.Z,{value:"hilt",label:"Hilt",default:!0,mdxType:"TabItem"},(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},"@HiltAndroidApp\nclass NiaApplication : Application(), ImageLoaderFactory {\n    /* ... */\n\n    override fun onCreate() {\n        super.onCreate()\n        /* ... */\n    }\n\n    /* ... */\n}\n"))),(0,a.kt)(r.Z,{value:"koject",label:"Koject",mdxType:"TabItem"},(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},"class NiaApplication : Application(), ImageLoaderFactory {\n    /* ... */\n\n    override fun onCreate() {\n        super.onCreate()\n\n        Koject.start {\n            application(this@NiaApplication)\n        }\n        /* ... */\n    }\n\n    /* ... */\n}\n")))),(0,a.kt)("h2",{id:"provide-imageloader"},"Provide ImageLoader"),(0,a.kt)("p",null,"Let's change the dependency distribution from Hilt to Koject.\nHere, I will use ",(0,a.kt)("inlineCode",{parentName:"p"},"ImageLoader")," as an example."),(0,a.kt)("p",null,"In Now in Android, ",(0,a.kt)("inlineCode",{parentName:"p"},"ImageLoader")," of ",(0,a.kt)("a",{parentName:"p",href:"https://github.com/coil-kt/coil"},"Coil")," was provided from ",(0,a.kt)("inlineCode",{parentName:"p"},"NetworkModule"),".\nCreating ",(0,a.kt)("inlineCode",{parentName:"p"},"ImageLoader")," requires ",(0,a.kt)("inlineCode",{parentName:"p"},"Call.Factory")," of ",(0,a.kt)("a",{parentName:"p",href:"https://square.github.io/okhttp/"},"OkHttp"),", which was also provided by Hilt.\nBoth are singletons to use the same instances each time."),(0,a.kt)("p",null,"Now, let's migrate to Koject.\nWe will remove ",(0,a.kt)("inlineCode",{parentName:"p"},"@Module")," and ",(0,a.kt)("inlineCode",{parentName:"p"},"@InstallIn"),".\nThere is no need to specify a component in Koject.\nAlthough the name is the same, we need to change ",(0,a.kt)("inlineCode",{parentName:"p"},"dagger.Provides")," to ",(0,a.kt)("inlineCode",{parentName:"p"},"com.moriatsushi.koject.Provides"),", and ",(0,a.kt)("inlineCode",{parentName:"p"},"javax.inject.Singleton")," to ",(0,a.kt)("inlineCode",{parentName:"p"},"com.moriatsushi.koject.Singleton"),".\nAlso, since Koject uses ",(0,a.kt)("inlineCode",{parentName:"p"},"ApplicationContext")," by default, we will remove ",(0,a.kt)("inlineCode",{parentName:"p"},"@ApplicationContext"),"."),(0,a.kt)(i.Z,{mdxType:"Tabs"},(0,a.kt)(r.Z,{value:"hilt",label:"Hilt",default:!0,mdxType:"TabItem"},(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},"@Module\n@InstallIn(SingletonComponent::class)\nobject NetworkModule {\n    /* ... */\n\n    @Provides\n    @Singleton\n    fun okHttpCallFactory(): Call.Factory = OkHttpClient.Builder()\n        .addInterceptor(/* ... */)\n        .build()\n\n    @Provides\n    @Singleton\n    fun imageLoader(\n        okHttpCallFactory: Call.Factory,\n        @ApplicationContext application: Context,\n    ): ImageLoader = ImageLoader.Builder(application)\n        .apply { /* ... */ }\n        .build()\n}\n\n"))),(0,a.kt)(r.Z,{value:"koject",label:"Koject",mdxType:"TabItem"},(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},"object NetworkModule {\n    /* ... */\n\n    @Provides\n    @Singleton\n    fun okHttpCallFactory(): Call.Factory = OkHttpClient.Builder()\n        .addInterceptor(/* ... */)\n        .build()\n\n\n    @Provides\n    @Singleton\n    fun imageLoader(\n        okHttpCallFactory: Call.Factory,\n        application: Context,\n    ): ImageLoader = ImageLoader.Builder(application)\n        .apply { /* ... */ }\n        .build()\n}\n")))),(0,a.kt)("p",null,(0,a.kt)("inlineCode",{parentName:"p"},"ImageLoader")," was used in the application class.\nIn Koject, we can use ",(0,a.kt)(l.tB,{mdxType:"Inject"})," to get ",(0,a.kt)("inlineCode",{parentName:"p"},"ImageLoader"),", so the change will look like this:"),(0,a.kt)(i.Z,{mdxType:"Tabs"},(0,a.kt)(r.Z,{value:"hilt",label:"Hilt",default:!0,mdxType:"TabItem"},(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},"@HiltAndroidApp\nclass NiaApplication : Application(), ImageLoaderFactory {\n    @Inject\n    lateinit var imageLoader: Provider<ImageLoader>\n\n    override fun onCreate() {\n        super.onCreate()\n        /* ... */\n    }\n\n    override fun newImageLoader(): ImageLoader = imageLoader.get()\n}\n"))),(0,a.kt)(r.Z,{value:"koject",label:"Koject",mdxType:"TabItem"},(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},"class NiaApplication : Application(), ImageLoaderFactory {\n    override fun onCreate() {\n        super.onCreate()\n        /* ... */\n    }\n\n    override fun newImageLoader(): ImageLoader = inject()\n}\n")))),(0,a.kt)("p",null,"Hilt and Koject have many similarities, so there won't be many changes in the code that need to be made."),(0,a.kt)("h2",{id:"provides-repositories"},"Provides Repositories"),(0,a.kt)("p",null,"Let's continue to migrate other dependencies."),(0,a.kt)("p",null,"The repositories, which are responsible for persisting data, consist of separate interfaces and implementation classes.\nWhen using Hilt, the implementation class was provided with ",(0,a.kt)("inlineCode",{parentName:"p"},"@Inject")," and it was associated with the interface using ",(0,a.kt)("inlineCode",{parentName:"p"},"@Binds")," in ",(0,a.kt)("inlineCode",{parentName:"p"},"DataModule"),"."),(0,a.kt)("p",null,"In Koject, we provide the classes using the ",(0,a.kt)("inlineCode",{parentName:"p"},"@Provides")," annotation.\nSince the primary constructor is automatically used, we can remove the ",(0,a.kt)("inlineCode",{parentName:"p"},"constructor")," keyword.\n",(0,a.kt)("inlineCode",{parentName:"p"},"DataModule")," is changed from an ",(0,a.kt)("inlineCode",{parentName:"p"},"interface")," to an ",(0,a.kt)("inlineCode",{parentName:"p"},"object")," and described using ",(0,a.kt)("inlineCode",{parentName:"p"},"@Provides"),"."),(0,a.kt)("p",null,"All types used in the constructor must be provided by Koject."),(0,a.kt)(i.Z,{mdxType:"Tabs"},(0,a.kt)(r.Z,{value:"hilt",label:"Hilt",default:!0,mdxType:"TabItem"},(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},"class OfflineFirstNewsRepository @Inject constructor(\n    private val newsResourceDao: NewsResourceDao,\n    private val topicDao: TopicDao,\n    private val network: NiaNetworkDataSource,\n) : NewsRepository {\n    /* ... */\n}\n")),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},"@Module\n@InstallIn(SingletonComponent::class)\ninterface DataModule {\n    @Binds\n    fun bindsNewsResourceRepository(\n        newsRepository: OfflineFirstNewsRepository,\n    ): NewsRepository\n\n    /* ... */\n}\n"))),(0,a.kt)(r.Z,{value:"koject",label:"Koject",mdxType:"TabItem"},(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},"@Provides\nclass OfflineFirstNewsRepository(\n    private val newsResourceDao: NewsResourceDao,\n    private val topicDao: TopicDao,\n    private val network: NiaNetworkDataSource,\n) : NewsRepository {\n    /* ... */\n}\n")),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},"object DataModule {\n    @Provides\n    fun bindsNewsResourceRepository(\n        newsRepository: OfflineFirstNewsRepository,\n    ): NewsRepository = newsRepository\n\n    /* ... */\n}\n")))),(0,a.kt)("p",null,"In Koject, you can easily provide it as a supertype by using the ",(0,a.kt)(l.NR,{mdxType:"Binds"})," annotation as follows, without the need for a ",(0,a.kt)("inlineCode",{parentName:"p"},"Module")," class."),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},"@Provides\n@Binds\nclass OfflineFirstNewsRepository(\n    private val newsResourceDao: NewsResourceDao,\n    private val topicDao: TopicDao,\n    private val network: NiaNetworkDataSource,\n) : NewsRepository {\n    /* ... */\n}\n")),(0,a.kt)("h2",{id:"provids-viewmodel"},"Provids ViewModel"),(0,a.kt)("p",null,"In this section, I will introduce how to migrate ViewModel distribution method.\nIn Koject, it is easy to use AndroidX ViewModel."),(0,a.kt)("p",null,"To migrate ViewModel, you need to remove ",(0,a.kt)("inlineCode",{parentName:"p"},"@Inject")," and ",(0,a.kt)("inlineCode",{parentName:"p"},"@HiltViewModel")," annotations, and add ",(0,a.kt)("inlineCode",{parentName:"p"},"@Provides")," and ",(0,a.kt)("inlineCode",{parentName:"p"},"@ViewModelComponent")," annotations."),(0,a.kt)(i.Z,{mdxType:"Tabs"},(0,a.kt)(r.Z,{value:"hilt",label:"Hilt",default:!0,mdxType:"TabItem"},(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},"@HiltViewModel\nclass TopicViewModel @Inject constructor(\n    savedStateHandle: SavedStateHandle,\n    stringDecoder: StringDecoder,\n    private val userDataRepository: UserDataRepository,\n    topicsRepository: TopicsRepository,\n    getSaveableNewsResources: GetUserNewsResourcesUseCase,\n) : ViewModel() {\n    /* ... */\n}\n"))),(0,a.kt)(r.Z,{value:"koject",label:"Koject",mdxType:"TabItem"},(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},"@ViewModelComponent\n@Provides\nclass TopicViewModel(\n    savedStateHandle: SavedStateHandle,\n    stringDecoder: StringDecoder,\n    private val userDataRepository: UserDataRepository,\n    topicsRepository: TopicsRepository,\n    getSaveableNewsResources: GetUserNewsResourcesUseCase,\n) : ViewModel() {\n    /* ... */\n}\n")))),(0,a.kt)("p",null,"If you want to use ViewModel from a Composable function, replace ",(0,a.kt)("inlineCode",{parentName:"p"},"hiltViewModel()")," with ",(0,a.kt)(l.GU,{mdxType:"InjectViewModel"}),"."),(0,a.kt)(i.Z,{mdxType:"Tabs"},(0,a.kt)(r.Z,{value:"hilt",label:"Hilt",default:!0,mdxType:"TabItem"},(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},"@Composable\ninternal fun TopicRoute(\n    onBackClick: () -> Unit,\n    onTopicClick: (String) -> Unit,\n    modifier: Modifier = Modifier,\n    viewModel: TopicViewModel = hiltViewModel(),\n) {\n    /* ... */\n}\n"))),(0,a.kt)(r.Z,{value:"koject",label:"Koject",mdxType:"TabItem"},(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},"@Composable\ninternal fun TopicRoute(\n    onBackClick: () -> Unit,\n    onTopicClick: (String) -> Unit,\n    modifier: Modifier = Modifier,\n    viewModel: TopicViewModel = injectViewModel(),\n) {\n    /* ... */\n}\n")))),(0,a.kt)("p",null,"If you want to use ",(0,a.kt)("inlineCode",{parentName:"p"},"ViewModel")," from an Activity, use ",(0,a.kt)(l.IQ,{mdxType:"ActivityLazyViewModels"}),".\nIf Hilt is not needed in the Activity, you can also remove ",(0,a.kt)("inlineCode",{parentName:"p"},"@AndroidEntryPoint"),"."),(0,a.kt)(i.Z,{mdxType:"Tabs"},(0,a.kt)(r.Z,{value:"hilt",label:"Hilt",default:!0,mdxType:"TabItem"},(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},"@AndroidEntryPoint\nclass MainActivity : ComponentActivity() {\n    /* ... */\n\n    val viewModel: MainActivityViewModel by viewModels()\n\n    /* ... */\n}\n"))),(0,a.kt)(r.Z,{value:"koject",label:"Koject",mdxType:"TabItem"},(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},"class MainActivity : ComponentActivity() {\n    /* ... */\n\n    val viewModel: MainActivityViewModel by lazyViewModels()\n\n    /* ... */\n}\n")))),(0,a.kt)("h2",{id:"provides-jankstats"},"Provides JankStats"),(0,a.kt)("p",null,"Finally, let me introduce a slightly more complex method for providing dependencies."),(0,a.kt)("p",null,(0,a.kt)("a",{parentName:"p",href:"https://developer.android.com/topic/performance/jankstats"},"JankStats")," is an AndroidX library for measuring app performance.\nCreating an instance requires access to the ",(0,a.kt)("inlineCode",{parentName:"p"},"window")," of an Activity, which was achieved in Hilt by using the ",(0,a.kt)("inlineCode",{parentName:"p"},"ActivityComponent"),".\nBy using this component, you can access the Activity instance."),(0,a.kt)("p",null,"Koject also provides ",(0,a.kt)(l.yc,{mdxType:"ActivityComponent"}),", but unlike Hilt, you need to annotate each individual function rather than using a module."),(0,a.kt)(i.Z,{mdxType:"Tabs"},(0,a.kt)(r.Z,{value:"hilt",label:"Hilt",default:!0,mdxType:"TabItem"},(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},"@Module\n@InstallIn(ActivityComponent::class)\nobject JankStatsModule {\n    @Provides\n    fun providesOnFrameListener(): JankStats.OnFrameListener {\n        return JankStats.OnFrameListener { frameData ->\n            /* ... */\n        }\n    }\n\n    @Provides\n    fun providesWindow(activity: Activity): Window {\n        return activity.window\n    }\n\n    @Provides\n    fun providesJankStats(\n        window: Window,\n        frameListener: JankStats.OnFrameListener,\n    ): JankStats {\n        return JankStats.createAndTrack(window, frameListener)\n    }\n}\n"))),(0,a.kt)(r.Z,{value:"koject",label:"Koject",mdxType:"TabItem"},(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},"object JankStatsModule {\n    @ActivityComponent\n    @Provides\n    fun providesOnFrameListener(): JankStats.OnFrameListener {\n        return JankStats.OnFrameListener { frameData ->\n            /* ... */\n        }\n    }\n\n    @ActivityComponent\n    @Provides\n    fun providesWindow(activity: Activity): Window {\n        return activity.window\n    }\n\n    @ActivityComponent\n    @Provides\n    fun providesJankStats(\n        window: Window,\n        frameListener: JankStats.OnFrameListener,\n    ): JankStats {\n        return JankStats.createAndTrack(window, frameListener)\n    }\n}\n")))),(0,a.kt)("p",null,"To use it in an Activity, use ",(0,a.kt)(l.TD,{mdxType:"ActivityLazyInject"}),"."),(0,a.kt)(i.Z,{mdxType:"Tabs"},(0,a.kt)(r.Z,{value:"hilt",label:"Hilt",default:!0,mdxType:"TabItem"},(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},"@AndroidEntryPoint\nclass MainActivity : ComponentActivity() {\n    @Inject\n    lateinit var lazyStats: dagger.Lazy<JankStats>\n\n    /* ... */\n}\n"))),(0,a.kt)(r.Z,{value:"koject",label:"Koject",mdxType:"TabItem"},(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-kotlin"},"class MainActivity : ComponentActivity() {\n    private val lazyStats: JankStats by lazyInject()\n\n    /* ... */\n}\n")))),(0,a.kt)("h2",{id:"review-all-changes"},"Review all changes"),(0,a.kt)("p",null,"Koject has many similarities to Hilt, so its usage should be easy to understand.\nHowever, migrating all dependencies to Koject can be a bit of a hassle.\nKoject, like Hilt, can check for missing dependencies at compile time, but I recommend thoroughly testing the migration."),(0,a.kt)("p",null,"You can see all the changes made in Now in Android in ",(0,a.kt)("a",{parentName:"p",href:"https://github.com/mori-atsushi/nowinandroid/pull/1"},"Migrate from hilt to koject #1"),".\nYou can also learn more about settings and usage in testing.\nIf you feel like trying Koject, feel free to use it as a reference."),(0,a.kt)("p",null,"If you encounter any issues while migrating from Hilt, please send me feedback via ",(0,a.kt)("a",{parentName:"p",href:"https://github.com/mori-atsushi/koject/issues"},"Issue"),".\nI will support you as much as possible."))}h.isMDXComponent=!0},1988:(e,t,n)=>{n.d(t,{Z:()=>o});const o=n.p+"assets/images/banner-6f2a098b3a3b8bf6c03fab0e74ff0cbb.png"}}]);