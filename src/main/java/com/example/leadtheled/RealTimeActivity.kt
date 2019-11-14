package com.example.leadtheled

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class RealTimeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButtonMatrix.initialize(this, true)

        setContentView(R.layout.activity_real_time)
        BluetoothManager.openSocket()
        val frame:DataFrame = DataFrame(ButtonMatrix.createButtonMatrixData(), FrameType.SINGLE_FRAME)
        BluetoothManager.startSendDataThread(frame)
    }

    override fun onDestroy() {
        super.onDestroy()
        BluetoothManager.closeSocket()
    }
}
