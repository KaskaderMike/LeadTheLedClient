package com.example.leadtheled

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val menuHeader = findViewById<TextView>(R.id.menu_header)
        val chooseDeviceBtn = findViewById<Button>(R.id.choose_device_button)
        val realTimeControlBtn = findViewById<Button>(R.id.real_time_control_button)
        val animationBtn = findViewById<Button>(R.id.animation_button)
        val textBtn = findViewById<Button>(R.id.text_button)
        var buttonColor = Color.rgb(255, 255, 204)

        chooseDeviceBtn.setBackgroundColor(buttonColor)

        if(BluetoothManager.selectedDevice == null)
        {
            menuHeader.setTextColor(Color.WHITE)
            menuHeader.setText("You have to choose LtL device to start")
            realTimeControlBtn.setBackgroundColor(Color.GRAY)
            realTimeControlBtn.isClickable = false
            realTimeControlBtn.isEnabled = false
            animationBtn.setBackgroundColor(Color.GRAY)
            animationBtn.isClickable = false
            animationBtn.isEnabled = false
            textBtn.setBackgroundColor(Color.GRAY)
            textBtn.isClickable = false
            textBtn.isEnabled = false
        }
        else
        {
            menuHeader.setTextColor(Color.WHITE)
            menuHeader.setText("Selected device: " + BluetoothManager!!.selectedDevice?.name + "(" + BluetoothManager!!.selectedDevice?.address + ")")
            realTimeControlBtn.setBackgroundColor(buttonColor)
            realTimeControlBtn.isClickable = true
            realTimeControlBtn.isEnabled = true
            animationBtn.setBackgroundColor(buttonColor)
            animationBtn.isClickable = true
            animationBtn.isEnabled = true
            textBtn.setBackgroundColor(buttonColor)
            textBtn.isClickable = true
            textBtn.isEnabled = true
        }


        chooseDeviceBtn.setOnClickListener()
        {
            val intent = Intent(this@MainActivity, ChooseDeviceActivity::class.java)
            startActivity(intent)
        }

        realTimeControlBtn.setOnClickListener()
        {
            val intent = Intent(this@MainActivity, RealTimeActivity::class.java)
            startActivity(intent)
        }

        animationBtn.setOnClickListener()
        {
            val intent = Intent(this@MainActivity, AnimationActivity::class.java)
            startActivity(intent)
        }


    }
}
