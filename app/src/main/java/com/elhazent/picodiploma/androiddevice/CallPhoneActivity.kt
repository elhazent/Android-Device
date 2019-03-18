package com.training.applicationdevice

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_call_phone.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import android.content.Intent
import android.Manifest.permission
import android.Manifest.permission.CALL_PHONE
import android.app.Activity
import android.support.v4.app.ActivityCompat
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import com.training.applicationdevice.helper.MyFunction
import android.provider.ContactsContract
import com.elhazent.picodiploma.androiddevice.R






class CallPhoneActivity : MyFunction() {
    var nohp : String ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call_phone)
btncall.onClick {
    val nohp = edtnumber.text.toString()
    if (TextUtils.isEmpty(nohp)) {
        edtnumber.error = "tidak boleh kosong"
        edtnumber.requestFocus()
        myAnimation(edtnumber)
    } else {
        val checkPermission = ContextCompat.checkSelfPermission(this@CallPhoneActivity, Manifest.permission.CALL_PHONE)
        if (checkPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this@CallPhoneActivity,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    3)
        } else {
            startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:$nohp")))
        }
    }
}
        btntampilcall.onClick {
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$nohp")))
        }
        btnlistcontact.onClick {
            val i = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
            i.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
            startActivityForResult(i, 1)
        }
    }

    //untuk menangkap response dari startActivityForResult
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            var cursor: Cursor? = null
            try {
                val uri = data!!.data
                cursor = contentResolver.query(uri!!, arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER), null, null, null)
                if (cursor != null && cursor!!.moveToNext()) {
                    val phone = cursor!!.getString(0)
                    edtnumber.setText(phone)
                }
            } catch (e: Exception) {
                e.printStackTrace()

            }

        }
    }
}
