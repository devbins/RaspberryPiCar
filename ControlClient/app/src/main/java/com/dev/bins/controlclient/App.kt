package com.dev.bins.controlclient

import android.app.Application
import java.net.Socket

/**
 * Created by bin on 13/06/2017.
 */

class App :Application(){



    companion object static {
        val GO = "1"
        val BACK = "2"
        val LEFT = "3"
        val RIGHT = "4"
        val PASUSE = "5"
        val STOP = "6"

        @JvmField
        var socket: Socket? = null

    }



}