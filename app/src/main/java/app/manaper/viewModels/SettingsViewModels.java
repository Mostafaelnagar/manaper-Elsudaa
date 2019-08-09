package app.manaper.viewModels;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;

import androidx.databinding.Bindable;
import app.manaper.R;
import app.manaper.base.BaseViewModel;
import app.manaper.base.MovementManager;
import app.manaper.base.UserPreferenceHelper;
import app.manaper.base.constantsutils.Codes;
import app.manaper.base.constantsutils.WebServices;
import app.manaper.base.volleyutils.ConnectionHelper;
import app.manaper.base.volleyutils.ConnectionListener;
import app.manaper.base.volleyutils.MyApplication;
import app.manaper.base.volleyutils.URLS;
import app.manaper.models.settings.GetContactResponse;
import app.manaper.models.settings.suggestion.SuggestionRequest;
import app.manaper.models.settings.suggestion.SuggestionResponse;
import app.manaper.view.SuggestionFragment;

public class SettingsViewModels extends BaseViewModel {
    public GetContactResponse contactResponse;
    public SuggestionRequest suggestionRequest;
    public String suggestHintTitle, suggestHintBody;

    public SettingsViewModels() {
        contactResponse = new GetContactResponse();
        suggestionRequest = new SuggestionRequest();
        suggestionRequest.setFKUsersId(UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).getUserData().getUsersId() + "");
    }

    @Bindable
    public GetContactResponse getGetContactResponse() {
        return contactResponse;
    }

    @Bindable
    public SuggestionRequest getSuggestionRequest() {
        return suggestionRequest;
    }

    public void getContactData() {

        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                accessLoadingBar(View.GONE);
                GetContactResponse userResponse = (GetContactResponse) response;
                if (userResponse.getCode() == WebServices.SUCCESS) {
                    getGetContactResponse().setData(userResponse.getData());
                } else
                    Log.i("onRequestSuccess", "onRequestSuccess: error " + userResponse.getMsg());
                notifyChange();
            }
        }).requestJsonObject(Request.Method.GET, URLS.GET_CONATACT_US, new Object(), GetContactResponse.class);

    }

    public void getAboutData() {

        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                accessLoadingBar(View.GONE);
                GetContactResponse aboutRespo = (GetContactResponse) response;
                if (aboutRespo.getCode() == WebServices.SUCCESS) {
                    getGetContactResponse().setData(aboutRespo.getData());
                }
                notifyChange();
            }
        }).requestJsonObject(Request.Method.GET, URLS.ABOUT_US, contactResponse, GetContactResponse.class);

    }

    public void sendSuggestion() {
        if (getSuggestionRequest().isVaild()) {

            accessLoadingBar(View.VISIBLE);
            new ConnectionHelper(new ConnectionListener() {
                @Override
                public void onRequestSuccess(Object response) {
                    accessLoadingBar(View.GONE);
                    SuggestionResponse suggestionResponse = (SuggestionResponse) response;
                    if (suggestionResponse.getCode() == WebServices.SUCCESS) {
                        Toast.makeText(MyApplication.getInstance().getApplicationContext(), suggestionResponse.getMsg(), Toast.LENGTH_SHORT).show();
                        getClicksMutableLiveData().setValue(1);
                        notifyChange();
                    }
                }

            }).requestJsonObject(Request.Method.POST, URLS.SUGGEST, suggestionRequest, SuggestionResponse.class);

        } else
            Toast.makeText(MyApplication.getInstance().getApplicationContext(), MyApplication.

                    getInstance().

                    getResources().

                    getString(R.string.emptyData), Toast.LENGTH_SHORT).show();
    }

    @Bindable
    public String getSuggestHintTitle() {
        return MyApplication.getInstance().getResources().getString(R.string.suggestTitleText);
    }

    public void setSuggestHintTitle(String suggestHintTitle) {
        notifyChange();
        this.suggestHintTitle = suggestHintTitle;
    }

    @Bindable
    public String getSuggestHintBody() {
        return MyApplication.getInstance().getResources().getString(R.string.suggestBodyHint);

    }

    public void setSuggestHintBody(String suggestHintBody) {
        notifyChange();
        this.suggestHintBody = suggestHintBody;
    }
}
