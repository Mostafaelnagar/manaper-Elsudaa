package app.manaper.view.mandop;


import android.app.Activity;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.manaper.R;
import app.manaper.base.BaseFragment;
import app.manaper.base.PopUpMenus;
import app.manaper.base.UserPreferenceHelper;
import app.manaper.base.constantsutils.Codes;
import app.manaper.databinding.FragmentMandopFirstDirectionBinding;
import app.manaper.models.delegate.firstDetails.DelegatePlaces;
import app.manaper.viewModels.delegate.DelegateTripDetailsViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class MandopFirstDirectionFragment extends BaseFragment {
    FragmentMandopFirstDirectionBinding firstDirectionBinding;
    DelegateTripDetailsViewModel viewModel;


    public MandopFirstDirectionFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        firstDirectionBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mandop_first_direction, container, false);
        viewModel = new DelegateTripDetailsViewModel();
        firstDirectionBinding.setTripDetailsViewModels(viewModel);
        liveDataListeners();

        return firstDirectionBinding.getRoot();
    }

    private void liveDataListeners() {
        viewModel.getClicksMutableLiveData().observe(this, result -> {
            if (result == Codes.BACK) {
                UserPreferenceHelper.getInstance(getActivity()).removeSecDepsId();
                ((Activity) getActivity()).finish();
            } else if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
            } else if (result == Codes.SELECT_CITIES) {
                showPlaces();
            }
        });
    }

    private void showPlaces() {
        PopUpMenus.showDelegatePlacesPopUp(getActivity(), firstDirectionBinding.places, viewModel.getPlacesList()).setOnMenuItemClickListener(item -> {
            viewModel.setPlaces(viewModel.getPlacesList().get(item.getItemId()).getPlacesName());
            viewModel.setDate(viewModel.getPlacesList().get(item.getItemId()).getPlacesDate());
            viewModel.setTime(viewModel.getPlacesList().get(item.getItemId()).getPlacesTime());
            viewModel.notifyChange();
            return false;
        });
    }


}
