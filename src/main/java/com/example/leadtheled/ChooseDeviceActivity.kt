package com.example.leadtheled

import android.Manifest
import android.os.Bundle
import android.app.ListActivity
import android.bluetooth.BluetoothAdapter
import android.content.pm.PackageManager
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.view.View
import android.widget.Toast
import android.bluetooth.BluetoothDevice
import android.content.Intent






class ChooseDeviceActivity : ListActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_device)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH)
            != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
        }
        else
        {
            val DeviceList = ArrayAdapter<BluetoothDevice>(this,
                android.R.layout.simple_list_item_1)

            for (device in BluetoothManager.bluetoothAdapter.bondedDevices) {
                DeviceList.add(device)
            }

            val NameList = ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1)

            for (device in BluetoothManager.bluetoothAdapter.bondedDevices) {
                NameList.add(device.name)
            }
            val lv = listView
            lv.onItemClickListener = object : OnItemClickListener {

                override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    if(DeviceList.getItem(id.toInt()) != null)
                    {
                        val dName = DeviceList.getItem(id.toInt())!!.name;
                        val toast = Toast.makeText(applicationContext, dName, Toast.LENGTH_SHORT)
                        toast.show()
                        BluetoothManager.selectDevice(DeviceList.getItem(id.toInt())!!)
                        //BluetoothManager.openSocket()
                        val intent = Intent(this@ChooseDeviceActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                    else
                    {
                        println("Wrong device selected")
                    }
                }
            }

            listView.setAdapter(NameList)
        }
    }
}
