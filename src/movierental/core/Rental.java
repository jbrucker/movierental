package movierental.core;

public class Rental {
	private final Movie movie;
	private final int daysRented;

	public Rental(Movie movie, int daysRented) {
		this.movie = movie;
		this.daysRented = daysRented;
	}

	double computeRentalAmount() {
		return movie.rentalAmount(daysRented);
	}

	public Movie getMovie() {
		return movie;
	}

	public int getDaysRented() {
		return daysRented;
	}

	int rentalPoints() {
		return movie.rentalPoints(daysRented);
	}

	public String getTitle() {
		return movie.getTitle();
	}
}
