package com.example.forumdisigin.ui.account

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi

import androidx.appcompat.app.AppCompatActivity


import com.bumptech.glide.Glide
import com.example.forumdisigin.MainActivity
import com.example.forumdisigin.R
import com.example.forumdisigin.ui.login.LoginViewModel

import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_account.image
import kotlinx.android.synthetic.main.nav_header_main.*


class Account : AppCompatActivity() {



    private val TAG: String = "AppDebug"

    private val GALLERY_REQUEST_CODE = 1234

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.nav_header_main)
//        button3.setOnClickListener{
//            pickFromGallery()
            setContentView(R.layout.fragment_account)
            update_textview.setOnClickListener {
                pickFromGallery()
        }
      /*  setContentView(R.layout.fragment_account)
        update_textview.setOnClickListener {
            pickFromGallery()
        }*/
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {

            GALLERY_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    data?.data?.let { uri ->
                        launchImageCrop(uri)
                    }
                }
                else{
                    Log.e(TAG, "Image selection error: Couldn't select that image from memory." )
                }
            }

            CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE -> {
                val result = CropImage.getActivityResult(data)
                if (resultCode == Activity.RESULT_OK) {
                    setImage(result.uri)
                }
                else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Log.e(TAG, "Crop error: ${result.getError()}" )
                }
            }
        }
    }

    private fun setImage(uri: Uri){
      /*  Glide.with(this)
            .load(uri)
            .into(image)*/
        Glide.with(this)
            .load(uri)

            .into(image)
    }

    private fun launchImageCrop(uri: Uri){
        CropImage.activity(uri)
          .setGuidelines(CropImageView.Guidelines.ON)
           .setAspectRatio(1, 1)
            .setCropShape (CropImageView.CropShape.OVAL )
          //  .setCropShape(CropImageView.CropShape.RECTANGLE) // default is rectangle
            .start(this)
    }


    private fun pickFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        val mimeTypes = arrayOf("image/jpeg", "image/png", "image/jpg")
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }




}
