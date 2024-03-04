import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ExpenseTrackerApp {
    private static final List<Expense> expenses = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nExpense Tracker Menu:");
            System.out.println("1. Add Expense");
            System.out.println("2. Edit Expense");
            System.out.println("3. Delete Expense");
            System.out.println("4. View Expenses");
            System.out.println("5. Generate Report");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    editExpense();
                    break;
                case 3:
                    deleteExpense();
                    break;
                case 4:
                    viewExpenses();
                    break;
                case 5:
                    generateReport();
                    break;
                case 6:
                    System.out.println("Exiting the Expense Tracker. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
    }

    private static void addExpense() {
        System.out.println("\nAdd Expense:");
        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.print("Category: ");
        String category = scanner.nextLine();

        System.out.print("Date (yyyy-MM-dd): ");
        String dateString = scanner.nextLine();
        Date date = parseDate(dateString);

        Expense expense = new Expense(description, amount, category, date);
        expenses.add(expense);

        System.out.println("Expense added successfully!");
    }

    private static void editExpense() {
        System.out.println("\nEdit Expense:");
        System.out.print("Enter the index of the expense to edit: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (index >= 0 && index < expenses.size()) {
            Expense oldExpense = expenses.get(index);

            System.out.print("New Description (" + oldExpense.getDescription() + "): ");
            String newDescription = scanner.nextLine();
            if (!newDescription.isEmpty()) {
                oldExpense.setDescription(newDescription);
            }

            System.out.print("New Amount (" + oldExpense.getAmount() + "): ");
            String newAmountString = scanner.nextLine();
            if (!newAmountString.isEmpty()) {
                double newAmount = Double.parseDouble(newAmountString);
                oldExpense.setAmount(newAmount);
            }

            System.out.print("New Category (" + oldExpense.getCategory() + "): ");
            String newCategory = scanner.nextLine();
            if (!newCategory.isEmpty()) {
                oldExpense.setCategory(newCategory);
            }

            System.out.print("New Date (" + formatDate(oldExpense.getDate()) + "): ");
            String newDateString = scanner.nextLine();
            if (!newDateString.isEmpty()) {
                Date newDate = parseDate(newDateString);
                oldExpense.setDate(newDate);
            }

            System.out.println("Expense edited successfully!");
        } else {
            System.out.println("Invalid index. No expense found at index " + index);
        }
    }

    private static void deleteExpense() {
        System.out.println("\nDelete Expense:");
        System.out.print("Enter the index of the expense to delete: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (index >= 0 && index < expenses.size()) {
            Expense deletedExpense = expenses.remove(index);
            System.out.println("Expense deleted successfully: " + deletedExpense);
        } else {
            System.out.println("Invalid index. No expense found at index " + index);
        }
    }

    private static void viewExpenses() {
        System.out.println("\nExpenses:");

        if (expenses.isEmpty()) {
            System.out.println("No expenses available.");
        } else {
            for (int i = 0; i < expenses.size(); i++) {
                System.out.println(i + ". " + expenses.get(i));
            }
        }
    }

    private static void generateReport() {
        System.out.println("\nExpense Report:");

        if (expenses.isEmpty()) {
            System.out.println("No expenses available. Generate a report after adding expenses.");
        } else {
            double totalAmount = 0;
            for (Expense expense : expenses) {
                totalAmount += expense.getAmount();
            }

            System.out.println("Total Expenses: " + totalAmount);
        }
    }

    private static Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            return null;
        }
    }

    private static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
}
