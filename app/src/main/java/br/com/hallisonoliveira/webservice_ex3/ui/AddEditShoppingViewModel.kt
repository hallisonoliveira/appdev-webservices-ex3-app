package br.com.hallisonoliveira.webservice_ex3.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hallisonoliveira.webservice_ex3.interactor.GetShoppingInteractor
import br.com.hallisonoliveira.webservice_ex3.interactor.SaveShoppingInteractor
import br.com.hallisonoliveira.webservice_ex3.model.domain.Shopping
import kotlinx.coroutines.launch
import java.lang.Exception

class AddEditShoppingViewModel (
    private val getShoppingInteractor: GetShoppingInteractor,
    private val saveShoppingInteractor: SaveShoppingInteractor
) : ViewModel() {

    val shoppingLiveData = MutableLiveData<Shopping>()

    val saveShoppingStatusLiveData = MutableLiveData<SaveRequestStatus>()

    fun getShopping(id: String) {
        viewModelScope.launch {
            shoppingLiveData.postValue(getShoppingInteractor.get(id))
        }
    }

    fun saveShopping(shopping: Shopping) {
        viewModelScope.launch {
            try {
                saveShoppingInteractor.save(shopping)
                saveShoppingStatusLiveData.postValue(SaveRequestStatus.Success)
            } catch (e: Exception) {
                Log.e("AddShoppingViewModel", e.message)
                saveShoppingStatusLiveData.postValue(SaveRequestStatus.Error)
            }

        }
    }

}

sealed class SaveRequestStatus {
    object Success : SaveRequestStatus()
    object Error : SaveRequestStatus()
}