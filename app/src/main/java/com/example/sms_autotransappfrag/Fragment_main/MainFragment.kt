package com.example.sms_autotransappfrag.Fragment_main


import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sms_autotransappfrag.MainActivity
import com.example.sms_autotransappfrag.MainActivity.Companion.LOG_FRAG
import com.example.sms_autotransappfrag.MainActivity.Companion.MAIN_FRAG
import com.example.sms_autotransappfrag.MainActivity.Companion.REGIST_FRAG
import com.example.sms_autotransappfrag.MainActivity.Companion.SEND_FRAG
import com.example.sms_autotransappfrag.MainViewAdapter
import com.example.sms_autotransappfrag.MainViewModel

import com.example.sms_autotransappfrag.R

class MainFragment : Fragment() {
    val TAG = "MainFragment"
    private val recyclerview_main_screen: RecyclerView by lazy {
        view?.findViewById(R.id.recyclerview_main_screen) as RecyclerView
    }
    private val list by lazy {
        mutableListOf<MainViewModel>(
            MainViewModel(R.drawable.network, "전송 리스트","자동 문자 전송 정보를 저장하는 화면입니다."),
            MainViewModel(R.drawable.log, "전송 내역","자동 전송된 문자 내역을 확인하는 화면입니다."),
            MainViewModel(R.drawable.sms, "문자 보내기","문자를 전송 할수 있는 화면입니다."),
            MainViewModel(R.drawable.info, "정보","버전을 확인 할수 있습니다.")
        )
    }

    private lateinit var mainAdapter : MainViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerview_main_screen.setHasFixedSize(true) //lazy 접근
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            recyclerview_main_screen.layoutManager = LinearLayoutManager(context)
        }
        val mainViewAdapter = MainViewAdapter(requireContext(), R.layout.sub_main_view, list)
        mainViewAdapter.setItemClickListener(object  : MainViewAdapter.ItemClickListener{
            override fun onClick(view: View, position: Int) {
                Log.d(TAG , "${position}번 리스트 선택")
                when(position){
                    0 -> (activity as MainActivity).changeFragment(REGIST_FRAG)
                    1 -> (activity as MainActivity).changeFragment(SEND_FRAG)
                    2 -> (activity as MainActivity).changeFragment(LOG_FRAG)
                    3 -> Toast.makeText(context,"버전 : 2020.01.29",Toast.LENGTH_LONG).show()


                }


            }
        })

        recyclerview_main_screen.apply {
            this.adapter = mainViewAdapter
            setHasFixedSize(true)
            val gridLayout = GridLayoutManager(context,1 )
            layoutManager = gridLayout
        }

    }


}