//[app](../../../index.md)/[com.example.expensesage.workers](../index.md)/[SyncWorker](index.md)/[doWork](do-work.md)

# doWork

[androidJvm]\
open suspend override fun [doWork](do-work.md)(): [ListenableWorker.Result](https://developer.android.com/reference/kotlin/androidx/work/ListenableWorker.Result.html)

This function is called on the background thread, so it is safe to do long running operations here.

#### Return

[Result.success](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/success.html) if the work was successfully completed,
