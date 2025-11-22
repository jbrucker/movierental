package movierental.core.strategies;

import movierental.core.Movie;

public class DefaultFrequentRenterPointsCalculator implements Movie.FrequentRenterPointsCalculator {
    @Override
    public int calculate(int daysRented) {
        return 1;
    }
}
