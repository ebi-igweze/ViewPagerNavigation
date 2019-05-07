package com.example.viewpagernavigation

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.viewpagernavigation.shared.views.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(),
        ViewPager.OnPageChangeListener,
        BottomNavigationView.OnNavigationItemReselectedListener,
        BottomNavigationView.OnNavigationItemSelectedListener {


    // overall back stack of containers
    private val backStack = Stack<Int>()

    // list of base destination containers
    private val fragments = listOf(
            BaseFragment.newInstance(R.layout.content_home_base, R.id.toolbar_home, R.id.nav_host_home),
            BaseFragment.newInstance(R.layout.content_library_base, R.id.toolbar_library, R.id.nav_host_library),
            BaseFragment.newInstance(R.layout.content_settings_base, R.id.toolbar_settings, R.id.nav_host_settings))


    // map of navigation_id to container index
    private val indexToPage = mapOf(0 to R.id.home, 1 to R.id.library, 2 to R.id.settings)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // setup main view pager
        main_pager.addOnPageChangeListener(this)
        main_pager.adapter = ViewPagerAdapter()
        main_pager.post(this::checkDeepLink)
        main_pager.offscreenPageLimit = fragments.size

        // set bottom nav
        bottom_nav.setOnNavigationItemSelectedListener(this)
        bottom_nav.setOnNavigationItemReselectedListener(this)

        // initialize backStack with elements
        if (backStack.empty()) backStack.push(0)
    }

    /// BottomNavigationView ItemSelected Implementation
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val position = indexToPage.values.indexOf(item.itemId)
        if (main_pager.currentItem != position) setItem(position)
        return true
    }

    override fun onNavigationItemReselected(item: MenuItem) {
        val position = indexToPage.values.indexOf(item.itemId)
        val fragment = fragments[position]
        fragment.popToRoot()
    }

    override fun onBackPressed() {
        val fragment = fragments[main_pager.currentItem]
        val hadNestedFragments = fragment.onBackPressed()
        // if no fragments were popped
        if (!hadNestedFragments) {
            if (backStack.size > 1) {
                // remove current position from stack
                backStack.pop()
                // set the next item in stack as current
                main_pager.currentItem = backStack.peek()

            } else super.onBackPressed()
        }
    }

    /// OnPageSelected Listener Implementation
    override fun onPageScrollStateChanged(state: Int) {}

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}

    override fun onPageSelected(page: Int) {
        val itemId = indexToPage[page] ?: R.id.home
        if (bottom_nav.selectedItemId != itemId) bottom_nav.selectedItemId = itemId
    }

    private fun setItem(position: Int) {
        main_pager.currentItem = position
        backStack.push(position)
    }

    private fun checkDeepLink() {
        fragments.forEachIndexed { index, fragment ->
            val hasDeepLink = fragment.handleDeepLink(intent)
            if (hasDeepLink) setItem(index)
        }
    }

    inner class ViewPagerAdapter : FragmentPagerAdapter(supportFragmentManager) {

        override fun getItem(position: Int): Fragment = fragments[position]

        override fun getCount(): Int = fragments.size

    }
}
