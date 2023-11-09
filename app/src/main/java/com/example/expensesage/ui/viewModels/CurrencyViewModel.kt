package com.example.expensesage.ui.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensesage.data.UserSettings
import com.example.expensesage.data.currencies.Currency
import com.example.expensesage.data.currencies.CurrencyRepository
import com.example.expensesage.network.CurrencyApiExecutor
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.time.LocalDate

class CurrencyViewModel(
    private val userPref: UserSettings,
    private val currencyApiExecutor: CurrencyApiExecutor,
    private val currencyRepository: CurrencyRepository,
) : ViewModel() {

    var currencyUIState: CurrencyUIState by mutableStateOf(CurrencyUIState.Loading)
        private set

    private var originalData : List<Currency> by mutableStateOf( emptyList() )
    var list: List<Currency> by mutableStateOf( emptyList() )
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
                currency = userPref.currency.first()
                var date: String
                if (currencyRepository.getAllCurrencies().first().isEmpty()){
                    val data  = currencyApiExecutor.getCurrencyRates()
                    originalData  = data[currency.lowercase()]?.jsonObject?.entries?.toList()?.map {
                        Currency(
                            currencyCode = it.key,
                            date = data["date"]?.jsonObject?.entries?.first()?.value?.jsonPrimitive?.content ?: "",
                            rate = it.value.jsonPrimitive.content.toDouble(),
                            comparedCurrency = currency.lowercase()
                        )
                    } ?: emptyList()
                    list = originalData
                    date = data["date"]?.jsonPrimitive?.content ?: LocalDate.now().toString()
                } else {
                    originalData = currencyRepository.getAllCurrencies().first()
                    list = originalData
                    date = originalData.first().date
                }

                CurrencyUIState.Success(
                    date
                )
            } catch (e: Exception) {
                CurrencyUIState.Error(e.localizedMessage ?: "An unknown error occured")
            }
        }
    }

    fun search() {
        list = originalData
        list = list.filter { it.currencyCode.contains(queryState.lowercase()) }
    }

    fun updateQuery(searchQuery: String){
        queryState = searchQuery
    }

}

sealed interface CurrencyUIState {
    data object Loading : CurrencyUIState
    data class Success(val date: String) : CurrencyUIState
    data class Error(val error: String) : CurrencyUIState

}
