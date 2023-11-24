//[app](../../../index.md)/[com.example.expensesage.network](../index.md)/[CurrencyApiService](index.md)/[getRate](get-rate.md)

# getRate

[androidJvm]\

@Headers(value = [&quot;Content-Type: application/json&quot;])

@GET(value = &quot;eur/{id}.json&quot;)

abstract suspend fun [getRate](get-rate.md)(@Path(value = &quot;id&quot;)id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): JsonObject
