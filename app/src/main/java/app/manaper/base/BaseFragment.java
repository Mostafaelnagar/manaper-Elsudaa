package app.manaper.base;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.fragment.app.Fragment;

import app.manaper.view.BaseActivity;
import app.manaper.view.MainActivity;

public class BaseFragment extends Fragment {
    Context context;


    public void accessLoadingBar(int visiablity) {
        try {
            if (visiablity == View.VISIBLE) {
                ((BaseActivity) context).activityBaseBinding.pbBaseLoadingBar.setVisibility(visiablity);

                ((BaseActivity) context).activityBaseBinding.pbBaseLoadingBar.start();
            } else {
                ((BaseActivity) context).activityBaseBinding.pbBaseLoadingBar.setVisibility(visiablity);
                ((BaseActivity) context).activityBaseBinding.pbBaseLoadingBar.stop();
            }

        } catch (ClassCastException e) {
            e.getStackTrace();
        }
        try {
            if (visiablity == View.VISIBLE) {
                ((MainActivity) context).activityBaseBinding.pbBaseLoadingBar.setVisibility(visiablity);
                ((MainActivity) context).activityBaseBinding.pbBaseLoadingBar.start();
            } else {
                ((MainActivity) context).activityBaseBinding.pbBaseLoadingBar.setVisibility(visiablity);
                ((MainActivity) context).activityBaseBinding.pbBaseLoadingBar.stop();
            }
        } catch (ClassCastException e) {
            e.getStackTrace();
        }
    }

    public void showMessage(String message) {
        //beacuse of handling snack bar
        ((BaseActivity) context).showMessage(message);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.context = null;
    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        this.context = context;
    }


}
