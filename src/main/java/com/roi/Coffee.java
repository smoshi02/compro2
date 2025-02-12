package com.roi;

public class Coffee {


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
    int flavorCount;


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
        this.flavorCount = 0;
        this.brewMethod = brewMethod;
    }

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

        return finalPrice;
    }


    public boolean checkStock() {
        return stock > 0;
    }

    public void addFlavor(String note) {
        if (flavorCount < 5) {
            flavorNotes[flavorCount] = note;
            flavorCount++;
        } else {
            for (int i = 1; i < flavorNotes.length; i++) {
                flavorNotes[i - 1] = flavorNotes[i];
            }
            flavorNotes[4] = note;  // Add the new flavor note at the last position
        }
    }

    public void updateStock(int quantity) {
        stock += quantity;
    }

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

    public void setDecaf(boolean isDecaf) {
        this.isDecaf = isDecaf;
    }

    public void changeRoastLevel(String newRoastLevel) {
        this.roastLevel = newRoastLevel;
    }

    public void discount(double percentage) {
        price = price - (price * (percentage / 100));
    }

    public int getFlavorNoteCount() {
        return flavorCount;
    }

    public double getPrice() {
        return this.price;
    }

    public int getStock() {
        return this.stock;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }
}

