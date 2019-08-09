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
import app.manaper.base.PopUpMenus;
import app.manaper.base.UserPreferenceHelper;
import app.manaper.base.constantsutils.Codes;
import app.manaper.databinding.FragmentMandopSecondDirectionBinding;
import app.manaper.viewModels.delegate.DelegateTripDetailsViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class MandopSecondDirectionFragment extends BaseFragment {

    FragmentMandopSecondDirectionBinding secondDirectionBinding;
    DelegateTripDetailsViewModel viewModel;

    public MandopSecondDirectionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        secondDirectionBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mandop_second_direction, container, false);
        viewModel = new DelegateTripDetailsViewModel();
        secondDirectionBinding.setTripDetailsViewModels(viewModel);
        liveDataListeners();

        return secondDirectionBinding.getRoot();
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

        PopUpMenus.showDelegatePlacesPopUp(getActivity(), secondDirectionBinding.places, viewModel.getPlacesList()).setOnMenuItemClickListener(item -> {
            viewModel.setPlaces(viewModel.getPlacesList().get(item.getItemId()).getPlacesName());
            viewModel.setDate(viewModel.getPlacesList().get(item.getItemId()).getPlacesDate());
            viewModel.setTime(viewModel.getPlacesList().get(item.getItemId()).getPlacesTime());
            viewModel.notifyChange();
            return false;
        });
    }


}

