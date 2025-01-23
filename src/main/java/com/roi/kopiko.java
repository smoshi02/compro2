package com.roi;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This is a coffee ordering system that allows a user to select coffee options and input quantities
 * it also shows the subtotal, VAT, and grand total
 */
public class kopiko {
    public static void main(String[] args) {
        double[] prices = {50.0, 70.0, 65.0, 80.0}; //array for prices

        Scanner input = new Scanner(System.in);
        int[] quantities = new int[prices.length];

        //subtotal
        double subtotal = takeOrders(input, prices, quantities);

        // Calculate VAT and grand total
        double vat = calculateVat(subtotal);
        double grandTotal = calculateGrandTotal(subtotal, vat);

        // Display the receipt
        displayReceipt(prices, quantities, subtotal, vat, grandTotal);

        //Display the receipt to file coffee.txt
        saveFileReceipt(prices, quantities, subtotal, vat, grandTotal);

        input.close();

    }

    //loop of input of the user
    public static double takeOrders(Scanner input, double[] prices, int[] quantities){
        double subtotal = 0;
        int choice = -1;
        do {
            System.out.println();
            displayMenu(); //display menu in any instances to order more
            try {
                System.out.print("Choose your coffee (1-4, or 0 to finish): ");
                choice = input.nextInt(); // Get user's choice for coffee
                input.nextLine();
                if (choice >= 1 && choice <= prices.length) {


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
                    subtotal += prices[choice - 1] * quantity;

                } else if (choice != 0) {
                    System.out.println("Invalid option! Please choose a valid number (1-4, or 0 to finish).");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Only numbers are allowed.");
                input.nextLine();
            }
        }while (choice != 0);
        return subtotal;
    }

    //coffee that available
    public static String getCoffeeName(int choice){
        switch(choice){
            case 1: return "Espresso";
            case 2: return "Latte";
            case 3: return "Cappuccino";
            case 4: return "Mocha";
            default: return "INVALID";

        }
    }

    //menu of the coffee
    public static void displayMenu(){
        System.out.println("--- Coffee Menu ---");
        System.out.println("1. Espresso - 50.0 PHP");
        System.out.println("2. Latte - 70.0 PHP");
        System.out.println("3. Cappuccino - 65.0 PHP");
        System.out.println("4. Mocha - 80.0 PHP");
        System.out.println("0. Finish Order");
    }

    //VAT
    public static double calculateVat(double subtotal){
        return subtotal * .12;
    }
    //GRAND TOTAL
    public static double calculateGrandTotal(double subtotal, double vat){
        return subtotal + vat;
    }
    //DISPLAY OF THE RECEIPT
    public static void displayReceipt(double[] prices, int[] quantities, double subtotal, double vat, double grandTotal){
        System.out.println();
        System.out.println("---- Coffee Order Receipt -----");
        for (int i = 0; i < prices.length; i++) {
            if (quantities[i] > 0) {
                System.out.println(quantities[i] + " x " + getCoffeeName(i + 1) + " @ " + String.format("%.2f", prices[i])
                        + " each = " + String.format("%.2f", prices[i] * quantities[i]));
            }
        }

        System.out.println("------------------------");
        System.out.println("Subtotal: " + String.format("%.2f", subtotal) + " PHP");
        System.out.printf("VAT (12%%): %.2f PHP\n", vat);
        System.out.println("Grand Total: " + String.format("%.2f", grandTotal) + " PHP");
        System.out.println("------------------------");

    }
    //SAVING FILE TO THE COFFEE.TXT
    public static void saveFileReceipt(double[] prices, int[] quantities, double subtotal, double vat, double grandTotal) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("CoffeeReceipt.txt"))) {
            // Writing header to the file
            writer.write("---- Coffee Order Receipt -----\n");

            // Writing each item ordered
            for (int i = 0; i < prices.length; i++) {
                if (quantities[i] > 0) {
                    writer.write(quantities[i] + " x " + getCoffeeName(i + 1) + " @ " + String.format("%.2f", prices[i])
                            + " each = " + String.format("%.2f", prices[i] * quantities[i]) + "\n");
                }
            }

            // Writing subtotal, VAT, and grand total
            writer.write("------------------------\n");
            writer.write("Subtotal: " + String.format("%.2f", subtotal) + " PHP\n");
            writer.write(String.format("VAT (12%%): %.2f PHP\n", vat));
            writer.write("Grand Total: " + String.format("%.2f", grandTotal) + " PHP\n");
            writer.write("------------------------\n");

            // Inform the user that the receipt was saved
            System.out.println("Receipt saved to CoffeeReceipt.txt");
        } catch (IOException e) {
            // Handle error if there is an issue saving the file
            System.out.println("Error occurred while saving the receipt: " + e.getMessage());
        }
    }
}
