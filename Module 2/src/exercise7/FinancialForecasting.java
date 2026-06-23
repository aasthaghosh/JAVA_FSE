package exercise7;

public class FinancialForecasting {
    public static double calculateFutureValue(double presentValue, double growthRate, int periods) {
        if (periods <= 0) {
            return presentValue;
        }
        return calculateFutureValue(presentValue * (1 + growthRate), growthRate, periods - 1);
    }
}
