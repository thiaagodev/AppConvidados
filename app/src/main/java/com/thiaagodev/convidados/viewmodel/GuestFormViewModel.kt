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

    fun save(guest: GuestModel) {
        if(guest.id == null) {
            repository.insert(guest)
        } else {
            repository.update(guest)
        }
    }

    fun get(id: Int?) {
        guestLiveData.value = repository.get(id)
    }

}