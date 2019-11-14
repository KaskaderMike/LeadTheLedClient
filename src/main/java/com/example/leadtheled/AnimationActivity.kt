package com.example.leadtheled

import android.graphics.Color
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class AnimationActivity: AppCompatActivity() {
    val framesToSend = ArrayList<DataFrame>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButtonMatrix.initialize(this, false)

        setContentView(R.layout.activity_animation)
        BluetoothManager.openSocket()
        val frame:DataFrame = DataFrame(ButtonMatrix.createButtonMatrixData(), FrameType.SINGLE_FRAME)
        BluetoothManager.startSendDataThread(frame)
        var buttonColor = Color.rgb(255, 255, 204)


        val nextBtn = findViewById<Button>(R.id.new_matrix)
        nextBtn.setBackgroundColor(buttonColor)
        nextBtn.setOnClickListener()
        {
            val frame = DataFrame(ButtonMatrix.createButtonMatrixData(), FrameType.PACKET_FRAME)
            framesToSend.add(frame)
        }

        val startBtn = findViewById<Button>(R.id.start)
        startBtn.setBackgroundColor(buttonColor)
        startBtn.setOnClickListener()
        {
            val frame = DataFrame(FrameType.HEADER_FRAME)
            BluetoothManager.sendData(frame)
            Thread.sleep(200)
            for(f in framesToSend)
            {
                BluetoothManager.sendData(f)
                Thread.sleep(200)
            }
            Thread.sleep(200)
            BluetoothManager.sendData(frame)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        BluetoothManager.closeSocket()
    }
}
