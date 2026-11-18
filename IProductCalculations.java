package za.student.mattress;

/** Defines the calculations required for a mattress sale. */
public interface IProductCalculations {
    double GetVat();
    double GetCommission();
    double GetTotal();
}
