package middleware;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import constant.Schema.*;
import util.*;

public class Model {

    public static class UserDB {
        List<User> users = new ArrayList<User>();

        void read() {
            String filePath = "./db/user.db"; // Path to the text file

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(" ");
                    User user;
                    if (data.length >= 4) {
                        user = new User(data[0], data[1], data[2], data[3]);
                    } else {
                        user = new User(data[0], data[1], data[2], "");
                    }
                    users.add(user);
                }
            } catch (IOException e) {
                Logger.log("error", e.getMessage(), System.getProperty("user.dir"));
            }

        }

        public UserDB() {
            read();
        }

        public List<User> getUsers() {
            return this.users;
        }
    }
}


