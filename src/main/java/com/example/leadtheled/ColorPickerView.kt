package com.example.leadtheled

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.util.AttributeSet
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.SeekBar
import android.widget.Toast
import androidx.annotation.RequiresApi




class ColorPickerView: RelativeLayout {
    constructor(context: android.content.Context) : this(context, null)
    constructor(context: android.content.Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: android.content.Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs) {
        var red = 0;
        var green = 0;
        var blue = 0;

        var color = 0

        this.layoutParams =
            LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
            )


        val redBar = SeekBar(this.context)

        redBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                Toast.makeText(context,"Progress: $i",Toast.LENGTH_SHORT).show()
                red = 255*i/100
                val barColor = calculateColor(0xFF,red,0, 0)

                seekBar.getProgressDrawable().setColorFilter(barColor, PorterDuff.Mode.SRC_IN);
                seekBar.getThumb().setColorFilter(barColor, PorterDuff.Mode.SRC_IN);
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Do something
                Toast.makeText(context,"start tracking",Toast.LENGTH_SHORT).show()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                color = calculateColor(0xFF, red, green, blue)
                ButtonMatrix.selectedColor = color
                if(ButtonMatrix.selectedButton != null)
                {
                    ButtonMatrix.setColor(ButtonMatrix.selectedButton!!)
                }
            }
        })
        var redBarLayParams=  RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        redBarLayParams.addRule(ALIGN_PARENT_TOP)
        this.addView(redBar,redBarLayParams)

        val greenBar = SeekBar(this.context)

        greenBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                Toast.makeText(context,"Progress: $i",Toast.LENGTH_SHORT).show()
                green = 255*i/100

                val barColor = calculateColor(0xFF,0,green, 0)

                seekBar.getProgressDrawable().setColorFilter(barColor, PorterDuff.Mode.SRC_IN);
                seekBar.getThumb().setColorFilter(barColor, PorterDuff.Mode.SRC_IN);
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Do something
                Toast.makeText(context,"start tracking",Toast.LENGTH_SHORT).show()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                color = calculateColor(0xFF, red, green, blue)
                ButtonMatrix.selectedColor = color
                if(ButtonMatrix.selectedButton != null)
                {
                    ButtonMatrix.setColor(ButtonMatrix.selectedButton!!)
                }
            }
        })
        var greenBarLayParams=  RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        greenBarLayParams.addRule(ALIGN_BOTTOM, redBar.id)
        greenBarLayParams.topMargin = 100
        this.addView(greenBar,greenBarLayParams)

        val blueBar = SeekBar(this.context)

        blueBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                Toast.makeText(context,"Progress: $i",Toast.LENGTH_SHORT).show()
                blue = 255*i/100

                val barColor = calculateColor(0xFF,0,0, blue)

                seekBar.getProgressDrawable().setColorFilter(barColor, PorterDuff.Mode.SRC_IN);
                seekBar.getThumb().setColorFilter(barColor, PorterDuff.Mode.SRC_IN);
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Do something
                Toast.makeText(context,"start tracking",Toast.LENGTH_SHORT).show()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                color = calculateColor(0xFF, red, green, blue)
                ButtonMatrix.selectedColor = color
                if(ButtonMatrix.selectedButton != null)
                {
                    ButtonMatrix.setColor(ButtonMatrix.selectedButton!!)
                }
            }
        })
        var blueBarLayParams=  LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        blueBarLayParams.addRule(ALIGN_BOTTOM, greenBar.id)
        blueBarLayParams.topMargin = 200
        this.addView(blueBar,blueBarLayParams)

    }

    fun calculateColor(alpha:Int, red:Int, green:Int, blue:Int):Int
    {
        return alpha and 0xff shl 24 or (red and 0xff shl 16) or (green and 0xff shl 8) or (blue and 0xff)
    }
}