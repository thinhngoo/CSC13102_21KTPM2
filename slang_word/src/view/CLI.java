package view;
import util.Utils;

import java.util.Scanner;

import core.SlangDictionary;

import java.util.ArrayList;
import java.util.Collections;

public class CLI extends SlangDictionary implements UI {
    private Scanner scanner;

    public CLI() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = Utils.getChoice();
            switch (choice) {
                case 1:  { feature1();  break; }
                case 2:  { feature2();  break; }
                case 3:  { feature3();  break; }
                case 4:  { feature4();  break; }
                case 5:  { feature5();  break; }
                case 6:  { feature6();  break; }
                case 7:  { feature7();  break; }
                case 8:  { feature8();  break; }
                case 9:  { feature9();  break; }
                case 10: { feature10(); break; }
                case 11: { running = false;          break; }
                default: Utils.pauseScreen("[Message]: Invalid choice. Please try again.", 2000);
            }
        }
        saveToFile("./data/local.data");
    }

    // * Feature 1
    private void feature1() {
        Utils.clearScreen();
        printTitle();
        System.out.print("Enter slang word: ");
        String slangWord = scanner.nextLine();
        String result = searchBySlangWord(slangWord);
        if (result == null)
            System.out.println("[Message]: The slang word does not exist.");
        else
            System.out.println("Definition -> " + searchBySlangWord(slangWord));
        Utils.pauseScreen();
    }

    // * Feature 2
    private void feature2() {
        Utils.clearScreen();
        printTitle();
        System.out.print("Enter definition: ");
        String definition = scanner.nextLine();
        ArrayList<String> result = searchByDefinition(definition);
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
    private void feature3() {
        Utils.clearScreen();
        printTitle();
        System.out.println("Search history -> ");
        for (String word : getSearchHistory())
            System.out.println(" - " + word);
        Utils.pauseScreen();
    }

    // * Feature 4
    private void feature4() {
        Utils.clearScreen();
        printTitle();
        System.out.print("Enter the new slang word: ");
        String word = scanner.nextLine();
        System.out.print("Enter the definition: ");
        String definition = scanner.nextLine();
        
        if (isSlangWordExist(word))
            addExistHandler(word, definition);
        else {
            addSlangWord(word, definition);
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
                addSlangWord(word, definition);
                System.out.println("[Message]: The slang word has been overwritten.");
                break;
            case 2:
                int i = 1;
                while (isSlangWordExist(word + i)) {
                    i++;
                }
                addSlangWord(word + i, definition);
                System.out.println("[Message]: A new slang word has been created: " + word + i);
                break;
            default:
                System.out.println("[Message]: Invalid choice. The slang word has not been added.");
        }
    }

    // * Feature 5
    private void feature5() {
        Utils.clearScreen();
        printTitle();
        System.out.print("Enter the slang word to edit: ");
        String word = scanner.nextLine();
        if (isSlangWordExist(word)) {
            System.out.print("Enter the new definition: ");
            String newDefinition = scanner.nextLine();
            editSlangWord(word, newDefinition);
            System.out.println("[Message]: The slang word has been updated.");
        } else {
            System.out.println("[Message]: The slang word does not exist.");
        }
        Utils.pauseScreen();
    }

    // * Feature 6
    private void feature6() {
        Utils.clearScreen();
        printTitle();
        System.out.print("Enter the slang word to delete: ");
        String word = scanner.nextLine();
        if (isSlangWordExist(word)) {
            System.out.println("Are you absolutely sure you want to delete this slang word?");
            System.out.print("Please type \"yes\" to confirm: ");
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("yes")) {
                deleteSlangWord(word);
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
    private void feature7() {
        Utils.clearScreen();
        printTitle();
        System.out.println("Are you sure you want to reset the slang words to the default?");
        System.out.print("Please type \"yes\" to confirm: ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("yes")) {
            feature7();
            System.out.println("[Message]: The slang words have been reset to the default.");
        } else {
            System.out.println("[Message]: The slang words have not been reset.");
        }
        Utils.pauseScreen();
    }

    // * Feature 8
    private void feature8() {
        Utils.clearScreen();
        printTitle();
        String key = (String) randomSlangWord();
        System.out.println("On this day slang word: " + key + " - " + searchBySlangWord(key));
        Utils.pauseScreen();
    }

    // * Feature 9
    public void feature9() {
        Utils.clearScreen();
        printTitle();
        Object correctKey = randomSlangWord();
        System.out.println("What is the meaning of the slang word: " + correctKey + "?");
    
        // Generate random answers
        ArrayList<Object> answers = getSlangWordQuiz(correctKey);
        String correctAnswer = searchBySlangWord(correctKey.toString());
        answersHander(answers, correctAnswer);
        Utils.pauseScreen();
    }

    // * Feature 10
    private void feature10() {
        Utils.clearScreen();
        printTitle();
        Object correctAnswer = randomSlangWord();
        Object correctValue = searchBySlangWord(correctAnswer.toString());
        System.out.println("What is the slang word of the definition: " + correctValue + "?");
        
        // Generate random answers
        ArrayList<Object> answers = getDefinitionQuiz(correctAnswer);
        answersHander(answers,  correctAnswer.toString());
        Utils.pauseScreen();
    }

    private void answersHander(ArrayList<Object> answers, String correctAnswer) {
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
}