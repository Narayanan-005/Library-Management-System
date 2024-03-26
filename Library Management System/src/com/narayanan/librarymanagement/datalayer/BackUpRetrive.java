package com.narayanan.librarymanagement.datalayer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.narayanan.librarymanagement.model.Book;
import com.narayanan.librarymanagement.model.Library;
import com.narayanan.librarymanagement.model.User;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BackUpRetrive {
    private static BackUpRetrive backUpRetrive;

    private BackUpRetrive() {

    }

    public static BackUpRetrive getInstance() {
        if (backUpRetrive == null) {
            backUpRetrive = new BackUpRetrive();
        }
        return backUpRetrive;
    }

    private void createFileIfNotExist(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("could't create file");
            }
        }
    }

    public void backUpLibrary(Library library) {
        File file = new File("/home/prabakar/Documents/Task/console_application/Libraty_Management_App/LibraryManagementJson/Library.json");
        Gson gson = new Gson();
        String string = gson.toJson(library);
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(string);
            writer.close();
        } catch (IOException e) {
            System.out.println("could't write file");
        }
    }

    public Library retriveLibrary() {
        File file = new File("/home/prabakar/Documents/Task/console_application/Libraty_Management_App/LibraryManagementJson/Library.json");
        createFileIfNotExist(file);
        Gson gson = new Gson();
        Library library = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String string = reader.readLine();
            while (string != null) {
                stringBuilder.append(string);
                string = reader.readLine();
            }
            library = gson.fromJson(stringBuilder.toString(), Library.class);
            reader.close();
        } catch (IOException e) {
            System.out.println("could't read from file");
        }
        return library;
    }

    public void backUpBooksList(List<Book> bookList) {
        File file = new File("/home/prabakar/Documents/Task/console_application/Libraty_Management_App/LibraryManagementJson/BookList.json");
        Gson gson = new Gson();
        String string = gson.toJson(bookList);
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(string);
            writer.close();
        } catch (IOException e) {
            System.out.println("could't write file");
        }
    }

    public List<Book> retriveBooksList() {
        File file = new File("/home/prabakar/Documents/Task/console_application/Libraty_Management_App/LibraryManagementJson/BookList.json");
        createFileIfNotExist(file);
        Gson gson = new Gson();
        List<Book> bookList = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String string = reader.readLine();
            while (string != null) {
                stringBuilder.append(string);
                string = reader.readLine();
            }
            if (stringBuilder.isEmpty()) {
                return bookList;
            }
            Type type = new TypeToken<List<Book>>() {
            }.getType();
            bookList = gson.fromJson(stringBuilder.toString(), type);
            reader.close();
        } catch (IOException e) {
            System.out.println("could't read from file");
        }
        return bookList;
    }

    public void backUpUserList(List<User> userList) {
        File file = new File("/home/prabakar/Documents/Task/console_application/Libraty_Management_App/LibraryManagementJson/UserList.json");
        Gson gson = new Gson();
        String string = gson.toJson(userList);
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(string);
            writer.close();
        } catch (IOException e) {
            System.out.println("could't write file");
        }
    }

    public List<User> retriveUserList() {
        File file = new File("/home/prabakar/Documents/Task/console_application/Libraty_Management_App/LibraryManagementJson/UserList.json");
        createFileIfNotExist(file);
        Gson gson = new Gson();
        List<User> userList = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String string = reader.readLine();
            while (string != null) {
                stringBuilder.append(string);
                string = reader.readLine();
            }
            if (stringBuilder.isEmpty()) {
                return userList;
            }
            Type type = new TypeToken<List<User>>() {
            }.getType();
            userList = gson.fromJson(stringBuilder.toString(), type);
            reader.close();
        } catch (IOException e) {
            System.out.println("could't read from file");
        }
        return userList;
    }

    public void backUpBookMap(Map<String, String> bookMap) {
        File file = new File("/home/prabakar/Documents/Task/console_application/Libraty_Management_App/LibraryManagementJson/BookMap.json");
        Gson gson = new Gson();
        String string = gson.toJson(bookMap);
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(string);
            writer.close();
        } catch (IOException e) {
            System.out.println("could't write file");
        }
    }

    public Map<String, String> retriveBookMap() {
        File file = new File("/home/prabakar/Documents/Task/console_application/Libraty_Management_App/LibraryManagementJson/BookMap.json");
        createFileIfNotExist(file);
        Gson gson = new Gson();
        Map<String, String> bookMap = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String string = reader.readLine();
            while (string != null) {
                stringBuilder.append(string);
                string = reader.readLine();
            }
            if (stringBuilder.isEmpty()) {
                return bookMap;
            }
            Type type = new TypeToken<Map<String, String>>() {
            }.getType();
            bookMap = gson.fromJson(stringBuilder.toString(), type);
            reader.close();
        } catch (IOException e) {
            System.out.println("could't read from file");
        }
        return bookMap;
    }
}
