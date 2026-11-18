package za.student.mattress;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class ProductCalculationsTest {

    @Test
    void GetVat_WithValidData_ReturnsCorrectValue() {
        ProductCalculations calculation = new ProductCalculations(3500);
        assertEquals(525.0, calculation.GetVat(), 0.001);
    }

    @Test
    void GetTotal_WithValidData_ReturnsCorrectValue() {
        ProductCalculations calculation = new ProductCalculations(3500);
        assertEquals(4200.0, calculation.GetTotal(), 0.001);
    }
}
