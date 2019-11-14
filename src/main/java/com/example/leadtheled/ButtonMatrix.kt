package com.example.leadtheled

import android.content.Context
import android.graphics.Point
import android.provider.ContactsContract

object ButtonMatrix{
    var cols = 8
    var rows = 8
    var buttons: Array<Array<LedButton>> = arrayOf()
    var selectedButton:Point? = null
    var realTimeChanges = false
    var selectedColor:Int = 0


    fun initialize(context: Context, realTimeChanges: Boolean){
        this.buttons = Array(cols, {i -> Array<LedButton>(rows, { j -> LedButton(context, i, j)})})
        this.realTimeChanges = realTimeChanges
    }

    fun getColor(position:Point):Int{
        return buttons[position.x][position.y].color
    }

    fun setColor(position:Point){
        buttons[position.x][position.y].color = this.selectedColor
        if(realTimeChanges)
        {
            val frame:DataFrame = DataFrame(createButtonMatrixData(), FrameType.SINGLE_FRAME)
            BluetoothManager.startSendDataThread(frame)
        }
        buttons[position.x][position.y].invalidate()
    }

    fun createButtonMatrixData():ByteArray{
        val data = ByteArray(cols*rows*4)
        for(i in 0 until cols){
            for(j in 0 until rows){
                var color:Int = buttons[i][j].color
                val index = i*8*4 + j*4
                data[index] = color.toByte()
                color = color shr 8
                data[index + 2] = color.toByte()
                color = color shr 8
                data[index + 1] = color.toByte()//WHY???
                data[index + 3] = 0x00
            }
        }

        return data
    }
}
