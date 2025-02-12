package com.roi;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This is a coffee ordering system that allows a user to select coffee options and input quantities.
 * It also shows the subtotal, VAT, and grand total.
 */
public class CoffeeOrder {
    public static void main(String[] args) {
        // 2D array where the first column is coffee name and the second column is price
        String[][] coffees = {
                {"Espresso", "50.0"},
                {"Latte", "70.0"},
                {"Cappuccino", "65.0"},
                {"Mocha", "80.0"}
        };

        Scanner input = new Scanner(System.in);
        int[] quantities = new int[coffees.length];

        // Subtotal calculation
        double subtotal = takeOrders(input, coffees, quantities);

        // Calculate VAT and grand total
        double vat = calculateVat(subtotal);
        double grandTotal = calculateGrandTotal(subtotal, vat);

        // Display the receipt
        displayReceipt(coffees, quantities, subtotal, vat, grandTotal);

        // Save the receipt to file
        saveFileReceipt(coffees, quantities, subtotal, vat, grandTotal);

        input.close();
    }

    /**
     * User will input their orders.
     * @param input the orders
     * @param coffees 2D array with coffee names and prices
     * @param quantities how many orders
     * @return order
     */
    public static double takeOrders(Scanner input, String[][] coffees, int[] quantities){
        double subtotal = 0;
        int choice = -1;
        do {
            System.out.println();
            displayMenu(coffees); // Display menu in any instances to order more
            try {
                System.out.print("Choose your coffee (1-4, or 0 to finish): ");
                choice = input.nextInt(); // Get user's choice for coffee
                input.nextLine();
                if (choice >= 1 && choice <= coffees.length) {
                    int quantity = -1;
                    while (quantity <= 0) {
                        System.out.print("Enter quantity: ");
                        if (input.hasNextInt()) {
                            quantity = input.nextInt();
                            if (quantity <= 0) {
                                System.out.println("Please enter a quantity greater than 0.");
                            }
                        } else {
                            System.out.println("Invalid input! Only numbers are allowed.");
                            input.next();
                        }
                    }
                    quantities[choice - 1] += quantity;
                    subtotal += Double.parseDouble(coffees[choice - 1][1]) * quantity;
                } else if (choice != 0) {
                    System.out.println("Invalid option! Please choose a valid number (1-4, or 0 to finish).");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Only numbers are allowed.");
                input.nextLine();
            }
        } while (choice != 0);
        return subtotal;
    }

    /**
     * Menu of the coffee
     */
    public static void displayMenu(String[][] coffees){
        System.out.println("--- Coffee Menu ---");
        for (int i = 0; i < coffees.length; i++) {
            System.out.println((i + 1) + ". " + coffees[i][0] + " - " + coffees[i][1] + " PHP");
        }
        System.out.println("0. Finish Order");
    }

    /**
     * Calculate the VAT
     */
    public static double calculateVat(double subtotal){
        return subtotal * 0.12;
    }

    /**
     * Calculate the grand total
     */
    public static double calculateGrandTotal(double subtotal, double vat){
        return subtotal + vat;
    }

    /**
     * Displaying the receipt
     */
    public static void displayReceipt(String[][] coffees, int[] quantities, double subtotal, double vat, double grandTotal){
        StringBuilder receipt = new StringBuilder();
        receipt.append("---- Coffee Order Receipt -----\n");

        for (int i = 0; i < coffees.length; i++) {
            if (quantities[i] > 0) {
                receipt.append(quantities[i])
                        .append(" x ")
                        .append(coffees[i][0])
                        .append(" @ ")
                        .append(String.format("%.2f", Double.parseDouble(coffees[i][1])))
                        .append(" each = ")
                        .append(String.format("%.2f", Double.parseDouble(coffees[i][1]) * quantities[i]))
                        .append("\n");
            }
        }

        receipt.append("------------------------\n")
                .append("Subtotal: ").append(String.format("%.2f", subtotal)).append(" PHP\n")
                .append(String.format("VAT (12%%): %.2f PHP\n", vat))
                .append("Grand Total: ").append(String.format("%.2f", grandTotal)).append(" PHP\n")
                .append("------------------------\n");

        System.out.println(receipt.toString());
    }

    /**
     * Saving the receipt to a file
     */
    public static void saveFileReceipt(String[][] coffees, int[] quantities, double subtotal, double vat, double grandTotal) {
        StringBuilder fileContent = new StringBuilder();
        fileContent.append("---- Coffee Order Receipt -----\n");

        for (int i = 0; i < coffees.length; i++) {
            if (quantities[i] > 0) {
                fileContent.append(quantities[i])
                        .append(" x ")
                        .append(coffees[i][0])
                        .append(" @ ")
                        .append(String.format("%.2f", Double.parseDouble(coffees[i][1])))
                        .append(" each = ")
                        .append(String.format("%.2f", Double.parseDouble(coffees[i][1]) * quantities[i]))
                        .append("\n");
            }
        }

        fileContent.append("------------------------\n")
                .append("Subtotal: ").append(String.format("%.2f", subtotal)).append(" PHP\n")
                .append(String.format("VAT (12%%): %.2f PHP\n", vat))
                .append("Grand Total: ").append(String.format("%.2f", grandTotal)).append(" PHP\n")
                .append("------------------------\n");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("CoffeeReceipt.txt"))) {
            writer.write(fileContent.toString());
            System.out.println("Receipt saved to CoffeeReceipt.txt");
        } catch (IOException e) {
            System.out.println("Error occurred while saving the receipt: " + e.getMessage());
        }
    }
}
