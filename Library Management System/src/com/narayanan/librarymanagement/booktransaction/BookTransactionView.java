package com.narayanan.librarymanagement.booktransaction;

import java.util.Map;
import java.util.Scanner;

public class BookTransactionView {
    private BookTransactionModel bookTransactionModel;

    public BookTransactionView() {
        bookTransactionModel = new BookTransactionModel(this);
    }

    public void init() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1.Get Book\n2.Show User-Book List\n3.Return Book\n0.Home Page\nEnter your choice");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    initGetBookToUser();
                    break;
                case 2:
                    initShowUserBookList();
                    break;
                case 3:
                    initReturnBook();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("\nInvalid Input Enter valid input");
                    init();
            }
        }
    }


    private void initGetBookToUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter User Id");
        int userId = scanner.nextInt();
        System.out.println("\nEnter Book Id");
        int bookId = scanner.nextInt();
        bookTransactionModel.getBookToUser(userId, bookId);
    }


    public void onGetBookFailed(String s) {
        System.out.println("\n" + s);
        chekForBookIssue();
    }

    private void chekForBookIssue() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nDo you want to try again?\nType yes/no");
        String choice = scanner.next();
        if (choice.equalsIgnoreCase("yes")) {
            initGetBookToUser();
        } else if (!choice.equalsIgnoreCase("no")) {
            System.out.println("\nInvalid input Please Enter valid input");
            chekForBookIssue();
        }
    }

    public void showSucessMessage(String s) {
        System.out.println("\n" + s);
    }

    private void initReturnBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter User Id");
        int userId = scanner.nextInt();
        System.out.println("\nEnter Book Id");
        int bookId = scanner.nextInt();
        bookTransactionModel.returnUserBook(userId, bookId);
    }

    public void onReturnBook(long remainigDays) {
        System.out.println("Your due Amount is Rs " + ((remainigDays - 14) * 5));
        Scanner scanner = new Scanner(System.in);
        long amount = scanner.nextLong();
        if (amount == ((remainigDays - 14) * 5)) {
            showSucessMessage("Book Returned Sucessfully");
        } else {
            System.out.println("\nInvalid Input please Try again");
            onReturnBook(remainigDays);
        }
    }

    private void initShowUserBookList() {
        bookTransactionModel.showUserBookList();
    }

    public void showUserBookList(Map<String, String> userBookMap) {
        for (Map.Entry<String, String> entry : userBookMap.entrySet()) {
            System.out.println("UserID:" + entry.getKey().split("-")[0] + " BookID:" + entry.getKey().split("-")[1] + " DateOfBookBorrowed:" + entry.getValue());
        }
    }
}
