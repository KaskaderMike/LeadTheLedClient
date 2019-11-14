package com.example.leadtheled

import androidx.core.view.ViewCompat.getMeasuredState
import android.R.layout
import android.view.View.MeasureSpec
import android.R.attr.x
import android.content.Context
import android.content.Context.WINDOW_SERVICE
import android.graphics.Point
import android.util.AttributeSet
import androidx.core.content.ContextCompat.getSystemService
import android.view.WindowManager
import android.view.Display
import android.view.View
import android.view.ViewGroup
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.*
import androidx.core.view.marginTop
import androidx.core.view.setPadding


class LedMatrixView: TableLayout {
    val cols = 8
    val rows = 8

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs) {
        this.layoutParams =
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        addButtonsView()
    }

    fun addButtonsView()
    {
        val tableParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
        val rowParams = TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT
        )

        for(i in 0 until rows)
        {
            val tableRow = TableRow(context)
            tableRow.setLayoutParams(tableParams)// TableLayout is the parent view

            ButtonMatrix.buttons[i].forEach {
                rowParams.setMargins(5,5,5,5)
                tableRow.addView(it, rowParams)
            }

            this.addView(tableRow)
        }
    }

}