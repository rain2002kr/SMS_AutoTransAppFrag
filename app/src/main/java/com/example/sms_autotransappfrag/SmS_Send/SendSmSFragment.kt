package com.example.sms_autotransappfrag.SmS_Send


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.roomexample_yena.Contact
import com.example.roomexample_yena.ContactViewModel
import com.example.sms_autotransappfrag.App
import com.example.sms_autotransappfrag.Dcontact
import com.example.sms_autotransappfrag.MainActivity

import com.example.sms_autotransappfrag.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_send_sms.*

/**
 * A simple [Fragment] subclass.
 */
class SendSmSFragment : Fragment() {
    val TAG = "SendSmSFragment"
    private lateinit var contactViewModel : ContactViewModel
    private var sender : String? = null
    private var contents : String? = null
    private var receivedDate : String? = null
    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_send_sms, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // [START initialize_database_ref]

        //private lateinit var database: FirebaseDatabase
        //private lateinit var myRef: DatabaseReference
        database = FirebaseDatabase.getInstance()
        myRef = database.getReference()





        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)
        contactViewModel.getAll().observe(requireActivity(), Observer<List<Contact>>{
        val contacts  = contactViewModel.getAll().value

        Log.d(TAG,"start ")
        contacts?.forEach {
            Log.d(TAG,"${it.receiveName.toString()}")
            }
        })

        //마지막 문자 확인
        btCheck.setOnClickListener({
            //sender = App.prefs.getV("sender")
            //contents = App.prefs.getV("contents")
            //receivedDate = App.prefs.getV("receivedDate")
            val contact:Dcontact = Dcontact("이경훈","010-4697-3907","문자","2020년")
            App.prefs.setContact("contact",contact)

            println("${contact}")

            //myRef.push().setValue("Data")
            myRef.child("Data").push().setValue("hello , this is first time")
            myRef.child("Data").push().setValue("hello2 , this is first time2")




        })
        //확인한 문자 로드
        btLoad.setOnClickListener({
            val contactGet = App.prefs.getContact("contact","01046973907")
            edSMS.setText(contactGet.toString())
        })
        //문자 전송
        btSend.setOnClickListener({
            val number  = txtSetReceNumber.text.toString()
            val message = edSMS.text.toString()
            (activity as MainActivity).sendSMS(number , message)
        })

    }

    private fun println(data:String){
        txtlogSMS.setText("${data}\n")
    }


}
