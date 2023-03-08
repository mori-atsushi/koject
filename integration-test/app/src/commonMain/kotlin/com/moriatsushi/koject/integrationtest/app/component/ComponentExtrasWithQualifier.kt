@file:OptIn(ExperimentalKojectApi::class)

package com.moriatsushi.koject.integrationtest.app.component

import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Qualifier
import com.moriatsushi.koject.component.Component
import com.moriatsushi.koject.component.ComponentExtras

@Component
@Retention(AnnotationRetention.BINARY)
annotation class WithQualifierComponent

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class WithQualifierID1

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class WithQualifierID2

@ComponentExtras(WithQualifierComponent::class)
class WithQualifierComponentExtras(
    @WithQualifierID1
    val id1: String,
    @WithQualifierID2
    val id2: String,
)

@WithQualifierComponent
@Provides
class WithQualifierComponentClass(
    @WithQualifierID1
    val id1: String,
    @WithQualifierID2
    val id2: String,
)
