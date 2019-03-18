package com.training.applicationdevice.helper

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.content.Intent
import android.widget.Toast
import android.R.attr.animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import com.elhazent.picodiploma.androiddevice.R
import java.text.SimpleDateFormat
import java.util.*


open class MyFunction : AppCompatActivity() {
    var context:Context = this
    var animation :Animation ? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_function)


    }

    fun moveClass(kelastujuan: Class<*>) {
        startActivity(Intent(context, kelastujuan))
    }

    fun myAnimation(editText: EditText) {
        animation = AnimationUtils.loadAnimation(context, R.anim.animasigetar)
        editText.setAnimation(animation)
    }

    fun mytoast(isipesan: String) {
        Toast.makeText(context, isipesan, Toast.LENGTH_SHORT).show()

    }

    fun currentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss")
        val date = Date()
        return dateFormat.format(date)
    }
}
