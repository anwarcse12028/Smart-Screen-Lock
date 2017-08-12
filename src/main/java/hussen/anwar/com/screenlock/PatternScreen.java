package hussen.anwar.com.screenlock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;

import java.util.List;

import io.paperdb.Paper;

public class PatternScreen extends AppCompatActivity {

    String save_pattern_key = "pattern_code";
    String final_pattern = "";
    PatternLockView mPatternLockView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Paper.init(this);
        startService(new Intent(getApplicationContext(), LockService.class));
        final String save_pattern = Paper.book().read(save_pattern_key);
        //setContentView(R.layout.activity_pattern_screen);
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
                    Toast.makeText(PatternScreen.this, "Password Correct!", Toast.LENGTH_SHORT).show();
                    Intent mainActivity = new Intent(Intent.ACTION_MAIN);
                    mainActivity.addCategory(Intent.CATEGORY_HOME);
                    mainActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(mainActivity);
                    finish();
                }else
                    Toast.makeText(PatternScreen.this, "Password incorrect!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCleared() {

            }
        });

    }

}
