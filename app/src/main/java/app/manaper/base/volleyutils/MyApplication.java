package app.manaper.base.volleyutils;


import android.util.Log;

import androidx.multidex.MultiDexApplication;
import app.manaper.base.ConnectivityReceiver;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


/**
 * Created by Akshay Raj on 7/17/2016.
 * Snow Corporation Inc.
 * www.snowcorp.org
 */
public class MyApplication extends MultiDexApplication {
    public static final String TAG = MyApplication.class.getSimpleName();
    private static boolean activityVisible;

    private RequestQueue mRequestQueue;

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));

        /*OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new GanderInterceptor(this))
                .build();

        // For In Memory DB (tripSections retained in memory lost on app close)
        Gander.setGanderStorage(GanderIMDB.getInstance());
        new GanderInterceptor(this).showNotification(true);*/

    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public static boolean isActivityVisible() {
        Log.i(TAG, "isActivityVisible: " + activityVisible);
        return activityVisible;
    }

    public static void activityResumed() {
        Log.i(TAG, "activityResumed: " + activityVisible);
        activityVisible = true;
    }

    public static void activityPaused() {
        Log.i(TAG, "activityPaused: " + activityVisible);
        activityVisible = false;
    }


}