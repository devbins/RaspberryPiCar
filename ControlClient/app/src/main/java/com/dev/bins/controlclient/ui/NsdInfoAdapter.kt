package com.dev.bins.controlclient.ui

import android.net.nsd.NsdServiceInfo
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dev.bins.controlclient.R
import com.dev.bins.controlclient.ui.NsdInfoAdapter.Holder
import org.jetbrains.anko.find

/**
 * Created by bin on 14/06/2017.
 */

class NsdInfoAdapter(data: List<NsdServiceInfo>) : RecyclerView.Adapter<Holder>() {


    var data: List<NsdServiceInfo>
    var onClickListener: ItemOnClick? = null

    init {
        this.data = data
    }


    override fun onBindViewHolder(holder: Holder?, position: Int) {
        holder!!.nsdName.text = data.get(position).serviceName
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent!!.context).inflate(R.layout.nsd_item, parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }


    class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        var nsdName: TextView

        init {
            nsdName = itemView!!.find(R.id.tv_nsd)
        }

    }

    interface ItemOnClick {
        fun onclik(position: Int)
    }

}