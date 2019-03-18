package com.training.applicationdevice

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sms.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import android.provider.ContactsContract
import android.content.Intent
import android.text.TextUtils
import com.training.applicationdevice.helper.MyFunction
import android.widget.Toast
import android.Manifest.permission
import android.Manifest.permission.SEND_SMS
import android.app.Activity
import android.support.v4.app.ActivityCompat
import android.content.pm.PackageManager
import android.database.Cursor
import android.support.v4.content.ContextCompat
import android.telephony.SmsManager
import com.elhazent.picodiploma.androiddevice.R


class SmsActivity : MyFunction() {
var nohp :String ?=null
var pesan :String ?=null
     val REQUESTSMS = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms)

        edtnohp.onClick {

            val i = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
            i.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
            startActivityForResult(i, 1)
        }
        btnsmsintent.onClick {
            nohp = edtnohp.text.toString()
            pesan = edtmessage.text.toString()

            if (TextUtils.isEmpty(nohp)) {
                edtnohp.error = "can not be empty\n"
                edtnohp.requestFocus()
                myAnimation(edtnohp)
            } else if (TextUtils.isEmpty(pesan)) {
                edtmessage.error = "can not be empty\n"
                edtmessage.requestFocus()
                myAnimation(edtmessage)
            } else {
                val sms = Intent(Intent.ACTION_VIEW)
                sms.putExtra("address", nohp)
                sms.putExtra("sms_body", pesan)
                sms.type = "vnd.android-dir/mms-sms"
                startActivity(sms)
            }
        }
        btnkirimsms.onClick {
            if (ContextCompat.checkSelfPermission(this@SmsActivity,
                            Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this@SmsActivity,
                                Manifest.permission.SEND_SMS)) {
                } else {
                    ActivityCompat.requestPermissions(this@SmsActivity,
                            arrayOf(Manifest.permission.SEND_SMS),
                            REQUESTSMS)
                }
            } else {
                try {
                    val manager = SmsManager.getDefault()
                    manager.sendTextMessage(nohp, null, pesan, null, null)
                    Toast.makeText(this@SmsActivity, "sms sent", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(this@SmsActivity, "" + e.message, Toast.LENGTH_SHORT).show()
                }

            }
        }
    }


}
