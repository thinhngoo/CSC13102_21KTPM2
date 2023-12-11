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
            this.role = "admin";
        }

        public User(String username, String password, String email) {
            this.username = username;
            this.password = password;
            this.email = email;
            this.role = "user";
        }

        public User(String username, String password) {
            this.username = username;
            this.password = password;
            this.email = "";
            this.role = "user";
        }
    
        public String getUsername() {
            return this.username;
        }

        public String getPassword() {
            return this.password;
        }

        public String getEmail() {
            return this.email;
        }

        public boolean isUsernameMatch(String providedUsername) {
            return this.username.equals(providedUsername);
        }

        public boolean isPasswordMatch(String providedPassword) {
            return this.password.equals(providedPassword);
        }
    }
}
