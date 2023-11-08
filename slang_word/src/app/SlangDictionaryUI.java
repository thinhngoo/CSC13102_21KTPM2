package app;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class SlangDictionaryUI extends Application {
    private SlangDictionary dictionary = new SlangDictionary();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(featureMenu());
        primaryStage.setTitle("Slang Dictionary");
        primaryStage.setScene(scene);
        primaryStage.show(); 
    }

    private Region featureMenu() {
        ListView<String> listView = new ListView<>();

        ObservableList<String> items = FXCollections.observableArrayList (
            "1 - Tải dữ liệu mặc định (slang.txt)",
            "2 - Tìm kiếm theo Slang word",
            "3 - Tìm kiếm theo Definition",
            "4 - Hiển thị lịch sử tìm kiếm",
            "5 - Thêm Slang word mới",
            "6 - Sửa Slang word",
            "7 - Xóa Slang word",
            "8 - Phục hồi lại dữ liệu ban đầu",
            "9 - Hiển thị 1 slang word ngẫu nhiên",
            "10 - Đố vui (Slang word -> Definition)",
            "11 - Đố vui (Definition -> Slang word)"
            );

        listView.setItems(items);

        listView.setOnMouseClicked(event -> {
            int selectedIndex = listView.getSelectionModel().getSelectedIndex();
            System.out.println("Selected feature " + (selectedIndex + 1));
        
            switch (selectedIndex) {
                case 0:
                    dictionary.loadDefaultFile();
                    break;
                case 1:
                    System.out.println(dictionary.searchBySlangWord("C/O"));
                    break;
                case 2:
                    System.out.println(dictionary.searchByDefinition("Brother"));
                    break;
                case 3:
                    System.out.println(dictionary.getSearchHistory());
                    break;
                default:
                    break;
            }
        });

        HBox vbox = new HBox(listView);
        vbox.setSpacing(6);
        return vbox;
    }

    

    public static void main(String[] args) {
        launch(args);
    }
}