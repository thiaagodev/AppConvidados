package com.thiaagodev.convidados.view.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.thiaagodev.convidados.databinding.RowGuestBinding
import com.thiaagodev.convidados.model.GuestModel
import com.thiaagodev.convidados.view.listener.OnGuestListener

class GuestViewHolder(private val binding: RowGuestBinding, private val listener: OnGuestListener) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(guest: GuestModel) {
        binding.textName.text = guest.name
        binding.checkboxPresence.isChecked = guest.presence

        binding.textName.setOnClickListener{
            listener.onClick(guest.id)
        }

        binding.textName.setOnLongClickListener(View.OnLongClickListener {
            listener.onDelete(guest.id)
            true
        })

    }
}