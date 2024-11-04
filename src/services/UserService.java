package services;

import models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> users;
    private final String FILE_NAME = "users.dat";

    public UserService() {
        users = new ArrayList<>();
        loadUsers();
    }

    public void registerUser(String username, String password) {
        if (findUser(username) == null) {
            users.add(new User(username, password));
            saveUsers();
            System.out.println("User registered successfully.");
        } else {
            System.out.println("Username already exists. Please choose another.");
        }
    }

    public User authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        System.out.println("Invalid username or password.");
        return null;
    }

    private User findUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    private void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            users = (List<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            users = new ArrayList<>();
        }
    }

    public List<User> getUsers() {
        return users;
    }
}
