package com.thiaagodev.convidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.thiaagodev.convidados.constants.DataBaseConstants
import com.thiaagodev.convidados.databinding.FragmentAbsentBinding
import com.thiaagodev.convidados.view.adapter.GuestsAdapter
import com.thiaagodev.convidados.view.listener.OnGuestListener
import com.thiaagodev.convidados.viewmodel.GuestsViewModel

class AbsentFragment : Fragment() {

    private lateinit var viewModel: GuestsViewModel
    private var _binding: FragmentAbsentBinding? = null
    private val binding get() = _binding!!
    private val adapter = GuestsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        viewModel = ViewModelProvider(this)[GuestsViewModel::class.java]

        _binding = FragmentAbsentBinding.inflate(inflater, container, false)

        binding.recyclerGuests.layoutManager = LinearLayoutManager(context)
        binding.recyclerGuests.adapter = adapter

        val listener = object: OnGuestListener {
            override fun onClick(id: Int?) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.Guest.ID, id ?: 0)

                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int?) {
                viewModel.delete(id)
                viewModel.getAbsent()
            }
        }

        adapter.attachListener(listener)

        observe()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()

        viewModel.getAbsent()
    }

    private fun observe() {
        viewModel.guests.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)
        }
    }


}