package com.ivancoder.elektrainterviewtechnicaltest.tools

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class MVVMFragment<T : ViewDataBinding, V : ToolViewModel>: Fragment() {

    var simpleActivity: AppCompatActivity? = null
    //lateinit var rootView: View
    var binding: T? = null
    private var mViewModel: V? = null

    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * Override for set binding variable
     *
     * @return varible id
     */
    abstract fun getBindingVariable(): Int

    /**
     * Override for set view model
     * @return view model instance
     */
    abstract fun getViewModel(): V


    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        mViewModel = getViewModel()
    }

    abstract fun loadedView()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AppCompatActivity) {
            this.simpleActivity = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return binding?.root!!
    }

    override fun onDetach() {
        simpleActivity = null
        super.onDetach()
    }

    override fun onResume() {
        super.onResume()
        loadedView()
    }

    /**
     *
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        binding?.setVariable(getBindingVariable(), mViewModel)
        binding?.lifecycleOwner = this
        binding?.executePendingBindings()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}