package za.student.sales;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    void GetTotalProductsSold_WithValidData_ReturnsCorrectValue() {
        String[] names = {"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"};
        int[][] sales = {{25, 18, 20}, {15, 10, 12}, {18, 15, 20}};
        Product product = new Product(names, sales);

        assertEquals(153, product.GetTotalProductsSold());
    }

    @Test
    void GetAverageProductsSold_WithValidData_ReturnsCorrectValue() {
        String[] names = {"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"};
        int[][] sales = {{25, 18, 20}, {15, 10, 12}, {18, 15, 20}};
        Product product = new Product(names, sales);

        assertEquals(17.0, product.GetAverageProductsSold(), 0.001);
    }
}
