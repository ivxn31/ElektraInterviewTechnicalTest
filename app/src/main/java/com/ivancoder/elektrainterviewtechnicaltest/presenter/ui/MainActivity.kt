package com.ivancoder.elektrainterviewtechnicaltest.presenter.ui

import android.view.LayoutInflater
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.ivancoder.elektrainterviewtechnicaltest.tools.VBActivity
import dagger.hilt.android.AndroidEntryPoint
import com.ivancoder.elektrainterviewtechnicaltest.databinding.MainActivityBinding


@AndroidEntryPoint
class MainActivity : VBActivity<MainActivityBinding>() {

    var navController:NavController? = null

    override val bindingInflater: (LayoutInflater) -> MainActivityBinding
        get() = MainActivityBinding::inflate

    override fun setup() {
        navController = findNavController(binding.fgtContentMain.id)
        setupActionBarWithNavController(navController!!)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home -> {
                navController?.popBackStack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}