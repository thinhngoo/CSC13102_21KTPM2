import connection.*;
import constant.Term;
import util.*;

public class App {

    public App() {
        run();
    }

    private void run() {
        int port = Term.Init.PORT;
        try {
            Server server = new Server(port);
            Logger.log("info", "Server is started at port " + port, "App.java");
            server.run();
        } catch (Exception e) {
            Logger.log("error", e.getMessage(), "App.java");
        }
    }

    public static void main(String[] args) {
        new App();
    }
}