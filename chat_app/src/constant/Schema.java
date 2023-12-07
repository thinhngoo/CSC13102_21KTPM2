package constant;

public class Schema {

    public static class User {
        private String username;
        private String password;
        private String email;
        private String role;
    
        public User(String username, String password, String email, String role) {
            this.username = username;
            this.password = password;
            this.email = email;
            if (role.equals("*")) {
                this.role = "admin";
            } else {
                this.role = "user";
            }
        }

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }
    
        public String getUsername() {
            return this.username;
        }
    
        public String toString() {
            return "Username: " + this.username + "\nPassword: " + this.password + "\nEmail: " + this.email + "\nRole: "
                    + this.role;
        }
    }
}
