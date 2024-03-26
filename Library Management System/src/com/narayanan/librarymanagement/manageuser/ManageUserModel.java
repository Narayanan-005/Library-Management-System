package com.narayanan.librarymanagement.manageuser;


import com.narayanan.librarymanagement.datalayer.LibraryDataBase;
import com.narayanan.librarymanagement.model.Book;
import com.narayanan.librarymanagement.model.User;

import java.util.List;

class ManageUserModel {
    private ManageUserView manageUserView;


    ManageUserModel(ManageUserView manageUserView) {
        this.manageUserView = manageUserView;
    }

    public void insetUser(User user) {
        if (user.getName().length() > 3 && user.getName().length() < 50) {
            if (validPhoneNo(user.getPhoneNo()) && validEmail(user.getEmailId())) {
                if (LibraryDataBase.getInstance().insertUser(user)) {
                    manageUserView.onUserAdded(user);
                } else {
                    manageUserView.onUserExist(user);
                }
            } else {
                manageUserView.onUserFailed("Invalid Phone Number or Email");
            }
        } else {
            manageUserView.onUserFailed("Invalid User Name");
        }
    }

    private boolean validEmail(String emailId) {
        return emailId.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    private boolean validPhoneNo(String phoneNo) {
        return phoneNo.matches("\\d{10}");
    }


    public void showUser() {
        List<User> userList = LibraryDataBase.getInstance().showUserList();
        if (userList.size() == 0) {
            manageUserView.onUserNotFound();
        } else {
            manageUserView.userList(userList);
        }
    }

    public void userDelete(int id) {
        User user = LibraryDataBase.getInstance().deleteUser(id);
        if (user != null) {
            manageUserView.showAlert("User " + user.getName() + " Deleted Sucessfully");
        } else {
            manageUserView.showAlert("User Not found");
        }
    }
}
