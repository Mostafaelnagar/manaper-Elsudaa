package app.manaper.viewModels;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.BindingAdapter;
import app.manaper.base.BaseViewModel;
import app.manaper.base.UserPreferenceHelper;
import app.manaper.base.constantsutils.Codes;
import app.manaper.base.constantsutils.WebServices;
import app.manaper.base.filesutils.VolleyFileObject;
import app.manaper.base.volleyutils.ConnectionHelper;
import app.manaper.base.volleyutils.ConnectionListener;
import app.manaper.base.volleyutils.MyApplication;
import app.manaper.base.volleyutils.URLS;
import app.manaper.models.cities.Cities;
import app.manaper.models.cities.CitiesResponse;
import app.manaper.models.login.ResponseLogin;
import app.manaper.models.login.UserItem;
import app.manaper.models.register.RegisterRequest;
import app.manaper.models.updateprofile.UpdateProfileItem;
import app.manaper.models.updateprofile.UpdateUserResponse;

public class  ProfileViewModel extends BaseViewModel {
    private ArrayList<VolleyFileObject> volleyFileObjects;
    private String userType;
    public RegisterRequest registerRequest;
    public List<Cities> citiesList;
    public String ProfileImage;

    public ProfileViewModel() {
        volleyFileObjects = new ArrayList<>();
        registerRequest = new RegisterRequest();

        getCities();
//        getUserData();
        registerRequest.setUsers_name(UserPreferenceHelper.getInstance(MyApplication.getInstance()).getUserData().getUsersName());
        registerRequest.setEmail(UserPreferenceHelper.getInstance(MyApplication.getInstance()).getUserData().getEmail());
        registerRequest.setUsers_phonenumber(UserPreferenceHelper.getInstance(MyApplication.getInstance()).getUserData().getUsersPhonenumber());
        registerRequest.setFK_cities_id(UserPreferenceHelper.getInstance(MyApplication.getInstance()).getUserData().getFKCitiesId());
        userType = UserPreferenceHelper.getInstance(MyApplication.getInstance()).getUserData().getCity();
        ProfileImage = UserPreferenceHelper.getInstance(MyApplication.getInstance()).getUserData().getUsersImg();

    }

    public String getProfileImage() {
        return ProfileImage;
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


    public void updateUser() {
        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                accessLoadingBar(View.GONE);
                UpdateUserResponse userResponse = (UpdateUserResponse) response;
                if (userResponse.getCode() == WebServices.SUCCESS) {
                    UpdateProfileItem data = userResponse.getData();
                    UserItem userItem = new UserItem();
                    userItem.setCity(data.getCity());
                    userItem.setEmail(data.getEmail());
                    userItem.setJwt(data.getJwt());
                    userItem.setUsersId(data.getUsersId());
                    userItem.setUsersImg(data.getUsersImg());
                    userItem.setUsersName(data.getUsersName());
                    userItem.setUsersType(data.getUsersType());
                    userItem.setActiveStatus(1);
                    userItem.setFKCitiesId(data.getFKCitiesId());
                    userItem.setUsersPhonenumber(data.getUsersPhonenumber());
                    UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).userLogin(userItem);
                    Toast.makeText(MyApplication.getInstance().getApplicationContext(), userResponse.getMsg(), Toast.LENGTH_SHORT).show();

                } else
                    Toast.makeText(MyApplication.getInstance().getApplicationContext(), userResponse.getMsg(), Toast.LENGTH_SHORT).show();
            }

        }).multiPartConnect(URLS.UPDATE_PROFILE, registerRequest, volleyFileObjects, UpdateUserResponse.class);

    }


    @BindingAdapter({"profileImage"})
    public static void loadImage(ImageView view, String profileImage) {
        ConnectionHelper.loadImage(view, profileImage);

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