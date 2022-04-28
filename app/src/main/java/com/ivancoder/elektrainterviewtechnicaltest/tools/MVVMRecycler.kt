package com.ivancoder.elektrainterviewtechnicaltest.tools

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class MVVMRecycler<T>(val toolViewModel: ToolViewModel, val mvvmList: MVVMList<T>) : RecyclerView.Adapter<MVVMRecycler<T>.MVVMViewHolder>(){


    private  var dataSource: List<T>? = null



    /**
     *
     */
    fun setList(newlist: List<T>?){
        this.dataSource = newlist
        notifyDataSetChanged()
    }




    /**
     *
     */
    override fun getItemViewType(position: Int) = mvvmList.getLayoutIdForPosition(position)


    /**
     *
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MVVMViewHolder {
        val layoutInflater : LayoutInflater = LayoutInflater.from(parent.context)
        val viewDataBinding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false)
        return MVVMViewHolder(viewDataBinding)
    }




    /**
     *
     */
    override fun getItemCount(): Int {
        return dataSource?.size ?: 0
    }




    /**
     *
     */
    override fun onBindViewHolder(holder: MVVMViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class MVVMViewHolder (binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root){

        private var binding: ViewDataBinding? = null

        init{
            this.binding = binding
        }


        /**
         *
         */
        fun bind(position:Int){
            binding?.setVariable(mvvmList.getBindingVariable(),toolViewModel)
            binding?.setVariable(mvvmList.getPositionVariable(),position)
            binding?.executePendingBindings()
        }
    }

}