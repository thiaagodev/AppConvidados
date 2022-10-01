package com.thiaagodev.convidados

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.thiaagodev.convidados.databinding.ActivityGuestFormBinding

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
    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.button_save -> {
                TODO("")
            }
        }
    }

    private fun setListeners() {
        binding.buttonSave.setOnClickListener(this)
    }
}