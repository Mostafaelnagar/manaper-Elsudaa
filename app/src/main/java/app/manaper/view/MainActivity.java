package app.manaper.view;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import app.manaper.R;
import app.manaper.base.MovementManager;
import app.manaper.base.ParentActivity;
import app.manaper.base.UserPreferenceHelper;
import app.manaper.base.constantsutils.Codes;
import app.manaper.base.constantsutils.Params;
import app.manaper.base.volleyutils.MyApplication;
import app.manaper.common.Common;
import app.manaper.customViews.NavView;
import app.manaper.databinding.ActivityBaseBinding;
import app.manaper.databinding.ActivityMainBinding;
import app.manaper.view.company.TripDetailsFragment;
import app.manaper.view.mandop.DelHomeFragment;
import app.manaper.view.mandop.MandopArrivalFragment;
import app.manaper.view.mandop.MandopDepartureOutFragment;
import app.manaper.view.mandop.MandopFirstDirectionFragment;
import app.manaper.view.mandop.MandopSecondDirectionFragment;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends ParentActivity {
    public ActivityMainBinding activityBaseBinding;
    public NavView navView;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/font1.otf")
                .setFontAttrId(R.attr.fontPath)
                .build());
        super.onCreate(savedInstanceState);
        activityBaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        navView = new NavView(this);
        activityBaseBinding.llBaseContainer.addView(navView);
        if (UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).getUserData() != null) {
            if (UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).getUserData().getActiveStatus() == 0)
                MovementManager.addHomeFragment(this, new VideoFragment(), "");
            else if (UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).getUserData().getUsersType() == 1)
                MovementManager.addHomeFragment(this, new HomeFragment(), "");
            else {
                notificationsDirections();
                MovementManager.addHomeFragment(this, new DelHomeFragment(), "");
            }
        }
    }

    private void notificationsDirections() {
        if (getIntent().getStringExtra("TYPE") != null) {
            String type = getIntent().getStringExtra("TYPE");
            String tripId = getIntent().getStringExtra("TRIP_ID");
            UserPreferenceHelper.getInstance(MainActivity.this).addTripId(tripId);
            Common.visiable = 0;
            if (type.equals("1")) {
                UserPreferenceHelper.getInstance(MainActivity.this).addArrDepsId("0");
                MovementManager.startActivity(MainActivity.this, Codes.DELEGATE_TRIP_ARRIVAL);
            } else if (type.equals("2") || type.equals("3")) {
                UserPreferenceHelper.getInstance(MainActivity.this).addSecDepsId("0");
                MovementManager.startActivity(MainActivity.this, Codes.DELEGATE_TRIP_FIR);
            } else if (type.equals("4") || type.equals("5")) {
                UserPreferenceHelper.getInstance(MainActivity.this).addSecDepsId("1");
                MovementManager.startActivity(MainActivity.this, Codes.DELEGATE_TRIP_SEC);
            } else if (type.equals("6")) {
                UserPreferenceHelper.getInstance(MainActivity.this).addArrDepsId("1");
                MovementManager.startActivity(MainActivity.this, Codes.DELEGATE_TRIP_DEP);
            } else if (type.equals("7")) {
            } else if (type.equals("8")) {
                MovementManager.replaceHomeFragment(MainActivity.this, new NotificationsFragment(), "");
            }
        }
    }

    @Override
    protected void onResume() {
        if (navView != null && navView.getNavViewModel() != null) {
            navView.getNavViewModel().setUserData();
        }
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Codes.FILE_TYPE_IMAGE) {
            try {
                Fragment fragmentManager = getSupportFragmentManager().findFragmentByTag("REGISTER_SCREEN");
                fragmentManager.onActivityResult(requestCode, resultCode, data);
            } catch (Exception e) {
                Log.i("Ex", "Ex: " + e.getMessage());
            }
        }
    }

    @Override
    public void onBackPressed() {
        showExit();
    }

    private void showExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        builder.setMessage(getBaseContext().getString(R.string.exitText))
                .setCancelable(false)
                .setPositiveButton(getBaseContext().getString(R.string.yesText),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //MovementManager.popAllFragments(MainActivity.this);
                                if (UserPreferenceHelper.getInstance(MainActivity.this).getUserData().getActiveStatus() != 1)
                                    UserPreferenceHelper.getInstance(MainActivity.this).loggout();
                                finish();
                            }
                        })
                .setNegativeButton(getBaseContext().getString(R.string.noText), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

}