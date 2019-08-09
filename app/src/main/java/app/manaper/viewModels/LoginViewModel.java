package app.manaper.viewModels;

import android.view.View;
import android.widget.Toast;

import androidx.databinding.Bindable;

import com.android.volley.Request;

import app.manaper.R;
import app.manaper.base.BaseViewModel;
import app.manaper.base.UserPreferenceHelper;
import app.manaper.base.constantsutils.Codes;
import app.manaper.base.constantsutils.WebServices;
import app.manaper.base.volleyutils.ConnectionHelper;
import app.manaper.base.volleyutils.ConnectionListener;
import app.manaper.base.volleyutils.MyApplication;
import app.manaper.base.volleyutils.URLS;
import app.manaper.common.Common;
import app.manaper.models.login.LoginRequest;
import app.manaper.models.login.ResponseLogin;
import app.manaper.models.login.UserItem;

public class LoginViewModel extends BaseViewModel {
    LoginRequest loginRequest;
    public boolean isEmpty = true;

    public LoginViewModel() {

        loginRequest = new LoginRequest();
    }

    @Bindable
    public LoginRequest getUserDetails() {
        return loginRequest;
    }

    public void signUpCompany() {
        getClicksMutableLiveData().setValue(Codes.REGISTER_SCREEN);
    }

    public void forgetPassword() {
        getClicksMutableLiveData().setValue(Codes.FORGOT_PASSWORD_SCREEN);
    }

    public void signIn() {
        goLogin();
    }

    private void goLogin() {
        loginRequest.setFirebase_token(UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).getToken());
        if (getUserDetails().isValid()) {
            accessLoadingBar(View.VISIBLE);
            new ConnectionHelper(new ConnectionListener() {
                @Override
                public void onRequestSuccess(Object response) {
                    accessLoadingBar(View.GONE);
                    ResponseLogin userResponse = (ResponseLogin) response;
                    if (userResponse.getCode() == WebServices.SUCCESS) {
                        UserItem userItem = userResponse.getUserItem();
                        UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).userLogin(userItem);
                        getClicksMutableLiveData().setValue(Codes.HOME_SCREEN);
                    } else if (userResponse.getCode() == 405) {
                        Toast.makeText(MyApplication.getInstance().getApplicationContext(), userResponse.getMsg(), Toast.LENGTH_SHORT).show();
                        getClicksMutableLiveData().setValue(Codes.ENTER_CODE_SCREEN);

                    } else
                        Toast.makeText(MyApplication.getInstance().getApplicationContext(), userResponse.getMsg(), Toast.LENGTH_SHORT).show();

                }
            }).requestJsonObject(Request.Method.POST, URLS.LOGIN, loginRequest, ResponseLogin.class);
            setEmpty(false);
        } else

            Toast.makeText(MyApplication.getInstance().

                    getApplicationContext(), MyApplication.getInstance().

                    getApplicationContext().getResources().getString(R.string.emptyData), Toast.LENGTH_SHORT).

                    show();
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        notifyChange();
        isEmpty = empty;
    }
}