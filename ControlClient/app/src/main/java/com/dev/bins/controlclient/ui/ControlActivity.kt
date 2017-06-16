package com.dev.bins.controlclient.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dev.bins.controlclient.App
import com.dev.bins.controlclient.R
import org.jetbrains.anko.doAsync
import java.io.BufferedOutputStream
import java.io.OutputStream

class ControlActivity : AppCompatActivity() {

    var os: OutputStream? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control)
        doAsync {
            os = App.socket!!.getOutputStream()
        }
    }


    fun up(view:View) {
        doAsync {
            os!!.write("1".toByteArray())
            os!!.flush()
        }
    }

    fun down(view: View) {
        doAsync {
            os!!.write("2".toByteArray())
            os!!.flush()

        }
    }

    fun left(view: View) {
        doAsync {
            os!!.write("3".toByteArray())
            os!!.flush()

        }
    }

    fun right(view: View) {
        doAsync {
            os!!.write("4".toByteArray())
            os!!.flush()

        }
    }

    fun stop(view: View) {
        doAsync {
            os!!.write("5".toByteArray())
            os!!.flush()

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        doAsync {
            os!!.write("6".toByteArray())
            os!!.flush()
        }
        os!!.close()
        App.socket!!.close()
        App.socket = null
    }

}
