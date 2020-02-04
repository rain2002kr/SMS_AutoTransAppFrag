package com.example.sms_autotransappfrag

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.roomexample_yena.Contact
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

data class Dcontact(val name:String,val number:String ,val sms:String="",val time:String="")

class MySharedPreferences(context: Context) {
    var gson = GsonBuilder().create()
    var listType : TypeToken<MutableList<Dcontact>> = object : TypeToken<MutableList<Dcontact>>() {}
    //var setType : TypeToken<MutableSet<Dcontact>> = object : TypeToken<MutableSet<Dcontact>>() {}
    //var mapType : TypeToken<MutableMap<String, Dcontact>> = object : TypeToken<MutableMap<String, Dcontact>>() {}
    private var dcontacts : MutableList<Dcontact> = mutableListOf()


    val PREFS_FILENAME = "prefs"
    val PREF_KEY_MY_EDITTEXT = "myEditText"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)
    /* 파일 이름과 EditText 를 저장할 Key 값을 만들고 prefs 인스턴스 초기화 */

    fun setContact(key:String, name: String, number: String){
        dcontacts.add(Dcontact(name = name, number = number))
        var jsonCon = gson.toJson(dcontacts,listType.type)
        prefs.edit().putString(key,jsonCon).apply()
    }

    fun setContact(key:String, dcontact : Dcontact){
        dcontacts.add(dcontact)
        var jsonCon = gson.toJson(dcontacts,listType.type)
        prefs.edit().putString(key,jsonCon).apply()
    }

    fun setContacts(key:String, dcontacts : MutableList<Dcontact>){
        this.dcontacts = dcontacts
        var jsonCon = gson.toJson(dcontacts,listType.type)
        prefs.edit().putString(key,jsonCon).apply()
    }

    fun getContact(key:String):MutableList<Dcontact>{
        val gsonCon = prefs.getString(key,"")
        dcontacts = gson.fromJson(gsonCon,listType.type)
        return dcontacts

    }

    fun getContact(key:String, number: String):Dcontact {
        val gsonCon = prefs.getString(key, "")
        dcontacts = gson.fromJson(gsonCon, listType.type)
        var dcon : Dcontact = Dcontact("","","","")
        dcontacts.forEach({
            val numberout = it.number.replace("-", "")
            if (numberout == number) {
                dcon =  it
            }
        })
        return dcon
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