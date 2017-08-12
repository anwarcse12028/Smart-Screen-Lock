package hussen.anwar.com.screenlock;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;

import java.util.List;

import io.paperdb.Paper;

import static android.R.attr.button;

public class LauncherActivity extends AppCompatActivity {

    String save_pattern_key = "pattern_code";
    String final_pattern = "";
    String final_pattern2 = "#";
    PatternLockView mPatternLockView;
    Button btnSetup,btnCancel;
    TextView tvPattern;
    int save  = 0;
   // public static int state = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //service start
        startService(new Intent(getApplicationContext(), LockService.class));
        Paper.init(this);
        final String save_pattern = Paper.book().read(save_pattern_key);
       /* if(save_pattern != null && !save_pattern.equals("null"))
        {
            setContentView(R.layout.pattern_screen);
            mPatternLockView = (PatternLockView)findViewById(R.id.pattern_lock_view);
            mPatternLockView.addPatternLockListener(new PatternLockViewListener() {
                @Override
                public void onStarted() {

                }

                @Override
                public void onProgress(List<PatternLockView.Dot> progressPattern) {

                }

                @Override
                public void onComplete(List<PatternLockView.Dot> pattern) {
                    final_pattern = PatternLockUtils.patternToString(mPatternLockView,pattern);
                    if(final_pattern.equals(save_pattern)) {
                        Toast.makeText(LauncherActivity.this, "Password Correct!", Toast.LENGTH_SHORT).show();
                        finish();

                    }else
                        Toast.makeText(LauncherActivity.this, "Password incorrect!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCleared() {

                }
            });
        }
        else
        {*/

            save = 0;
            setContentView(R.layout.activity_launcher);
            tvPattern = (TextView)findViewById(R.id.tvPatternText);
            btnCancel = (Button)findViewById(R.id.btnCancelPattern);
            btnSetup = (Button)findViewById(R.id.btnSetPattern);
            mPatternLockView = (PatternLockView)findViewById(R.id.pattern_lock_view);
            btnCancel.setActivated(false);
            btnSetup.setActivated(false);
            mPatternLockView.addPatternLockListener(new PatternLockViewListener() {
                @Override
                public void onStarted() {

                }

                @Override
                public void onProgress(List<PatternLockView.Dot> progressPattern) {

                }

                @Override
                public void onComplete(List<PatternLockView.Dot> pattern) {

                    if(save == 0) {
                        final_pattern = PatternLockUtils.patternToString(mPatternLockView, pattern);
                        btnSetup.setActivated(true);
                        btnSetup.setText(R.string.btn_contininue);
                        save = 1;
                    }else if(save == 1) {
                        final_pattern2 = PatternLockUtils.patternToString(mPatternLockView, pattern);
                        if(final_pattern.equals(final_pattern2)){
                            Toast.makeText(getApplicationContext(),"Pattern are Correct",Toast.LENGTH_LONG).show();
                            btnSetup.setText(R.string.btn_confirm);
                            btnSetup.setActivated(true);
                            btnCancel.setActivated(true);
                            save = 2;
                        }else {
                            Toast.makeText(getApplicationContext(),"Pattern are not Correct",Toast.LENGTH_LONG).show();
                            btnCancel.setActivated(true);
                            btnSetup.setActivated(false);
                            save = 1;
                        }

                    }

                }

                @Override
                public void onCleared() {

                }
            });




            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tvPattern.setText(R.string.pattern_text);
                    btnSetup.setText(R.string.btn_contininue);
                    btnSetup.setActivated(false);
                    mPatternLockView.clearPattern();
                    save = 0;

                }
            });

            btnSetup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(save == 2) {
                        Paper.book().write(save_pattern_key, final_pattern);
                        Toast.makeText(LauncherActivity.this, "Save pattern okay!", Toast.LENGTH_SHORT).show();
                        finish();
                    }else if(save == 1){

                        tvPattern.setText(R.string.pattern_text_again);
                        btnCancel.setActivated(true);
                        btnSetup.setText(R.string.btn_confirm);
                        mPatternLockView.clearPattern();

                    }
                }
            });
        }
   // }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);//Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                Toast.makeText(getApplicationContext(),"Developed by Anwar Hussen Wadud",Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_settings:
                Toast.makeText(getApplicationContext(),"Setting your apps",Toast.LENGTH_LONG).show();
                openAlertDialog();
                return true;
            case R.id.action_exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void openAlertDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Select your Pattern Lock Settings ");
                alertDialogBuilder.setPositiveButton("Enable",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(getApplicationContext(),"Your Pattern Lock Application is Running",Toast.LENGTH_LONG).show();
                                startService(new Intent(getApplicationContext(), LockService.class));
                            }
                        });

        alertDialogBuilder.setNegativeButton("Disable",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"Your Pattern Lock Application is Stop",Toast.LENGTH_LONG).show();
                stopService(new Intent(getApplicationContext(), LockService.class));
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
