package com.thiaagodev.convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.thiaagodev.convidados.model.GuestModel
import com.thiaagodev.convidados.R
import com.thiaagodev.convidados.constants.DataBaseConstants
import com.thiaagodev.convidados.databinding.ActivityGuestFormBinding
import com.thiaagodev.convidados.viewmodel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel
    private var guestId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[GuestFormViewModel::class.java]

        setListeners()

        binding.radioPresent.isChecked = true

        observe()
        loadData()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_save -> {
                val name = binding.editName.text.toString()
                val presence = binding.radioPresent.isChecked

                val guest = GuestModel(guestId, name, presence)
                viewModel.save(guest)

                finish()
            }
        }
    }

    private fun setListeners() {
        binding.buttonSave.setOnClickListener(this)
    }

    private fun observe() {
        viewModel.guest.observe(this) {
            binding.editName.setText(it?.name)

            if (it?.presence == true) {
                binding.radioPresent.isChecked = true
            } else {
                binding.radioAbsent.isChecked = true
            }
        }
    }


    private fun loadData() {
        val bundle = intent.extras

        guestId = bundle?.getInt(DataBaseConstants.Guest.ID)

        guestId?.let {
            viewModel.get(guestId)
        }
    }
}