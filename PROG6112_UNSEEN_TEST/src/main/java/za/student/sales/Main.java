package za.student.sales;

import java.util.Scanner;

/** Starts the product sales report application. */
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // A single-dimensional array stores the product names.
        String[] productNames = {
            "Cozy Nights Monarch",
            "Restonic Barbados",
            "Sealy Phuket"
        };

        // A two-dimensional array stores three months of sales for each product.
        int[][] monthlySales = new int[3][3];

        for (int product = 0; product < productNames.length; product++) {
            for (int month = 0; month < 3; month++) {
                System.out.print("Enter number of sales for month " + (month + 1)
                        + " for " + productNames[product] + " mattress: ");
                monthlySales[product][month] = readSales(input);
            }
            System.out.println();
        }

        Product productReport = new Product(productNames, monthlySales);
        PrintProduct printer = new PrintProduct(productReport);
        printer.PrintReport();
        input.close();
    }

    // Keeps asking until the user enters a valid non-negative whole number.
    private static int readSales(Scanner input) {
        while (true) {
            if (input.hasNextInt()) {
                int value = input.nextInt();
                if (value >= 0) {
                    return value;
                }
            } else {
                input.next();
            }
            System.out.print("Please enter a whole number that is 0 or more: ");
        }
    }
}
