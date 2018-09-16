package com.example.dell.echo3.Util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import com.example.dell.echo3.Activities.MainActivity
import com.example.dell.echo3.Fragments.SongPlayingFragment
import com.example.dell.echo3.R

class CaptureBroadcast : BroadcastReceiver() {
     override fun onReceive(context: Context?, intent: Intent?) {
         /*Here we check whether the user has an outgoing call or not*/
         if (intent?.action == Intent.ACTION_NEW_OUTGOING_CALL) {
             try{
                 if (SongPlayingFragment.Statified.mediaplayer?.isPlaying as Boolean) {
                     MainActivity.Statified.notificationManager?.cancel(1978)
                 }
             }
             catch(e: Exception){
                 e.printStackTrace()
             }
             try {
                 /*If the media player was playing we pause it and change the play/pause button*/
                 if (SongPlayingFragment.Statified.mediaplayer?.isPlaying as Boolean){
                     SongPlayingFragment.Statified.mediaplayer?.pause()
                     SongPlayingFragment.Statified.playpauseImageButton?.setBackgroundResource(R.drawable.play_icon)
                 }
             } catch (e: Exception){
                 e.printStackTrace()
             }
         } else {
             /*Here we use the telephony manager to get the access for the service*/
             val tm: TelephonyManager = context?.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
             try{
                 if (SongPlayingFragment.Statified.mediaplayer?.isPlaying as Boolean) {
                     MainActivity.Statified.notificationManager?.cancel(1978)
                 }
             }
             catch(e: Exception){
                 e.printStackTrace()
             }
             when (tm?.callState) {
                 /*We check the call state and if the call is ringing i.e. the user has an incoming call
                        *  * then also we pause the media player*/
                 TelephonyManager.CALL_STATE_RINGING -> {
                     try {
                         if (SongPlayingFragment.Statified.mediaplayer?.isPlaying as Boolean) {
                             SongPlayingFragment.Statified.mediaplayer?.pause()
                             SongPlayingFragment.Statified.playpauseImageButton?.setBackgroundResource(R.drawable.play_icon)
                         }                    }
                     catch (e: Exception) {
                         e.printStackTrace()
                     }
                 }
                 /*Else we do nothing*/
                 else -> {
                }
             }
         }
     }
}