package com.roi;

public class Main {
    public static void main(String[] args) {
        Coffee coffee1 = createCoffee("Espresso", "Arabica", 75.00, "Dark",
                "Colombia", "Espresso Machine", false, 10);

        Coffee coffee2 = createCoffee("Latte", "Robusta", 80.00, "Medium",
                "Brazil", "Drip", false, 5);

        handleOrder1(coffee1);
        handleOrder2(coffee2);
    }


    private static Coffee createCoffee(String name, String type, double price, String roastLevel, String origin,
                                       String brewMethod, boolean isDecaf, int stock) {
        return new Coffee(name, type, "Medium", price, roastLevel, origin, isDecaf, stock, brewMethod);
    }


    private static void handleOrder1(Coffee coffee) {
        coffee.addFlavor("Chocolate");
        coffee.addFlavor("Nutty");
        coffee.setDecaf(true);
        coffee.calculatePrice("Medium");
        coffee.discount(10);
        coffee.updateStock(-2);
        displayOrder(coffee);
    }

    private static void handleOrder2(Coffee coffee) {
        coffee.addFlavor("Vanilla");
        coffee.setDecaf(true);
        coffee.calculatePrice("Large");
        coffee.discount(5);
        coffee.updateStock(3);
        displayOrder(coffee);
    }


    private static void displayOrder(Coffee coffee) {
        System.out.println(coffee.describe());
        System.out.println("Final Price: ₱" + coffee.getPrice());
        System.out.println("Remaining Stock: " + coffee.getStock());
        System.out.println();
    }
}

