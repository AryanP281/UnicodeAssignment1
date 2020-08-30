package com.example.unicodeassignment1

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.res.ResourcesCompat
import kotlinx.android.synthetic.main.activity_main.*

class ColorFragment : Fragment()
{
    companion object
    {
        fun newInstance() : ColorFragment
        {
            return ColorFragment();
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val fragmentView : View? = inflater.inflate(R.layout.fragment_color, container, false) // Inflate the layout for this fragment

        //Setting the navigation bar background
        val bgDrawable : GradientDrawable = ResourcesCompat.getDrawable(resources,R.drawable.hw_fragment_nav_bg, null) as GradientDrawable;
        (activity as MainActivity).navigation_view.background = bgDrawable;

        //Initializing the toolbar
        (activity as MainActivity).toolbar.title = "Hello World Fragment"; //Setting the title of the toolbar
        (activity as MainActivity).toolbar.background = bgDrawable; //Setting the toolbar background

        return fragmentView;
    }

}