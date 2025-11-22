package movierental.core.strategies;

import movierental.core.Movie;

public class NewReleaseRentalCalculator implements Movie.RentalCalculator {
    @Override
    public double calculate(int daysRented) {
        return  3* daysRented;
    }
}