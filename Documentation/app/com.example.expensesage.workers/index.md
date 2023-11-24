//[app](../../index.md)/[com.example.expensesage.workers](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [BudgetWorker](-budget-worker/index.md) | [androidJvm]<br>class [BudgetWorker](-budget-worker/index.md)(ctx: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), params: [WorkerParameters](https://developer.android.com/reference/kotlin/androidx/work/WorkerParameters.html)) : [CoroutineWorker](https://developer.android.com/reference/kotlin/androidx/work/CoroutineWorker.html) |
| [OwedReminderWorker](-owed-reminder-worker/index.md) | [androidJvm]<br>class [OwedReminderWorker](-owed-reminder-worker/index.md)(ctx: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), params: [WorkerParameters](https://developer.android.com/reference/kotlin/androidx/work/WorkerParameters.html)) : [CoroutineWorker](https://developer.android.com/reference/kotlin/androidx/work/CoroutineWorker.html)<br>This class is responsible for sending a notification to the user if they have any expenses to pay. |
| [SyncWorker](-sync-worker/index.md) | [androidJvm]<br>class [SyncWorker](-sync-worker/index.md)(ctx: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), params: [WorkerParameters](https://developer.android.com/reference/kotlin/androidx/work/WorkerParameters.html)) : [CoroutineWorker](https://developer.android.com/reference/kotlin/androidx/work/CoroutineWorker.html)<br>This class is responsible for syncing currencies. |

## Properties

| Name | Summary |
|---|---|
| [NOTIFICATION_CHANNEL](-n-o-t-i-f-i-c-a-t-i-o-n_-c-h-a-n-n-e-l.md) | [androidJvm]<br>const val [NOTIFICATION_CHANNEL](-n-o-t-i-f-i-c-a-t-i-o-n_-c-h-a-n-n-e-l.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [NOTIFICATION_ID](-n-o-t-i-f-i-c-a-t-i-o-n_-i-d.md) | [androidJvm]<br>const val [NOTIFICATION_ID](-n-o-t-i-f-i-c-a-t-i-o-n_-i-d.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [NOTIFICATION_NAME](-n-o-t-i-f-i-c-a-t-i-o-n_-n-a-m-e.md) | [androidJvm]<br>const val [NOTIFICATION_NAME](-n-o-t-i-f-i-c-a-t-i-o-n_-n-a-m-e.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [NOTIFICATION_WORK](-n-o-t-i-f-i-c-a-t-i-o-n_-w-o-r-k.md) | [androidJvm]<br>const val [NOTIFICATION_WORK](-n-o-t-i-f-i-c-a-t-i-o-n_-w-o-r-k.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Functions

| Name | Summary |
|---|---|
| [changeInterval](change-interval.md) | [androidJvm]<br>suspend fun [changeInterval](change-interval.md)(ctx: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), userSettings: [UserSettingsService](../com.example.expensesage.data/-user-settings-service/index.md))<br>This function changes the interval of the budget worker. |
| [executeWorkers](execute-workers.md) | [androidJvm]<br>suspend fun [executeWorkers](execute-workers.md)(ctx: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), userSettings: [UserSettingsService](../com.example.expensesage.data/-user-settings-service/index.md))<br>This function executes the workers. |
| [makeStatusNotification](make-status-notification.md) | [androidJvm]<br>fun [makeStatusNotification](make-status-notification.md)(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), applicationContext: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))<br>This function creates a one time sync worker. |
