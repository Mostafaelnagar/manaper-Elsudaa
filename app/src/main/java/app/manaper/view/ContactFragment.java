package app.manaper.view;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;
import app.manaper.R;
import app.manaper.base.BaseFragment;
import app.manaper.databinding.FragmentContactBinding;
import app.manaper.viewModels.SettingsViewModels;
import app.manaper.viewModels.SignUpViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends BaseFragment {
    FragmentContactBinding contactBinding;
    SettingsViewModels settingsViewModels;

    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        contactBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_contact, container, false);

        settingsViewModels = new SettingsViewModels();
        contactBinding.setContactViewModels(settingsViewModels);
        settingsViewModels.getContactData();
        liveDataListeners();
        return contactBinding.getRoot();
    }

    private void liveDataListeners() {
        settingsViewModels.getClicksMutableLiveData().observe(this, result -> {
            if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
            }
        });
    }

}
