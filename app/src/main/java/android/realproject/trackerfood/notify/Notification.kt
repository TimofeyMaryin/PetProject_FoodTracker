package android.realproject.trackerfood.notify

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.realproject.trackerfood.R
import androidx.core.app.NotificationCompat

class Notification: BroadcastReceiver() {
    val channel_id = "channel_id"
    val channelName = "notify_name"
    val notifyId = 0

    val titleExtra = "titleExtra"
    val messageExtra = "messageExtra"


    override fun onReceive(context: Context, intent: Intent) {

        val builder = NotificationCompat.Builder(context, channel_id)
            .setSmallIcon(R.drawable.ic_notify)
            .setContentTitle("Не пора ли тебе покушать?")
            .setContentText("Я ни на что не намекаю, но такими темпами РПП тебе гарантирован")
            .setAutoCancel(false)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(notifyId, builder)
    }
}