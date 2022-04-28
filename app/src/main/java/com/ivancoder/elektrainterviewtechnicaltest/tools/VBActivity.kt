package com.ivancoder.elektrainterviewtechnicaltest.tools

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class VBActivity<VB : ViewBinding>: AppCompatActivity() {

    private var vb: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater) -> VB

    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = vb as VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = bindingInflater.invoke(layoutInflater)
        setContentView(requireNotNull(vb).root)
    }

    override fun onResume() {
        super.onResume()
        setup()
    }

    override fun onDestroy() {
        super.onDestroy()
        vb = null
    }

    abstract fun setup()
}