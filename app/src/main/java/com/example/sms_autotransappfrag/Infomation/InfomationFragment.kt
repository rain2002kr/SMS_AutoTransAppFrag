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
            InfoViewModel("2020-02월","개발 계획",
                  "*[기능] 문자 보내기 화면 에서 리싸이클러 저장"+
                        "\n *[기능] 문자 보내기 리싸이클러 문자 로드 기능"+
                        "\n *[기능] -"+
                        "\n *[기능] -"+
                        "\n *[기능] 구글 앱 심사 신청"+
                        "\n *[문서] 문자자동전송 앱 설명 및 메뉴얼 제작"

            ),

            InfoViewModel("2020-01-31","개발 이력",
                " *[기능] 홈 화면 으로 이동 기능 추가" +
                        "\n *[기능] 정보 이력 화면 추가" +
                        "\n *[기능] 시스템 백키 1회 누름 '토스트 메시지' 2회 누름 '종료 기능' 추가"),

            InfoViewModel("2020-02-01","개발 이력",
                " * [디자인]홈버튼 아이콘 변경 " +
                        "\n * [기능]문자 보내기 " +
                        "\n * [디자인] 앱 아이콘 변경 " +
                        "\n * [디자인+ 기능] 다국어 지원"

            )
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
