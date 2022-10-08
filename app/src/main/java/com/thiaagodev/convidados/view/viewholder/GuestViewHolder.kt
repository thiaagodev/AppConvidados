package com.thiaagodev.convidados.view.viewholder

import android.content.DialogInterface
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.thiaagodev.convidados.databinding.RowGuestBinding
import com.thiaagodev.convidados.model.GuestModel
import com.thiaagodev.convidados.view.listener.OnGuestListener

class GuestViewHolder(private val binding: RowGuestBinding, private val listener: OnGuestListener) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(guest: GuestModel) {
        binding.textName.text = guest.name
        binding.checkboxPresence.isChecked = guest.presence

        binding.textName.setOnClickListener {
            listener.onClick(guest.id)
        }

        binding.textName.setOnLongClickListener(View.OnLongClickListener {

            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de convidado")
                .setMessage("Tem certeza que deseja remover o convidado ${guest.name}?")
                .setPositiveButton(
                    "Sim"
                ) { dialog, which -> listener.onDelete(guest.id)}
                .setNegativeButton("Não", null)
                .create()
                .show()

            true
        })

    }
}