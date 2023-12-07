package middleware;

import constant.Schema.User;
import java.util.List;

public class Authentication {
    public static boolean isUserExist(List<User> users, String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
