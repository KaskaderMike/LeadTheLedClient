package com.example.leadtheled

import kotlin.experimental.or

class DataFrame {
    val frameSize = 256
    var type:FrameType = FrameType.UNSPECIFIED
    var content:ByteArray = ByteArray(frameSize)

    constructor()
    {
    }

    constructor(type:FrameType)
    {
        this.setFrameType(type)
    }

    constructor(data:ByteArray, type:FrameType)
    {
        if(data.size != frameSize)
        {
            print("Error, color matrix size and frame size missmatch")
        }
        else
        {
            data.copyInto(this.content)
        }

        this.setFrameType(type)
    }

    fun setFrameType(type:FrameType)
    {
        this.type = type

        if(type == FrameType.HEADER_FRAME)
        {
            this.content[0] = 0xFF.toByte()
            this.content[1] = 0xFF.toByte()
            this.content[2] = 0xFF.toByte()
            this.content[3] = 0xFF.toByte()
        }/*
        else if(type == FrameType.PACKET_FRAME)
        {
            for(i in 0 until this.frameSize)
            {
                this.content[i] = this.content[i] or 0x80.toByte()
            }
        }
         */
    }


}

enum class FrameType {
    SINGLE_FRAME, HEADER_FRAME, PACKET_FRAME, UNSPECIFIED
}