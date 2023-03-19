@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.lib2

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.component.Component
import com.moriatsushi.koject.component.ComponentExtras
import com.moriatsushi.koject.inject

@Component
annotation class Lib2Component

class Lib2ComponentExtras(
    val extra: Lib2ComponentExtra,
) : ComponentExtras<Lib2Component>

@Lib2Component
@Provides
class Lib2ComponentClass(
    val extra: Lib2ComponentExtra,
)

class Lib2ComponentExtra

fun injectLib2ComponentClass() {
    inject<Lib2ComponentClass>(
        componentExtras = Lib2ComponentExtras(
            Lib2ComponentExtra(),
        ),
    )
}
