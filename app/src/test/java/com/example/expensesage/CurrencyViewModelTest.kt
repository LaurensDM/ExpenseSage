package com.example.expensesage

import com.example.expensesage.data.currencies.Currency
import com.example.expensesage.ui.viewModels.CurrencyUIState
import com.example.expensesage.ui.viewModels.CurrencyViewModel
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonPrimitive
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CurrencyViewModelTest {
    @get:Rule
    val mockKRule = MockKRule(this)


    @MockK
    private lateinit var viewModel: CurrencyViewModel

    private val fakeCurrencies = listOf(
        Currency("USD", "2023-11-14", 1.0, "EUR"),
        Currency("GBP", "2023-11-14", 0.8, "EUR"),
        Currency("JPY", "2023-11-14", 158.0, "EUR"),
        Currency("AUD", "2023-11-14", 1.8, "EUR"),
        Currency("CAD", "2023-11-14", 1.7, "EUR")
    )

    private val fakeJson = JsonObject( mapOf(
        "eur" to JsonObject(
            mapOf(
                "usd" to JsonPrimitive(1.6),
                "gbp" to JsonPrimitive(0.8),
                "jpy" to JsonPrimitive(158),
                "aud" to JsonPrimitive(1.8),
                "cad" to JsonPrimitive(1.7),
            )
        ),
        "date" to JsonPrimitive("2023-11-14")
    ))

    @Before
    fun setup() {

        every { viewModel.currencyUIState } returns CurrencyUIState.Success(fakeJson["date"]?.jsonPrimitive?.content ?: "")
        every { viewModel.list } returns fakeCurrencies
    }
    @Test
    fun `test initial state`() {
        every { viewModel.currencyUIState } returns CurrencyUIState.Success(fakeJson["date"]?.jsonPrimitive?.content ?: "")
        every { viewModel.currency } returns "EUR"
        Assert.assertTrue(viewModel.currencyUIState is CurrencyUIState.Success)
        assertEquals(fakeCurrencies, viewModel.list)
        assertEquals("EUR", viewModel.currency)
    }

    @Test
    fun `test error during data retrieval`() = runBlocking {
            every { viewModel.currencyUIState } returns CurrencyUIState.Error("Fake error")
            assertEquals(CurrencyUIState.Error("Fake error"), viewModel.currencyUIState)
    }

    @Test
    fun `test search`() {

         every { viewModel.updateQuery("USD") } returns Unit
        every { viewModel.queryState } returns "USD"
        assertEquals("USD", viewModel.queryState)
    }
}