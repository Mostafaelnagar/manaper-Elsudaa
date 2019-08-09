package app.manaper.view;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import app.manaper.Playing;
import app.manaper.R;
import app.manaper.base.ConnectivityReceiver;
import app.manaper.base.MovementManager;
import app.manaper.base.ParentActivity;
import app.manaper.base.UserPreferenceHelper;
import app.manaper.base.constantsutils.Codes;
import app.manaper.base.constantsutils.Params;
import app.manaper.base.volleyutils.MyApplication;
import app.manaper.common.Common;
import app.manaper.customViews.NavView;
import app.manaper.databinding.ActivityBaseBinding;
import app.manaper.models.company.Arrival;
import app.manaper.models.company.TripDetailsResponse;
import app.manaper.view.company.ArrivalFragment;
import app.manaper.view.company.TripDetailsFragment;
import app.manaper.view.mandop.DelHomeFragment;
import app.manaper.view.mandop.MandopArrivalFragment;
import app.manaper.view.mandop.MandopDepartureOutFragment;
import app.manaper.view.mandop.MandopFirstDirectionFragment;
import app.manaper.view.mandop.MandopSecondDirectionFragment;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BaseActivity extends ParentActivity implements ConnectivityReceiver.ConnectivityReceiverListener {
    public ActivityBaseBinding activityBaseBinding;
    boolean hasNotifications = false;

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
        activityBaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base);
        checkConnection();
        //get Token Id

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.e("getInstanceId", "getInstanceId failed" + task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();
                        UserPreferenceHelper.getInstance(BaseActivity.this).saveToken(token);


                    }
                });
        getNotifications();
        if (getIntent().hasExtra(Params.INTENT_PAGE)) {
            addFragment(getIntent().getIntExtra(Params.INTENT_PAGE, 0));
        }
    }

    private void getNotifications() {
        if (getIntent() != null) {
            if (getIntent().hasExtra("TYPE") && getIntent().hasExtra("TRIP_ID")) {
                hasNotifications = true;
            }
        } else {

        }
    }


    private void addFragment(int page) {
        if (!hasNotifications) {
            if (page == Codes.LOGIN_SCREEN) {
                if (UserPreferenceHelper.getInstance(this).getUserData() != null) {
                    MovementManager.startMainActivity(this, Codes.HOME_SCREEN);
                } else
                    MovementManager.addFragment(this, new LoginFragment(), "");
            } else if (page == Codes.REGISTER_SCREEN) {
                MovementManager.addFragment(this, new SignUpFragment(), "");
            } else if (page == Codes.FORGOT_PASSWORD_SCREEN) {
                MovementManager.addFragment(this, new EmailConfirmationFragment(), "");
            } else if (page == Codes.SEND_CODE_SCREEN) {
                MovementManager.addFragment(this, new CodeConfirmationFragment(), "");
            } else if (page == Codes.ENTER_CODE_SCREEN) {
                MovementManager.addFragment(this, new ForgetPasswordResetFragment(), "");
            } else if (page == Codes.TRIP_DETAILS) {
                MovementManager.addFragment(this, new TripDetailsFragment(), "");
            } else if (page == Codes.DELEGATE_TRIP_ARRIVAL) {
                MovementManager.addFragment(this, new MandopArrivalFragment(), "");
            } else if (page == Codes.DELEGATE_TRIP_DEP) {
                MovementManager.addFragment(this, new MandopDepartureOutFragment(), "");
            } else if (page == Codes.DELEGATE_TRIP_FIR) {
                MovementManager.addFragment(this, new MandopFirstDirectionFragment(), "");
            } else if (page == Codes.DELEGATE_TRIP_SEC) {
                MovementManager.addFragment(this, new MandopSecondDirectionFragment(), "");
            } else if (page == Codes.PROFILE) {
                MovementManager.addFragment(this, new ProfileFragment(), "");
            } else if (page == Codes.PLAYING_VIDEO) {
                MovementManager.addFragment(this, new Playing(), "");
            }
        } else {
            notificationsDirections();
        }

    }

    private void notificationsDirections() {
        if (getIntent().getStringExtra("TYPE") != null) {
            String type = getIntent().getStringExtra("TYPE");
            String tripId = getIntent().getStringExtra("TRIP_ID");
            UserPreferenceHelper.getInstance(BaseActivity.this).addTripId(tripId);
            Common.visiable = 0;
            if (type.equals("1")) {
                UserPreferenceHelper.getInstance(BaseActivity.this).addArrDepsId("0");
                MovementManager.startActivity(BaseActivity.this, Codes.DELEGATE_TRIP_ARRIVAL);
            } else if (type.equals("2") || type.equals("3")) {
                UserPreferenceHelper.getInstance(BaseActivity.this).addSecDepsId("0");
                MovementManager.startActivity(BaseActivity.this, Codes.DELEGATE_TRIP_FIR);
            } else if (type.equals("4") || type.equals("5")) {
                UserPreferenceHelper.getInstance(BaseActivity.this).addSecDepsId("1");
                MovementManager.startActivity(BaseActivity.this, Codes.DELEGATE_TRIP_SEC);
            } else if (type.equals("6")) {
                UserPreferenceHelper.getInstance(BaseActivity.this).addArrDepsId("1");
                MovementManager.startActivity(BaseActivity.this, Codes.DELEGATE_TRIP_DEP);
            } else if (type.equals("7")) {
            } else if (type.equals("8")) {
                MovementManager.replaceHomeFragment(BaseActivity.this, new NotificationsFragment(), "");
            } else if (type.equals("9")) {
                MovementManager.startActivity(BaseActivity.this, Codes.TRIP_DETAILS);
            }
        }
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
        finish();
    }

    // Method to manually check connection status
    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected);
    }

    // Showing the status in Snackbar
    private void showSnack(boolean isConnected) {
        String message;
        int color;
        if (isConnected) {
            message = getBaseContext().getString(R.string.connection_vaild_msg);
//            Toast.makeText(this, "" + message, Toast.LENGTH_LONG).show();

        } else {
            message = getBaseContext().getString(R.string.connection_invaild_msg);
            color = Color.RED;
            Snackbar snackbar = Snackbar
                    .make(findViewById(R.id.base), message, Snackbar.LENGTH_LONG);

            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(R.id.snackbar_text);
            textView.setTextColor(color);
            snackbar.show();
        }


    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.getInstance().setConnectivityListener(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}