package middleware;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;

import constant.*;
import util.*;

public class Model {

    public static class UserDB {
        List<Schema.User> users = new ArrayList<Schema.User>();

        void readDB() {
            String filePath = "./db/user.db"; // Path to the text file

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(" ");
                    Schema.User user;
                    if (data.length >= 4) {
                        user = new Schema.User(data[0], data[1], data[2], data[3]);
                    } else {
                        user = new Schema.User(data[0], data[1], data[2]);
                    }
                    users.add(user);
                }
            } catch (IOException e) {
                Logger.log("error", e.getMessage(), "Model.java");
            }

        }

        public UserDB() {
            readDB();
        }

        public List<Schema.User> getUsers() {
            return this.users;
        }

        public boolean addUser(Schema.User user) {
            String line = user.getUsername() + " " + user.getPassword() + " " + user.getEmail();
            File.addLine("./db/user.db", line);
            this.users.add(user);
            return true;
        }
    }
}


