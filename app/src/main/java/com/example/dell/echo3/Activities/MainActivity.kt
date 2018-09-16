package com.example.dell.echo3.Activities

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.example.dell.echo3.Fragments.MainScreenFragment
import com.example.dell.echo3.Fragments.SongPlayingFragment
import com.example.dell.echo3.R
import com.example.dell.echo3.adapters.NavigationDrawerAdapter

class MainActivity : AppCompatActivity() {
    var images_for_navdrawer = intArrayOf(R.drawable.navigation_allsongs,R.drawable.navigation_favorites,
            R.drawable.navigation_settings,R.drawable.navigation_aboutus)
    var navigationDrawerIconsList: ArrayList<String> = arrayListOf()
    object Statified{
        var drawerlayout : DrawerLayout?= null
        var notificationManager: NotificationManager?=null
    }
   var trackNotificationBuilder: Notification?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        MainActivity.Statified.drawerlayout = findViewById(R.id.drawer_layout)
        navigationDrawerIconsList.add("All Songs")
        navigationDrawerIconsList.add("Favorites")
        navigationDrawerIconsList.add("Settings")
        navigationDrawerIconsList.add("About Us")
        var toggle = ActionBarDrawerToggle(this@MainActivity,MainActivity.Statified.drawerlayout,toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        MainActivity.Statified.drawerlayout?.setDrawerListener(toggle)
        toggle.syncState()
        val mainscreenfragment = MainScreenFragment()
        this.supportFragmentManager
                .beginTransaction()
                .add(R.id.details_fragment, Fragment(),"MainScreenFragment")
                .commit()
        var _navigationadapter = NavigationDrawerAdapter(navigationDrawerIconsList,images_for_navdrawer,this)
        _navigationadapter.notifyDataSetChanged()
        var navigation_recycler_view = findViewById<RecyclerView>(R.id.navigation_recycler_view)
        navigation_recycler_view.layoutManager = LinearLayoutManager(this)
        navigation_recycler_view.itemAnimator = DefaultItemAnimator()
        navigation_recycler_view.adapter = _navigationadapter
        navigation_recycler_view.setHasFixedSize(true)
        var intent = Intent(this@MainActivity,MainActivity::class.java)
        var preintent = PendingIntent.getActivity(this@MainActivity,System.currentTimeMillis().toInt()
        ,intent,0)
        trackNotificationBuilder = Notification.Builder(this)
                .setContentTitle("A track is playing in background")
                .setSmallIcon(R.drawable.echo_logo)
                .setContentIntent(preintent)
                .setOngoing(true)
                .setAutoCancel(true)
                .build()
        Statified.notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }
    override fun onStart() {
        super.onStart()
        try{
            if (SongPlayingFragment.Statified.mediaplayer?.isPlaying as Boolean) {
                Statified.notificationManager?.cancel(1978)
            }
        }
        catch(e: Exception){
            e.printStackTrace()
        }
    }

    override fun onStop() {
        super.onStop()
        try{
            if (SongPlayingFragment.Statified.mediaplayer?.isPlaying as Boolean) {
                Statified.notificationManager?.notify(1978,trackNotificationBuilder)
            }
        }
        catch(e: Exception){
            e.printStackTrace()
        }
    }

    override fun onResume(){
        super.onResume()
        try{
            if (SongPlayingFragment.Statified.mediaplayer?.isPlaying as Boolean) {
                Statified.notificationManager?.cancel(1978)
            }
        }
        catch(e: Exception){
            e.printStackTrace()
        }
    }

}
