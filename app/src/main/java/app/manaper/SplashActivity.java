package app.manaper;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.Locale;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import app.manaper.base.MovementManager;
import app.manaper.base.ParentActivity;
import app.manaper.base.UserPreferenceHelper;
import app.manaper.base.constantsutils.Codes;
import app.manaper.base.constantsutils.Params;
import app.manaper.common.Common;
import app.manaper.databinding.ActivitySplashBinding;
import app.manaper.view.BaseActivity;
import app.manaper.view.MainActivity;
import app.manaper.view.NotificationsFragment;
import app.manaper.viewModels.SplashScreenViewModel;

public class SplashActivity extends ParentActivity {
    ActivitySplashBinding splashBinding;
    SplashScreenViewModel screenViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        screenViewModel = new SplashScreenViewModel();
        startApp();

    }


    private void changeLan(String lang) {
        Resources res = this.getResources();
        // Change locale settings in the app.
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale(lang)); // API 17+ only.
        // Use conf.locale = new Locale(...) if targeting lower versions
        res.updateConfiguration(conf, dm);
        UserPreferenceHelper.getInstance(this).setLanguage(this, lang);
    }

    private void startApp() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (UserPreferenceHelper.getInstance(SplashActivity.this).getCurrentLanguage(SplashActivity.this) != null)
                    changeLan(UserPreferenceHelper.getInstance(SplashActivity.this).getCurrentLanguage(SplashActivity.this));
                if (getIntent().hasExtra("TYPE") && getIntent().hasExtra("TRIP_ID")) {
                    notificationsDirections();
                } else
                    MovementManager.startBaseActivity(SplashActivity.this, Codes.LOGIN_SCREEN);
            }
        }, 3000);

    }

    private void notificationsDirections() {
        if (getIntent().getStringExtra("TYPE") != null) {
            String type = getIntent().getStringExtra("TYPE");
            String tripId = getIntent().getStringExtra("TRIP_ID");
            UserPreferenceHelper.getInstance(SplashActivity.this).addTripId(tripId);
            Common.visiable = 0;
            if (type.equals("1")) {
                UserPreferenceHelper.getInstance(SplashActivity.this).addArrDepsId("0");
                MovementManager.startActivityFromNotifications(SplashActivity.this, Codes.DELEGATE_TRIP_ARRIVAL);
            } else if (type.equals("2") || type.equals("3")) {
                UserPreferenceHelper.getInstance(SplashActivity.this).addSecDepsId("0");
                MovementManager.startActivityFromNotifications(SplashActivity.this, Codes.DELEGATE_TRIP_FIR);
            } else if (type.equals("4") || type.equals("5")) {
                UserPreferenceHelper.getInstance(SplashActivity.this).addSecDepsId("1");
                MovementManager.startActivityFromNotifications(SplashActivity.this, Codes.DELEGATE_TRIP_SEC);
            } else if (type.equals("6")) {
                UserPreferenceHelper.getInstance(SplashActivity.this).addArrDepsId("1");
                MovementManager.startActivityFromNotifications(SplashActivity.this, Codes.DELEGATE_TRIP_DEP);
            } else if (type.equals("7")) {
            } else if (type.equals("8")) {
                MovementManager.replaceHomeFragment(SplashActivity.this, new NotificationsFragment(), "");
            }
        }
    }


}
