package com.example.expensesage

import com.example.expensesage.data.currencies.Currency
import com.example.expensesage.ui.viewModels.CurrencyUIState
import com.example.expensesage.ui.viewModels.CurrencyViewModel
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonPrimitive
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CurrencyViewModelTest {
    private val testScope = StandardTestDispatcher()
    private val fakeCurrencyRepository = FakeCurrencyRepository()
    private val fakeDataStore = FakeUserSettings()
    private lateinit var currencyViewModel: CurrencyViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testScope)
        currencyViewModel = CurrencyViewModel(fakeDataStore, fakeCurrencyRepository)
    }

    @Test
    fun `test viewModel initialized`() =
        runTest {
            assertTrue(currencyViewModel.currencyUIState is CurrencyUIState.Success)
        }


    @Test
    fun `test viewModel initialized success with date`() =
        runTest {
            val currencyUIState = currencyViewModel.currencyUIState as CurrencyUIState.Success
            assertEquals(currencyUIState.date, fakeCurrencyRepository.getCurrency("eur").first().date)
        }


    @Test
    fun `test viewModel initialized with correct data`() =
        runTest {
            val currency = currencyViewModel.currency
            val list = currencyViewModel.list
            val query = currencyViewModel.queryState
            assertEquals(currency, "EUR")
            assertEquals(list, fakeCurrencyRepository.getAllCurrencies().first())
            assertEquals(query, "")
        }


    @Test
    fun `test viewModel query search` () =
        runTest {
            currencyViewModel.updateQuery("USD")
            val query = currencyViewModel.queryState
            currencyViewModel.search()
            val list = currencyViewModel.list
            assertEquals(query, "USD")
            assertEquals(list, listOf(fakeCurrencyRepository.getCurrency("usd").first()))
        }


}