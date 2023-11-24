//[app](../../index.md)/[com.example.expensesage](index.md)/[AppBar](-app-bar.md)

# AppBar

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [AppBar](-app-bar.md)(currentScreen: [Navigations](../com.example.expensesage.ui.utils/-navigations/index.md), navigationType: [NavigationType](../com.example.expensesage.ui.utils/-navigation-type/index.md), onNavIconPressed: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), onBackIconPressed: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), scrollBehavior: [TopAppBarScrollBehavior](https://developer.android.com/reference/kotlin/androidx/compose/material3/TopAppBarScrollBehavior.html) = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(), drawerState: [DrawerState](https://developer.android.com/reference/kotlin/androidx/compose/material3/DrawerState.html), scope: CoroutineScope)

App bar for the app

#### Parameters

androidJvm

| | |
|---|---|
| currentScreen | the current screen |
| navigationType | the type of navigation |
| onNavIconPressed | the function to call when the navigation icon is pressed |
| onBackIconPressed | the function to call when the back icon is pressed |
| scrollBehavior | the scroll behavior |
| drawerState | the state of the drawer |
| scope | the coroutine scope |
