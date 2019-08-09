package app.manaper.view;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.manaper.R;
import app.manaper.base.BaseFragment;
import app.manaper.databinding.FragmentNotificationsBinding;
import app.manaper.viewModels.HomeViewModels;
import app.manaper.viewModels.NotificationsViewModels;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationsFragment extends BaseFragment {
    FragmentNotificationsBinding notificationsBinding;
    NotificationsViewModels notificationsViewModels;

    public NotificationsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        notificationsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_notifications, container, false);
        notificationsViewModels = new NotificationsViewModels();
        notificationsBinding.setNotificationsViewModel(notificationsViewModels);
        liveDataListeners();
        return notificationsBinding.getRoot();
    }

    private void liveDataListeners() {
        notificationsViewModels.getClicksMutableLiveData().observe(this, result -> {
            if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
            }
        });
    }
}
