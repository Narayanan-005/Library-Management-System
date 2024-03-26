package com.narayanan.librarymanagement.manageuser;

import com.narayanan.librarymanagement.model.User;

import java.util.List;
import java.util.Scanner;

public class ManageUserView {
    private ManageUserModel manageUserModel;

    public ManageUserView() {
        manageUserModel = new ManageUserModel(this);
    }

    public void init() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1.Add User\n2.Show User\n3.Delete User\n0.Home Page\nEnter your Choice");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    initAdd();
                    break;
                case 2:
                    initShow();
                    break;
                case 3:
                    initDelete();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("\nPlease Enter Valid Input\n");
                    init();
            }
        }
    }


    public void initAdd() {
        Scanner scanner = new Scanner(System.in);
        User user = new User();
        System.out.println("\nEnter User Name");
        user.setName(scanner.next());
        System.out.println("Enter Phone Number");
        user.setPhoneNo(scanner.next());
        System.out.println("Enter Email Id");
        user.setEmailId(scanner.next());
        System.out.println("Enter Address");
        user.setAddress(scanner.next());
        manageUserModel.insetUser(user);
    }

    public void onUserAdded(User user) {
        System.out.println("User " + user.getName() + " Added successfully");
        checkForUser();
    }

    public void onUserExist(User user) {
        System.out.println("User " + user.getName() + " Already Exist");
        checkForUser();
    }

    private void checkForUser() {
        System.out.println("\nDo you want to add more User\nType yes/no");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        if (choice.equals("yes")) {
            initAdd();
        } else if (choice.equals("no")) {
            System.out.println("\nThank you for adding user");
        } else {
            System.out.println("Invalid input please enter valid input");
            checkForUser();
        }
    }

    public void onUserFailed(String alertText) {
        System.out.println("\n" + alertText);
        initAdd();
    }

    private void initShow() {
        manageUserModel.showUser();
    }


    public void onUserNotFound() {
        System.out.println("\nUser not Found");
    }

    public void userList(List<User> userList) {
        for (User user : userList) {
            System.out.println("UserId:" + user.getId() + " UserName:" + user.getName());
        }
    }

    private void initDelete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter User Id");
        int id = scanner.nextInt();
        manageUserModel.userDelete(id);
    }

    public void showAlert(String s) {
        System.out.println("\n" + s);
    }
}
