package app.manaper.view;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.manaper.R;
import app.manaper.base.BaseFragment;
import app.manaper.base.MovementManager;
import app.manaper.base.UserPreferenceHelper;
import app.manaper.base.constantsutils.Codes;
import app.manaper.common.Common;
import app.manaper.databinding.FragmentLoginBinding;
import app.manaper.viewModels.LoginViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment {
    FragmentLoginBinding loginBinding;
    LoginViewModel loginViewModel;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        loginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        loginViewModel = new LoginViewModel();
        loginBinding.setLoginViewModel(loginViewModel);
        liveDataListeners();
        return loginBinding.getRoot();
    }

    private void liveDataListeners() {
        loginViewModel.getClicksMutableLiveData().observe(this, result -> {
            if (result == Codes.REGISTER_SCREEN) {
//                MovementManager.startActivity(getActivity(), result);
                MovementManager.replaceFragment(getActivity(), new SignUpFragment(), Common.stackValue);
            } else if (result == Codes.FORGOT_PASSWORD_SCREEN) {
                MovementManager.replaceFragment(getActivity(), new EmailConfirmationFragment(), Common.stackValue);
            } else if (result == Codes.HOME_SCREEN) {
                MovementManager.startMainActivity(getActivity(), result);
            } else if (result == Codes.ENTER_CODE_SCREEN) {
                MovementManager.replaceFragment(getActivity(), new CodeConfirmationFragment(), Common.stackValue);
            } else if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
            }
        });
    }


}
