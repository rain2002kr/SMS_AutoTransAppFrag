package com.example.sms_autotransappfrag.Infomation


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
import com.example.sms_autotransappfrag.MainViewAdapter

import com.example.sms_autotransappfrag.R
import kotlinx.android.synthetic.main.fragment_infomation.*

/**
 * A simple [Fragment] subclass.
 */


class InfomationFragment : Fragment() {
    val TAG = "InfomationFragment"
    private val recyclerview_info_log: RecyclerView by lazy {
        view?.findViewById(R.id.recyclerview_info_log) as RecyclerView
    }
    private val list by lazy{
        mutableListOf<InfoViewModel>(
            InfoViewModel("2020-01-31","메뉴 업그레이드",
                " * 홈 화면 으로 이동 기능 추가" +
                        "\n * 정보 이력 화면 추가" +
                        "\n * 시스템 백 1회 '토스트 메시지' 2회 '종료 기능' 추가")
        )

    }
    private lateinit var infoAdapter : InfoViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val view =inflater.inflate(R.layout.fragment_infomation, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerview_info_log.setHasFixedSize(true) //lazy 접근
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            recyclerview_info_log.layoutManager = LinearLayoutManager(context)
        }
        val infoAdapter = InfoViewAdapter(requireContext(), R.layout.sub_infomation_log_view, list)

        recyclerview_info_log.apply {
            this.adapter = infoAdapter
            setHasFixedSize(true)
            val gridLayout = GridLayoutManager(context,1 )
            layoutManager = gridLayout
        }

    }



}
