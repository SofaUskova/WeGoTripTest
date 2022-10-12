package com.example.wegotriptest.utils

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.wegotriptest.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

fun downloadImage(
    context: BottomSheetDialogFragment,
    url: String?,
    avatarImageView: ImageView
) {
    Glide
        .with(context)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.placeholder)
        .error(R.drawable.ic_image_not_found)
        .into(avatarImageView)
}

fun errorMessage(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}