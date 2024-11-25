package com.bw.kf.club_fengzy.ui.main.mall

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bw.kf.club_fengzy.R

class MallHomeFragment : Fragment() {

    companion object {
        fun newInstance() = MallHomeFragment()
    }

    private val viewModel: MallHomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return inflater.inflate(R.layout.fragment_mall_home, container, false)
    }
}