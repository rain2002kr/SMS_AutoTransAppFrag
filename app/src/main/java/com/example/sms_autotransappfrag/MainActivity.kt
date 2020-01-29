package com.example.sms_autotransappfrag

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomexample_yena.*
import com.example.sms_autotransappfrag.Contact_Regist_item.ContactRegistFragment
import com.example.sms_autotransappfrag.Fragment_main.MainFragment
import com.example.sms_autotransappfrag.SmS_Send.SendSmSFragment
import com.example.sms_autotransappfrag.SmS_SentLog.SentLogSmSFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_sent_log_sm.*
import kotlinx.android.synthetic.main.sub_contact_log_view.*
import java.lang.Exception

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
    private lateinit var contactViewModel : ContactViewModel
    private lateinit var contactLogViewModel : ContactLogViewModel
    private var id: Long? = null

    //onNewIntent
    override fun onNewIntent(intent: Intent) {
        try {
            contactLog(intent, "onNewIntent" )
            changeFragment(LOG_FRAG)
        }catch (e: Exception ){
            e.printStackTrace()
        }
        //Log.d(TAG,"onNewIntent")
        super.onNewIntent(intent)
    }

    //start onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkPermissions()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment,MainFragment()).commit()

        //val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        //inflater.inflate(R.layout.sub_contact_log_view,main_log_view,true)

        // Set contactItemClick & contactItemLongClick lambda
        val adapter = ContactLogAdapter({ contactLog ->
            id = contactLog.id

        }, { contactLog ->
            deleteLogDialog(contactLog)
        })

        val lm = LinearLayoutManager(this)
        main_log_view.adapter = adapter
        main_log_view.layoutManager = lm
        main_log_view.setHasFixedSize(true)


        // Set contactItemClick & contactItemLongClick lambda
        val adapter2 = ContactAdapter({ contact ->
            id = contact.id

        }, { contact ->
            deleteDialog(contact)

        })
        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)
        contactViewModel.getAll().observe(this, Observer<List<Contact>>{ contacts ->
            adapter2.setContacts(contacts!!)
        })


        contactLogViewModel = ViewModelProviders.of(this).get(ContactLogViewModel::class.java)
        contactLogViewModel.getAll().observe(this, Observer<List<ContactLog>>{ contactsLog ->
            adapter.setContacts(contactsLog!!)
        })

        contactLog(intent, "onCreateIntent" )

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
        when (frag) {
            MAIN_FRAG -> {
                ft.replace(R.id.fragment, MainFragment()).addToBackStack(null).commit()
            }
            REGIST_FRAG -> {
                ft.replace(R.id.fragment, ContactRegistFragment()).addToBackStack(null).commit()
            }
            SEND_FRAG -> {
                ft.replace(R.id.fragment, SendSmSFragment()).addToBackStack(null).commit()
            }
            LOG_FRAG -> {
                ft.replace(R.id.fragment, SentLogSmSFragment()).addToBackStack(null).commit()
            }

    }

    }

    //Dialog for adpter
    private fun deleteDialog(contact: Contact) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Delete selected contactLog?")
            .setNegativeButton("NO") { _, _ -> }
            .setPositiveButton("YES") { _, _ ->
                contactViewModel.delete(contact)
            }
        builder.show()
    }

    //Dialog for adpter2
    private fun deleteLogDialog(contactLog: ContactLog) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Delete selected contactLog?")
            .setNegativeButton("NO") { _, _ -> }
            .setPositiveButton("YES") { _, _ ->
                contactLogViewModel.delete(contactLog)
            }
        builder.show()
    }

    // get SmS function
    private fun contactLog(getintent : Intent, intentType :String) {

        val receivesender = getintent.getStringExtra(EXTRA_BROD_NUMBER)
        val contents = getintent.getStringExtra(EXTRA_BROD_CONTENTS)
        val receivedDate = getintent.getStringExtra(EXTRA_BROD_RECEIVED_DATE)


        Log.d(TAG,"${intentType}"
                + " sender : ${receivesender}"
                + " contents : ${contents}"
                + " receivedDate : ${receivedDate}"

        )
        val contacts  : List<Contact> ?= contactViewModel.getAll().value
        contacts?.forEach { contact -> Unit
            Log.d( TAG, "inside contacts"    )
            val receiveNumber = contact.receiveNumber.replace("-", "")
            Log.d(
                TAG, "inside contacts number ${receiveNumber}"

            )
            if (receiveNumber == receivesender) {
                val receiveName = contact.receiveName
                val receiveTime = receivedDate
                val receiveNumber = contact.receiveNumber
                val message = contents
                val transNumber = contact.transNumber
                val transName = contact.transName
                val transTime = receivedDate
                val contactlog = ContactLog(
                    id,
                    receiveName,
                    receiveTime,
                    receiveNumber,
                    message,
                    transName,
                    transTime,
                    transNumber
                )


                contactLogViewModel.insert(contactlog)

                Log.d(
                    TAG, "[inside ${intentType}: ] receiveName: ${receiveName}" +
                            " receiveTime : ${receiveTime}" +
                            " receiveNumber : ${receiveNumber}" +
                            " message : ${message}" +
                            " transNumber : ${transNumber}" +
                            " transName : ${transName}" +
                            " transTime : ${transTime}"
                )
            } else {
                Log.d(
                    TAG, "[${intentType}: ] receivesender: ${receivesender}" +
                            " contents : ${contents}" +
                            " receiveNumber : ${receiveNumber}" +
                            " receivedDate : ${receivedDate}"
                )
            }

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
