package com.thiaagodev.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.thiaagodev.convidados.model.GuestModel
import com.thiaagodev.convidados.repository.GuestRepository
import kotlinx.coroutines.launch

class GuestsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GuestRepository(application)

    private val listAllGuests = MutableLiveData<List<GuestModel>>()

    val guests: LiveData<List<GuestModel>> = listAllGuests


    fun getAll() {
        viewModelScope.launch {
            listAllGuests.value = repository.getAll()
        }
    }

    fun delete(id: Int?) {
        viewModelScope.launch {
            repository.delete(id)
            getAll()
        }
    }

    fun getPresent() {
        viewModelScope.launch {
            listAllGuests.value = repository.getByPresence(1)
        }
    }

    fun getAbsent() {
        viewModelScope.launch {
            listAllGuests.value = repository.getByPresence(0)
        }
    }

}