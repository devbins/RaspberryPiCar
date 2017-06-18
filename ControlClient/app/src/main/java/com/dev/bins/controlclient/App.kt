package com.dev.bins.controlclient

import android.app.Application
import java.io.OutputStream
import java.net.Socket

/**
 * Created by bin on 13/06/2017.
 */

class App :Application(){



    companion object static {


        @JvmField
        var socket: Socket? = null
    }



}