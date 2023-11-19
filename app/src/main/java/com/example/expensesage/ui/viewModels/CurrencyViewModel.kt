package com.example.expensesage.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensesage.data.UserSettings
import com.example.expensesage.data.UserSettingsService
import com.example.expensesage.data.currencies.Currency
import com.example.expensesage.data.currencies.CurrencyRepository
import com.example.expensesage.network.CurrencyApiExecutor
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.time.LocalDate

/**
 * This class is responsible for the currency view model.
 *
 * @property userPref The user preferences
 * @property currencyApiExecutor The currency API executor
 * @property currencyRepository The currency repository
 */
class CurrencyViewModel(
    private val userPref: UserSettingsService,
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
//        Log.d("CurrencyViewModel", "init")

            getData()


    }

    /**
     * This function gets the data.
     *
     */
    fun getData() {
        viewModelScope.launch {
            currencyUIState = CurrencyUIState.Loading
            currencyUIState = try {
                currency = userPref.currency.first()

                originalData = currencyRepository.getAllCurrencies().first()
                list = originalData
                var date: String = originalData.first().date


                CurrencyUIState.Success(
                    date
                )
            } catch (e: Exception) {
                CurrencyUIState.Error(e.localizedMessage ?: "An unknown error occured")
            }
        }
    }

    /**
     * This function searches for a currency.
     *
     */
    fun search() {
        list = originalData.filter { it.currencyCode.contains(queryState.lowercase()) }
    }

    /**
     * This function updates the query.
     *
     * @param searchQuery The search query
     */
    fun updateQuery(searchQuery: String){
        queryState = searchQuery
    }

}

/**
 * This interface is responsible for the currency UI state.
 *
 */
sealed interface CurrencyUIState {
    data object Loading : CurrencyUIState
    data class Success(val date: String) : CurrencyUIState
    data class Error(val error: String) : CurrencyUIState

}
