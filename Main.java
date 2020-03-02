package com.company;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static class ActionsUpdater implements Runnable {
        private Library actionsLibrary = new Library();
        private Scanner scanner;

        // Using Java's Scanner class, allow the user to select their actions by
        // entering numbers into the command line while the program is running
        public void run() {
            scanner = new Scanner(System.in);

            printOptions();

            boolean exit = false;
            int choice;
            while(!exit) {
                System.out.println("Enter the option number for your choice");
                choice = scanner.nextInt();
                scanner.nextLine();
                switch(choice) {
                    case 0:
                        printOptions();
                        break;
                    case 1:
                        addAction();
                        break;
                    case 2:
                        getActionStats();
                        break;
                    case 3:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option number for your choice.");
                        printOptions();
                        break;
                }
            }
        }

        // Provides the user with a list of options that they can select by entering a number into the command line
        public static void printOptions() {
            System.out.println("Select from the following options:");
            System.out.println("0. Print options menu");
            System.out.println("1. Add action");
            System.out.println("2. Get stats");
            System.out.println("3. Exit");
        }

        // Allows the user to enter a name and time for an action, then creates
        // a JSON serialized string and passes it to the addAction method for the Library
        public void addAction() {
            System.out.println("Enter an action:");
            String name = scanner.nextLine();
            System.out.println("Enter a time for that action:");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer time for the action.");
                scanner.next();
            }
            int time = scanner.nextInt();
            Action newAction = new Action(name, time);

            Gson gson = new Gson();
            String jsonAction = gson.toJson(newAction);
            actionsLibrary.addAction(jsonAction);
        }

        // Retrieves the current action stats from the Library and prints the array
        public void getActionStats() {
            System.out.println("Here is your current list of actions along with their average times:");
            String[] actions = actionsLibrary.getStats();
            System.out.println(Arrays.toString(actions));
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        // Delay, in milliseconds before we interrupt ActionsUpdater thread (default one hour).
        long patience = 1000 * 60 * 60;

        System.out.println("Starting ActionsUpdater thread");
        long startTime = System.currentTimeMillis();
        Thread t = new Thread(new ActionsUpdater());
        t.start();

        System.out.println("Waiting for ActionsUpdater thread to finish");
        // loop until ActionsUpdater thread exits
        while (t.isAlive()) {
            // Wait maximum of 1 second for ActionsUpdater thread to finish.
            t.join(1000);
            if (((System.currentTimeMillis() - startTime) > patience)
                    && t.isAlive()) {
                System.out.println("Thread " + t.getName() + " is being interrupted due to inactivity.");
                t.interrupt();
                t.join();
            }
        }
        System.out.println("You have completed entering actions.");
    }
}
