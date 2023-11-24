//[app](../../index.md)/[com.example.expensesage.data](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [AppContainer](-app-container/index.md) | [androidJvm]<br>interface [AppContainer](-app-container/index.md) |
| [AppDataContainer](-app-data-container/index.md) | [androidJvm]<br>class [AppDataContainer](-app-data-container/index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), scope: CoroutineScope) : [AppContainer](-app-container/index.md)<br>Class that handles the creation of the repositories |
| [DataStoreSingleton](-data-store-singleton/index.md) | [androidJvm]<br>object [DataStoreSingleton](-data-store-singleton/index.md)<br>Singleton class that handles the creation of the DataStore |
| [ExpenseSageDatabase](-expense-sage-database/index.md) | [androidJvm]<br>abstract class [ExpenseSageDatabase](-expense-sage-database/index.md) : [RoomDatabase](https://developer.android.com/reference/kotlin/androidx/room/RoomDatabase.html) |
| [UserSettings](-user-settings/index.md) | [androidJvm]<br>class [UserSettings](-user-settings/index.md)(dataStore: [DataStore](https://developer.android.com/reference/kotlin/androidx/datastore/core/DataStore.html)&lt;[Preferences](https://developer.android.com/reference/kotlin/androidx/datastore/preferences/core/Preferences.html)&gt;) : [UserSettingsService](-user-settings-service/index.md) |
| [UserSettingsService](-user-settings-service/index.md) | [androidJvm]<br>interface [UserSettingsService](-user-settings-service/index.md) |

## Properties

| Name | Summary |
|---|---|
| [currencyList](currency-list.md) | [androidJvm]<br>val [currencyList](currency-list.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |
