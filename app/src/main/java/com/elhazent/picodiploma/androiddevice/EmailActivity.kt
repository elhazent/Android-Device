package com.training.applicationdevice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_email.*
import android.content.Intent
import android.text.TextUtils
import com.elhazent.picodiploma.androiddevice.R
import com.training.applicationdevice.helper.MyFunction


class EmailActivity : MyFunction() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.getItemId()
        val to = edtto.text.toString()
        val message = edtmessage.text.toString()
        val subject = edtsubject.text.toString()

        if (id == R.id.mn_send) {
            if (TextUtils.isEmpty(to)) {
                edtto.error = "tujuan tidak boleh kosong"
                edtto.requestFocus()
                myAnimation(edtto)
            } else if (TextUtils.isEmpty(message)) {
                edtmessage.error = "pesan tidak boleh kosong"
                edtmessage.requestFocus()
                myAnimation(edtmessage)
            } else if (TextUtils.isEmpty(subject)) {
                edtsubject.error = "subject tidak boleh kosong"
                edtsubject.requestFocus()
                myAnimation(edtsubject)
            } else {
                val intent = Intent(Intent.ACTION_SEND)
                intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(to))
                intent.putExtra(Intent.EXTRA_TEXT, message)
                intent.putExtra(Intent.EXTRA_SUBJECT, subject)

                intent.type = "message/rfc822"
                startActivity(Intent.createChooser(intent, "silahkan pilih email client"))
            }
        } else {
            edtto.setText("")
            edtmessage.setText("")
            edtsubject.setText("")
        }
        return super.onOptionsItemSelected(item)
    }
}
