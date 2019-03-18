package com.training.applicationdevice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import com.elhazent.picodiploma.androiddevice.R
import kotlinx.android.synthetic.main.activity_tts.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import com.training.applicationdevice.helper.MyFunction
import java.util.*


class TTSActivity : MyFunction(), TextToSpeech.OnInitListener {
    override fun onInit(status: Int) {
        if (status === TextToSpeech.SUCCESS) {
            val indo = Locale("id", "ID")
            val hasil = tts?.setLanguage(Locale.JAPANESE)
            if (hasil == TextToSpeech.LANG_MISSING_DATA || hasil == TextToSpeech.LANG_NOT_SUPPORTED) {
                mytoast("bahasa tidak mendukung")
            } else {
                speak()
                btnSpeech.isEnabled = true
            }
        } else {
            mytoast("TTS Tidak support")
        }
    }

    var tts: TextToSpeech? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tts)
         tts = TextToSpeech(this@TTSActivity, this)
        btnSpeech.onClick {
            speak()

        }
    }

    private fun speak() {
        val text = editText.text.toString()
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null)
    }
}
