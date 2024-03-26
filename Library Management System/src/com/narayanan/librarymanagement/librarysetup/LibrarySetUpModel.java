package com.narayanan.librarymanagement.librarysetup;

import com.narayanan.librarymanagement.datalayer.LibraryDataBase;
import com.narayanan.librarymanagement.model.Library;

class LibrarySetUpModel {

    private LibrarySetUpView librarySetUpView;

    private Library library;

    LibrarySetUpModel(LibrarySetUpView librarySetUpView) {
        this.librarySetUpView = librarySetUpView;
        library=LibraryDataBase.getInstance().getLibrary();
    }

    public void startSetUp() {
        if(library==null){
            librarySetUpView.initiateSetUp();
        }else {
            librarySetUpView.onSetUpComplete(library);
        }
    }

    public void createLibrary(Library library) {
        if(library.getLibraryName().length()>3 && library.getLibraryName().length()<50){
            if(validPhoneNo(library) && validMain(library)){
                this.library=LibraryDataBase.getInstance().insertLibrary(library);
                librarySetUpView.onSetUpComplete(library);
            }else{
                librarySetUpView.showAlert("Invalid Mail Id or Phone Number");
            }
        }else{
            librarySetUpView.showAlert("Invalid Library Name");
        }
    }

    private boolean validMain(Library library) {
        return library.getEmailId().matches("^[A-Za-z0-9+_.-]+@(.+)$" );
    }

    private boolean validPhoneNo(Library library) {
        return library.getPhoneNO().matches("\\d{10}");
    }
}
