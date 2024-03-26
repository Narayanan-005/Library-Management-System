package com.narayanan.librarymanagement.librarysetup;

import com.narayanan.librarymanagement.LibraryManagement;
import com.narayanan.librarymanagement.datalayer.BackUpRetrive;
import com.narayanan.librarymanagement.datalayer.LibraryDataBase;
import com.narayanan.librarymanagement.login.LoginView;
import com.narayanan.librarymanagement.managebook.ManageBookView;
import com.narayanan.librarymanagement.manageuser.ManageUserView;
import com.narayanan.librarymanagement.booktransaction.BookTransactionView;
import com.narayanan.librarymanagement.model.Library;

import java.util.Scanner;

public class LibrarySetUpView {

    private LibrarySetUpModel librarySetUpModel;

    public LibrarySetUpView() {
        librarySetUpModel = new LibrarySetUpModel(this);
    }

    public void init() {
        librarySetUpModel.startSetUp();
    }

    public void initiateSetUp() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Library Name");
        String libraryName = scanner.nextLine();
        System.out.println("Enter PhoneNumber");
        String phoneNo = scanner.next();
        System.out.println("Enter Email Id");
        String email = scanner.next();
        scanner.nextLine();
        System.out.println("Enter the address");
        String address = scanner.nextLine();
        Library library = new Library();
        library.setLibraryName(libraryName);
        library.setLibraryId(1);
        library.setPhoneNO(phoneNo);
        library.setEmailId(email);
        library.setAddress(address);
        librarySetUpModel.createLibrary(library);
    }

    public void onSetUpComplete(Library library) {
        System.out.println("\n\nLibrary SetUp Completed");
        System.out.println("\nWelcome to - " + library.getLibraryName() + " Library");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1.Manage Books\n2.Manage User\n3.Manage Book Transaction\n4.Log Out\n0.Exit\nEnter Your Choice");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    new ManageBookView().init();
                    break;
                case 2:
                    new ManageUserView().init();
                    break;
                case 3:
                    new BookTransactionView().init();
                    break;
                case 4:
                    new LoginView().init();
                    return;
                case 0:
                    LibraryDataBase.getInstance().backUp();
                    System.out.println("\n--ThankYou for using " + LibraryManagement.getInstance().getAppName() + "--");
                    return;
                default:
                    System.out.println("\nPlease Enter Valid Choice\n");
            }
        }
       /* while (true) {

            System.out.println("\n1.Add Book\n2.Add User\n3.Search Book\n4.Show All Book List\n5.Remove Book" +
                    "\n6.Show Book Details\n7.Assign Book to User\n9.Logout\n0.Exit\nEnter Your Choice");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    new ManageBookView().initAdd();
                    break;
                case 2:
                    new ManageUserView().initAdd();
                    break;
                case 3:
                    new ManageBookView().intiSearch();
                    break;
                case 4:
                    new ManageBookView().initShowBookList();
                    break;
                case 5:
                    new ManageBookView().initRemove();
                    break;
                case 6:
                    new ManageBookView().initShowBook();
                    break;
                case 7:
                    new DueBookview().initAssignBook();
                    break;
                case 9:
                    new LoginView().init();
                    return;
                case 0:
                    System.out.println("\n--ThankYou for using " + LibraryManagement.getInstance().getAppName() + "--");
                    return;
                default:
                    System.out.println("\nPlease Enter Valid Choice\n");

            }
        }*/
    }

    public void showAlert(String alertText) {
        System.out.println("\n" + alertText + "\n");
        initiateSetUp();
    }
}
