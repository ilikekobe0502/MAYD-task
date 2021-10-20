package com.mayd.homework.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mayd.homework.R
import com.mayd.homework.model.api.model.response.Shorten
import kotlinx.android.synthetic.main.item_history.view.*


class HistoryAdapter(
    private val funListener: HistoryFunListener
) : ListAdapter<Shorten, RecyclerView.ViewHolder>(DiffCallback()) {
    private var latestCopiedCode: String = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)

        holder.itemView.apply {
            original_url_tv.text = item.originalLink
            shorten_url_tv.text = item.fullShortLink
            copy_tv.apply {
                if (latestCopiedCode == item.code) {
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
                latestCopiedCode = item.code
                funListener.onCopyClick(item)
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    private class DiffCallback : DiffUtil.ItemCallback<Shorten>() {
        override fun areItemsTheSame(oldItem: Shorten, newItem: Shorten): Boolean {
            return oldItem.code == newItem.code
        }

        override fun areContentsTheSame(oldItem: Shorten, newItem: Shorten): Boolean {
            return oldItem == newItem
        }
    }
}