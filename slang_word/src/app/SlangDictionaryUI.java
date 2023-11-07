package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label ;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SlangDictionaryUI extends Application {
    private SlangDictionary dictionary = new SlangDictionary();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("app.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
        
        TextField textField = new TextField();
        Button button = new Button("Search");
        Label label = new Label();

        button.setOnAction(e -> {
            String word = textField.getText();
            String meaning = dictionary.getMeaning(word);
            if (meaning == null) {
                label.setText("Word not found");
            } else {
                label.setText(meaning);
            }
        });

        VBox vbox = new VBox(textField, button, label);
        Scene scene = new Scene(vbox, 200, 100);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}