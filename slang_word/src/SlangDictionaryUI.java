import java.util.Scanner;

import app.SlangDictionary;

import java.io.IOException;

public class SlangDictionaryUI {
    private SlangDictionary dictionary;
    private Scanner scanner;

    private SlangDictionaryUI() {
        dictionary = new SlangDictionary();
        scanner = new Scanner(System.in);
    }

    private void start() {
        while (true) {
            printMenu();
            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    searchBySlangWord();
                    break;
                case 2:
                    searchByDefinition();
                    break;
                case 3:
                    getSearchHistory();
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
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

    private void searchBySlangWord() {
        clearScreen();
        printTitle();
        System.out.print("Enter slang word: ");
        String slangWord = scanner.next();
        System.out.println(dictionary.searchBySlangWord(slangWord));
        pressToContinue();
    }

    private void searchByDefinition() {
        clearScreen();
        printTitle();
        System.out.print("Enter definition: ");
        String definition = scanner.next();
        System.out.println(dictionary.searchByDefinition(definition));
        pressToContinue();
    }

    private void getSearchHistory() {
        clearScreen();
        printTitle();
        System.out.println(dictionary.getSearchHistory());
        pressToContinue();
    }

    private void printMenu() {
        clearScreen();
        printTitle();
        System.out.println("1. Search by Slang Word");
        System.out.println("2. Search by definition");
        System.out.println("3. Show search history");
        System.out.println("11. Exit");
    }

    private void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    private void pressToContinue() {
        System.out.println("\nPress Enter to continue...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SlangDictionaryUI().start();
    }
}