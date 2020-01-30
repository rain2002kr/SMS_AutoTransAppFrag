package com.example.sms_autotransappfrag

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
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

/* MySharedPreferences.kt */

class MySharedPreferences(context: Context) {

    val PREFS_FILENAME = "prefs"
    //val PREF_KEY_MY_EDITTEXT = "myEditText"
    var check :Boolean = false
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)
    /* 파일 이름과 EditText를 저장할 Key 값을 만들고 prefs 인스턴스 초기화 */

    //var myEditText: String
        //get() = prefs.getString(PREF_KEY_MY_EDITTEXT, "")
        //get() = prefs.getBoolean( "check")

        //set(value) = prefs.edit().putString(PREF_KEY_MY_EDITTEXT, value).apply()
    /* get/set 함수 임의 설정. get 실행 시 저장된 값을 반환하며 default 값은 ""
     * set(value) 실행 시 value로 값을 대체한 후 저장 */
}

