package com.example.leadtheled

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import java.util.*

object BluetoothManager  {
    private val MY_UUID = UUID.fromString("0000110E-0000-1000-8000-00805F9B34FB")

    var socket: BluetoothSocket? = null
    var fallbackSocket: BluetoothSocket? = null

    val bluetoothAdapter: BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    var selectedDevice: BluetoothDevice? = null

    fun selectDevice(device: BluetoothDevice)
    {
        selectedDevice = device
    }

    fun openSocket()
    {
        socket = selectedDevice?.createInsecureRfcommSocketToServiceRecord(MY_UUID)
    }

    fun closeSocket()
    {
        if(fallbackSocket != null)
        {
            fallbackSocket!!.close()
        }

        if(socket != null)
        {
            socket!!.close()
        }
    }

    fun startSendDataThread(frame: DataFrame)
    {
        Thread(){
            sendData(frame)
        }.start()
    }

    @Synchronized fun sendData(frame: DataFrame)
    {

        try {
            var remoteDeviceClass = socket!!.remoteDevice.javaClass
            var paramTypes = arrayOf<Class<*>>(Integer.TYPE)
            var m = remoteDeviceClass.getMethod("createRfcommSocket", *paramTypes)
            fallbackSocket = m.invoke(socket!!.remoteDevice, Integer.valueOf(1)) as BluetoothSocket
            fallbackSocket!!.connect()

            var ostream = fallbackSocket!!.outputStream
            var istream = fallbackSocket!!.inputStream

            ostream.write(frame.content)
            Thread.sleep(200)

            istream.close()
            ostream.close()
            fallbackSocket!!.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}