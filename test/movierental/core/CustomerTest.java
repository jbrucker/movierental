package movierental.core;

import movierental.core.strategies.ChildrensRentalCalculator;
import movierental.core.strategies.DefaultFrequentRenterPointsCalculator;
import movierental.core.strategies.NewMovieFrequentRenterPointsCalculator;
import movierental.core.strategies.NewReleaseRentalCalculator;
import movierental.core.strategies.RegularAmountRentalCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    private Customer customer;
    private final Movie.RentalCalculator regularRentalCalculator = new RegularAmountRentalCalculator();
    private final Movie.RentalCalculator newReleaseRentalCalculator = new NewReleaseRentalCalculator();
    private final Movie.RentalCalculator childrensRentalCalculator = new ChildrensRentalCalculator();
    private final Movie.FrequentRenterPointsCalculator frequentRenterPointsCalculator = new DefaultFrequentRenterPointsCalculator();
    private final Movie.FrequentRenterPointsCalculator newMovieFrequentRenterPointsCalculator = new NewMovieFrequentRenterPointsCalculator();
    private final Movie regularMovie = new Movie("Regular Movie", regularRentalCalculator, frequentRenterPointsCalculator);
    private final Movie newReleaseMovie = new Movie("New Release Movie", newReleaseRentalCalculator, newMovieFrequentRenterPointsCalculator);
    private final Movie childrensMovie = new Movie("Childrens Movie", childrensRentalCalculator, frequentRenterPointsCalculator);

    @BeforeEach
    void setup() {
        this.customer = new Customer("Customer");
    }

    @Test
    void emptyStatement() {
        assertEquals("""
        Rental Report for Customer
        
        Movie Title                              Days Price\s\s\s
        Total Charges                                    0.00
        Frequent Renter Points earned                    0
        """, customer.statement());
    }

    @Test
    void regularMovie() {
        customer.addRental(new Rental(regularMovie, 1));

        String expected = String.format("""
                Rental Report for Customer
                
                Movie Title                              Days Price\s\s\s
                Regular Movie                              1     2.00
                Total Charges                                    2.00
                Frequent Renter Points earned                    1
                """, regularMovie.getTitle());
        assertEquals(expected, customer.statement());
    }

    @Test
    void regularMovieRentedFor3days() {
        customer.addRental(new Rental(regularMovie, 3));

        String expected = String.format("""
                Rental Report for Customer
                
                Movie Title                              Days Price\s\s\s
                Regular Movie                              3     3.50
                Total Charges                                    3.50
                Frequent Renter Points earned                    1
                """, regularMovie.getTitle());
        assertEquals(expected, customer.statement());
    }

    @Test
    void newRelease() {
        customer.addRental(new Rental(newReleaseMovie, 1));
        assertEquals("""
        Rental Report for Customer
        
        Movie Title                              Days Price\s\s\s
        New Release Movie                          1     3.00
        Total Charges                                    3.00
        Frequent Renter Points earned                    1
        """, customer.statement());
    }

    @Test
    void newReleaseRentedFor5Days() {
        customer.addRental(new Rental(newReleaseMovie, 5));
        assertEquals("""
        Rental Report for Customer
        
        Movie Title                              Days Price\s\s\s
        New Release Movie                          5    15.00
        Total Charges                                   15.00
        Frequent Renter Points earned                    5
        """, customer.statement());
    }

    @Test
    void childrensMovie() {
        customer.addRental(new Rental(childrensMovie, 1));
        assertEquals("""
        Rental Report for Customer
        
        Movie Title                              Days Price\s\s\s
        Childrens Movie                            1     1.50
        Total Charges                                    1.50
        Frequent Renter Points earned                    1
        """, customer.statement());
    }

    @Test
    void childrensMovieForFourDays() {
        customer.addRental(new Rental(childrensMovie, 4));
        assertEquals("""
        Rental Report for Customer
        
        Movie Title                              Days Price\s\s\s
        Childrens Movie                            4     3.00
        Total Charges                                    3.00
        Frequent Renter Points earned                    1
        """, customer.statement());
    }
}