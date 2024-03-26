package com.narayanan.librarymanagement.login;

import com.narayanan.librarymanagement.LibraryManagement;
import com.narayanan.librarymanagement.librarysetup.LibrarySetUpView;

import java.util.Scanner;

public class LoginView {

    private LoginModel loginModel;

    public LoginView() {
        loginModel = new LoginModel(this);
    }

    public void init() {
        System.out.println("--- " + LibraryManagement.getInstance().getAppName() + " ---\nVersion " + LibraryManagement.getInstance().getAppVersion());
        System.out.println("\n\nPlease login to proceed.");
        login();
    }

    private void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter the user name");
        String userName = scanner.next();
        System.out.println("Enter the password");
        String password = scanner.next();
        loginModel.validateUser(userName, password);
    }

    public void showAlert(String alertText) {
        System.out.println(alertText);
        login();
    }

    public void onSuccess() {
        System.out.flush();
        System.out.println("\n\nLogin Successful...\n----Welcome to " + LibraryManagement.getInstance().getAppName() + " -v" + LibraryManagement.getInstance().getAppVersion() + "___");
        LibrarySetUpView librarySetUpView = new LibrarySetUpView();
        librarySetUpView.init();
    }
}
