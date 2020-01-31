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
        home2.setOnClickListener({
            (activity as MainActivity).changeFragment(MainActivity.MAIN_FRAG)
        })
        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)
        contactViewModel.getAll().observe(requireActivity(), Observer<List<Contact>>{

        })



        checkbt.setOnClickListener({
            val contacts  = contactViewModel.getAll().value
            Log.d(TAG,"start ")
            contacts?.forEach {
                Log.d(TAG,"${it.receiveName.toString()}")
                //txtlogSMS.text = it.receiveName.toString()

            }
            val sender = App.prefs.getV("sender")
            txtlogSMS.text = sender

        })



    }


}
