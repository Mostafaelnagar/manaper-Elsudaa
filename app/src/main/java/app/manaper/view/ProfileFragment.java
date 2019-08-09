package app.manaper.view;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import app.manaper.R;
import app.manaper.base.BaseFragment;
import app.manaper.base.MovementManager;
import app.manaper.base.PopUpMenus;
import app.manaper.base.constantsutils.Codes;
import app.manaper.base.constantsutils.WebServices;
import app.manaper.base.filesutils.FileOperations;
import app.manaper.base.filesutils.VolleyFileObject;
import app.manaper.databinding.FragmentProfileBinding;
import app.manaper.models.cities.Cities;
import app.manaper.viewModels.ProfileViewModel;

public class ProfileFragment extends BaseFragment {
    FragmentProfileBinding profileBinding;
    ProfileViewModel profileViewModel;
    List<Cities> popUpItems;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        profileBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);

        profileViewModel = new ProfileViewModel();
        profileBinding.setProfileViewModel(profileViewModel);
        liveDataListeners();
        return profileBinding.getRoot();
    }

    private void liveDataListeners() {
        profileViewModel.getClicksMutableLiveData().observe(this, result -> {

            if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
            } else if (result == Codes.SELECT_PROFILE_IMAGE) {
                selectProfilePhoto();
            } else if (result == Codes.SHOW_MESSAGE) {
                showMessage(profileViewModel.getReturnedMessage());
            } else if (result == Codes.SELECT_CITIES) {
                showCities();
            } else {

            }

        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Codes.FILE_TYPE_IMAGE) {
            if (data != null) {
                VolleyFileObject volleyFileObject = FileOperations.getVolleyFileObject(getActivity(), data, WebServices.IMAGE, requestCode);
                profileBinding.userProfileImgae.setImageBitmap(Objects.requireNonNull(volleyFileObject).getCompressObject().getImage());
                profileViewModel.getVolleyFileObjects().add(volleyFileObject);
            }
        }
    }

    private void showCities() {
        popUpItems = profileViewModel.citiesList;
        PopUpMenus.showCitiesPopUp(getActivity(), profileBinding.etRegisterUserTypes, popUpItems).setOnMenuItemClickListener(item -> {
            profileViewModel.setUserType(popUpItems.get(item.getItemId()).getName());
            profileViewModel.getRegisterRequest().setFK_cities_id(popUpItems.get(item.getItemId()).getId());
            profileViewModel.notifyChange();
            return false;
        });
    }


    private void selectProfilePhoto() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getActivity(), "Permission Denied", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            } else {
                // start picker to get image for cropping and then use the image in cropping activity
                FileOperations.pickImage(getActivity());
            }
        } else {
            FileOperations.pickImage(getActivity());

        }
    }
}
