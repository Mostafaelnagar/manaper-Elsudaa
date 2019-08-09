package app.manaper.viewModels;

import android.os.AsyncTask;
import android.os.Handler;

import app.manaper.base.BaseViewModel;
import app.manaper.base.UserPreferenceHelper;
import app.manaper.base.constantsutils.Codes;
import app.manaper.base.volleyutils.MyApplication;

public class SplashScreenViewModel extends BaseViewModel {

    public SplashScreenViewModel() {
//        startApp();
    }

    private void startApp() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getClicksMutableLiveData().setValue(Codes.LOGIN_SCREEN);
            }
        }, 3000);

    }
}