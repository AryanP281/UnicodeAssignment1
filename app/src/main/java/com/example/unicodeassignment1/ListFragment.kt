package com.example.unicodeassignment1

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class ListFragment : Fragment()
{
    companion object
    {
        @JvmStatic
        fun newInstance() : ListFragment
        {
            return ListFragment();
        }
    }

    class RecyclerAdapter(private val daysList : Array<String>, private val clickListener : (TextView) -> Unit) : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>()
    {
        class RecyclerViewHolder(val textView : TextView) : RecyclerView.ViewHolder(textView); //The view holder for the recycler view

        override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : RecyclerViewHolder
        {
            //Creating a new view
            val textView : TextView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_element, parent, false) as TextView;

            return RecyclerViewHolder(textView); //Adding the text view to the view holder and returning the holder
        }

        override fun onBindViewHolder(holder : RecyclerViewHolder, position : Int)
        {
            holder.textView.setText(daysList[position]);
            holder.textView.setOnClickListener{clickListener(holder.textView)};
        }

        override fun getItemCount(): Int
        {
            return daysList.size;
        }
    } //Adapter for the recycler view

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        val fragmentView : View =  inflater.inflate(R.layout.fragment_list, container, false)

        //Initializing the recycler view
        val daysList : Array<String> = arrayOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
        val recyclerView : RecyclerView = fragmentView.findViewById(R.id.list_fragment_recyclerview) as RecyclerView;
        recyclerView.adapter = RecyclerAdapter(daysList, {textView : TextView -> Toast.makeText(context, textView.text, Toast.LENGTH_SHORT).show()} ); //Setting the view adapter
        recyclerView.setHasFixedSize(true); //Notifying that the item list will not change
        recyclerView.layoutManager = LinearLayoutManager(context); //Setting the view layout manager
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL)); //Adding divider lines

        //Setting the navigation bar background
        val bgDrawable : GradientDrawable = ResourcesCompat.getDrawable(resources,R.drawable.list_fragment_nav_bg, null) as GradientDrawable;
        (activity as MainActivity).navigation_view.background = bgDrawable;

        //Initializing the toolbar
        (activity as MainActivity).toolbar.title = "List Fragment"; //Setting the title of the toolbar
        (activity as MainActivity).toolbar.background = bgDrawable; //Setting the toolbar background

        return fragmentView;
    }
}