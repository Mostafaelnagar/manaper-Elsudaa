package app.manaper.base;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.icu.text.SimpleDateFormat;
import android.location.LocationManager;
import android.os.BatteryManager;
import android.webkit.URLUtil;

import java.net.InetAddress;
import java.util.Date;

@SuppressLint("NewApi")
public class Validate {
    public static String appColor = "#9966ff";

    //<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    public static boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com"); //You can replace it with your name
            return ipAddr.equals("") ? false : true;
        } catch (Exception e) {
            return false;
        }

    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


    public static Boolean isEmpty(String str) {
        return str == null || str.isEmpty() ? true : false;
    }



    public static  Boolean  isAvLen(String str, int from, int to) {
        return str.length() >= from && str.length() <= to ? true : false;
    }




    public static Boolean isEqual(String str1, String str2) {
        return (str1==null||str2==null) ?false : str1.equals(str2) ? true : false;
    }


    //Different between URL and NetworkURL http://stackoverflow.com/questions/23866700/difference-between-isnetworkurl-and-isvalidurl
    public static Boolean isUrl(String url) {
        return URLUtil.isValidUrl(url);
    }

    public static Boolean isNetworkUrl(String url) {
        return URLUtil.isNetworkUrl(url);
    }

    public static Boolean haveParticularChars(String str, char chars[]) {
        for (int i = 0; i < chars.length; i++)
            if (str.contains("" + chars[i]))
                return true;
        return false;
    }

    public static Boolean isMail(CharSequence str) {

        return str == null ? false : android.util.Patterns.EMAIL_ADDRESS.matcher(str).matches();
    }

    public static Boolean isIPAddress(CharSequence str) {

        return str == null ? false : android.util.Patterns.IP_ADDRESS.matcher(str).matches();
    }

    public static Boolean isPhone(CharSequence str) {

        return str == null ? false : android.util.Patterns.PHONE.matcher(str).matches();
    }


    //<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    public static boolean isLocationEnabled(Context context) {
        LocationManager lm = null;
        boolean gps_enabled = false, network_enabled = false;
        if (lm == null)
            lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }
        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }

        return (gps_enabled || network_enabled) ? true : false;
    }


    public static boolean isEndTimeLarger(String startTime , String endTime){
        long elapsed = 0 ;
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Date d1 = sdf.parse(startTime);
            Date d2 = sdf.parse(endTime);
            elapsed = d2.getTime() - d1.getTime();

        }catch (Exception e){
            e.getStackTrace();
        }
        return elapsed>0;
    }



}
