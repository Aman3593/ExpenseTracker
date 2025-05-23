package org.example;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TransactionManager {
    private List<Transaction> transactions = new ArrayList<>();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void addTransaction(String type, String category, double amount, LocalDate date) {
        transactions.add(new Transaction(type, category, amount, date));
    }

    public void loadFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String type = parts[0];
                    String category = parts[1];
                    double amount = Double.parseDouble(parts[2]);
                    LocalDate date = LocalDate.parse(parts[3], formatter);
                    addTransaction(type, category, amount, date);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    public void saveToFile(String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Transaction t : transactions) {
                pw.println(t);
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    public void showMonthlySummary(int year, int month) {
        double totalIncome = 0;
        double totalExpense = 0;

        System.out.println("\nMonthly Summary for " + year + "-" + String.format("%02d", month));
        for (Transaction t : transactions) {
            if (t.getDate().getYear() == year && t.getDate().getMonthValue() == month) {
                if (t.getType().equalsIgnoreCase("income")) {
                    totalIncome += t.getAmount();
                } else if (t.getType().equalsIgnoreCase("expense")) {
                    totalExpense += t.getAmount();
                }
            }
        }

        System.out.println("Total Income: " + totalIncome);
        System.out.println("Total Expense: " + totalExpense);
        System.out.println("Net Savings: " + (totalIncome - totalExpense));
    }
}

