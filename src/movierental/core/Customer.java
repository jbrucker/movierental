package movierental.core;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private final String name;
	private final List<Rental> rentals;
	
	public Customer(String name) {
		this.name = name;
		this.rentals = new ArrayList<>();
	}
	
	public void addRental(Rental rental) {
		if (! rentals.contains(rental)) rentals.add(rental);
	}
	
	public String getName() {
		return name;
	}

	public String statement() {
		double totalCharges = 0;
		int frequentRenterPoints = 0;
		StringBuilder stmt = buildStatementHeaders();

		for(Rental rental: rentals) {
			double rentalAmount = rental.computeRentalAmount();
			frequentRenterPoints += rental.rentalPoints();
			buildStatementLineItem(rental, stmt, rentalAmount);
			totalCharges += rentalAmount;
		}
		buildStatementFooters(stmt, totalCharges, frequentRenterPoints);
		return stmt.toString();
	}

	private StringBuilder buildStatementHeaders() {
		StringBuilder stmt = new StringBuilder("Rental Report for "+getName()).append("\n\n");
		stmt.append(String.format("%-40.40s %4s %-8s\n", "Movie Title", "Days", "Price"));
		return stmt;
	}

	private static void buildStatementLineItem(Rental rental, StringBuilder stmt, double rentalAmount) {
		stmt.append(String.format("%-40.40s %3d %8.2f\n", rental.getTitle(), rental.getDaysRented(), rentalAmount));
	}

	private static void buildStatementFooters(StringBuilder stmt, double totalCharges, int frequentRenterPoints) {
		stmt.append( String.format("%-44.44s %8.2f\n", "Total Charges", totalCharges));
		stmt.append( String.format("%-44.44s %5d\n","Frequent Renter Points earned", frequentRenterPoints) );
	}
}
