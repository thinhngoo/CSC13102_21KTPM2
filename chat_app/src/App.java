import connection.*;
import util.*;

public class App {
    private static final long serialVersionUID = 1L;

    public App() {
        run();
    }

    private void run() {
        int port = 5000;
        try {
            Server server = new Server(port);
            Logger.log("info", "Server is started at port " + port, System.getProperty("user.dir"));
            server.run();
        } catch (Exception e) {
            Logger.log("error", e.getMessage(), System.getProperty("user.dir"));
        }
    }

    public static void main(String[] args) {
        new App();
    }
}