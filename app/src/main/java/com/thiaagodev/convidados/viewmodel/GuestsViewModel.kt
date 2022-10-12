package com.thiaagodev.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thiaagodev.convidados.model.GuestModel
import com.thiaagodev.convidados.repository.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GuestRepository(application)

    private val listAllGuests = MutableLiveData<List<GuestModel>>()

    val guests: LiveData<List<GuestModel>> = listAllGuests


    fun getAll() {
        listAllGuests.value = repository.getAll()
    }

    fun delete(id: Int?) {
        repository.delete(id)
    }

    fun getPresent() {
        listAllGuests.value = repository.getByPresence(1)
    }

    fun getAbsent() {
        listAllGuests.value = repository.getByPresence(0)
    }

}