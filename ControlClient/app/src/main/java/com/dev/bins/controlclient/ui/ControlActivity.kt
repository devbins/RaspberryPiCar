package com.dev.bins.controlclient.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dev.bins.controlclient.App
import com.dev.bins.controlclient.R
import com.dev.bins.controlclient.widget.ControlView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import java.io.OutputStream

class ControlActivity : AppCompatActivity(), ControlView.OnDirectionChangeListener {


    var os: OutputStream? = null
    var controlView: ControlView? = null

    companion object static {
        val GO = "1".toByteArray()
        val BACK = "2".toByteArray()
        val LEFT = "3".toByteArray()
        val RIGHT = "4".toByteArray()
        val PAUSE = "5".toByteArray()
        val STOP = "6".toByteArray()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control)
        controlView = find(R.id.controlView)
        controlView!!.directionChange = this
        doAsync {
            os = App.socket!!.getOutputStream()
        }
    }

    override fun left() {
        doAsync {
            os!!.write(LEFT)
            os!!.flush()
        }
    }

    override fun right() {
        doAsync {
            os!!.write(RIGHT)
            os!!.flush()

        }
    }

    override fun up() {
        doAsync {
            os!!.write(GO)
            os!!.flush()
        }
    }

    override fun down() {
        doAsync {
            os!!.write(BACK)
            os!!.flush()

        }
    }

    override fun pause() {
        doAsync {
            os!!.write(PAUSE)
            os!!.flush()

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        doAsync {
            os!!.write(STOP)
            os!!.flush()
        }
        os!!.close()
        App.socket!!.close()
        App.socket = null
    }

}
