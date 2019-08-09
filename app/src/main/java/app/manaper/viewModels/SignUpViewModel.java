package app.manaper.viewModels;

 import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;

import java.util.ArrayList;
import java.util.List;

import app.manaper.R;
import app.manaper.base.BaseViewModel;
import app.manaper.base.UserPreferenceHelper;
import app.manaper.base.constantsutils.Codes;
 import app.manaper.base.filesutils.VolleyFileObject;
import app.manaper.base.volleyutils.ConnectionHelper;
import app.manaper.base.volleyutils.ConnectionListener;
import app.manaper.base.volleyutils.MyApplication;
import app.manaper.base.volleyutils.URLS;
import app.manaper.models.SuccessResponse;
import app.manaper.models.cities.Cities;
import app.manaper.models.cities.CitiesResponse;
 import app.manaper.models.register.RegisterRequest;

public class SignUpViewModel extends BaseViewModel {
    private ArrayList<VolleyFileObject> volleyFileObjects;
    private String userType;
    public RegisterRequest registerRequest;
    public List<Cities> citiesList;

    public SignUpViewModel() {
        volleyFileObjects = new ArrayList<>();
        registerRequest = new RegisterRequest();
        getCities();
    }

    public RegisterRequest getRegisterRequest() {
        return registerRequest;
    }

    public void PickUpProfileImage() {
        getClicksMutableLiveData().setValue(Codes.SELECT_PROFILE_IMAGE);
    }

    public void accountTypeClick() {
        getClicksMutableLiveData().setValue(Codes.SELECT_CITIES);
    }

    public void createAccount() {
        companyRegister();
    }

    private void companyRegister() {
        registerRequest.setFirebase_token(UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).getToken());
        if (getRegisterRequest().isValid()) {
            accessLoadingBar(View.VISIBLE);
            new ConnectionHelper(new ConnectionListener() {
                @Override
                public void onRequestSuccess(Object response) {
                    accessLoadingBar(View.GONE);
                    SuccessResponse userResponse = (SuccessResponse) response;
                    if (userResponse.getCode() == 405) {
                        UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).addUserPhone(registerRequest.getUsers_phonenumber());
                        getClicksMutableLiveData().setValue(Codes.SEND_CODE_SCREEN);
                    } else {
                        accessLoadingBar(View.GONE);
                        Toast.makeText(MyApplication.getInstance().getApplicationContext(), userResponse.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }


            }).multiPartConnect(URLS.REGISTER, registerRequest, volleyFileObjects, SuccessResponse.class);
        } else
            Toast.makeText(MyApplication.getInstance().

                    getApplicationContext(), MyApplication.

                    getInstance().

                    getResources().

                    getString(R.string.emptyData), Toast.LENGTH_SHORT).

                    show();

    }

    private void getCities() {
        citiesList = new ArrayList<>();
        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                accessLoadingBar(View.GONE);
                CitiesResponse citiesResponse = (CitiesResponse) response;
                citiesList = citiesResponse.getData();
            }
        }).requestJsonObject(Request.Method.GET, URLS.CITIES, new Object(), CitiesResponse.class);
    }

    public ArrayList<VolleyFileObject> getVolleyFileObjects() {
        return volleyFileObjects;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }


}