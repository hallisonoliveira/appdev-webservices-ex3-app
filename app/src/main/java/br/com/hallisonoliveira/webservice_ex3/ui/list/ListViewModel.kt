package br.com.hallisonoliveira.webservice_ex3.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hallisonoliveira.webservice_ex3.interactor.DeleteShoppingInteractor
import br.com.hallisonoliveira.webservice_ex3.interactor.ListShoppingInteractor
import br.com.hallisonoliveira.webservice_ex3.model.domain.Shopping
import kotlinx.coroutines.launch

class ListViewModel(
    private val listShoppingInteractor: ListShoppingInteractor,
    private val deleteShoppingInteractor: DeleteShoppingInteractor
) : ViewModel() {

    val listLiveData = MutableLiveData<List<Shopping>>()

    fun list() {
        viewModelScope.launch {
            listLiveData.postValue(
                listShoppingInteractor.list()
            )
        }
    }

    fun remove(id: String) {
        viewModelScope.launch {
            deleteShoppingInteractor.execute(id)
            list()
        }
    }

}