package com.moriatsushi.koject.integrationtest.app.compose.viewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.moriatsushi.koject.compose.viewmodel.injectViewModel
import com.moriatsushi.koject.integrationtest.app.viewmodel.SampleViewModel

class RecreationTestActivity : ComponentActivity() {
    lateinit var viewModel: SampleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            viewModel = injectViewModel()
        }
    }
}
