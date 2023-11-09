import java.util.Scanner;

import app.SlangDictionary;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

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
            scanner.nextLine();
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
                case 4:
                    addNewSlangWord();
                    break;
                case 5:
                    editSlangWord();
                    break;
                case 6:
                    deleteSlangWord();
                    break;
                case 7:
                    resetSlangWords();
                    break;
                case 8:
                    randomSlangWord();
                    break;
                case 9:
                    quizGameSlangWord();
                    break;
                case 10:
                    quizGameWithDefinition();
                    break;
                case 11:
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

    private void addNewSlangWord() {
        clearScreen();
        printTitle();
        System.out.print("Enter the new slang word: ");
        String word = scanner.nextLine();
        System.out.print("Enter the definition: ");
        String definition = scanner.nextLine();
    
        if (dictionary.isSlangWordExist(word)) {
            clearScreen();
            printTitle();
            System.out.println("The slang word already exists.");
            System.out.println("1. Overwrite the existing slang word");
            System.out.println("2. Duplicate to a new slang word");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scannerSkippedHandler();
            switch (choice) {
                case 1:
                    dictionary.addSlangWord(word, definition);
                    System.out.println("The slang word has been overwritten.");
                    break;
                case 2:
                    int i = 1;
                    while (dictionary.isSlangWordExist(word + i)) {
                        i++;
                    }
                    dictionary.addSlangWord(word + i, definition);
                    System.out.println("A new slang word has been created: " + word + i);
                    break;
                default:
                    System.out.println("Invalid choice. The slang word has not been added.");
            }
        } else {
            dictionary.addSlangWord(word, definition);
            System.out.println("The slang word has been added.");
        }
        pressToContinue();
    }

    private void editSlangWord() {
        clearScreen();
        printTitle();
        System.out.print("Enter the slang word to edit: ");
        String word = scanner.nextLine();
        if (dictionary.isSlangWordExist(word)) {
            System.out.print("Enter the new definition: ");
            String newDefinition = scanner.nextLine();
            dictionary.editSlangWord(word, newDefinition);
            System.out.println("The slang word has been updated.");
        } else {
            System.out.println("The slang word does not exist.");
        }
        pressToContinue();
    }

    private void deleteSlangWord() {
        clearScreen();
        printTitle();
        System.out.print("Enter the slang word to delete: ");
        String word = scanner.nextLine();
        if (dictionary.isSlangWordExist(word)) {
            System.out.println("Are you sure you want to delete this slang word? Type (yes/no)");
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("yes")) {
                dictionary.deleteSlangWord(word);
                System.out.println("The slang word has been deleted.");
            } else {
                System.out.println("The slang word has not been deleted.");
            }
        } else {
            System.out.println("The slang word does not exist.");
        }
        pressToContinue();
    }

    private void resetSlangWords() {
        clearScreen();
        printTitle();
        System.out.println("Are you sure you want to reset the slang words to the default? (yes/no)");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("yes")) {
            dictionary.resetSlangWords();
            System.out.println("The slang words have been reset to the default.");
        } else {
            System.out.println("The slang words have not been reset.");
        }
        pressToContinue();
    }

    private void randomSlangWord() {
        clearScreen();
        printTitle();
        String key = (String) dictionary.randomSlangWord();
        System.out.println("On this day slang word: " + key + " - " + dictionary.searchBySlangWord(key));
        pressToContinue();
    }

    public void quizGameSlangWord() {
        clearScreen();
        printTitle();
        Object correctKey = dictionary.randomSlangWord();
        System.out.println("What is the meaning of the slang word: " + correctKey + "?");
    
        // Generate random answers
        List<Object> answers = dictionary.getSlangWordQuiz(correctKey);
        Collections.shuffle(answers);
        for (int i = 0; i < answers.size(); i++) {
            System.out.println((i + 1) + ". " + answers.get(i));
        }
    
        // Get user's answer
        System.out.print("Enter your choice (1-4): ");
        int userChoice = scanner.nextInt();
        scannerSkippedHandler();
    
        // Check if the answer is correct
        String correctAnswer = dictionary.searchBySlangWord(correctKey.toString());
        if (answers.get(userChoice - 1).equals(correctAnswer)) {
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect. The correct answer is: " + correctAnswer);
        }
        pressToContinue();
    }

    private void quizGameWithDefinition() {
        clearScreen();
        printTitle();
        Object correctDefinition = dictionary.searchBySlangWord(dictionary.randomSlangWord().toString());
        System.out.println("What is the slang word of the definition: " + correctDefinition + "?");
        
        // Generate random answers
        List<Object> answers = dictionary.getDefinitionQuiz(correctDefinition);
        Collections.shuffle(answers);
        for (int i = 0; i < answers.size(); i++) {
            System.out.println((i + 1) + ". " + answers.get(i));
        }

        // Get user's answer
        System.out.print("Enter your choice (1-4): ");
        int userChoice = scanner.nextInt();
        scannerSkippedHandler();
    
        // Check if the answer is correct
        if (answers.get(userChoice - 1).equals(correctDefinition)) {
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect. The correct answer is: " + correctDefinition);
        }
        pressToContinue();
    }

    private void printMenu() {
        clearScreen();
        printTitle();
        System.out.println(" 1. Search by Slang Word");
        System.out.println(" 2. Search by definition");
        System.out.println(" 3. Show search history");
        System.out.println(" 4. Add new slang word");
        System.out.println(" 5. Edit slang word");
        System.out.println(" 6. Delete slang word");
        System.out.println(" 7. Reset default slang words");
        System.out.println(" 8. Get random slang words");
        System.out.println(" 9. Quiz game: What is the meaning of the slang word?");
        System.out.println("10. Quiz game: What is the slang word of the definition?");
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

    private void scannerSkippedHandler() {
        scanner.nextLine();
    }

    public static void main(String[] args) {
        new SlangDictionaryUI().start();
    }
}