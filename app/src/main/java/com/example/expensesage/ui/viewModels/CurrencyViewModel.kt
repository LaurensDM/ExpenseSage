package com.example.expensesage.ui.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensesage.data.UserSettings
import com.example.expensesage.network.CurrencyApiExecutor
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

class CurrencyViewModel(
    private val userPref: UserSettings,
    private val currencyApiExecutor: CurrencyApiExecutor,
) : ViewModel() {

    var currencyUIState: CurrencyUIState by mutableStateOf(CurrencyUIState.Loading)
        private set

    private var originalData : List<Map.Entry<String, JsonElement>> by mutableStateOf( emptyList() )
    var list: List<Map.Entry<String, JsonElement>> by mutableStateOf( emptyList() )
        private set

    var queryState by mutableStateOf("")
        private set

    var currency by mutableStateOf("EUR")
        private set


    init {
        Log.d("CurrencyViewModel", "init")
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            currencyUIState = CurrencyUIState.Loading
            currencyUIState = try {
                val data  = currencyApiExecutor.getCurrencyRates()
                currency = userPref.currency.first()
                originalData  = data[currency.lowercase()]?.jsonObject?.entries?.toList() ?: emptyList()
                list = originalData
                CurrencyUIState.Success(
                    data
                )
            } catch (e: Exception) {
                CurrencyUIState.Error(e.localizedMessage ?: "An unknown error occured")
            }
        }
    }

    fun search() {
        list = originalData
        list = list.filter { it.key.contains(queryState.lowercase()) }
    }

    fun updateQuery(searchQuery: String){
        queryState = searchQuery
    }

}

sealed interface CurrencyUIState {
    data object Loading : CurrencyUIState
    data class Success(val data: JsonObject) : CurrencyUIState
    data class Error(val error: String) : CurrencyUIState

}
