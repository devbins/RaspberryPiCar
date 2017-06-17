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
            os!!.write("3".toByteArray())
            os!!.flush()
        }
    }

    override fun right() {
        doAsync {
            os!!.write("4".toByteArray())
            os!!.flush()

        }
    }

    override fun up() {
        doAsync {
            os!!.write("1".toByteArray())
            os!!.flush()
        }
    }

    override fun down() {
        doAsync {
            os!!.write("2".toByteArray())
            os!!.flush()

        }
    }

    override fun pause() {
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
