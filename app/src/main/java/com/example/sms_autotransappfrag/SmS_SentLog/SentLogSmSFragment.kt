package com.example.sms_autotransappfrag.SmS_SentLog


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sms_autotransappfrag.MainActivity

import com.example.sms_autotransappfrag.R
import kotlinx.android.synthetic.main.fragment_send_sm.*
import kotlinx.android.synthetic.main.fragment_sent_log_sm.*

class SentLogSmSFragment : Fragment() {
    val TAG = "SentLogSmSFragment"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sent_log_sm, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        home3.setOnClickListener({
            (activity as MainActivity).changeFragment(MainActivity.MAIN_FRAG)
        })
    }


}
