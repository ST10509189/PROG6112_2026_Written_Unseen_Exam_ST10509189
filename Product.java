package za.student.sales;

/** Stores the sales figures and calculates the required report values. */
public class Product implements IProducts {
    private final String[] productNames;
    private final int[][] monthlySales;

    public Product(String[] productNames, int[][] monthlySales) {
        this.productNames = productNames;
        this.monthlySales = monthlySales;
    }

    @Override
    public int GetTotalProductsSold() {
        int total = 0;
        for (int row = 0; row < monthlySales.length; row++) {
            for (int col = 0; col < monthlySales[row].length; col++) {
                total += monthlySales[row][col];
            }
        }
        return total;
    }

    @Override
    public double GetAverageProductsSold() {
        int count = 0;
        for (int[] row : monthlySales) {
            count += row.length;
        }
        if (count == 0) {
            return 0;
        }
        return (double) GetTotalProductsSold() / count;
    }

    /*
     * The assignment's sample report displays Cozy Nights Monarch (25).
     * This matches the highest individual monthly figure in the sample.
     */
    @Override
    public String GetMostPopularProduct() {
        if (monthlySales.length == 0) {
            return "No sales data";
        }

        int highestSales = -1;
        int popularIndex = 0;

        for (int row = 0; row < monthlySales.length; row++) {
            for (int col = 0; col < monthlySales[row].length; col++) {
                if (monthlySales[row][col] > highestSales) {
                    highestSales = monthlySales[row][col];
                    popularIndex = row;
                }
            }
        }
        return productNames[popularIndex] + " (" + highestSales + ")";
    }
}
