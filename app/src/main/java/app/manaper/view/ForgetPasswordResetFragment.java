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
import app.manaper.databinding.FragmentCodeConfirmationBinding;
import app.manaper.databinding.FragmentForgetPasswordResetBinding;
import app.manaper.viewModels.ForgetPasswordViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgetPasswordResetFragment extends BaseFragment {
    FragmentForgetPasswordResetBinding passwordResetBinding;
    ForgetPasswordViewModel passwordViewModel;

    public ForgetPasswordResetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        passwordResetBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_forget_password_reset, container, false);
        passwordViewModel = new ForgetPasswordViewModel();
        passwordResetBinding.setForgetPasswordViewModel(passwordViewModel);
        liveDataListeners();
        return passwordResetBinding.getRoot();
    }

    private void liveDataListeners() {
        passwordViewModel.getClicksMutableLiveData().observe(this, result -> {
            if (result == Codes.HOME_SCREEN) {
                MovementManager.startMainActivity(getActivity(), result);
            } else if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
            }
        });
    }

}
