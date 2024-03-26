package com.narayanan.librarymanagement.managebook;

import com.narayanan.librarymanagement.model.Book;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class ManageBookView {
    private ManageBookModel manageBookModel;

    public ManageBookView() {
        manageBookModel = new ManageBookModel(this);
    }

    public void init() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1.Add Book\n2.Remove Book\n3.Search Book\n4.Show All Books\n5.Show Book Details\n6.Update Book\n0.Home Page\nEnter your choice");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    initAdd();
                    break;
                case 2:
                    initRemove();
                    break;
                case 3:
                    intiSearch();
                    break;
                case 4:
                    initShowBookList();
                    break;
                case 5:
                    initShowBook();
                    break;
                case 6:
                    initUpdate();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("\nPlease Enter Valid Input\n");
            }
        }
    }

    public void initAdd() {
        Scanner scanner = new Scanner(System.in);
        Book book = new Book();
        System.out.println("\nEnter the book name");
        book.setName(scanner.nextLine());
        System.out.println("Enter Author name");
        book.setAuthor(scanner.nextLine());
        System.out.println("Enter Publication");
        book.setPublication(scanner.nextLine());
        System.out.println("Enter Edition");
        book.setEdition(scanner.nextLine());
        System.out.println("Enter the Journer");
        book.setJourner(scanner.nextLine());
        try {
            System.out.println("Enter available count");
            book.setAvailableCount(scanner.nextInt());
            System.out.println("Enter volume");
            book.setVolume(scanner.nextInt());
        } catch (InputMismatchException e) {
            System.out.println("\n Invalid Input please try again");
            initAdd();
            return;
        }
        manageBookModel.addBook(book);
    }

    public void onBookAdded(Book book) {
        System.out.println("Book " + book.getName() + " Added sucessfully");
        checkForAddBook();
    }

    public void onBookExist(Book book) {
        System.out.println("Book " + book.getName() + " already Exist");
        checkForAddBook();
    }

    private void checkForAddBook() {
        System.out.println("\nDo you want to add more Bookd?\nType yes/no");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        if (choice.equalsIgnoreCase("yes")) {
            initAdd();
        } else if (choice.equalsIgnoreCase("no")) {
            System.out.println("\nThank You for Adding books");
        } else {
            System.out.println("\nInvalid choice ,Please enter valid choice");
            checkForAddBook();
        }
    }

    public void initRemove() {
        System.out.println("\nEnter the book id to remove");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        manageBookModel.removeBook(id);
    }

    public void onBookRemoved() {
        System.out.println("Book Removed Sucessfully");
    }

    public void onBookNotFount() {
        System.out.println("Book not found");
    }


    public void intiSearch() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter search book");
        String key = scanner.next();
        manageBookModel.searchBook(key);
    }

    public void onSearchNotFount() {
        System.out.println("Search not fount");
        checkForSearch();
    }

    public void seachResult(List<Book> seachresult) {
        for (Book book : seachresult) {
            System.out.println("BookId:" + book.getId() + " BookName:" + book.getName());
        }
        checkForSearch();
    }

    private void checkForSearch() {
        System.out.println("\nDo you want to search again\nType yes/no");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        if (choice.equalsIgnoreCase("yes")) {
            intiSearch();
        } else if (!choice.equalsIgnoreCase("no")) {
            System.out.println("\nInvalid choice Enter valid input");
            checkForSearch();
        }
    }

    public void initShowBookList() {
        for (Book book : manageBookModel.showBookList()) {
            System.out.println("BookId:" + book.getId() + " BookName:" + book.getName());
        }
    }

    public void initShowBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter the Book id");
        int id = scanner.nextInt();
        manageBookModel.showBook(id);
    }

    public void onShowBook(Book book) {
        System.out.println("\nName :" + book.getName());
        System.out.println("AuthorName :" + book.getAuthor());
        System.out.println("Edition :" + book.getEdition());
        System.out.println("Journer :" + book.getJourner());
        System.out.println("PublicationName :" + book.getPublication());
        System.out.println("AvaliableCount :" + book.getAvailableCount());
        System.out.println("volume :" + book.getVolume());
    }

    private void initUpdate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter the Book id");
        int bookId = scanner.nextInt();
        Book book = manageBookModel.getBook(bookId);
        if (book != null) {
            bookUpdate(book);
        } else {
            System.out.println("\nInvalid Book Please enter valid Book Id");
            chechForUpdate();
        }
    }

    private void chechForUpdate() {
        System.out.println("\nDo you want to try again\nType yes/no");
        Scanner scanner = new Scanner(System.in);
        String choices = scanner.next();
        if (choices.equalsIgnoreCase("yes")) {
            initUpdate();
        } else if (!choices.equalsIgnoreCase("no")) {
            System.out.println("\nInvalid choice please Enter valid choice");
            chechForUpdate();
        }
    }

    private void bookUpdate(Book book) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n1.Update Edition\n2.Update Avaliable Count\n3.Update Volume");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Enter Edition");
                String edition = scanner.next();
                manageBookModel.updateEdition(edition, book);
                break;
            case 2:
                chechForIncreaseOrDecrease(book);
                break;
            case 3:
                System.out.println("Enter Updated Volume");
                int volume = scanner.nextInt();
                manageBookModel.updateVolume(volume, book);
                break;
            default:
                System.out.println("\nInvalid input please enter valid input");
                initUpdate();
                return;
        }
    }

    private void chechForIncreaseOrDecrease(Book book) {
        System.out.println("\n1.Increase Available Count\n2.Decrease Available Count");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("\nEnter How much");
            int count = scanner.nextInt();
            book.setAvailableCount(book.getAvailableCount()+count);
            manageBookModel.updateAvaliableCount(book);
        } else if (choice == 2) {
            System.out.println("\nEnter How much");
            int count = scanner.nextInt();
            manageBookModel.updateAvaliableCountDecrease(count,book);
        } else {
            System.out.println("\nInvalid Input Enter Valid Input");
            chechForIncreaseOrDecrease(book);
        }
    }

    public void showMessage(String message) {
        System.out.println("\n" + message);
    }
}
