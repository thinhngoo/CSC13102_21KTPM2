package middleware;

import java.util.List;

import constant.*;

public class Authentication {

    public static boolean isUserExist(List<Schema.User> users, String username) {
        for (Schema.User user : users) {
            if (user.isUsernameMatch(username)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPasswordMatch(List<Schema.User> users, Schema.User user) {
        String providedPassword = user.getPassword();
        for (Schema.User _user : users) {
            if (_user.isPasswordMatch(providedPassword)) {
                return true;
            }
        }
        return false;
    }
}