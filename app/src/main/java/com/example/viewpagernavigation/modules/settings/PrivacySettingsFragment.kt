package com.example.viewpagernavigation.modules.settings

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.viewpagernavigation.R


class PrivacySettingsFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_privacy_settings, container, false)
    }


    companion object {


        @JvmStatic
        fun newInstance() = PrivacySettingsFragment()
    }
}
