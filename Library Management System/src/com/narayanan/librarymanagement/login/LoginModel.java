package com.narayanan.librarymanagement.login;

class LoginModel {
    private LoginView loginView;

    LoginModel(LoginView loginView) {
        this.loginView = loginView;
    }

    public void validateUser(String userName, String password) {
        if (isValidUserName(userName)) {
            if (isValidUserPassword(userName, password)) {
                loginView.onSuccess();
            } else {
                loginView.showAlert("Invalid Password");
            }
        } else {
            loginView.showAlert("Invalid User Name");
        }
    }

    private boolean isValidUserPassword(String userName, String password) {

        return userName.equals("Narayanan") && password.equals("Admin");
    }

    private boolean isValidUserName(String userName) {
        return userName.equals("Narayanan");
    }
}
