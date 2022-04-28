package com.ivancoder.elektrainterviewtechnicaltest.tools

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

open class ToolViewModel: ViewModel(), CoroutineScope {


    var loading = ObservableBoolean(false)

    /**
     * This is the job for all coroutines started by this ViewModel.
     * Cancelling this job will cancel all coroutines started by this ViewModel.
     */
    private val job = SupervisorJob()

    override val coroutineContext = Dispatchers.Main + job

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}