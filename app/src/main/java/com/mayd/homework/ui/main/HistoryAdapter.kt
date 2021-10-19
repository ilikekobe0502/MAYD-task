package com.mayd.homework.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mayd.homework.R
import com.mayd.homework.model.api.model.response.Shorten
import kotlinx.android.synthetic.main.item_history.view.*


class HistoryAdapter(
    private val funListener: HistoryFunListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data: ArrayList<Shorten> = ArrayList()
    private var latestCopiedPosition: Int = -1

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
            copy_tv.apply {
                if (latestCopiedPosition == position) {
                    background = ContextCompat.getDrawable(context, R.drawable.bg_rectangle_violet)
                    text = context.getString(R.string.main_item_copied)
                } else {
                    background = ContextCompat.getDrawable(context, R.drawable.bg_rectangle_cyan)
                    text = context.getString(R.string.main_item_copy)
                }
            }

            del_iv.setOnClickListener {
                funListener.onDeleteClick(item)
            }
            copy_tv.setOnClickListener {
                funListener.onCopyClick(item)
                latestCopiedPosition = position
                notifyItemChanged(position)
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