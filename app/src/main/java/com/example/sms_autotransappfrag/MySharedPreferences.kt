package com.example.sms_autotransappfrag

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.roomexample_yena.Contact

class tContact(val name:String,val sms:String,val time:String)

class MySharedPreferences(context: Context) {

    val PREFS_FILENAME = "prefs"
    val PREF_KEY_MY_EDITTEXT = "myEditText"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)
    private var  list = mutableSetOf<String>()
    private var  sender = mutableSetOf<String>()
    private var  content = mutableSetOf<String>()
    private var  time = mutableSetOf<String>()
    /* 파일 이름과 EditText 를 저장할 Key 값을 만들고 prefs 인스턴스 초기화 */

    fun setSender(key:String , value:String ){
        sender.add(value)
        prefs.edit().putStringSet(key,sender)

    }
    fun setContent(key:String , value:String ){
        content.add(value)
        prefs.edit().putStringSet(key,content)
    }
    fun setTime(key:String , value:String ){
        time.add(value)
        prefs.edit().putStringSet(key,time)
    }

    fun getSender(key:String) :MutableSet<String> {
        prefs.getStringSet(key, sender)
        return sender
    }



    fun setV(key:String, value:String?){
        prefs.edit().putString(key,value).apply()
     }
    fun getV(key:String):String?{
        return prefs.getString(key,"")
    }

    var myEditText: String?
        get() = prefs.getString(PREF_KEY_MY_EDITTEXT, "")
        set(value) = prefs.edit().putString(PREF_KEY_MY_EDITTEXT, value).apply()
    /* get/set 함수 임의 설정. get 실행 시 저장된 값을 반환하며 default 값은 ""
     * set(value) 실행 시 value로 값을 대체한 후 저장 */

}


class App : Application() {

    companion object {
        lateinit var prefs : MySharedPreferences
    }
    /* prefs라는 이름의 MySharedPreferences 하나만 생성할 수 있도록 설정. */

    override fun onCreate() {
        prefs = MySharedPreferences(applicationContext)
        super.onCreate()
    }
}