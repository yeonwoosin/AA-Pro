import java.util.ArrayList;
import java.util.Date;
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

	// SRP violation, long method
	// feature envy - divergent change
	// getReport를 class로? builder?
	// duplication
	// switch, strategy?, magic number?(daysRented)

	public String getReport() {
		String result = "Customer Report for " + getName() + "\n";

		List<Rental> rentals = getRentals();

		double totalCharge = 0;
		int totalPoint = 0;

		for (Rental each : rentals) {
			int eachPoint = 0 ;

			double eachCharge = 0;
			int daysRented = getDaysRented(each);

			switch (each.getVideo().getPriceCode()) {
			case Video.REGULAR:
				eachCharge += 2;
				if (daysRented > 2)
					eachCharge += (daysRented - 2) * 1.5;
				break;
			case Video.NEW_RELEASE:
				eachCharge = daysRented * 3;
				break;
			}

			eachPoint++;

			if ((each.getVideo().getPriceCode() == Video.NEW_RELEASE) )
				eachPoint++;

			// feature envy
			if ( daysRented > each.getDaysRentedLimit() )
				eachPoint -= Math.min(eachPoint, each.getVideo().getLateReturnPointPenalty()) ;

			result += "\t" + each.getVideo().getTitle() + "\tDays rented: " + daysRented + "\tCharge: " + eachCharge
					+ "\tPoint: " + eachPoint + "\n";

			// change, point 분할
			totalCharge += eachCharge;
			totalPoint += eachPoint ;
		}

		// string gen 분리
		result += "Total charge: " + totalCharge + "\tTotal Point:" + totalPoint + "\n";


		if ( totalPoint >= 10 ) {
			System.out.println("Congrat! You earned one free coupon");
		}
		if ( totalPoint >= 30 ) {
			System.out.println("Congrat! You earned two free coupon");
		}
		return result ;
	}

	private static int getDaysRented(Rental each) {
		int daysRented = 0;


		if (each.getStatus() == 1) { // returned Video
			long diff = each.getReturnDate().getTime() - each.getRentDate().getTime();
			daysRented = (int) (diff / (1000 * 60 * 60 * 24)) + 1;
		} else { // not yet returned
			long diff = new Date().getTime() - each.getRentDate().getTime();
			daysRented = (int) (diff / (1000 * 60 * 60 * 24)) + 1;
		}
		return daysRented;
	}
}
