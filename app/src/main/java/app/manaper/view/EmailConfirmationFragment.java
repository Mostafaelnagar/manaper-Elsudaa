package app.manaper.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import app.manaper.R;
import app.manaper.base.BaseFragment;
import app.manaper.base.MovementManager;
import app.manaper.base.constantsutils.Codes;
import app.manaper.common.Common;
import app.manaper.databinding.FragmentEmailConfirmationBinding;
import app.manaper.databinding.FragmentLoginBinding;
import app.manaper.viewModels.ForgetPasswordViewModel;
import app.manaper.viewModels.LoginViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmailConfirmationFragment extends BaseFragment {
    FragmentEmailConfirmationBinding confirmationBinding;
    ForgetPasswordViewModel passwordViewModel;

    public EmailConfirmationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        confirmationBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_email_confirmation, container, false);
        passwordViewModel = new ForgetPasswordViewModel();
        confirmationBinding.setForgetPasswordViewModel(passwordViewModel);
        liveDataListeners();
        return confirmationBinding.getRoot();
    }

    private void liveDataListeners() {
        passwordViewModel.getClicksMutableLiveData().observe(this, result -> {
            if (result == Codes.SEND_CODE_SCREEN) {
                MovementManager.addFragment(getActivity(), new CodeConfirmationFragment(), Common.stackValue);
            } else if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
            }
        });
    }
}
