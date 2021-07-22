package wenjie.star.system_alarm_clock_by_adb;

import android.app.Activity;
import android.os.Bundle;

/**
 * 仅调试用
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.finish();
    }
}