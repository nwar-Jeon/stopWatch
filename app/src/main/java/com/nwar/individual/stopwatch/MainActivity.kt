package com.nwar.individual.stopwatch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var displayTime : TextView
    var isRunning = false
    var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayTime = findViewById<TextView>(R.id.displaytime)
        val start = findViewById<TextView>(R.id.start)
        val reset = findViewById<TextView>(R.id.reset)

        start.setOnClickListener {
            if(!isRunning) start()
            reset.text = "중지"
            start.text = "시작"
        }

        reset.setOnClickListener {
            if(isRunning) {
                isRunning = false
                reset.text = ("초기화")
                start.text = ("재시작")
            } else {
                displayTime.text = "00:00:00"
                count = 0
                start.text = "시쟉"
            }
        }
    }

    fun start(){
        isRunning = true

        val thread = object : Thread(){
            override fun run(){
                while(isRunning){
                    count++
                    handler.sendEmptyMessage(count)
                    Thread.sleep(10)
                }
            }
        }
        thread.start()
    }
    val handler : Handler = object : Handler(){
        override fun handleMessage(msg : Message){
            val format = TimeFormat(msg.what)
            displayTime.text = format.format()
        }
    }
}
