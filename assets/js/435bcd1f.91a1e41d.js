"use strict";(self.webpackChunkdocs=self.webpackChunkdocs||[]).push([[358],{3905:(e,t,o)=>{o.d(t,{Zo:()=>l,kt:()=>u});var n=o(7294);function r(e,t,o){return t in e?Object.defineProperty(e,t,{value:o,enumerable:!0,configurable:!0,writable:!0}):e[t]=o,e}function i(e,t){var o=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),o.push.apply(o,n)}return o}function a(e){for(var t=1;t<arguments.length;t++){var o=null!=arguments[t]?arguments[t]:{};t%2?i(Object(o),!0).forEach((function(t){r(e,t,o[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(o)):i(Object(o)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(o,t))}))}return e}function s(e,t){if(null==e)return{};var o,n,r=function(e,t){if(null==e)return{};var o,n,r={},i=Object.keys(e);for(n=0;n<i.length;n++)o=i[n],t.indexOf(o)>=0||(r[o]=e[o]);return r}(e,t);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);for(n=0;n<i.length;n++)o=i[n],t.indexOf(o)>=0||Object.prototype.propertyIsEnumerable.call(e,o)&&(r[o]=e[o])}return r}var c=n.createContext({}),m=function(e){var t=n.useContext(c),o=t;return e&&(o="function"==typeof e?e(t):a(a({},t),e)),o},l=function(e){var t=m(e.components);return n.createElement(c.Provider,{value:t},e.children)},p="mdxType",d={inlineCode:"code",wrapper:function(e){var t=e.children;return n.createElement(n.Fragment,{},t)}},k=n.forwardRef((function(e,t){var o=e.components,r=e.mdxType,i=e.originalType,c=e.parentName,l=s(e,["components","mdxType","originalType","parentName"]),p=m(o),k=r,u=p["".concat(c,".").concat(k)]||p[k]||d[k]||i;return o?n.createElement(u,a(a({ref:t},l),{},{components:o})):n.createElement(u,a({ref:t},l))}));function u(e,t){var o=arguments,r=t&&t.mdxType;if("string"==typeof e||r){var i=o.length,a=new Array(i);a[0]=k;var s={};for(var c in t)hasOwnProperty.call(t,c)&&(s[c]=t[c]);s.originalType=e,s[p]="string"==typeof e?e:r,a[1]=s;for(var m=2;m<i;m++)a[m]=o[m];return n.createElement.apply(null,a)}return n.createElement.apply(null,o)}k.displayName="MDXCreateElement"},1054:(e,t,o)=>{o.d(t,{$h:()=>y,BQ:()=>A,E:()=>x,GU:()=>z,Hc:()=>i,IQ:()=>R,Kj:()=>V,N8:()=>q,NR:()=>m,O$:()=>F,Or:()=>S,Pc:()=>u,Q4:()=>D,TD:()=>T,UK:()=>K,Uf:()=>v,Vi:()=>B,Vn:()=>g,Zf:()=>c,_P:()=>l,dK:()=>I,eG:()=>a,eh:()=>O,f3:()=>f,kJ:()=>C,m:()=>k,oX:()=>N,pn:()=>P,pt:()=>j,q:()=>s,q0:()=>M,qK:()=>p,ql:()=>h,s7:()=>U,tB:()=>d,tQ:()=>E,wn:()=>b,yc:()=>w});var n=o(7294);const r=e=>{let{children:t,href:o}=e;return n.createElement("a",{href:`/koject/api${o}`,target:"_blank"},n.createElement("code",null,t))},i=()=>n.createElement(r,{href:"/koject-core/com.moriatsushi.koject/-provides/index.html"},"@Provides"),a=()=>n.createElement(r,{href:"/koject-core/com.moriatsushi.koject/-singleton/index.html"},"@Singleton"),s=()=>n.createElement(r,{href:"/koject-core/com.moriatsushi.koject/-qualifier/index.html"},"@Qualifier"),c=()=>n.createElement(r,{href:"/koject-core/com.moriatsushi.koject/-named/index.html"},"@Named"),m=()=>n.createElement(r,{href:"/koject-core/com.moriatsushi.koject/-binds/index.html"},"@Binds"),l=()=>n.createElement(r,{href:"/koject-core/com.moriatsushi.koject/start.html"},"Koject.start()"),p=()=>n.createElement(r,{href:"/koject-core/com.moriatsushi.koject/-koject/stop.html"},"Koject.stop()"),d=()=>n.createElement(r,{href:"/koject-core/com.moriatsushi.koject/inject.html"},"inject()"),k=()=>n.createElement(r,{href:"/koject-core/com.moriatsushi.koject/lazy-inject.html"},"lazyInject()"),u=()=>n.createElement(r,{href:"/koject-core/com.moriatsushi.koject.extras/-koject-extras/index.html"},"KojectExtras"),h=()=>n.createElement(r,{href:"/koject-core/com.moriatsushi.koject.extras/-koject-extras-message/index.html"},"@KojectExtrasMessage"),j=()=>n.createElement(r,{href:"/koject-core/com.moriatsushi.koject.component/-component-extras/index.html"},"ComponentExtras"),f=()=>n.createElement(r,{href:"/koject-core/com.moriatsushi.koject.error/-missing-extras-exception/index.html"},"MissingExtrasException"),y=()=>n.createElement(r,{href:"/koject-test/com.moriatsushi.koject.test/run-test.html"},"Koject.runTest()"),g=()=>n.createElement(r,{href:"/koject-test/com.moriatsushi.koject.test/start-test.html"},"Koject.startTest()"),v=()=>n.createElement(r,{href:"/koject-test/com.moriatsushi.koject.test/-test-provides/index.html"},"@TestProvides"),x=()=>n.createElement(r,{href:"/android/koject-android-core/com.moriatsushi.koject.android/application.html"},"application()"),E=()=>n.createElement(r,{href:"/android/koject-android-viewmodel/com.moriatsushi.koject.android.viewmodel/-view-model-component/index.html"},"@ViewModelComponent"),S=()=>n.createElement(r,{href:"/android/koject-android-viewmodel/com.moriatsushi.koject.android.viewmodel/-view-model-coroutine-scope/index.html"},"@ViewModelCoroutineScope"),b=()=>n.createElement(r,{href:"/android/koject-android-activity/com.moriatsushi.koject/inject.html"},"ComponentActivity.inject()"),T=()=>n.createElement(r,{href:"/android/koject-android-activity/com.moriatsushi.koject/lazy-inject.html"},"ComponentActivity.lazyInject()"),w=()=>n.createElement(r,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/-activity-component/index.html"},"@ActivityComponent"),C=()=>n.createElement(r,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/-activity-context/index.html"},"@ActivityContext"),N=()=>n.createElement(r,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/-activity-context/index.html"},"@ActivityCoroutineScope"),R=()=>n.createElement(r,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/lazy-view-models.html"},"ComponentActivity.lazyViewModels()"),O=()=>n.createElement(r,{href:"/android/koject-android-activity/com.moriatsushi.koject.android.activity/inject-view-models.html"},"ComponentActivity.injectViewModels()"),F=()=>n.createElement(r,{href:"/android/koject-android-fragment/com.moriatsushi.koject/inject.html"},"Fragment.inject()"),P=()=>n.createElement(r,{href:"/android/koject-android-fragment/com.moriatsushi.koject/lazy-inject.html"},"Fragment.lazyInject()"),K=()=>n.createElement(r,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/-fragment-component/index.html"},"@FragmentComponent"),I=()=>n.createElement(r,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/-fragment-coroutine-scope/index.html"},"@FragmentCoroutineScope"),A=()=>n.createElement(r,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/-fragment-view-coroutine-scope/index.html"},"@FragmentViewCoroutineScope"),M=()=>n.createElement(r,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/lazy-view-models.html"},"Fragment.lazyViewModels()"),D=()=>n.createElement(r,{href:"/android/koject-android-fragment/com.moriatsushi.koject.android.fragment/inject-view-models.html"},"Fragment.injectViewModels()"),V=()=>n.createElement(r,{href:"/compose/koject-compose-core/com.moriatsushi.koject.compose/remember-inject.html"},"rememberInject()"),z=()=>n.createElement(r,{href:"/compose/koject-compose-viewmodel/com.moriatsushi.koject.compose.viewmodel/inject-view-model.html"},"injectViewModel()"),B=()=>n.createElement(r,{href:"/compose/koject-compose-core/com.moriatsushi.koject.compose/-compose-component/index.html"},"@ComposeComponent"),U=()=>n.createElement(r,{href:"/compose/koject-compose-core/com.moriatsushi.koject.compose/-compose-coroutine-scope/index.html"},"@ComposeCoroutineScope"),q=()=>n.createElement(r,{href:"/compose/koject-compose-core/com.moriatsushi.koject.compose/-compose-context/index.html"},"@ComposeContext")},5898:(e,t,o)=>{o.r(t),o.d(t,{assets:()=>m,contentTitle:()=>s,default:()=>k,frontMatter:()=>a,metadata:()=>c,toc:()=>l});var n=o(7462),r=(o(7294),o(3905)),i=o(1054);const a={},s="Test",c={unversionedId:"test",id:"test",title:"Test",description:"When writing test code, it is recommended to use actual dependencies as much as possible rather than mocks.",source:"@site/docs/test.mdx",sourceDirName:".",slug:"/test",permalink:"/koject/docs/test",draft:!1,editUrl:"https://github.com/Mori-Atsushi/koject/tree/main/docs/docs/test.mdx",tags:[],version:"current",frontMatter:{},sidebar:"docs",previous:{title:"Component (Experimental)",permalink:"/koject/docs/core/component"},next:{title:"Android",permalink:"/koject/docs/android/"}},m={},l=[{value:"Setup for Testing",id:"setup-for-testing",level:2},{value:"Multiplatform",id:"multiplatform",level:3},{value:"Single platform",id:"single-platform",level:3},{value:"Running tests",id:"running-tests",level:2},{value:"Swapping dependencies",id:"swapping-dependencies",level:2}],p={toc:l},d="wrapper";function k(e){let{components:t,...o}=e;return(0,r.kt)(d,(0,n.Z)({},p,o,{components:t,mdxType:"MDXLayout"}),(0,r.kt)("h1",{id:"test"},"Test"),(0,r.kt)("p",null,"When writing test code, it is recommended to use actual dependencies as much as possible rather than mocks.\nBy using Koject, you can easily replace only some dependencies and test them."),(0,r.kt)("h2",{id:"setup-for-testing"},"Setup for Testing"),(0,r.kt)("p",null,"To use Koject in tests, add ",(0,r.kt)("inlineCode",{parentName:"p"},"koject-test")," to the test dependencies and register ",(0,r.kt)("inlineCode",{parentName:"p"},"koject-processor-app")," to the test source set."),(0,r.kt)("h3",{id:"multiplatform"},"Multiplatform"),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-diff",metastring:'title="build.gradle.kts"',title:'"build.gradle.kts"'},'kotlin {\n    /* ... */\n\n    sourceSets {\n        val commonMain by getting {\n            dependencies {\n                implementation("com.moriatsushi.koject:koject-core:1.3.0-beta01")\n            }\n        }\n\n        val commonTest by getting {\n            dependencies {\n                implementation(kotlin("test"))\n+                implementation("com.moriatsushi.koject:koject-test:1.3.0-beta01")\n            }\n        }\n    }\n}\n\n\ndependencies {\n    // Add it according to your targets.\n    val processor = "com.moriatsushi.koject:koject-processor-app:1.3.0-beta01"\n    add("kspAndroid", processor)\n    add("kspJvm", processor)\n    add("kspJs", processor)\n    add("kspIosX64", processor)\n    add("kspIosArm64", processor)\n\n+     add("kspAndroidTest", processor)\n+     add("kspJvmTest", processor)\n+     add("kspJsTest", processor)\n+     add("kspIosX64Test", processor)\n+     add("kspIosArm64Test", processor)\n}\n')),(0,r.kt)("h3",{id:"single-platform"},"Single platform"),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-diff",metastring:'title="build.gradle.kts"',title:'"build.gradle.kts"'},'dependencies {\n    implementation("com.moriatsushi.koject:koject-core:1.3.0-beta01")\n\n    testImplementation(kotlin("test"))\n+     testImplementation("com.moriatsushi.koject:koject-test:1.3.0-beta01")\n+     kspTest("com.moriatsushi.koject:koject-processor-app:1.3.0-beta01")\n}\n')),(0,r.kt)("h2",{id:"running-tests"},"Running tests"),(0,r.kt)("p",null,"Using ",(0,r.kt)(i.$h,{mdxType:"KojectRunTest"}),", you can start the test DI container and use the provided dependencies."),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-kotlin"},"@Provides\nclass Controller\n")),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-kotlin"},"class SampleTest() {\n    @Test\n    fun test() = Koject.runTest {\n        val controller = inject<Controller>() // can be injected\n    }\n}\n")),(0,r.kt)("p",null,"You can also start a test DI container by executing ",(0,r.kt)(i.Vn,{mdxType:"KojectStartTest"})," in ",(0,r.kt)("inlineCode",{parentName:"p"},"@Before")," and ",(0,r.kt)(i.qK,{mdxType:"KojectStop"})," in ",(0,r.kt)("inlineCode",{parentName:"p"},"@After"),"."),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-kotlin"},"class SampleTest() {\n    @Before\n    fun start() {\n        Koject.startTest()\n    }\n\n    @After\n    fun stop() {\n        Koject.stop()\n    }\n\n    @Test\n    fun test() {\n        val controller = inject<Controller>()\n    }\n}\n")),(0,r.kt)("h2",{id:"swapping-dependencies"},"Swapping dependencies"),(0,r.kt)("p",null,"Using ",(0,r.kt)(i.Uf,{mdxType:"TestProvides"}),", you can replace dependencies.\nIn the following example, ",(0,r.kt)("inlineCode",{parentName:"p"},"SampleRepositoryImpl")," is used in production, and ",(0,r.kt)("inlineCode",{parentName:"p"},"FakeSampleRepository")," is used in testing."),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-kotlin",metastring:'title="src/main/kotlin/SampleRepository.kt"',title:'"src/main/kotlin/SampleRepository.kt"'},"@Provides\ninterface SampleRepository\n\n@Provide\n@Binds\nclass SampleRepositoryImpl: SampleRepository\n")),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-kotlin",metastring:'title="src/main/kotlin/main.kt"',title:'"src/main/kotlin/main.kt"'},"fun main() {\n    Koject.start()\n\n    val repository = inject<SampleRepository>() // is SampleRepositoryImpl\n}\n")),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-kotlin",metastring:'title="src/test/kotlin/FakeSampleRepository.kt"',title:'"src/test/kotlin/FakeSampleRepository.kt"'},"@TestProvides\n@Binds\nclass FakeSampleRepository: SampleRepository\n")),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-kotlin",metastring:'title="src/test/kotlin/FakeSampleRepository.kt"',title:'"src/test/kotlin/FakeSampleRepository.kt"'},"class SampleTest() {\n    @Test\n    fun test() = Koject.runTest {\n        val repository = inject<SampleRepository>() // is FakeSampleRepository\n    }\n}\n")),(0,r.kt)("p",null,"You can provide both ",(0,r.kt)("inlineCode",{parentName:"p"},"FakeSampleRepository")," and ",(0,r.kt)("inlineCode",{parentName:"p"},"SampleRepository")," by using ",(0,r.kt)("inlineCode",{parentName:"p"},"@TestProvides")," as follows."),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-kotlin",metastring:'title="src/test/kotlin/FakeSampleRepository.kt"',title:'"src/test/kotlin/FakeSampleRepository.kt"'},"@TestProvides\nclass FakeSampleRepository: SampleRepository {\n    companion object {\n        @TestProvides\n        fun bindAsSampleRepository(\n            fake: FakeSampleRepository\n        ): SampleRepository {\n            return fake\n        }\n    }\n}\n")),(0,r.kt)("pre",null,(0,r.kt)("code",{parentName:"pre",className:"language-kotlin",metastring:'title="src/test/kotlin/FakeSampleRepository.kt"',title:'"src/test/kotlin/FakeSampleRepository.kt"'},"class SampleTest() {\n    @Test\n    fun test() = Koject.runTest {\n        val fakeRepository = inject<FakeSampleRepository>() // is FakeSampleRepository\n        val repository = inject<SampleRepository>()         // is FakeSampleRepository\n    }\n}\n")))}k.isMDXComponent=!0}}]);