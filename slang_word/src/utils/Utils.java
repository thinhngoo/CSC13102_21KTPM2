package utils;

import java.io.IOException;
import java.util.Scanner;

public class Utils {
    private static Scanner scanner = new Scanner(System.in);

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }

    public static void pauseScreen() {
        System.out.println("\nPress to continue...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void pauseScreen(String message) {
        System.out.println("\n" + message);
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void pauseScreen(String message, int timeout) {
        System.out.println("\n" + message);
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int getChoice() {
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scannerSkippedHandler();
        return choice;
    }

    public static int getChoice(String message) {
        System.out.print(message);
        int choice = scanner.nextInt();
        scannerSkippedHandler();
        return choice;
    }

    public static void scannerSkippedHandler() {
        scanner.nextLine();
    }
}