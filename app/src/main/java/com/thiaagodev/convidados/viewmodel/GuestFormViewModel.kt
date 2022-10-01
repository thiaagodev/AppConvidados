package com.thiaagodev.convidados.viewmodel

import androidx.lifecycle.ViewModel
import com.thiaagodev.convidados.repository.GuestRepository

class GuestFormViewModel: ViewModel() {

    private val repository = GuestRepository.getInstance()

}