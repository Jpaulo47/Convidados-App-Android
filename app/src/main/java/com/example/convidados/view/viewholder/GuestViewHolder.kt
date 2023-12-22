package com.example.convidados.view.viewholder

import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.databinding.RowguestBinding
import com.example.convidados.model.GuestModel
import com.example.convidados.view.listener.OnGuestListener

class GuestViewHolder(private val bind: RowguestBinding, private val listener: OnGuestListener) :
    RecyclerView.ViewHolder(bind.root) {

    fun bind(guest: GuestModel) {
        bind.textName.text = guest.name

        bind.textName.setOnClickListener {
            listener.onClick(guest.id)
        }

        bind.textName.setOnLongClickListener() {
            AlertDialog.Builder(bind.root.context)
                .setTitle("Remoção de convidado")
                .setMessage("Deseja remover o convidado?")
                .setPositiveButton("Remover") { dialog, which ->
                    listener.onDelete(guest.id)
                }
                .setNegativeButton("Cancelar", null)
                .show()
            true
        }
    }
}