package movierental.core.strategies;

import movierental.core.Movie;

public class RegularAmountRentalCalculator implements Movie.RentalCalculator {
    @Override
    public double calculate(int daysRented) {
        double rentalAmount = 2;
        if (daysRented > 2) rentalAmount += 1.5*(daysRented-2);
        return rentalAmount;
    }
}