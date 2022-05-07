package com.example.simplenotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    NotificationManager notificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    NotificationChannel notificationChannel = new NotificationChannel("com.example.notification", "Simple Notification", NotificationManager.IMPORTANCE_DEFAULT);
                    notificationChannel.setLightColor(Color.RED);
                    notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

                    notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    assert notificationManager != null;
                    notificationManager.createNotificationChannel(notificationChannel);

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this,"com.example.notification");
                    Notification notification = builder.setOngoing(true)
                            .setSmallIcon(R.drawable.ic_launcher_foreground)
                            .setContentTitle("Simple Notification")
                            .setPriority(NotificationManager.IMPORTANCE_HIGH)
                            .setCategory(Notification.CATEGORY_SERVICE)
                            .build();
                    notificationManager.notify(0, notification);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        notificationManager.cancel(0);
    }
}