package com.learntocode;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Transaction> transactionList = new ArrayList<Transaction>();

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
                Transaction transaction = new Transaction(date, time, description, vendor, price);
                System.out.println(transaction);
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
                case "L":
                    ledger();
                    break;
                case "X":
                    System.out.println("Exiting your Bank Application. Thank You!");
                    return;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    public static void makePayment() {
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

        Transaction newRecord = new Transaction(date, time, description, vendor, price);
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

    public static void addDeposit() {
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

        Transaction newRecord = new Transaction(date, time, description, vendor, price);
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
    public static void ledger() {
        boolean choices = true;
        String choice;
        while (choices != false) {
            System.out.println("A. Display All Entries");
            System.out.println("D. Display Deposits");
            System.out.println("P. Display Payments");
            System.out.println("R. Run Reports");
            System.out.println("H. Home page");
            choice = scanner.next();
            switch (choice){
                case "A":
                    showEntries();
                    break;
                case "D":
                    displayDeposits();
                    break;
                case "P":
                    displayPayments();
                    break;
                case "R":
                    runReports();
                case "H":
                    returnHome();
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
    public static void showEntries(){
        loadTransactions();
    }
    public static void displayDeposits(){
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
                Transaction transaction = new Transaction(date, time, description, vendor, price);
                if (transaction.getPrice() > 0 ){
                    System.out.println(transaction);
                }
            }
        }catch (Exception a){
            System.out.println("Invalid");
        }
    }
    public static void displayPayments(){
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
                Transaction transaction = new Transaction(date, time, description, vendor, price);
                if (transaction.getPrice() < 0 ){
                    System.out.println(transaction);
                }
            }
        }catch (Exception a){
            System.out.println("Invalid");
        }
    }
    public static void runReports(){
        LocalDate date = LocalDate.now();

        boolean choices = true;
        int choice;
        while (choices != false) {
            System.out.println("Custom Search Report:");
            System.out.println("1. Month to Date");
            System.out.println("2. Previous Month");
            System.out.println("3. Year to Date");
            System.out.println("4. Previous Year");
            System.out.println("5. Search by Vendor");
            System.out.println("0. Return to Report Page");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    for (Transaction comp : transactionList) {
                        if (comp.getDate().isBefore(date.plusDays(1)) && comp.getDate().isAfter(date.minusMonths(1).minusDays(1))) {
                            System.out.println(comp);
                        }
                    }
                    break;
                case 2:
                    for(Transaction comp:transactionList) {
                    if (comp.getDate().isAfter(date.minusMonths(1).minusDays(1)) && comp.getDate().isBefore(date.minusMonths(1))) {
                        System.out.println(comp);
                    }

                }
                break;
                case 3:
                    for(Transaction comp:transactionList) {
                        if (comp.getDate().isBefore(date.plusDays(1)) && comp.getDate().isAfter(date.minusYears(1))) {
                            System.out.println(comp);
                        }
                    }
                    break;
                case 4:
                    for(Transaction comp:transactionList) {
                        if (comp.getDate().isAfter(date.minusYears(1).minusMonths(date.getMonthValue())) && comp.getDate().isBefore(date.minusMonths(date.getMonthValue() - 1))) {
                            System.out.println(comp);
                        }
                    }
                    break;
                case 5:
                    System.out.println("What is the name of the vendor?");
                    String response = scanner.next();
                    for (Transaction comp:transactionList){
                        if(comp.getVendor().equals(response)){
                            System.out.println(comp);
                        }
                    }
                    break;
                case 0:
                    returnHome();
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }
    }
    public static void returnHome() {
        loadEntrance();
    }
}

