package com.thiaagodev.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.thiaagodev.convidados.model.GuestModel
import com.thiaagodev.convidados.repository.GuestRepository
import kotlinx.coroutines.launch

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository(application)
    private val guestLiveData = MutableLiveData<GuestModel?>()
    private val _saveGuest = MutableLiveData<String>()

    val guest: LiveData<GuestModel?> = guestLiveData
    val saveGuest: LiveData<String> = _saveGuest

    fun save(guest: GuestModel) {
        viewModelScope.launch {
            if (guest.id == null) {
                if (repository.insert(guest)) {
                    _saveGuest.value = "Inserção com sucesso"
                } else {
                    _saveGuest.value = "Falha ao salvar convidado"
                }
            } else {
                if (repository.update(guest)) {
                    _saveGuest.value = "Atualização com sucesso"
                } else {
                    _saveGuest.value = "Falha ao salvar convidado"
                }
            }
        }
    }

    fun get(id: Int?) {
        viewModelScope.launch {
            guestLiveData.value = repository.get(id)
        }
    }

}