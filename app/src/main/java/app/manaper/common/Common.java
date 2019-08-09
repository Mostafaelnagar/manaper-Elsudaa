package app.manaper.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import app.manaper.R;
import app.manaper.models.addTrip.places.PlacesDate;
import app.manaper.models.addTrip.places.PlacesItem;
import app.manaper.models.addTrip.places.PlacesName;
import app.manaper.models.login.UserItem;

public class Common {
    public static UserItem userItem = null;
    public static List<String> placesItemList = new ArrayList<>();
    public static List<String> placesNames = new ArrayList<>();
    public static List<String> placesDates = new ArrayList<>();
    public static int visiable;
    public static final String stackValue = "STACK";
    public static   String type = "";
    public static   String activeAccount = "";

    public static void replaceFragment(Context context, int layout_Id, Fragment fragment) {
        FragmentActivity activity = (FragmentActivity) context;
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(layout_Id, fragment);
        fragmentTransaction.commit();
    }


    public static void addNewView(View rootView, Context context, String placeTime, String placesDate, String placesName) {
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = vi.inflate(R.layout.attractive_item, null);

        // insert into main view
        LinearLayout linearLayout = rootView.findViewById(R.id.addedView);
        linearLayout.addView(v, linearLayout.getChildCount());
        // fill in any details dynamically here
        LinearLayout child = (LinearLayout) v.findViewById(R.id.lin_Child);
        int idx = linearLayout.indexOfChild(child);
        child.setTag(Integer.toString(idx));
        TextView textView = (TextView) v.findViewById(R.id.test);
        textView.setText(placesName);
        placesItemList.add(idx, placeTime);
        placesNames.add(idx, placesName);
        placesDates.add(idx, placesDate);
        child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.removeView(v);
                String index = (String) v.getTag();
                placesItemList.remove(Integer.parseInt(index));
                placesNames.remove(Integer.parseInt(index));
                placesDates.remove(Integer.parseInt(index));
            }
        });
    }
}
