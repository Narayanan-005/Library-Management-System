package com.narayanan.librarymanagement.datalayer;

import com.narayanan.librarymanagement.model.Book;
import com.narayanan.librarymanagement.model.Library;
import com.narayanan.librarymanagement.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryDataBase {
    private int bookIdCount = 1;
    private static LibraryDataBase libraryDataBase;
    private Library library;
    private List<Book> bookList = new ArrayList<>();
    private List<User> userList = new ArrayList<>();
    private Map<String, String> bookMap = new HashMap<>();

    private LibraryDataBase() {
    }

    public static LibraryDataBase getInstance() {
        if (libraryDataBase == null) {
            libraryDataBase = new LibraryDataBase();
        }
        return libraryDataBase;
    }

    public Library getLibrary() {
        return library;
    }

    public Library insertLibrary(Library library) {
        this.library = library;
        return this.library;
    }

    public boolean insertBook(Book book) {
        boolean hasBook = false;
        for (Book book1 : bookList) {
            if (book1.getName().equals(book.getName()) && book1.getAuthor().equals(book.getAuthor())) {
                hasBook = true;
                break;
            }
        }
        if (hasBook) {
            return false;
        } else {
            if (bookList.size() == 0) {
                book.setId(1);
            } else {
                Book preBook = bookList.get(bookList.size() - 1);
                book.setId(preBook.getId() + 1);
            }
            bookList.add(book);
            return true;
        }
    }

    public boolean insertUser(User user) {
        boolean hasUser = false;
        for (User user1 : userList) {
            if (user1.getEmailId().equals(user.getEmailId())) {
                hasUser = true;
                break;
            }
        }
        if (hasUser) {
            return false;
        } else {
            if (userList.size() == 0) {
                user.setId(1);
            } else {
                User preUser = userList.get(userList.size() - 1);
                user.setId(preUser.getId() + 1);
            }
            userList.add(user);
            return true;
        }
    }

    public List<Book> searchBook(String key) {
        List<Book> searchResult = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getName().contains(key)) {
                searchResult.add(book);
            }
        }
        return searchResult;
    }

    public boolean removeBook(int id) {
        for (Book book : bookList) {
            if (book.getId() == id) {
                return bookList.remove(book);
            }
        }
        return false;
    }

    public List<Book> showBookList() {
        return bookList;
    }

    public Book showBook(int id) {
        for (Book book : bookList) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    public void updateBook(Book book) {
        for (Book book1 : bookList) {
            if (book1.getId() == book.getId()) {
                bookList.remove(book1);
                bookList.add(book);
                break;
            }
        }
    }

    public List<User> showUserList() {
        return userList;
    }

    public User deleteUser(int id) {
        for (User user : userList) {
            if (user.getId() == id) {
                userList.remove(user);
                return user;
            }
        }
        return null;
    }

    public User getUser(int userId) {
        for (User user : userList) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;
    }

    public void getBookToUser(String str) {
//        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
//        String date = format.format(new Date());
        String date = "03-01-2024";
        bookMap.put(str, date);
    }

    public String returnUserBook(String arr) {
        String first = null;
        if (bookMap.containsKey(arr)) {
            first = bookMap.get(arr);
            bookMap.remove(arr);
        }
        return first;
    }


    public Map<String, String> showUserBookList() {
        return this.bookMap;
    }

    public void backUp() {
        BackUpRetrive.getInstance().backUpLibrary(library);
        BackUpRetrive.getInstance().backUpBooksList(bookList);
        BackUpRetrive.getInstance().backUpUserList(userList);
        BackUpRetrive.getInstance().backUpBookMap(bookMap);
    }

    public void retrive() {
        this.library = BackUpRetrive.getInstance().retriveLibrary();
        this.bookList = BackUpRetrive.getInstance().retriveBooksList();
        this.userList = BackUpRetrive.getInstance().retriveUserList();
        this.bookMap = BackUpRetrive.getInstance().retriveBookMap();
    }

}
