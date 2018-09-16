package com.example.dell.echo3.adapters
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.dell.echo3.Fragments.SongPlayingFragment
import com.example.dell.echo3.R
import com.example.dell.echo3.Songs

class FavoriteAdapter(_songDetails: ArrayList<Songs>, _context: Context) :
        RecyclerView.Adapter<FavoriteAdapter.MyViewHolder>() {
    var songDetails: ArrayList<Songs>? = null
    var mContext: Context? = null
    init {
        this.songDetails = _songDetails
        this.mContext = _context
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val songObject = songDetails?.get(position)
        holder.trackTitle?.text = songObject?.songTitle
        holder.trackArtist?.text = songObject?.artist
        holder.contentHolder?.setOnClickListener({
            val songPlayingFragment = SongPlayingFragment()
            var args = Bundle()
            args.putString("songArtist", songObject?.artist)
            args.putString("songTitle", songObject?.songTitle)
            args.putString("path", songObject?.songData)
            args.putInt("SongID", songObject?.songID?.toInt() as Int)
            args.putInt("songPosition", position)
            args.putParcelableArrayList("songData", songDetails)
            songPlayingFragment.arguments = args
            (mContext as FragmentActivity).supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.details_fragment, Fragment())
                    .addToBackStack("SongPlayingFragmentFavorite")
                    .commit()
        })
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.raw_custom_mainscreen_adapter, parent, false)
        return MyViewHolder(itemView)
    }
    override fun getItemCount(): Int {
            if (songDetails == null) {
                return 0
            }
            else {
                return (songDetails as ArrayList<Songs>).size
            }
    }
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
              var trackTitle: TextView? = null
        var trackArtist: TextView? = null
        var contentHolder: RelativeLayout? = null
               init {
                   trackTitle = view.findViewById(R.id.trackTitle) as TextView
                   trackArtist = view.findViewById(R.id.trackArtist) as TextView
                   contentHolder = view.findViewById(R.id.contentRow) as RelativeLayout
               }
    }
}
