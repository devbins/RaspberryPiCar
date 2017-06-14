package com.dev.bins.remotecontrol.ui

import android.os.Bundle
import android.os.HandlerThread
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import com.dev.bins.remotecontrol.App
import com.dev.bins.remotecontrol.R

import java.io.BufferedWriter
import java.io.IOException
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.net.Socket


/**
 * Created by bin on 13/06/2017.
 */

class MainActivity : AppCompatActivity() {

    var et_ip: EditText? = null
    var et_port: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        et_ip = findViewById(R.id.et_ip) as EditText
        et_port = findViewById(R.id.et_port) as EditText
        findViewById(R.id.btn_conn).setOnClickListener {
            var ip = et_ip?.text.toString()
            var port = et_port?.text.toString()
            if (TextUtils.isEmpty(ip)) {
                Toast.makeText(this, "ip is not empty!!!", Toast.LENGTH_SHORT)
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(port)) {
                Toast.makeText(this, "port is not empty!!!", Toast.LENGTH_SHORT)
                return@setOnClickListener
            }
        }

    }

}


