package com.narayanan.librarymanagement;

import com.narayanan.librarymanagement.datalayer.LibraryDataBase;
import com.narayanan.librarymanagement.login.LoginView;
import com.narayanan.librarymanagement.model.Book;

import java.lang.String;

public class LibraryManagement {
    private static LibraryManagement libraryManagement;
    private String appName = "Library Management System";
    private String appVersion = "0.0.1";

    private LibraryManagement() {

    }

    public String getAppName() {
        return appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void create() {
        LibraryDataBase.getInstance().retrive();
        LoginView loginView = new LoginView();
        loginView.init();
    }

    public static LibraryManagement getInstance() {
        if (libraryManagement == null) {
            libraryManagement = new LibraryManagement();
        }
        return libraryManagement;
    }

    public static void main(String[] args) {
        LibraryManagement.getInstance().create();
    }


}
