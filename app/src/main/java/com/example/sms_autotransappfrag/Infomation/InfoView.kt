package com.example.sms_autotransappfrag.Infomation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sms_autotransappfrag.MainViewHolder
import kotlinx.android.synthetic.main.sub_infomation_log_view.view.*


class InfoViewModel(val date:String, val update:String, val detail:String)

class InfoViewAdapter (val context: Context, val intId:Int, val list:List<InfoViewModel>): RecyclerView.Adapter<MainViewHolder>(){
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


    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        holder.containerView.txtDate.text = list[position].date
        holder.containerView.txtUpdate.text = list[position].update
        holder.containerView.txtDetail.text = list[position].detail

    }
}
