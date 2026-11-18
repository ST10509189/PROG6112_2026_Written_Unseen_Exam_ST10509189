package za.student.sales;

/** Methods used to calculate product sales information. */
public interface IProducts {
    int GetTotalProductsSold();
    double GetAverageProductsSold();
    String GetMostPopularProduct();
}
