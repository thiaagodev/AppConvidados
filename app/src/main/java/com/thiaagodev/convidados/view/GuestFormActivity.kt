package com.thiaagodev.convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.thiaagodev.convidados.model.GuestModel
import com.thiaagodev.convidados.R
import com.thiaagodev.convidados.constants.DataBaseConstants
import com.thiaagodev.convidados.databinding.ActivityGuestFormBinding
import com.thiaagodev.convidados.viewmodel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[GuestFormViewModel::class.java]

        setListeners()

        binding.radioPresent.isChecked = true

        loadData()

    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_save -> {
                val name = binding.editName.text.toString()
                val presence = binding.radioPresent.isChecked

                val guest = GuestModel(null, name, presence)
                viewModel.insert(guest)

                finish()
            }
        }
    }

    private fun setListeners() {
        binding.buttonSave.setOnClickListener(this)
    }

    private fun loadData() {
        val bundle = intent.extras

        val guestId = bundle?.getInt(DataBaseConstants.Guest.ID)

        guestId?.let {
            viewModel.get(guestId)
        }
    }
}