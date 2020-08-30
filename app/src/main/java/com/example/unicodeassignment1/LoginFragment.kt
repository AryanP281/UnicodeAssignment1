package com.example.unicodeassignment1

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.res.ResourcesCompat
import kotlinx.android.synthetic.main.activity_main.*

class LoginFragment : Fragment()
{
    companion object
    {
        @JvmStatic
        fun newInstance() : LoginFragment
        {
            return LoginFragment();
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val fragmentView : View = inflater.inflate(R.layout.fragment_login, container, false); // Inflate the layout for this fragment

        //Setting button click listener
        (fragmentView.findViewById(R.id.login_page_btn) as Button).setOnClickListener { v : View -> startLoginActivity() };

        //Setting the navigation bar background
        val bgDrawable : GradientDrawable = ResourcesCompat.getDrawable(resources,R.drawable.login_fragment_nav_bg, null) as GradientDrawable;
        (activity as MainActivity).navigation_view.background = bgDrawable;

        //Initializing the toolbar
        (activity as MainActivity).toolbar.title = "Login Fragment"; //Setting the title of the toolbar
        (activity as MainActivity).toolbar.background = bgDrawable; //Setting the toolbar background

        return fragmentView;
    }

    private fun startLoginActivity() : Unit
    {
        val intent : Intent = Intent(activity, LoginActivity::class.java); //The intent to start a new activity
        startActivity(intent); //Starting the activity
    }

}