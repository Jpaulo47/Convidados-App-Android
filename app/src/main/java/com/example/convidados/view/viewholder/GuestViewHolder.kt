package com.example.convidados.view.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.databinding.RowguestBinding

class GuestViewHolder(private val bind: RowguestBinding): RecyclerView.ViewHolder(bind.root) {

    fun bind(name: String) {
        bind.textName.text = name
    }
}