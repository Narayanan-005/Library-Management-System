package com.narayanan.librarymanagement.booktransaction;

import com.narayanan.librarymanagement.datalayer.LibraryDataBase;
import com.narayanan.librarymanagement.model.Book;
import com.narayanan.librarymanagement.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

class BookTransactionModel {
    private BookTransactionView bookTransactionView;

    BookTransactionModel(BookTransactionView manageUserBookView) {
        this.bookTransactionView = manageUserBookView;
    }

    public void getBookToUser(int userId, int bookId) {
        User user = LibraryDataBase.getInstance().getUser(userId);
        Book book = LibraryDataBase.getInstance().showBook(bookId);
        if (user != null && book != null) {
            if (book.getAvailableCount() > 0) {
                LibraryDataBase.getInstance().getBookToUser(""+userId+"-"+bookId);
                book.setAvailableCount(book.getAvailableCount() - 1);
                LibraryDataBase.getInstance().updateBook(book);
                bookTransactionView.showSucessMessage(book.getName() + " book is issued to " + user.getName() + "\nreturn the book with in 14 days");
            } else {
                bookTransactionView.onGetBookFailed("Out of stock ");
            }
        } else {
            bookTransactionView.onGetBookFailed("Invalid User/User not Found");
        }
    }

    public void returnUserBook(int userId, int bookId) {
        String first = LibraryDataBase.getInstance().returnUserBook(""+userId+"-"+bookId);
        String last = "";
        if (first != null) {
            Book book = LibraryDataBase.getInstance().showBook(bookId);
            book.setAvailableCount(book.getAvailableCount() + 1);
            LibraryDataBase.getInstance().updateBook(book);
            SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
            last = format.format(new Date());
            try {
                Date dateF = format.parse(first);
                Date dateL = format.parse(last);
                long timeDff = dateL.getTime() - dateF.getTime();
                long remainigDays = (timeDff / (1000 * 60 * 60 * 24)) % 365;
                if (remainigDays <= 14) {
                    bookTransactionView.showSucessMessage("Book Returned Sucessfully");
                } else {
                    bookTransactionView.onReturnBook(remainigDays);
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else {
            bookTransactionView.showSucessMessage("Record not Found");
        }
    }

    public void showUserBookList() {
        Map<String , String> userBookMap = LibraryDataBase.getInstance().showUserBookList();
        if (userBookMap.size() == 0) {
            bookTransactionView.showSucessMessage("No Record Found");
        } else {
            bookTransactionView.showUserBookList(userBookMap);
        }
    }
}
