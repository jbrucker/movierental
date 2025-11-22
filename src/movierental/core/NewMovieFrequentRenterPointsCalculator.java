package movierental.core.strategies;

import movierental.core.Movie;

public class NewMovieFrequentRenterPointsCalculator implements Movie.FrequentRenterPointsCalculator {
    @Override
    public int calculate(int daysRented) {
        return daysRented;
    }
}