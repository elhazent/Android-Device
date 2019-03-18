package com.training.applicationdevice.helper

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.widget.Toast
import com.elhazent.picodiploma.androiddevice.R


class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "alarm berbunyi", Toast.LENGTH_SHORT).show()
        val player = MediaPlayer.create(context, R.raw.alarm)
        player.start()
    }

}