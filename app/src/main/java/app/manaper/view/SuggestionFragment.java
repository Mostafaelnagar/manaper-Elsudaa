package app.manaper.view;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.manaper.R;
import app.manaper.base.BaseFragment;
import app.manaper.base.MovementManager;
import app.manaper.base.constantsutils.Codes;
import app.manaper.databinding.FragmentSuggestionBinding;
import app.manaper.viewModels.SettingsViewModels;

/**
 * A simple {@link Fragment} subclass.
 */
public class SuggestionFragment extends BaseFragment {

    FragmentSuggestionBinding suggestionBinding;
    SettingsViewModels settingsViewModels;

    public SuggestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        suggestionBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_suggestion, container, false);

        settingsViewModels = new SettingsViewModels();
        suggestionBinding.setContactViewModels(settingsViewModels);
        liveDataListeners();
        return suggestionBinding.getRoot();
    }

    private void liveDataListeners() {
        settingsViewModels.getClicksMutableLiveData().observe(this, result -> {
            if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
            } else if (result == 1) {
                MovementManager.replaceHomeFragment(getContext(), new SuggestionFragment(), "");

            }
        });
    }
}
