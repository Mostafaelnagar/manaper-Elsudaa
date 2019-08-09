package app.manaper.models.settings.suggestion;


import android.util.Log;

import com.google.gson.annotations.SerializedName;

public class SuggestionRequest {

    @SerializedName("FK_users_id")
    private String fKUsersId;

    @SerializedName("suggestions_text")
    private String suggestionsText;

    @SerializedName("suggestions_updated_at")
    private String suggestionsUpdatedAt;

    @SerializedName("suggestions_id")
    private int suggestionsId;

    @SerializedName("suggestions_created_at")
    private String suggestionsCreatedAt;

    @SerializedName("suggestions_title")
    private String suggestionsTitle;

    public void setFKUsersId(String fKUsersId) {
        this.fKUsersId = fKUsersId;
    }

    public String getFKUsersId() {
        return fKUsersId;
    }

    public void setSuggestionsText(String suggestionsText) {
        this.suggestionsText = suggestionsText;
    }

    public String getSuggestionsText() {
        return suggestionsText;
    }

    public void setSuggestionsUpdatedAt(String suggestionsUpdatedAt) {
        this.suggestionsUpdatedAt = suggestionsUpdatedAt;
    }

    public String getSuggestionsUpdatedAt() {
        return suggestionsUpdatedAt;
    }

    public void setSuggestionsId(int suggestionsId) {
        this.suggestionsId = suggestionsId;
    }

    public int getSuggestionsId() {
        return suggestionsId;
    }

    public void setSuggestionsCreatedAt(String suggestionsCreatedAt) {
        this.suggestionsCreatedAt = suggestionsCreatedAt;
    }

    public String getSuggestionsCreatedAt() {
        return suggestionsCreatedAt;
    }

    public void setSuggestionsTitle(String suggestionsTitle) {
        this.suggestionsTitle = suggestionsTitle;
    }

    public String getSuggestionsTitle() {
        return suggestionsTitle;
    }

    @Override
    public String toString() {
        return
                "SuggestionRequest{" +
                        "fK_users_id = '" + fKUsersId + '\'' +
                        ",suggestions_text = '" + suggestionsText + '\'' +
                        ",suggestions_updated_at = '" + suggestionsUpdatedAt + '\'' +
                        ",suggestions_id = '" + suggestionsId + '\'' +
                        ",suggestions_created_at = '" + suggestionsCreatedAt + '\'' +
                        ",suggestions_title = '" + suggestionsTitle + '\'' +
                        "}";
    }

    public boolean isVaild() {
        if (getSuggestionsTitle() == null && getSuggestionsText() == null)
            return false;
        else if (getSuggestionsTitle().equals(" ") && getSuggestionsText().equals(" ")) {
            return false;
        }else if (getSuggestionsTitle().equals("") && getSuggestionsText().equals("")) {
            return false;
        } else
            Log.i("isVaild", "isVaild: " + getSuggestionsTitle());
        return true;
    }

}