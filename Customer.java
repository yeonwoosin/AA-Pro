import java.util.ArrayList;
import java.util.List;


/*

Customer
@ get report : long method SRP, feature envy
each.getStatus() = duplication 내부에도 duplication (daysRented)
switch문 정리 , switch에 2= magic number
Charging, Point 정리
result+= <- string generation
*/

public class Customer {
	private String name;

	private List<Rental> rentals = new ArrayList<Rental>();

	public Customer(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	public void addRental(Rental rental) {
		rentals.add(rental);

	}

	public String getReport() {
		String result = "Customer Report for " + getName() + "\n";

		List<Rental> rentals = getRentals();

		double totalCharge = 0;

		int totalPoint = 0;

		for (Rental each : rentals) {
			int eachPoint = getPoint(each);
			double eachCharge = each.getRentalCharge();

			result += makeReportRental(each, eachPoint, eachCharge);

			totalCharge += eachCharge;
			totalPoint += eachPoint ;
		}

		result += makeTotalRentalReport(totalCharge, totalPoint);

		if ( totalPoint >= 10 ) {
			System.out.println("Congrat! You earned one free coupon");
		}
		if ( totalPoint >= 30 ) {
			System.out.println("Congrat! You earned two free coupon");
		}
		return result ;
	}

	private static String makeTotalRentalReport(double totalCharge, int totalPoint) {
		return "Total charge: " + totalCharge + "\tTotal Point:" + totalPoint + "\n";
	}

	private static String makeReportRental(Rental each, int eachPoint, double eachCharge) {
		return "\t" + each.getVideo().getTitle() + "\tDays rented: " + each.getRentDate() + "\tCharge: " + eachCharge
				+ "\tPoint: " + eachPoint + "\n";
	}

	private static int getPoint(Rental rental) {
		int eachPoint = 1 ;

		if (rental.getVideo().isNewReleaseType())
			eachPoint++;
		eachPoint -= rental.getLateReturnPointPenalty();
		if (eachPoint < 0) eachPoint = 0;
		return eachPoint;
	}
}
