package com.example.sms_autotransappfrag

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sms_autotransappfrag.Contact_Regist_item.ContactRegistFragment
import com.example.sms_autotransappfrag.Fragment_main.MainFragment
import com.example.sms_autotransappfrag.SmS_Send.SendSmSFragment
import com.example.sms_autotransappfrag.SmS_SentLog.SentLogSmSFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private val MY_PERMISSIONS_REQUEST_RECEIVE_SMS :Int  = 1
    private val MY_PERMISSIONS_REQUEST_SEND_SMS :Int  = 2
    private val multiplePermissionsCode = 100
    private val requiredPermissions = arrayOf(
        Manifest.permission.SEND_SMS,
        Manifest.permission.RECEIVE_SMS
        //Manifest.permission.READ_PHONE_STATE,
        //Manifest.permission.ACCESS_COARSE_LOCATION,
    )



    //start onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkPermissions()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment,MainFragment()).commit()


    }//Out of onCreate//

    ////권한 요청 함수
    private fun checkPermissions() {
        //거절되었거나 아직 수락하지 않은 권한(퍼미션)을 저장할 문자열 배열 리스트
        var rejectedPermissionList = ArrayList<String>()

        //필요한 퍼미션들을 하나씩 끄집어내서 현재 권한을 받았는지 체크
        for(permission in requiredPermissions){
            if(ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                //만약 권한이 없다면 rejectedPermissionList에 추가
                rejectedPermissionList.add(permission)
            }
        }
        //거절된 퍼미션이 있다면...
        if(rejectedPermissionList.isNotEmpty()){
            //권한 요청!
            val array = arrayOfNulls<String>(rejectedPermissionList.size)
            ActivityCompat.requestPermissions(this, rejectedPermissionList.toArray(array), multiplePermissionsCode)
        }
    }
    ////권한 요청 결과 함수
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            multiplePermissionsCode -> {
                if(grantResults.isNotEmpty()) {
                    for((i, permission) in permissions.withIndex()) {
                        if(grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            //권한 획득 실패
                            Log.i("TAG", "The user has denied to $permission")
                            Log.i("TAG", "I can't work for you anymore then. ByeBye!")
                        }
                    }
                }
            }
        }
    }

    //프래그먼트
    fun changeFragment(frag : Int){
        val ft = supportFragmentManager.beginTransaction()
        when(frag) {
            MAIN_FRAG -> {ft.replace(R.id.fragment,MainFragment()).addToBackStack(null).commit()}
            REGIST_FRAG -> {ft.replace(R.id.fragment,ContactRegistFragment()).addToBackStack(null).commit()}
            SEND_FRAG -> {ft.replace(R.id.fragment,SendSmSFragment()).addToBackStack(null).commit()}
            LOG_FRAG -> {ft.replace(R.id.fragment,SentLogSmSFragment()).addToBackStack(null).commit()}

        }

    }
    // object
    companion object {
        const val EXTRA_BROD_NUMBER = "EXTRA_BROD_NUMBER"
        const val EXTRA_BROD_CONTENTS = "EXTRA_BROD_CONTENTS"
        const val EXTRA_BROD_RECEIVED_DATE = "EXTRA_BROD_RECEIVED_DATE"
        const val MAIN_FRAG = 0
        const val REGIST_FRAG = 1
        const val SEND_FRAG = 2
        const val LOG_FRAG = 3


    }



}//END OF LINE
