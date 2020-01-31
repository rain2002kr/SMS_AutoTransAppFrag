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
import com.example.sms_autotransappfrag.MainActivity

import com.example.sms_autotransappfrag.R
import com.example.sms_autotransappfrag.tContact
import kotlinx.android.synthetic.main.fragment_contact_regist.*
import kotlinx.android.synthetic.main.fragment_send_sm.*

/**
 * A simple [Fragment] subclass.
 */
class SendSmSFragment : Fragment() {
    val TAG = "SendSmSFragment"
    private lateinit var contactViewModel : ContactViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_send_sm, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)
        contactViewModel.getAll().observe(requireActivity(), Observer<List<Contact>>{

        })



        checkbt.setOnClickListener({
            val contacts  = contactViewModel.getAll().value
            Log.d(TAG,"start ")
            contacts?.forEach {
                Log.d(TAG,"${it.receiveName.toString()}")

            }
            val sender = App.prefs.getV("sender")
            val contents = App.prefs.getV("contents")
            val receivedDate = App.prefs.getV("receivedDate")
            println("${sender} ${contents} ${receivedDate}")

        })

        saveBtApp.setOnClickListener({

        })
        loadBtApp.setOnClickListener({

        })





    }

    private fun println(data:String){
        txtlogSMS.setText("${data}\n")
    }


}
