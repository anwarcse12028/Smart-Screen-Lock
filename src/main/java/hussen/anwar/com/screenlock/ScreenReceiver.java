package hussen.anwar.com.screenlock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ScreenReceiver extends BroadcastReceiver {

    public static boolean wasScreenOn = true;

    public void onReceive(final Context context, final Intent intent) {
        Log.e("LOB", "onReceive");

        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            // do whatever you need to do here
            wasScreenOn = false;
            //Log.e("LOB","wasScreenOn"+wasScreenOn);
            Log.e("Screen ", "shutdown now");
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            // and do whatever you need to do here
            wasScreenOn = true;
            Log.e("Screen ", "awaked now");

            Intent i = new Intent(context, MainActivity.class);  //MyActivity can be anything which you want to start on bootup...
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);

        } else if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
            Log.e("LOB", "userpresent");
            //  Log.e("LOB","wasScreenOn"+wasScreenOn);


        }
    }
}
