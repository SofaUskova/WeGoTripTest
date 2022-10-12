package com.example.wegotriptest.presentation

import android.widget.ImageView
import android.widget.SeekBar
import com.example.wegotriptest.R

class SeekBarChangeListener(
    private val imageView: ImageView
) : SeekBar.OnSeekBarChangeListener {

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        imageView.setImageResource(
            when (progress) {
                0 -> R.drawable.ic_emoji_awful
                1 -> R.drawable.ic_emoji_bad
                2 -> R.drawable.ic_emoji_middle
                3 -> R.drawable.ic_emoji_good
                4 -> R.drawable.ic_emoji_great
                else -> R.drawable.ic_emoji_middle
            }
        )
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {}

    override fun onStopTrackingTouch(seekBar: SeekBar?) {}

}
