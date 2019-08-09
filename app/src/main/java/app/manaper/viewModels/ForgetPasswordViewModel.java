package app.manaper.viewModels;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;

import androidx.databinding.Bindable;
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
import app.manaper.models.SuccessResponse;
import app.manaper.models.login.ResponseLogin;
import app.manaper.models.login.UserItem;
import app.manaper.models.validation.SendCodeRequest;

public class ForgetPasswordViewModel extends BaseViewModel {
    SendCodeRequest sendCodeRequest;

    public ForgetPasswordViewModel() {
        sendCodeRequest = new SendCodeRequest();

    }

    @Bindable
    public SendCodeRequest getSendCodeRequest() {
        return sendCodeRequest;
    }


    public void sendCode() {
        if (getSendCodeRequest().isUserPhone()) {
            accessLoadingBar(View.VISIBLE);
            new ConnectionHelper(new ConnectionListener() {
                @Override
                public void onRequestSuccess(Object response) {
                    accessLoadingBar(View.GONE);
                    SuccessResponse successResponse = (SuccessResponse) response;
                    if (successResponse.getCode() == WebServices.SUCCESS) {
                        Common.type = "forget";
                        Log.i("onRequestSuccess", "onRequestSuccess: " + Common.type);
                        getClicksMutableLiveData().setValue(Codes.SEND_CODE_SCREEN);
                    } else
                        Toast.makeText(MyApplication.getInstance().getApplicationContext(), "" + ((SuccessResponse) response).getMsg(), Toast.LENGTH_SHORT).show();
                }
            }).requestJsonObject(Request.Method.POST, URLS.SEND_CODE, sendCodeRequest, SuccessResponse.class);
        } else
            Toast.makeText(MyApplication.getInstance().getApplicationContext(), MyApplication.getInstance().getApplicationContext().getResources().getString(R.string.emptyData), Toast.LENGTH_SHORT).show();
    }

    public void submitCode() {
        if (getSendCodeRequest().isCodeValid()) {
            accessLoadingBar(View.VISIBLE);
            new ConnectionHelper(new ConnectionListener() {
                @Override
                public void onRequestSuccess(Object response) {
                    accessLoadingBar(View.GONE);
                    ResponseLogin successResponse = (ResponseLogin) response;
                    if (successResponse.getCode() == WebServices.SUCCESS) {
                        UserItem userItem = successResponse.getUserItem();
                        UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).userLogin(userItem);
                        if (Common.type.equals("forget")) {
                            Common.type = "";
                            getClicksMutableLiveData().setValue(Codes.FORGOT_PASSWORD_SCREEN);
                        } else
                            getClicksMutableLiveData().setValue(Codes.HOME_SCREEN);

                    } else
                        Toast.makeText(MyApplication.getInstance().getApplicationContext(), "" + ((ResponseLogin) response).getMsg(), Toast.LENGTH_SHORT).show();
                }
            }).requestJsonObject(Request.Method.POST, URLS.CHECK_CODE, sendCodeRequest, ResponseLogin.class);
        } else
            Log.i("sendCode", "sendCode: ");
    }

    public void changePassword() {
        if (getSendCodeRequest().isPasswordsValid()) {
            if (getSendCodeRequest().getConfirmPassword().equals(getSendCodeRequest().getPassword())) {
                accessLoadingBar(View.VISIBLE);
                new ConnectionHelper(new ConnectionListener() {
                    @Override
                    public void onRequestSuccess(Object response) {
                        accessLoadingBar(View.GONE);
                        SuccessResponse successResponse = (SuccessResponse) response;
                        if (successResponse.getCode() == WebServices.SUCCESS) {
                            getClicksMutableLiveData().setValue(Codes.HOME_SCREEN);
                        } else
                            Toast.makeText(MyApplication.getInstance().getApplicationContext(), "" + ((SuccessResponse) response).getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }).requestJsonObject(Request.Method.POST, URLS.CAHNGE_PASSWORD, sendCodeRequest, SuccessResponse.class);
            } else
                Toast.makeText(MyApplication.getInstance().getApplicationContext(), MyApplication.getInstance().getResources().getString(R.string.notMatchedPasswords), Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(MyApplication.getInstance().getApplicationContext(), MyApplication.getInstance().getResources().getString(R.string.emptyData), Toast.LENGTH_SHORT).show();
    }

}