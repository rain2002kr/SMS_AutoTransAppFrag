package com.example.sms_autotransappfrag.Contact_Regist_item

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sms_autotransappfrag.MainActivity
import com.example.sms_autotransappfrag.MainActivity.Companion.MAIN_FRAG
import com.example.sms_autotransappfrag.MainViewAdapter
import com.example.sms_autotransappfrag.MainViewModel
import com.example.sms_autotransappfrag.R
import kotlinx.android.synthetic.main.fragment_contact_regist.*


class ContactRegistFragment : Fragment() {
    val TAG = "ContactRegistFragment"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_contact_regist, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        home.setOnClickListener({
            (activity as MainActivity).changeFragment(MAIN_FRAG)
        })
    }


}
