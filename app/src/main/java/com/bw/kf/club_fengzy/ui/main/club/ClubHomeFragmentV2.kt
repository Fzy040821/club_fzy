package com.bw.kf.club_fengzy.ui.main.club

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bw.kf.club_fengzy.R

class ClubHomeFragmentV2 : Fragment() {

    companion object {
        fun newInstance() = ClubHomeFragmentV2()
    }

    private val viewModel: ClubHomeFragmentV2ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return inflater.inflate(R.layout.fragment_club_home_fragment_v2, container, false)
    }
}