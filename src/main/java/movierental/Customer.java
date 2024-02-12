package movierental;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String name;
    private List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental arg) {
        rentals.add(arg);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = addHeaderLine();

        for (Rental rental : rentals) {
            double thisAmount = Rental.determineAmounts(rental);
            frequentRenterPoints += Rental.addFrequentRenterPoints(rental);
            result += showFiguresForThisRental(rental, thisAmount);
            totalAmount += thisAmount;
        }

        // add footer lines
        result += addFooterLines(totalAmount, frequentRenterPoints);

        return result;
    }

    private String addHeaderLine() {
        return "Rental Record for " + getName() + "\n";
    }

    private static String addFooterLines(double totalAmount, int frequentRenterPoints) {
        return "Amount owed is " + totalAmount + "\n"
                + "You earned " + frequentRenterPoints + " frequent renter points";
    }

    private static String showFiguresForThisRental(Rental each, double thisAmount) {
        return "\t" + each.getMovie().getTitle() + "\t" + thisAmount + "\n";
    }

}
