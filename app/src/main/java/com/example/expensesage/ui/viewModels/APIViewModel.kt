package com.example.expensesage.ui.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensesage.network.CurrencyApiExecutor
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonObject
import retrofit2.HttpException
import java.io.IOException

sealed interface CurrencyUiState {
    data class Success(val data: JsonObject) : CurrencyUiState
    data class Error(val error: String) : CurrencyUiState
    data object Loading : CurrencyUiState
}

class APIViewModel(private val currencyApiExecutor: CurrencyApiExecutor) : ViewModel() {

    var currencyUiState: CurrencyUiState by mutableStateOf(CurrencyUiState.Loading)
        private set

    private var currencyRate: Double by mutableDoubleStateOf(0.0)

    init {
        getCurrencies()
    }

    fun getCurrencies() {
        viewModelScope.launch {
            currencyUiState = CurrencyUiState.Loading
            currencyUiState = try {
                CurrencyUiState.Success(currencyApiExecutor.getCurrencyRates())
            } catch (e: IOException) {
                CurrencyUiState.Error(e.localizedMessage ?: "An unknown error occured")
            } catch (e: HttpException) {
                CurrencyUiState.Error(e.localizedMessage ?: "An unknown error occured")
            }
        }
    }

    suspend fun getRate(currency: String): Double {
        currencyRate = try {
            return currencyApiExecutor.getRate(currency)
        } catch (e: IOException) {
            Log.d("APIViewModel", "getRate: ${e.localizedMessage}")
            return 1.0
        } catch (e: HttpException) {
            Log.d("APIViewModel", "getRate: ${e.localizedMessage}")
            return 1.0
        }
    }
}
