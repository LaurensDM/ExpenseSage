package com.example.expensesage.workers

import android.app.Notification.DEFAULT_ALL
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.job.JobInfo.PRIORITY_MAX
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.graphics.Color.RED
import android.media.AudioAttributes
import android.media.AudioAttributes.CONTENT_TYPE_SONIFICATION
import android.media.AudioAttributes.USAGE_NOTIFICATION_RINGTONE
import android.media.RingtoneManager.TYPE_NOTIFICATION
import android.media.RingtoneManager.getDefaultUri
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.O
import androidx.core.app.NotificationCompat
import com.example.expensesage.MainActivity
import com.example.expensesage.R

private const val TAG = "WorkerUtils"


const val NOTIFICATION_ID = "appName_notification_id"
const val NOTIFICATION_NAME = "appName"
const val NOTIFICATION_CHANNEL = "appName_channel_01"
const val NOTIFICATION_WORK = "appName_notification_work"

/**
 * This function creates a one time sync worker.
 *
 * @param id The id of the notification
 * @param title The title of the notification
 * @param message The message of the notification
 * @param applicationContext The application [Context]
 */
fun makeStatusNotification(id: Int, title: String, message: String, applicationContext: Context) {
    val intent = Intent(applicationContext, MainActivity::class.java)
    intent.flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
    intent.putExtra(NOTIFICATION_ID, id)

    val notificationManager =
        applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

    val notification = NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL)
        .setSmallIcon(R.drawable.logo)
        .setContentTitle(title).setContentText(message)
        .setDefaults(DEFAULT_ALL)

    notification.priority = PRIORITY_MAX

    if (SDK_INT >= O) {
        notification.setChannelId(NOTIFICATION_CHANNEL)

        val ringtoneManager = getDefaultUri(TYPE_NOTIFICATION)
        val audioAttributes = AudioAttributes.Builder().setUsage(USAGE_NOTIFICATION_RINGTONE)
            .setContentType(CONTENT_TYPE_SONIFICATION).build()

        val channel =
            NotificationChannel(NOTIFICATION_CHANNEL, NOTIFICATION_NAME, IMPORTANCE_HIGH)

        channel.enableLights(true)
        channel.lightColor = RED
        channel.enableVibration(true)
        channel.vibrationPattern = longArrayOf(100, 200, 300)
        channel.setSound(ringtoneManager, audioAttributes)
        notificationManager.createNotificationChannel(channel)
    }

    notificationManager.notify(id, notification.build())
}