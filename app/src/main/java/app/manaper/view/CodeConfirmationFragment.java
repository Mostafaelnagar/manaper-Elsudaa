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
import app.manaper.common.Common;
import app.manaper.databinding.FragmentCodeConfirmationBinding;
import app.manaper.viewModels.ForgetPasswordViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class CodeConfirmationFragment extends BaseFragment {
    FragmentCodeConfirmationBinding confirmationBinding;
    ForgetPasswordViewModel passwordViewModel;


    public CodeConfirmationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        confirmationBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_code_confirmation, container, false);
        passwordViewModel = new ForgetPasswordViewModel();
        confirmationBinding.setForgetPasswordViewModel(passwordViewModel);
        liveDataListeners();
        return confirmationBinding.getRoot();
    }

    private void liveDataListeners() {
        passwordViewModel.getClicksMutableLiveData().observe(this, result -> {
            if (result == Codes.HOME_SCREEN) {
                MovementManager.startMainActivity(getActivity(), result);
            } else if (result == Codes.FORGOT_PASSWORD_SCREEN) {
                MovementManager.addFragment(getActivity(), new ForgetPasswordResetFragment(), Common.stackValue);
            } else if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
            }
        });
    }

}
