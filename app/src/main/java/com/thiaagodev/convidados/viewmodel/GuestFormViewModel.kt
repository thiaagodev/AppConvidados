package com.thiaagodev.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.thiaagodev.convidados.model.GuestModel
import com.thiaagodev.convidados.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application)
    private val guestLiveData = MutableLiveData<GuestModel?>()

    val guest: LiveData<GuestModel?> = guestLiveData

    fun insert(guest: GuestModel) {
        repository.insert(guest)
    }

    fun get(id: Int) {
        val guest: GuestModel? = repository.get(id)
    }

}