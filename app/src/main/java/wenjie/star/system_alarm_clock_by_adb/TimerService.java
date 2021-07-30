package wenjie.star.system_alarm_clock_by_adb;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.provider.AlarmClock;

import androidx.annotation.Nullable;

/**
 * 设置定时器（不同于闹钟，只能有一个）
 */
public class TimerService extends IntentService {

    private final String TAG = this.getClass().getSimpleName();

    public TimerService() {
        super("TimerService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        startForeground();
        String msg = intent.getStringExtra("msg");
        int afterSec = intent.getIntExtra("afterSec", 1);
        createTimer(msg, afterSec);
    }

    /**
     * 设置系统闹钟
     */
    public void createTimer(String message, int afterSec) {
        // 纯Service在没有activity时调用startActivity设置闹钟会失败，这里套了一层alarmManager去调用就成功了
        Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_LENGTH, afterSec)
                // 闹钟震动
                .putExtra(AlarmClock.EXTRA_VIBRATE, false)
                // 跳过UI
                .putExtra(AlarmClock.EXTRA_SKIP_UI, true);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void startForeground() {
        Intent intent = new Intent();
        String title = "后台服务";
        String content = "设置计时器ing..";
        Notification.Builder builder = new Notification.Builder(this, title)
                .setContentIntent(PendingIntent.getService(this, 0, intent, 0))// 设置PendingIntent
                .setSmallIcon(R.mipmap.ic_launcher) // 设置状态栏内的小图标
                .setContentTitle(title)
                .setContentText(content)
                .setWhen(System.currentTimeMillis());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String CHANNEL_ONE_ID = "timer_service";
            // 修改安卓8.1以上系统报错
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ONE_ID, title, NotificationManager.IMPORTANCE_MIN);
            notificationChannel.enableLights(false);//如果使用中的设备支持通知灯，则说明此通知通道是否应显示灯
            notificationChannel.setShowBadge(false);//是否显示角标
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_SECRET);

            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.createNotificationChannel(notificationChannel);
            builder.setChannelId(CHANNEL_ONE_ID);
        }
        startForeground(1, builder.build());
    }
}
