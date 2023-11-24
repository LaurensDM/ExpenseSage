//[app](../../../index.md)/[com.example.expensesage.network](../index.md)/[CurrencyApiService](index.md)

# CurrencyApiService

[androidJvm]\
interface [CurrencyApiService](index.md)

Currency API Service

## Functions

| Name | Summary |
|---|---|
| [getCurrencies](get-currencies.md) | [androidJvm]<br>@GET<br>abstract suspend fun [getCurrencies](get-currencies.md)(): JsonObject |
| [getCurrencyRates](get-currency-rates.md) | [androidJvm]<br>@GET(value = &quot;{id}.json&quot;)<br>abstract suspend fun [getCurrencyRates](get-currency-rates.md)(@Path(value = &quot;id&quot;)id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): JsonObject |
| [getEurRate](get-eur-rate.md) | [androidJvm]<br>@Headers(value = [&quot;Content-Type: application/json&quot;])<br>@GET(value = &quot;eur.json&quot;)<br>abstract suspend fun [getEurRate](get-eur-rate.md)(): JsonObject |
| [getRate](get-rate.md) | [androidJvm]<br>@Headers(value = [&quot;Content-Type: application/json&quot;])<br>@GET(value = &quot;eur/{id}.json&quot;)<br>abstract suspend fun [getRate](get-rate.md)(@Path(value = &quot;id&quot;)id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): JsonObject |
