package com.narayanan.librarymanagement.managebook;

import com.narayanan.librarymanagement.datalayer.LibraryDataBase;
import com.narayanan.librarymanagement.model.Book;

import java.util.List;

class ManageBookModel {
    private ManageBookView manageBookView;


    ManageBookModel(ManageBookView manageBookView) {
        this.manageBookView = manageBookView;
    }

    public void addBook(Book book) {
        if (LibraryDataBase.getInstance().insertBook(book)) {
            manageBookView.onBookAdded(book);
        } else {
            manageBookView.onBookExist(book);
        }
    }

    public void removeBook(int id) {
        boolean hasRemoved = LibraryDataBase.getInstance().removeBook(id);
        if (hasRemoved) {
            manageBookView.onBookRemoved();
        } else {
            manageBookView.onBookNotFount();
        }
    }

    public void searchBook(String key) {
        List<Book> seachresult = LibraryDataBase.getInstance().searchBook(key);
        if (seachresult.size() == 0) {
            manageBookView.onSearchNotFount();
        } else {
            manageBookView.seachResult(seachresult);
        }
    }


    public List<Book> showBookList() {
        return LibraryDataBase.getInstance().showBookList();
    }

    public void showBook(int id) {
        Book book = LibraryDataBase.getInstance().showBook(id);
        if (book == null) {
            manageBookView.onBookNotFount();
        } else {
            manageBookView.onShowBook(book);
        }
    }

    public Book getBook(int bookId) {
        return LibraryDataBase.getInstance().showBook(bookId);
    }

    public void updateEdition(String edition, Book book) {
        book.setEdition(edition);
        LibraryDataBase.getInstance().updateBook(book);
        manageBookView.showMessage("Book Updated Sucessfully");
    }

    public void updateAvaliableCount(Book book) {
        LibraryDataBase.getInstance().updateBook(book);
        manageBookView.showMessage("Book Updated Sucessfully");
    }

    public void updateVolume(int volume, Book book) {
        book.setVolume(volume);
        LibraryDataBase.getInstance().updateBook(book);
        manageBookView.showMessage("Book Updated Sucessfully");
    }

    public void updateAvaliableCountDecrease(int count, Book book) {
        if(book.getAvailableCount()<=count){
            manageBookView.showMessage("Can't Decrease Available count");
        }else {
            book.setAvailableCount(book.getAvailableCount()-count);
            updateAvaliableCount(book);
        }
    }
}
