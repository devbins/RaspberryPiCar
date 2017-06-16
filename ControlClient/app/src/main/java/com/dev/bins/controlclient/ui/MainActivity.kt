package com.dev.bins.controlclient.ui

import android.content.Intent
import android.net.nsd.NsdManager
import android.net.nsd.NsdServiceInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.dev.bins.controlclient.App
import com.dev.bins.controlclient.R
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.find
import org.jetbrains.anko.nsdManager
import java.net.Socket
import java.util.*

/**
 * Created by bin on 13/06/2017.
 */

class MainActivity : AppCompatActivity(), NsdInfoAdapter.ItemOnClick {


    val SERVICE_TYPE = "_http._tcp."
    var nsdmanager: NsdManager? = null
    var recyclerView: RecyclerView? = null
    var mResolverListener: NsdManager.ResolveListener? = null
    var mDiscoveryListener: NsdManager.DiscoveryListener? = null
    var mNsdServiceInfo: NsdServiceInfo? = null
    var adapter: NsdInfoAdapter? = null
    var data: ArrayList<NsdServiceInfo>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        init()
    }

    fun init() {
//        recyclerView = find(R.id.recycler)
        data = ArrayList()
        adapter = NsdInfoAdapter(data!!)
        adapter!!.onClickListener = this
        nsdmanager = baseContext.nsdManager
        createDiscoverListener()
        createResolverListener()
        nsdmanager!!.discoverServices(SERVICE_TYPE, NsdManager.PROTOCOL_DNS_SD, mDiscoveryListener)

    }

    private fun createResolverListener() {
        mResolverListener = object : NsdManager.ResolveListener {
            override fun onResolveFailed(nsdServiceInfo: NsdServiceInfo, i: Int) {
                Toast.makeText(this@MainActivity, "onResolveFailed", Toast.LENGTH_SHORT).show()
            }

            override fun onServiceResolved(nsdServiceInfo: NsdServiceInfo) {
                mNsdServiceInfo = nsdServiceInfo
                bg {
                    App.socket = Socket(mNsdServiceInfo!!.host.hostAddress, mNsdServiceInfo!!.port)
                }
                startActivity(Intent(this@MainActivity, ControlActivity::class.java))
            }
        }
    }


    private fun createDiscoverListener() {
        mDiscoveryListener = object : NsdManager.DiscoveryListener {
            override fun onStartDiscoveryFailed(s: String, i: Int) {
                Toast.makeText(this@MainActivity, "onStartDiscoveryFailed", Toast.LENGTH_SHORT).show()
            }

            override fun onStopDiscoveryFailed(s: String, i: Int) {
                Toast.makeText(this@MainActivity, "onStopDiscoveryFailed", Toast.LENGTH_SHORT).show()
            }

            override fun onDiscoveryStarted(s: String) {
                Toast.makeText(this@MainActivity, "onDiscoveryStarted", Toast.LENGTH_SHORT).show()
            }

            override fun onDiscoveryStopped(s: String) {
                Toast.makeText(this@MainActivity, "onDiscoveryStopped", Toast.LENGTH_SHORT).show()
            }

            override fun onServiceFound(nsdServiceInfo: NsdServiceInfo) {
                //这里的nsdServiceInfo只能获取到名字,ip和端口都不能获取到,要想获取到需要调用NsdManager.resolveService方法
                data!!.add(nsdServiceInfo)
                adapter!!.notifyItemInserted(data!!.size - 1)
            }

            override fun onServiceLost(nsdServiceInfo: NsdServiceInfo) {
                Toast.makeText(this@MainActivity, "onServiceLost", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onclik(position: Int) {
        nsdmanager!!.resolveService(data!![position], mResolverListener)
    }


    override fun onDestroy() {
        super.onDestroy()
        nsdmanager!!.stopServiceDiscovery(mDiscoveryListener)
    }

}


