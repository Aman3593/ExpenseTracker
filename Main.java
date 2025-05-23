package org.example;
import java.time.LocalDate;
import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TransactionManager manager = new TransactionManager();

        System.out.println("Do you want to load data from a file? (yes/no)");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.print("Enter filename (e.g., transactions.csv): ");
            String filename = scanner.nextLine();
            manager.loadFromFile(filename);
        }

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. Show Monthly Summary");
            System.out.println("4. Save Data to File");
            System.out.println("5. Exnoit");

            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 1 || choice == 2) {
                String type = (choice == 1) ? "income" : "expense";
                System.out.print("Enter category (e.g., salary/business/food/rent/travel): ");
                String category = scanner.nextLine();
                System.out.print("Enter amount: ");
                double amount = Double.parseDouble(scanner.nextLine());
                System.out.print("Enter date (yyyy-MM-dd): ");
                LocalDate date = LocalDate.parse(scanner.nextLine());
                manager.addTransaction(type, category, amount, date);
                System.out.println("Transaction added.");
            } else if (choice == 3) {
                System.out.print("Enter year (e.g., 2025): ");
                int year = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter month (1-12): ");
                int month = Integer.parseInt(scanner.nextLine());
                manager.showMonthlySummary(year, month);
            } else if (choice == 4) {
                System.out.print("Enter filename to save (e.g., transactions.csv): ");
                String filename = scanner.nextLine();
                manager.saveToFile(filename);
                System.out.println("Data saved.");
            } else if (choice == 5) {
                System.out.println("Exiting. Goodbye!");
                break;
            }
        }
    }
}