package com.training.applicationdevice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.app.TimePickerDialog
import android.util.Log
import kotlinx.android.synthetic.main.activity_alarm.*
import java.util.*
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import com.training.applicationdevice.helper.AlarmReceiver
import android.content.Intent
import com.elhazent.picodiploma.androiddevice.R

import org.jetbrains.anko.sdk27.coroutines.onClick


class AlarmActivity : AppCompatActivity() {
    var timePickerDialog: TimePickerDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)
        startSetDialog.onClick {
            opentimepickerdialog(false);
        }
    }

    private fun opentimepickerdialog(b: Boolean) {
        val calendar = Calendar.getInstance()
        timePickerDialog = TimePickerDialog(this, onTimeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), true)
        timePickerDialog!!.setTitle("set alarm time")
        timePickerDialog!!.show()
    }

    var onTimeSetListener: TimePickerDialog.OnTimeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, i, i1 ->
        val callnow = Calendar.getInstance()
        val callset = callnow.clone() as Calendar
        callset.set(Calendar.HOUR_OF_DAY, i)
        callset.set(Calendar.MINUTE, i1)
        callset.set(Calendar.SECOND, 0)
        callset.set(Calendar.MILLISECOND, 0)
        if (callset.compareTo(callnow) <= 0) {
            callset.add(Calendar.DATE, 1)
        } else if (callset.compareTo(callnow) > 0) {
            Log.i("hasil", ">0")
        } else {

        }
        setAlarm(callset)
    }

    private fun setAlarm(callset: Calendar) {
        alarmprompt.text = "***\n " + "alarm set on" + callset.time + "\n***"
        val intent = Intent(baseContext, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(baseContext,
                1, intent, 0)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC_WAKEUP, callset.timeInMillis, pendingIntent)
    }
}
