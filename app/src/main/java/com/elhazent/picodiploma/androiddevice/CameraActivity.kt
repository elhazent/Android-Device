package com.training.applicationdevice

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.Manifest.permission
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_camera.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import android.provider.MediaStore
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.os.Environment.getExternalStorageDirectory

import java.nio.file.Files.exists
import android.os.StrictMode
import com.training.applicationdevice.helper.MyFunction
import java.io.File
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import com.elhazent.picodiploma.androiddevice.R
import java.io.FileNotFoundException
import java.io.InputStream


class CameraActivity : MyFunction() {
    var lokasifile: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                        arrayOf(Manifest.permission.CAMERA),
                        10)
            }
            return
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        107)
            }
            return
        }
        btncamera.onClick {
            val builder = StrictMode.VmPolicy.Builder()
            StrictMode.setVmPolicy(builder.build())

            val foldercamera = "photo"
            val file = File(Environment.getExternalStorageDirectory(), foldercamera)
            if (!file.exists()) {
                file.mkdir()
            }
            val isifile = File(Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + "/" + foldercamera + "/PIC" + currentDate() + ".jpg")
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            lokasifile = Uri.fromFile(isifile)
            //lokasifile = FileProvider.getUriForFile(c, c.getApplicationContext().getPackageName(), isifile);

            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, lokasifile)
            startActivityForResult(intent, 1)
        }
        btnshow.onClick {
            val galery = Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galery, 2)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                mytoast("berhasil menyimpan gambar \n lokasi" + lokasifile.toString())
            } else if (resultCode == RESULT_CANCELED) {
                mytoast("cancel")
            } else {
                mytoast("gagal mengambil gambar")
            }
        } else if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                val lokasigambar = data!!.data
                var inputStream: InputStream? = null
                try {
                    inputStream = contentResolver.openInputStream(lokasigambar!!)
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                }

                val bitmap = BitmapFactory.decodeStream(inputStream)
                imgshow.setImageBitmap(bitmap)

            } else if (resultCode == RESULT_CANCELED) {
                mytoast("cancel")
            } else {
                mytoast("gagal menampilkan gambar")
            }
        }
    }
}
