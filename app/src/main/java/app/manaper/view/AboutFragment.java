package app.manaper.view;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.manaper.R;
import app.manaper.base.BaseFragment;
import app.manaper.databinding.FragmentAboutBinding;
import app.manaper.viewModels.SettingsViewModels;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends BaseFragment {

    FragmentAboutBinding fragmentAbout;
    SettingsViewModels settingsViewModels;

    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentAbout = DataBindingUtil.inflate(inflater, R.layout.fragment_about, container, false);

        settingsViewModels = new SettingsViewModels();
        fragmentAbout.setAboutViewModels(settingsViewModels);
        settingsViewModels.getAboutData();
        liveDataListeners();
        return fragmentAbout.getRoot();
    }

    private void liveDataListeners() {
        
        settingsViewModels.getClicksMutableLiveData().observe(this, result -> {
            if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
            }
        });
    }

}
