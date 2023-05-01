package com.learntocode;

import java.io.*;
import java.security.PublicKey;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<transaction> transactionList = new ArrayList<transaction>();

    public static void main(String[] args) {
        loadEntrance();
    }

    public static void loadTransactions() {
        String filename = "transactions.csv";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            System.out.println("Transactions:");
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\\|");
                LocalDate date = LocalDate.parse(tokens[0]);
                LocalTime time = LocalTime.parse(tokens[1]);
                String description = tokens[2];
                String vendor = tokens[3];
                float price = Float.parseFloat(tokens[4]);
                transaction transaction = new transaction(date, time, description, vendor, price);
                System.out.println(transaction);
                reader.close();
            }
        } catch (Exception e) {
            System.out.println("Error reading file");
        }
    }

    public static void loadEntrance() {
        boolean choices = true;
        String choice;
        while (choices != false) {
            System.out.println("Welcome to Your Bank Statement");
            System.out.println("------------------------------");
            System.out.println("What would you like to do today");
            System.out.println("D. Add new Deposit");
            System.out.println("P. Make Payment");
            System.out.println("L. View Ledger options");
            System.out.println("X. Exit");
            choice = scanner.next();
            switch (choice) {
                case "D":
                    addDeposit();
                    break;
                case "P":
                    makePayment();
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    public static void addDeposit() {
        System.out.println("What have you recently purchased?");
        String description = scanner.next();
        System.out.println("Who did you purchase it from?");
        String vendor = scanner.next();
        System.out.println("What day did you make the purchase? (yyyy-MM-dd)");
        LocalDate date = LocalDate.parse(scanner.next());
        System.out.println("What was the time of the purchase? (hh:mm:ss)");
        LocalTime time = LocalTime.parse(scanner.next());
        System.out.println("And how much was the payment made for?");
        float price = scanner.nextFloat();

        transaction newRecord = new transaction(date, time, description, vendor, price);
        transactionList.add(newRecord);
        try {
            String filePath = "transactions.csv";
            FileWriter newTransaction = new FileWriter(filePath, true);
            BufferedWriter bufWrite = new BufferedWriter(newTransaction);
            bufWrite.write(String.valueOf(newRecord));
            bufWrite.newLine();
            bufWrite.close();
        } catch (Exception w) {
            System.out.println("Invalid file");
        }
    }

    public static void makePayment() {
        System.out.println("What did you recently receive?");
        String description = scanner.next();
        System.out.println("Who did you receive it from?");
        String vendor = scanner.next();
        System.out.println("What day did you receive it? (yyyy-MM-dd)");
        LocalDate date = LocalDate.parse(scanner.next());
        System.out.println("What was the time of the transaction? (hh:mm:ss)");
        LocalTime time = LocalTime.parse(scanner.next());
        System.out.println("And how much was the payment received for?");
        float price = scanner.nextFloat();

        transaction newRecord = new transaction(date, time, description, vendor, price);
        transactionList.add(newRecord);
        try {
            String filePath = "transactions.csv";
            FileWriter newTransaction = new FileWriter(filePath, true);
            BufferedWriter bufWrite = new BufferedWriter(newTransaction);
            bufWrite.write(String.valueOf(newRecord));
            bufWrite.newLine();
            bufWrite.close();
        } catch (Exception w) {
            System.out.println("Invalid file");
        }
    }
}

