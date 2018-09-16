package com.example.dell.echo3.adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.dell.echo3.Activities.MainActivity
import com.example.dell.echo3.Fragments.AboutUsFragement
import com.example.dell.echo3.Fragments.FavoriteFragment
import com.example.dell.echo3.Fragments.MainScreenFragment
import com.example.dell.echo3.Fragments.SettingsFragment
import com.example.dell.echo3.R

class NavigationDrawerAdapter(_contentList:ArrayList<String>,_getimages:IntArray,_context: Context)
    : RecyclerView.Adapter<NavigationDrawerAdapter.NavViewHolder>(){
    var contentList:ArrayList<String>?=null
    var getimages:IntArray?=null
    var context:Context?=null
    init{
        this.contentList = _contentList
        this.getimages = _getimages
        this.contentList = _contentList
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavViewHolder {
        var itemview = LayoutInflater.from(parent?.context)
                .inflate(R.layout.row_custom_navigationdrawer,parent,false)
        var returnthis = NavViewHolder(itemview)
        return returnthis
    }

    override fun getItemCount(): Int { return (contentList as ArrayList).size}

    override fun onBindViewHolder(holder: NavViewHolder, position: Int) {
        holder?.icon?.setBackgroundResource(getimages?.get(position) as Int)
        holder?.text?.setText(contentList?.get(position))
        holder?.contentHolder?.setOnClickListener({
            if(position == 0){
                val mainscreenfragment = MainScreenFragment()
                (context as MainActivity).supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.details_fragment, Fragment())
                        .commit()
            }
            else if(position == 1){
                val favoriteFragment = FavoriteFragment()
                (context as MainActivity).supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.details_fragment, Fragment())
                        .commit()
            }
            else if(position == 2){
                val settingsFragment = SettingsFragment()
                (context as MainActivity).supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.details_fragment, Fragment())
                        .commit()
            }
            else{
                val aboutusFragment = AboutUsFragement()
                (context as MainActivity).supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.details_fragment, Fragment())
                        .commit()
            }
            MainActivity.Statified.drawerlayout?.closeDrawers()
        })
    }

    class NavViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var contentHolder: RelativeLayout?=null
        var icon: ImageView?=null
        var text: TextView?=null
        init{
            icon = itemView?.findViewById(R.id.icon_navdrawer)
            text = itemView?.findViewById(R.id.text_navdrawer)
            contentHolder = itemView?.findViewById(R.id.navigation_item_content_holder)
        }
    }
}