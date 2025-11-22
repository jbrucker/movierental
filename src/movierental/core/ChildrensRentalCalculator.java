package movierental.core.strategies;

import movierental.core.Movie;

public class ChildrensRentalCalculator implements Movie.RentalCalculator {
    @Override
    public double calculate(int daysRented) {
        double rentalAmount = 1.5;
        if (daysRented > 3) rentalAmount += 1.5*(daysRented-3);
        return rentalAmount;
    }
}