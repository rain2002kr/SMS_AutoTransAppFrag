package com.example.sms_autotransappfrag.Fragment_main


import android.content.Context
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
import com.example.sms_autotransappfrag.MainActivity.Companion.INFO_FRAG
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

    private val image     = listOf<Int>(R.drawable.network,R.drawable.log,R.drawable.sms,R.drawable.info)
    private lateinit var title :List<String>
    private lateinit var comment :List<String>
    private val list by lazy {
            mutableListOf<MainViewModel>(
            MainViewModel(image[0], title[0],comment[0]),
            MainViewModel(image[1], title[1],comment[1]),
            MainViewModel(image[2], title[2],comment[2]),
            MainViewModel(image[3], title[3],comment[3])

            )
        }

    private lateinit var mainAdapter : MainViewAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val language = resources.configuration.locale.language
        //한국말 지원
        if(language.contains("ko")) {
            title   = listOf<String>("전송리스트", "전송 내역", "문자 보내기", "정보")
            comment = listOf<String>(
                 "자동 문자 전송 정보를 저장하는 화면입니다."
                ,"자동 전송된 문자 내역을 확인하는 화면입니다."
                ,"문자를 전송 할수 있는 화면입니다."
                ,"버전을 확인 할수 있습니다.")
        }
        //영어 지원
        if(language.contains("en")){
            title = listOf<String>("Tranfer List", "Transfered log", "SMS Send", "Information")
            comment = listOf<String>(
                 "This screen saves the automatic text transfer list."
                ,"This is a screen to check the texts sent automatically."
                ,"It is a screen to send a text."
                ,"You can check the version and notice.")
        }
    }

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
                    1 -> (activity as MainActivity).changeFragment(LOG_FRAG)
                    2 -> (activity as MainActivity).changeFragment(SEND_FRAG)
                    3 -> (activity as MainActivity).changeFragment(INFO_FRAG)

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