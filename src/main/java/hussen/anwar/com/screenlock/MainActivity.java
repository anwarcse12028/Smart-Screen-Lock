package hussen.anwar.com.screenlock;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.WallpaperManager;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    float x1,x2;
    float y1, y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //disable key button
      //  KeyguardManager keyguardManager = (KeyguardManager)getSystemService(Activity.KEYGUARD_SERVICE);
      //  KeyguardManager.KeyguardLock lock = keyguardManager.newKeyguardLock(KEYGUARD_SERVICE);
      //  lock.reenableKeyguard();
      //  lock.disableKeyguard();

        //

      //  makeFullScreen();
       // startService(new Intent(getApplicationContext(), LockService.class));

        setContentView(R.layout.activity_main);

       /*
        //for wall paper setting
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        try {
            wallpaperManager.setResource(R.drawable.wallpaper);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

    }


    /*

    public void makeFullScreen() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if(Build.VERSION.SDK_INT < 19) { //View.SYSTEM_UI_FLAG_IMMERSIVE is only on API 19+
            this.getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        } else {
            this.getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE);
        }
    }
    */
    @Override
    public void onBackPressed() {
        return; //Do nothing!
    }

    @Override

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        boolean result;
        switch( event.getKeyCode() ) {

            case KeyEvent.KEYCODE_MENU:
                result = true;
                break;

            case KeyEvent.KEYCODE_VOLUME_UP:
                result = true;
                break;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                result = true;
                break;
            case KeyEvent.KEYCODE_BACK:
                result = true;
                break;
            default:
                result= super.dispatchKeyEvent(event);
                break;
        }

        return false;
    }
    /*
    @Override
    public void onAttachedToWindow() {
        this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock lock = keyguardManager.newKeyguardLock(KEYGUARD_SERVICE);
        lock.disableKeyguard();
    }
    */

    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(getApplicationContext(),"REsult called",Toast.LENGTH_LONG).show();
        if (resultCode == 2){
            android.os.Process.killProcess(android.os.Process.myPid());
            finish();
        }
    }
    */

    @Override
    public boolean onTouchEvent(MotionEvent touchevent) {


        switch (touchevent.getAction())
        {
            // when user first touches the screen we get x and y coordinate
            case MotionEvent.ACTION_DOWN:
            {
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                x2 = touchevent.getX();
                y2 = touchevent.getY();

             //if left to right sweep event on screen
                if (x1 < x2)
                {
                    Toast.makeText(this, "Left to Right Swap Performed", Toast.LENGTH_LONG).show();
                    //android.os.Process.killProcess(android.os.Process.myPid());
                   // startActivityForResult(new Intent(MainActivity.this, LauncherActivity.class),2);
                    startActivity(new Intent(MainActivity.this, PatternScreen.class));
                  //  android.os.Process.killProcess(android.os.Process.myPid());

                }

                // if right to left sweep event on screen
                if (x1 > x2)
                {
                    Toast.makeText(this, "Right to Left Swap Performed", Toast.LENGTH_LONG).show();
                    //android.os.Process.killProcess(android.os.Process.myPid());
                }

             /*
                // if UP to Down sweep event on screen
                if (y1 < y2)
                {
                    Toast.makeText(this, "UP to Down Swap Performed", Toast.LENGTH_LONG).show();
                }

              //if Down to UP sweep event on screen
                if (y1 > y2)
                {
                    Toast.makeText(this, "Down to UP Swap Performed", Toast.LENGTH_LONG).show();
                }
             */
                break;
            }
        }
        return false;
    }


}

