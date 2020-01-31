package com.example.sms_autotransappfrag

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.sub_main_view.view.*

class MainViewModel (val imageId : Int, val subject:String, val subject2:String )

class MainViewHolder (override val containerView: View) : RecyclerView.ViewHolder(containerView),LayoutContainer

class MainViewAdapter (val context: Context, val intId:Int, val list:List<MainViewModel>): RecyclerView.Adapter<MainViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(intId,parent,false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    interface ItemClickListener{
        fun onClick(view : View, position:Int)
    }

    private lateinit var itemClickListener : ItemClickListener

    fun setItemClickListener(itemClickListener : ItemClickListener){
        this.itemClickListener=itemClickListener
    }


    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.containerView.imageView.setImageResource(list[position].imageId)
        holder.containerView.textView.text=list[position].subject
        holder.containerView.textView2.text=list[position].subject2

        holder.containerView.setOnClickListener({
            itemClickListener.onClick(it,position)
        })


    }
}

