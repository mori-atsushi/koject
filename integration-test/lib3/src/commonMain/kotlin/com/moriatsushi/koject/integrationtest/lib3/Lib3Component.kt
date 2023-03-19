@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.lib3

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.component.Component
import com.moriatsushi.koject.component.ComponentExtras
import com.moriatsushi.koject.inject

@Component
annotation class Lib3Component

class Lib3ComponentExtras(
    val extra: Lib3ComponentExtra,
) : ComponentExtras<Lib3Component>

@Lib3Component
@Provides
class Lib2ComponentClass(
    val extra: Lib3ComponentExtra,
)

class Lib3ComponentExtra

fun injectLib3ComponentClass() {
    inject<Lib2ComponentClass>(
        componentExtras = Lib3ComponentExtras(
            Lib3ComponentExtra(),
        ),
    )
}
