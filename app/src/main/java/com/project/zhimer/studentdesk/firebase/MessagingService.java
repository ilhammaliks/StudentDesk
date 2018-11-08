package com.project.zhimer.studentdesk.firebase;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.project.zhimer.studentdesk.MainActivity;
import com.project.zhimer.studentdesk.R;

public class MessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        sendMyNotification(remoteMessage.getNotification().getBody());
//        super.onMessageReceived(remoteMessage);
    }

    private void sendMyNotification(String message) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


//        RemoteViews bigNotif = new RemoteViews(getApplicationContext().getPackageName(), R.layout.notifikasi);

        //work from here
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.logo_uai)
                .setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.logo_uai))
                .setContentTitle("Student Desk Notification")
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(soundUri)
//                .setCustomBigContentView(bigNotif)
                .setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, notificationBuilder.build());
    }
}
