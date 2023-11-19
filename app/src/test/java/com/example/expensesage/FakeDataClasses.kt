package com.example.expensesage

import androidx.datastore.preferences.core.edit
import com.example.expensesage.data.UserSettings
import com.example.expensesage.data.UserSettingsService
import com.example.expensesage.data.currencies.Currency
import com.example.expensesage.data.currencies.CurrencyRepository
import com.example.expensesage.data.expenses.Expense
import com.example.expensesage.data.expenses.ExpenseRepository
import com.example.expensesage.data.expenses.populateData
import com.example.expensesage.ui.utils.ExpenseSummaryItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import java.util.stream.Collectors
import kotlin.streams.toList

class FakeExpenseRepository : ExpenseRepository {
    var expenses = populateData()

    override fun getAllExpenses(): Flow<List<Expense>> {
        return MutableStateFlow(expenses)
    }

    override fun getExpenses(owed: Boolean): Flow<List<Expense>> {
        return MutableStateFlow(expenses.filter { it.owed == owed })
    }

    override fun getTop5Expenses(): Flow<List<Expense>> {
        return MutableStateFlow(expenses.take(5))
    }

    override fun getExpense(id: Int): Flow<Expense> {
        return MutableStateFlow(expenses.first { it.id == id })
    }

    override suspend fun insert(expense: Expense) {
        expenses = expenses.plus(expense.copy(id = expenses.size + 1))
    }

    override suspend fun update(expense: Expense) {
        val index = expenses.indexOfFirst { it.id == expense.id }
        expenses = expenses.toMutableList().apply {
            this[index] = expense
        }
    }

    override suspend fun delete(expense: Expense) {
        expenses = expenses.minus(expense)
    }

    override fun getSumOfCategory(searchQuery: String): Flow<List<ExpenseSummaryItem>> {
        val newList: List<ExpenseSummaryItem> =
            expenses.stream().filter { it.category == searchQuery }
                .map { ExpenseSummaryItem(it.category, it.amount) }.collect(Collectors.toList())
        return MutableStateFlow(newList)
    }

    override fun getSumOfOwed(owed: Boolean): Flow<Double> {
        val newList: List<Double> = expenses.stream().filter { it.owed == owed }.map { it.amount }
            .collect(Collectors.toList())
        return MutableStateFlow(newList.sum())
    }

    override fun getSumOfAll(): Flow<Double> {
        val newList: List<Double> = expenses.stream().map { it.amount }.collect(Collectors.toList())
        return MutableStateFlow(newList.sum())
    }

    override fun getMonthlyExpenseSummary(year: String): Flow<List<ExpenseSummaryItem>> {
        val newList: List<ExpenseSummaryItem> =
            expenses.stream().filter { it.date.year.toString() == year }
                .map { ExpenseSummaryItem(it.category, it.amount) }.collect(Collectors.toList())
        return MutableStateFlow(newList)
    }

    override fun getSumOfDate(searchQuery: String): Flow<Double> {
        val newList: List<Double> =
            expenses.stream().filter { it.date.toString() == searchQuery }.map { it.amount }
                .collect(Collectors.toList())
        return MutableStateFlow(newList.sum())
    }

    override fun getSumOfCategoryAndDate(searchQuery: String, date: String): Flow<Double> {
        val newList: List<Double> =
            expenses.stream().filter { it.category == searchQuery && it.date.toString() == date }
                .map { it.amount }.collect(Collectors.toList())
        return MutableStateFlow(newList.sum())
    }

    override fun getWeeklyExpenseSummary(yearMonth: String): Flow<List<ExpenseSummaryItem>> {
        val newList: List<ExpenseSummaryItem> =
            expenses.stream().filter { it.date.year.toString() + it.date.monthValue == yearMonth }
                .map { ExpenseSummaryItem(it.category, it.amount) }.collect(Collectors.toList())
        return MutableStateFlow(newList)
    }

    override fun getSumOfWeek(): Flow<Double> {
        val newList: List<Double> =
            expenses.stream().filter { it.date.dayOfYear.toString() == "1" }.map { it.amount }
                .collect(Collectors.toList())
        return MutableStateFlow(newList.sum())
    }

    override fun getSumOfMonth(): Flow<Double> {
        val newList: List<Double> =
            expenses.stream().filter { it.date.dayOfMonth.toString() == "1" }.map { it.amount }
                .collect(Collectors.toList())
        return MutableStateFlow(newList.sum())
    }

    override fun getSumOfYear(): Flow<Double> {
        val newList: List<Double> =
            expenses.stream().filter { it.date.dayOfYear.toString() == "1" }.map { it.amount }
                .collect(Collectors.toList())
        return MutableStateFlow(newList.sum())
    }
}

class FakeCurrencyRepository : CurrencyRepository {

    private var fakeData = listOf(
        Currency("eur", "2021-03-05", 1.0, "EUR"),
        Currency("usd", "2021-03-05", 1.0, "EUR"),
        Currency("gbp", "2021-03-05", 1.0, "EUR"),
        Currency("cad", "2021-03-05", 1.0, "EUR"),
        Currency("aud", "2021-03-05", 1.0, "EUR"),
        Currency("jpy", "2021-03-05", 1.0, "EUR"),
        Currency("chf", "2021-03-05", 1.0, "EUR"),
        Currency("cny", "2021-03-05", 1.0, "EUR"),
        Currency("sek", "2021-03-05", 1.0, "EUR"),
        Currency("nzd", "2021-03-05", 1.0, "EUR"),
        Currency("mxn", "2021-03-05", 1.0, "EUR"),
    )

    override suspend fun getAllCurrencies(): Flow<List<Currency>> {
        return MutableStateFlow(fakeData)
    }

    override suspend fun getCurrency(id: String): Flow<Currency> {
        return MutableStateFlow(fakeData.first { it.currencyCode == id })
    }

    override suspend fun insert(currency: Currency) {
        fakeData = fakeData.plus(currency)
    }

    override suspend fun insertAll(currencies: List<Currency>) {
        fakeData = fakeData.plus(currencies)
    }

    override suspend fun update(currency: Currency) {
        val index = fakeData.indexOfFirst { it.currencyCode == currency.currencyCode }
        fakeData = fakeData.toMutableList().apply {
            this[index] = currency
        }
    }

    override suspend fun delete(currency: Currency) {
        fakeData = fakeData.minus(currency)
    }
}

class FakeUserSettings(
    override var playSound: Flow<Boolean> = MutableStateFlow(true),
    override var soundVolume: Flow<Double> = MutableStateFlow(0.5),
    override var budget: Flow<Double> = MutableStateFlow(0.0),
    override var firstBudgetChange: Flow<Boolean> = MutableStateFlow(true),
    override var moneyAvailable: Flow<Double> = MutableStateFlow(0.0),
    override var budgetFrequency: Flow<String> = MutableStateFlow("Weekly"),
    override var currency: Flow<String> = MutableStateFlow("EUR"),
    override var currencyModifier: Flow<Double> = MutableStateFlow(1.0),
    override var firstTime: Flow<Boolean> = MutableStateFlow(true),
    override var moneyOwed: Flow<Double> = MutableStateFlow(0.0),
) : UserSettingsService {
    override suspend fun saveSoundPreference(playSound: Boolean) {
        this.playSound = MutableStateFlow(playSound)
    }

    override suspend fun saveSoundVolume(soundVolume: Double) {
        this.soundVolume = MutableStateFlow(soundVolume)
    }

    override suspend fun saveBudget(budget: Double) {
        this.budget = MutableStateFlow(budget)
    }

    override suspend fun saveFirstBudgetChange(firstBudgetChange: Boolean) {
        this.firstBudgetChange = MutableStateFlow(firstBudgetChange)
    }

    override suspend fun saveMoneyAvailable(moneyAvailable: Double) {
        this.moneyAvailable = MutableStateFlow(moneyAvailable)
    }

    override suspend fun saveBudgetFrequency(budgetFrequency: String) {
        this.budgetFrequency = MutableStateFlow(budgetFrequency)
    }

    override suspend fun saveCurrency(currency: String) {
        this.currency = MutableStateFlow(currency)
    }

    override suspend fun saveCurrencyModifier(currencyModifier: Double) {
        this.currencyModifier = MutableStateFlow(currencyModifier)
    }

    override suspend fun saveFirstTime(firstTime: Boolean) {
        this.firstTime = MutableStateFlow(firstTime)
    }

    override suspend fun saveMoneyOwed(moneyOwed: Double) {
        this.moneyOwed = MutableStateFlow(moneyOwed)
    }

}