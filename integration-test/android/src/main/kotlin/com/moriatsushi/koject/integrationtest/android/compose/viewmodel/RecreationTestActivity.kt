package com.moriatsushi.koject.integrationtest.android.compose.viewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.moriatsushi.koject.compose.viewmodel.injectViewModel
import com.moriatsushi.koject.integrationtest.android.viewmodel.SampleViewModel

class RecreationTestActivity : ComponentActivity() {
    lateinit var viewModel: SampleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            viewModel = injectViewModel()
        }
    }
}
