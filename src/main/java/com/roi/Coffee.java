package com.roi;

public class Coffee {

    // Properties (Attributes)
    String name;
    String type;
    String size;
    double price;
    String roastLevel;
    String origin;
    boolean isDecaf;
    int stock;
    String[] flavorNotes;
    String brewMethod;
    int flavorCount;  // To track the number of flavor notes

    // Constructor to initialize the Coffee object
    public Coffee(String name, String type, String size, double price, String roastLevel, String origin,
                  boolean isDecaf, int stock, String brewMethod) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.price = price;
        this.roastLevel = roastLevel;
        this.origin = origin;
        this.isDecaf = isDecaf;
        this.stock = stock;
        this.flavorNotes = new String[5];  // Set max size to 5
        this.flavorCount = 0;  // Initialize the count as 0
        this.brewMethod = brewMethod;
    }

    // Calculate price based on size using a switch statement
    public double calculatePrice(String size) {
        double finalPrice = price;  // Start with the base price

        switch (size.toLowerCase()) {
            case "small":
                finalPrice = price * 1.0;
                break;
            case "medium":
                finalPrice = price * 1.2;
                break;
            case "large":
                finalPrice = price * 1.5;
                break;
            default:
                System.out.println("Invalid size!");
        }

        return finalPrice;  // Return the final price after size adjustment
    }

    // Check if the coffee is in stock
    public boolean checkStock() {
        return stock > 0;
    }

    // Add a new flavor note (limit to 5)
    public void addFlavor(String note) {
        if (flavorCount < 5) {
            flavorNotes[flavorCount] = note;
            flavorCount++;
        } else {
            // If we already have 5 flavor notes, shift the elements and add the new flavor at the end
            for (int i = 1; i < flavorNotes.length; i++) {
                flavorNotes[i - 1] = flavorNotes[i];
            }
            flavorNotes[4] = note;  // Add the new flavor note at the last position
        }
    }

    // Update the stock (increase or decrease)
    public void updateStock(int quantity) {
        stock += quantity;
    }

    // Describe the coffee (skip null flavor notes)
    public String describe() {
        StringBuilder flavorDescription = new StringBuilder();

        for (String flavor : flavorNotes) {
            if (flavor != null) {
                if (flavorDescription.length() > 0) {
                    flavorDescription.append(", ");
                }
                flavorDescription.append(flavor);
            }
        }

        return "A " + roastLevel + " roast " + type + " coffee from " + origin + " with flavor notes of "
                + flavorDescription.toString() + ". Brewed using " + brewMethod + ".";
    }

    // Set whether the coffee is decaffeinated
    public void setDecaf(boolean isDecaf) {
        this.isDecaf = isDecaf;
    }

    // Change the roast level
    public void changeRoastLevel(String newRoastLevel) {
        this.roastLevel = newRoastLevel;
    }

    // Apply discount to the price
    public void discount(double percentage) {
        price = price - (price * (percentage / 100));
    }

    // Getter for the number of flavor notes
    public int getFlavorNoteCount() {
        return flavorCount;
    }

    // Getter for the price
    public double getPrice() {
        return this.price;
    }

    // Getter for the stock
    public int getStock() {
        return this.stock;
    }

    // Getter for the name
    public String getName() {
        return this.name;
    }

    // Getter for the type
    public String getType() {
        return this.type;
    }
}

