package com.ivancoder.elektrainterviewtechnicaltest.tools

interface ItemClick<T>{
    fun onItemClick(model: T,position:Int)
}