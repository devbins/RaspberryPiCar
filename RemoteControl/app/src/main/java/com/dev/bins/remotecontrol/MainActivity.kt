package com.dev.bins.remotecontrol

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import java.io.BufferedWriter
import java.io.IOException
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.net.Socket


/**
 * Created by bin on 13/06/2017.
 */

class MainActivity : AppCompatActivity() {


    val GO = "1"
    val BACK = "2"
    val LEFT = "3"
    val RIGHT = "4"
    val PASUSE = "5"
    val STOP = "6"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Thread(Runnable {
            try {
                val socket = Socket("192.168.0.103", 8888)
                val os = socket.getOutputStream()
                val bw = BufferedWriter(OutputStreamWriter(os))
                bw.write("hi i'm connect you !\n")
                bw.flush()
                bw.close()
                socket.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }).start()
    }
}
