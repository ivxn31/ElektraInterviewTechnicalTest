package com.ivancoder.elektrainterviewtechnicaltest.tools

interface MVVMList<T>{
    var itemClick:ItemClick<T>
    fun getLayoutIdForPosition(position: Int): Int
    fun getBindingVariable():Int
    fun getPositionVariable():Int
    fun onItemClick(index: Int)
    fun setupList()
    fun setList(data: ArrayList<T>?)
    fun getItem(position: Int): T?
}