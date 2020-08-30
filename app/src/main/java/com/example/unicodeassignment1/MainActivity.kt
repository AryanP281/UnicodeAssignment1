package com.example.unicodeassignment1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity()
{
    private val FRAGMENT_ID_BUNDLE_KEY : String = "FRAGMENT_ID";
    private var fragmentId : Int = 2; //The id of the currently displayed fragment

    private val navigationViewListener : BottomNavigationView.OnNavigationItemSelectedListener = object:BottomNavigationView.OnNavigationItemSelectedListener
    {
        override fun onNavigationItemSelected(item: MenuItem): Boolean
        {
            //Determining which item was selected
            when(item.itemId)
            {
                R.id.list_fragment ->
                {
                    displayFragment(ListFragment(), false);
                    fragmentId = 0; //Setting the fragment id
                }

                R.id.login_fragment ->
                {
                    displayFragment(LoginFragment(), false);
                    fragmentId = 1; //Setting the fragment id
                }

                R.id.color_fragment ->
                {
                    displayFragment(ColorFragment(), false);
                    fragmentId = 2; //Setting the fragment id
                }
            }

            return true;
        }
    };

    lateinit var navigationView : BottomNavigationView; //The bottom navigation view
    lateinit var toolbar : Toolbar; //The activity toolbar

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Initializing the bottom navigation view
        navigationView = findViewById(R.id.navigation_view); //Getting the naviation view
        navigationView.setOnNavigationItemSelectedListener(navigationViewListener); //Setting the item selected listener

        //Setting the action bar
        toolbar = findViewById(R.id.toolbar) as Toolbar;
        toolbar.setTitleTextColor(ResourcesCompat.getColor(resources, R.color.textColor, null)); //Setting the toolbar title text colour
        toolbar.setTitle(""); //Setting a blank title
        setSupportActionBar(toolbar); //Setting the toolbar as action bar

        //Displaying the default fragment
        if(savedInstanceState == null)
        {
            when(fragmentId)
            {
                0 -> displayFragment(ListFragment(), false);
                1 -> displayFragment(LoginFragment(), false);
                2 -> displayFragment(ColorFragment(), false);
            }
        }
        else
        {
            //Retrieving the fragment to be displayed
            fragmentId = savedInstanceState.getInt(FRAGMENT_ID_BUNDLE_KEY); //Getting the id of the fragment
            when(fragmentId)
            {
                0 -> displayFragment(ListFragment(), false);
                1 -> displayFragment(LoginFragment(), false);
                2 -> displayFragment(ColorFragment(), false);
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        //Adding the index of the currently displayed fragment to the
        outState.putInt(FRAGMENT_ID_BUNDLE_KEY, fragmentId);
    }

    private fun displayFragment(fragment : Fragment, addToBackstack : Boolean)
    {
        /**Displays the given fragment in the content frame**/

        val fragManager : FragmentManager = supportFragmentManager; //Getting the fragment manager for the activity

        //Starting the fragment replacement transaction
        val fragTransaction : FragmentTransaction = fragManager.beginTransaction();
        fragTransaction.replace(R.id.content_frame, fragment);
        fragTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE); //Setting the transition style
        if(addToBackstack) fragTransaction.addToBackStack(null); //Adding the transaction to backstack so that it can be reverted by pressing the back key
        fragTransaction.commit();
    }
}