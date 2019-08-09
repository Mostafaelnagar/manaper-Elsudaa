package app.manaper.models.validation;

public class SendCodeRequest {
    private String users_phonenumber;
    private String verifications_code;
    private String password;
    private String confirmPassword;


    public String getUsers_phonenumber() {
        return users_phonenumber;
    }

    public void setUsers_phonenumber(String users_phonenumber) {
        this.users_phonenumber = users_phonenumber;
    }

    public String getVerifications_code() {
        return verifications_code;
    }

    public void setVerifications_code(String verifications_code) {
        this.verifications_code = verifications_code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean isUserPhone() {
        return (users_phonenumber != null);
    }

    public boolean isCodeValid() {
        return (verifications_code != null);
    }

    public boolean isPasswordsValid() {
        return (password != null && confirmPassword != null);
    }
}
