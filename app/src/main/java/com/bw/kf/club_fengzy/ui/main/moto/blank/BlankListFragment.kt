package com.bw.kf.club_fengzy.ui.main.moto.blank

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bw.kf.club_fengzy.R

class BlankListFragment : Fragment() {

    companion object {
        fun newInstance() = BlankListFragment()
    }

    private val viewModel: BlankListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return inflater.inflate(R.layout.fragment_blank_list, container, false)
    }
}