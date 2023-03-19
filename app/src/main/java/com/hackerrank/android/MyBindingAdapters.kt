package com.hackerrank.android

import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.bumptech.glide.Glide

@BindingAdapter("loadImage")
fun ImageView.loadImage(int: Int){
    Log.d("nathanTesT", "loadImage: $int")
    this.load(int)
//    Glide.with(this).load(int).into(this)
}

@BindingAdapter("toggleCartButton")
fun Button.toggleCartButton(boolean: Boolean){
    if (boolean){
        this.text = resources.getString(R.string.remove_from_cart_btn_text)
    } else {
        this.text = resources.getString(R.string.add_to_cart_btn_text)
    }
}
