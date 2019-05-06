package com.example.viewpagernavigation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import com.example.viewpagernavigation.shared.views.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private val fragments =
            listOf(BaseFragment.newInstance(R.layout.content_home_base, R.id.toolbar_home, R.id.nav_host_home),
                    BaseFragment.newInstance(R.layout.content_library_base, R.id.toolbar_library, R.id.nav_host_library),
                    BaseFragment.newInstance(R.layout.content_settings_base, R.id.toolbar_settings, R.id.nav_host_settings))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // setup main view pager
        main_pager.adapter = ViewPagerAdapter()

    }


    inner class ViewPagerAdapter : FragmentPagerAdapter(supportFragmentManager) {

        override fun getItem(position: Int): Fragment = fragments[position]

        override fun getCount(): Int = fragments.size

    }
}
