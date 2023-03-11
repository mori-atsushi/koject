package com.moriatsushi.koject.integrationtest.android.viewmodel

import androidx.lifecycle.ViewModel
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Qualifier
import com.moriatsushi.koject.android.viewmodel.ViewModelComponent

@Qualifier
annotation class ViewModelQualifier

class QualifierViewModel(val name: String) : ViewModel()

@ViewModelQualifier
@ViewModelComponent
@Provides
fun provideQualifierViewModel(): QualifierViewModel {
    return QualifierViewModel("QualifierViewModel")
}
