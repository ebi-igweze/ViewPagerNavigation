package com.example.viewpagernavigation.modules.library

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.viewpagernavigation.R
import kotlinx.android.synthetic.main.fragment_library.*

class LibraryFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        awesome_book.setOnClickListener {
            val bundle = Bundle().apply {
                putString("title", "Winds of Winter")
                putString("page", "3,047")
            }

            findNavController().navigate(R.id.action_read, bundle)
        }
    }


    companion object {

        @JvmStatic
        fun newInstance() = LibraryFragment()
    }
}
