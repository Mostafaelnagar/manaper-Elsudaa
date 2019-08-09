package app.manaper.view.mandop;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import app.manaper.R;
import app.manaper.base.BaseFragment;
import app.manaper.base.UserPreferenceHelper;
import app.manaper.base.constantsutils.Codes;
import app.manaper.databinding.FragmentMandopDepartureOutBinding;
import app.manaper.viewModels.delegate.DelegateTripDetailsViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class MandopDepartureOutFragment extends BaseFragment {
    FragmentMandopDepartureOutBinding departureOutBinding;
    DelegateTripDetailsViewModel viewModel;


    public MandopDepartureOutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        departureOutBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mandop_departure_out, container, false);
        viewModel = new DelegateTripDetailsViewModel();
        departureOutBinding.setTripDetailsViewModels(viewModel);
        liveDataListeners();

        return departureOutBinding.getRoot();
    }

    private void liveDataListeners() {
        viewModel.getClicksMutableLiveData().observe(this, result -> {
            if (result == Codes.BACK) {
                UserPreferenceHelper.getInstance(getActivity()).removeArrDepsId();
                ((Activity) getActivity()).finish();
            } else if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
            }
        });
    }
}
