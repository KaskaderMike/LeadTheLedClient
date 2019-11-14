package com.example.leadtheled

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ShapeDrawable
import android.util.AttributeSet
import android.widget.Button
import androidx.core.graphics.alpha
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.view.marginTop
import androidx.core.view.setPadding
import android.R.attr.button
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.Drawable
import android.R
import android.graphics.Paint
import android.graphics.drawable.shapes.RectShape
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.graphics.drawable.GradientDrawable
import android.icu.lang.UCharacter.GraphemeClusterBreak.T






class LedButton: Button{
    var color = 0
    var buttonSize = 110
    var position: Point = Point()

    constructor(context: Context, col: Int, row: Int) : this(context, null, col, row)
    {
    }
    constructor(context: Context, attrs: AttributeSet?, col: Int, row: Int) : this(context, attrs, 0, col, row)
    {
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, col: Int, row: Int) : super(context, attrs, defStyleAttr)
    {
        this.height = buttonSize
        this.width = buttonSize
        this.isClickable = true
        position.set(col, row)

        this.setOnClickListener(OnClickListener {
            ButtonMatrix.selectedButton = Point(this.position.x, this.position.y)
            ButtonMatrix.setColor(this.position)
        })
    }


    override fun onDraw(canvas: Canvas) {
        if(this.color == 0)
        {
            this.setBackgroundColor(Color.rgb(242, 242, 242))
        }
        else
        {
            this.setBackgroundColor(color)
        }

        if(this.position.equals(ButtonMatrix.selectedButton))
        {
            val drawable = GradientDrawable()
            drawable.shape = GradientDrawable.RECTANGLE
            drawable.setStroke(5, Color.CYAN)
            if(this.color == 0)
            {
                drawable.setColor(Color.rgb(242, 242, 242))
            }
            else
            {
                drawable.setColor(this.color)
            }


            this.setBackgroundDrawable(drawable)
        }
        //super.onDraw(canvas)
    }
}