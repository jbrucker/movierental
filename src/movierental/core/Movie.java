package movierental.core;


public class Movie {
	private final String title;
	private final RentalCalculator rentalCalculator;
	private final FrequentRenterPointsCalculator frequentRenterPointsCalculator;

	@FunctionalInterface
	public interface FrequentRenterPointsCalculator {
		int calculate(int daysRented);
	}

	@FunctionalInterface
	public interface RentalCalculator {
		double calculate(int daysRented);
	}

	public Movie(String title, RentalCalculator rentalCalculator, FrequentRenterPointsCalculator frequentRenterPointsCalculator) {
		this.title = title;
		this.rentalCalculator = rentalCalculator;
		this.frequentRenterPointsCalculator = frequentRenterPointsCalculator;
	}

	public String getTitle() {
		return this.title;
	}

	double rentalAmount(int daysRented) {
		return rentalCalculator.calculate(daysRented);
	}

	int rentalPoints(int daysRented) {
		return frequentRenterPointsCalculator.calculate(daysRented);
	}
}
