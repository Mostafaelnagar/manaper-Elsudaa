package app.manaper.models.register;

import app.manaper.base.UserPreferenceHelper;

public class RegisterRequest {
    private String users_name;
    private String email;
    private String password;
    private String users_phonenumber;
    private int FK_cities_id;
    private String firebase_token;

    public boolean isValid() {
        return (users_name != null && email != null && password != null &&
                users_phonenumber != null && FK_cities_id != 0 && firebase_token != null);
    }

    public String getUsers_name() {
        return users_name;
    }

    public void setUsers_name(String users_name) {
        this.users_name = users_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsers_phonenumber() {
        return users_phonenumber;
    }

    public void setUsers_phonenumber(String users_phonenumber) {
        this.users_phonenumber = users_phonenumber;
    }

    public int getFK_cities_id() {
        return FK_cities_id;
    }

    public void setFK_cities_id(int FK_cities_id) {
        this.FK_cities_id = FK_cities_id;
    }

    public String getFirebase_token() {
        return firebase_token;
    }

    public void setFirebase_token(String firebase_token) {
        this.firebase_token = firebase_token;
    }

}
