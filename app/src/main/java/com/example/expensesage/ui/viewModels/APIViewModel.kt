package com.example.expensesage.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensesage.network.CurrencyApi
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonObject
import retrofit2.HttpException
import java.io.IOException

sealed interface CurrencyUiState {
    data class Success(val data: JsonObject) : CurrencyUiState
    data class Error(val error: String) : CurrencyUiState
    object Loading : CurrencyUiState
}

class APIViewModel : ViewModel() {

    var currencyUiState: CurrencyUiState by mutableStateOf(CurrencyUiState.Loading)
        private set

    init {
        getCurrencies()
    }

    fun getCurrencies() {
        viewModelScope.launch {
            currencyUiState = CurrencyUiState.Loading
           try {
               currencyUiState =  CurrencyUiState.Success(CurrencyApi.retrofitService.getEurRate())
           } catch (e: IOException) {
               currencyUiState = CurrencyUiState.Error(e.message ?: "An unknown error occured")
            } catch (e: HttpException) {
               currencyUiState = CurrencyUiState.Error(e.message ?: "An unknown error occured")
            }
        }
    }
}