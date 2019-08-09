package app.manaper.view;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import app.manaper.R;
import app.manaper.base.BaseFragment;
import app.manaper.base.MovementManager;
import app.manaper.base.PopUpMenus;
import app.manaper.base.constantsutils.Codes;
import app.manaper.base.constantsutils.WebServices;
import app.manaper.base.filesutils.FileOperations;
import app.manaper.base.filesutils.VolleyFileObject;
import app.manaper.common.Common;
import app.manaper.databinding.FragmentSignUpBinding;
import app.manaper.models.cities.Cities;
import app.manaper.viewModels.SignUpViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends BaseFragment {
    FragmentSignUpBinding signUpBinding;
    SignUpViewModel signUpViewModel;
    List<Cities> popUpItems;

    public SignUpFragment() {
        // Required empty public constructor
        popUpItems = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        signUpBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false);

        signUpViewModel = new SignUpViewModel();
        signUpBinding.setSignUpViewModel(signUpViewModel);
        liveDataListeners();
        return signUpBinding.getRoot();
    }

    private void liveDataListeners() {
        signUpViewModel.getClicksMutableLiveData().observe(this, result -> {

            if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
            } else if (result == Codes.SELECT_PROFILE_IMAGE) {
                selectProfilePhoto();
            } else if (result == Codes.SELECT_CITIES) {
                showCities();
            } else if (result == Codes.SEND_CODE_SCREEN) {
//                MovementManager.startActivity(getActivity(), result);
            MovementManager.addFragment(getActivity(),new CodeConfirmationFragment(), Common.stackValue);
            }

        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Codes.FILE_TYPE_IMAGE) {
            if (data != null) {
                VolleyFileObject volleyFileObject = FileOperations.getVolleyFileObject(getActivity(), data, WebServices.IMAGE, requestCode);
                signUpBinding.userProfileImgae.setImageBitmap(Objects.requireNonNull(volleyFileObject).getCompressObject().getImage());
                signUpViewModel.getVolleyFileObjects().add(volleyFileObject);
            }
        }
    }

    private void showCities() {
        popUpItems = signUpViewModel.citiesList;
        PopUpMenus.showCitiesPopUp(getActivity(), signUpBinding.etRegisterUserTypes, popUpItems).setOnMenuItemClickListener(item -> {
            signUpViewModel.setUserType(popUpItems.get(item.getItemId()).getName());
            signUpViewModel.getRegisterRequest().setFK_cities_id(popUpItems.get(item.getItemId()).getId());
            signUpViewModel.notifyChange();
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
