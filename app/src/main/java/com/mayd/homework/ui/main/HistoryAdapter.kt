package com.mayd.homework.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mayd.homework.R
import com.mayd.homework.model.api.model.response.Shorten
import kotlinx.android.synthetic.main.item_history.view.*


class HistoryAdapter(private val funListener: HistoryFunListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data: ArrayList<Shorten> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_history, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]


        holder.itemView.apply {
            original_url_tv.text = item.originalLink
            shorten_url_tv.text = item.fullShortLink

            del_iv.setOnClickListener {
                funListener.onDeleteClick(item)
            }
            copy_tv.setOnClickListener {
                funListener.onCopyClick(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    fun updateData(data: List<Shorten>) {
        this.data.apply {
            clear()
            addAll(data)
        }

        notifyDataSetChanged()
    }
}