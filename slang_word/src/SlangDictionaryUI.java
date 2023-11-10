import app.SlangDictionary;
import util.Utils;

import java.util.Scanner;
import java.util.List;
import java.util.Collections;

public class SlangDictionaryUI {
    private SlangDictionary dictionary;
    private Scanner scanner;

    private SlangDictionaryUI() {
        dictionary = new SlangDictionary();
        scanner = new Scanner(System.in);
    }

    private void start() {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = Utils.getChoice();
            switch (choice) {
                case 1:  { searchBySlangWord();      break; }
                case 2:  { searchByDefinition();     break; }
                case 3:  { getSearchHistory();       break; }
                case 4:  { addNewSlangWord();        break; }
                case 5:  { editSlangWord();          break; }
                case 6:  { deleteSlangWord();        break; }
                case 7:  { resetSlangWords();        break; }
                case 8:  { randomSlangWord();        break; }
                case 9:  { quizGameSlangWord();      break; }
                case 10: { quizGameDefinition(); break; }
                case 11: { running = false;          break; }
                default: Utils.pauseScreen("[Message]: Invalid choice. Please try again.", 2000);
            }
        }
        dictionary.saveToFile("../data/local.data");
    }

    // * Feature 1
    private void searchBySlangWord() {
        Utils.clearScreen();
        printTitle();
        System.out.print("Enter slang word: ");
        String slangWord = scanner.nextLine();
        String result = dictionary.searchBySlangWord(slangWord);
        if (result == null)
            System.out.println("[Message]: The slang word does not exist.");
        else
            System.out.println("Definition -> " + dictionary.searchBySlangWord(slangWord));
        Utils.pauseScreen();
    }

    // * Feature 2
    private void searchByDefinition() {
        Utils.clearScreen();
        printTitle();
        System.out.print("Enter definition: ");
        String definition = scanner.nextLine();
        List<String> result = dictionary.searchByDefinition(definition);
        if (result.isEmpty())
            System.out.println("[Message]: The search does not match any slang words");
        else {
            System.out.println("Slang words -> ");
            for (String word : result)
                System.out.println(" - " + word);
        }
        Utils.pauseScreen();
    }

    // * Feature 3
    private void getSearchHistory() {
        Utils.clearScreen();
        printTitle();
        System.out.println("Search history -> ");
        for (String word : dictionary.getSearchHistory())
            System.out.println(" - " + word);
        Utils.pauseScreen();
    }

    // * Feature 4
    private void addNewSlangWord() {
        Utils.clearScreen();
        printTitle();
        System.out.print("Enter the new slang word: ");
        String word = scanner.nextLine();
        System.out.print("Enter the definition: ");
        String definition = scanner.nextLine();
        
        if (dictionary.isSlangWordExist(word))
            addExistHandler(word, definition);
        else {
            dictionary.addSlangWord(word, definition);
            System.out.println("[Message]: The slang word has been added.");
        }

        Utils.pauseScreen();
    }

    private void addExistHandler(String word, String definition) {
        Utils.clearScreen();
        printTitle();
        System.out.println("The slang word already exists.");
        System.out.println("1. Overwrite the existing slang word");
        System.out.println("2. Duplicate to a new slang word\n");
        int choice = Utils.getChoice();
        switch (choice) {
            case 1:
                dictionary.addSlangWord(word, definition);
                System.out.println("[Message]: The slang word has been overwritten.");
                break;
            case 2:
                int i = 1;
                while (dictionary.isSlangWordExist(word + i)) {
                    i++;
                }
                dictionary.addSlangWord(word + i, definition);
                System.out.println("[Message]: A new slang word has been created: " + word + i);
                break;
            default:
                System.out.println("[Message]: Invalid choice. The slang word has not been added.");
        }
    }

    // * Feature 5
    private void editSlangWord() {
        Utils.clearScreen();
        printTitle();
        System.out.print("Enter the slang word to edit: ");
        String word = scanner.nextLine();
        if (dictionary.isSlangWordExist(word)) {
            System.out.print("Enter the new definition: ");
            String newDefinition = scanner.nextLine();
            dictionary.editSlangWord(word, newDefinition);
            System.out.println("[Message]: The slang word has been updated.");
        } else {
            System.out.println("[Message]: The slang word does not exist.");
        }
        Utils.pauseScreen();
    }

    // * Feature 6
    private void deleteSlangWord() {
        Utils.clearScreen();
        printTitle();
        System.out.print("Enter the slang word to delete: ");
        String word = scanner.nextLine();
        if (dictionary.isSlangWordExist(word)) {
            System.out.println("Are you absolutely sure you want to delete this slang word?");
            System.out.print("Please type \"yes\" to confirm: ");
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("yes")) {
                dictionary.deleteSlangWord(word);
                System.out.println("[Message]: The slang word has been deleted.");
            } else {
                System.out.println("[Message]: The slang word has not been deleted.");
            }
        } else {
            System.out.println("[Message]: The slang word does not exist.");
        }
        Utils.pauseScreen();
    }

    // * Feature 7
    private void resetSlangWords() {
        Utils.clearScreen();
        printTitle();
        System.out.println("Are you sure you want to reset the slang words to the default?");
        System.out.print("Please type \"yes\" to confirm: ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("yes")) {
            dictionary.resetSlangWords();
            System.out.println("[Message]: The slang words have been reset to the default.");
        } else {
            System.out.println("[Message]: The slang words have not been reset.");
        }
        Utils.pauseScreen();
    }

    // * Feature 8
    private void randomSlangWord() {
        Utils.clearScreen();
        printTitle();
        String key = (String) dictionary.randomSlangWord();
        System.out.println("On this day slang word: " + key + " - " + dictionary.searchBySlangWord(key));
        Utils.pauseScreen();
    }

    // * Feature 9
    public void quizGameSlangWord() {
        Utils.clearScreen();
        printTitle();
        Object correctKey = dictionary.randomSlangWord();
        System.out.println("What is the meaning of the slang word: " + correctKey + "?");
    
        // Generate random answers
        List<Object> answers = dictionary.getSlangWordQuiz(correctKey);
        String correctAnswer = dictionary.searchBySlangWord(correctKey.toString());
        answersHander(answers, correctAnswer);
        Utils.pauseScreen();
    }

    // * Feature 10
    private void quizGameDefinition() {
        Utils.clearScreen();
        printTitle();
        Object correctAnswer = dictionary.randomSlangWord();
        Object correctValue = dictionary.searchBySlangWord(correctAnswer.toString());
        System.out.println("What is the slang word of the definition: " + correctValue + "?");
        
        // Generate random answers
        List<Object> answers = dictionary.getDefinitionQuiz(correctAnswer);
        answersHander(answers,  correctAnswer.toString());
        Utils.pauseScreen();
    }

    private void answersHander(List<Object> answers, String correctAnswer) {
        Collections.shuffle(answers);
        for (int i = 0; i < answers.size(); i++)
            System.out.println((i + 1) + ". " + answers.get(i));

        int userChoice = Utils.getChoice("Enter your choice (1-4): ");
        if (answers.get(userChoice - 1).equals(correctAnswer))
            System.out.println("\n=> Correct!");
        else
            System.out.println("\n=> Incorrect. The correct answer is: " + correctAnswer);
    }

    private void printMenu() {
        Utils.clearScreen();
        printTitle();
        System.out.println(" 1 - Search by Slang Word");
        System.out.println(" 2 - Search by definition");
        System.out.println(" 3 - Show search history");
        System.out.println(" 4 - Add new slang word");
        System.out.println(" 5 - Edit slang word");
        System.out.println(" 6 - Delete slang word");
        System.out.println(" 7 - Reset default slang words");
        System.out.println(" 8 - Get random slang words");
        System.out.println(" 9 - Quiz game: What is the meaning of the slang word ?");
        System.out.println("10 - Quiz game: What is the slang word of the definition ?");
        System.out.println("11 - Exit\n");
        System.out.println("==========================================================\n");
    }

    private void printTitle() {
        System.out.println("  _____ _                    __          __           _  ");
        System.out.println(" / ____| |                   \\ \\        / /          | |");
        System.out.println("| (___ | | __ _ _ __   __ _   \\ \\  /\\  / /__  _ __ __| |");
        System.out.println(" \\___ \\| |/ _` | '_ \\ / _` |   \\ \\/  \\/ / _ \\| '__/ _` |");
        System.out.println(" ____) | | (_| | | | | (_| |    \\  /\\  / (_) | | | (_| |");
        System.out.println("|_____/|_|\\__,_|_| |_|\\__, |     \\/  \\/ \\___/|_|  \\__,_|");
        System.out.println("                      __/ |                            ");
        System.out.println("                     |___/                             ");
        System.out.println("\n\n==========================================================\n");
    }

    public static void main(String[] args) {
        new SlangDictionaryUI().start();
    }
}