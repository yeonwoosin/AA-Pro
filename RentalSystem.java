import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class RentalSystem {
	private List<Customer> customers = new ArrayList<Customer>() ;

	private List<Video> videos = new ArrayList<Video>() ;

	private Customer findCustomer(String customerName)
	{
		Customer foundCustomer = null ;
		for ( Customer customer: customers ) {
			if ( customer.getName().equals(customerName)) {
				foundCustomer = customer ;
				return foundCustomer;
			}
		}
		return null;
	}
	private Video findVideo(String videoTitle) {
		Video foundVideo = null ;
		for ( Video video: videos ) {
			if ( video.getTitle().equals(videoTitle) && video.isRented() == false ) {
				foundVideo = video ;
				return foundVideo;
			}
		}
		return null;
	}
	public void clearRentals(String customerName) {
		Customer foundCustomer = findCustomer(customerName);
		if ( foundCustomer == null ) {
			System.out.println("No customer found") ;
			return;
		}
		foundCustomer.clearRentals();
	}

	public void returnVideo(String customerName, String videoTitle) {
		Customer foundCustomer = findCustomer(customerName);
		if ( foundCustomer == null ) return ;

		foundCustomer.returnVideo(videoTitle);
	}

	public void showCustomerAndClearRental(String customerName) {
		Customer foundCustomer = findCustomer(customerName);
		if ( foundCustomer == null ) {
			System.out.println("No customer found") ;
			return;
		}
		foundCustomer.clearRentals();
	}
	public void init() {
		Customer james = new Customer("James") ;
		Customer brown = new Customer("Brown") ;
		customers.add(james) ;
		customers.add(brown) ;

		VideoFactory videoFactory = new VideoFactory();
		Video v1 = videoFactory.create("v1", VideoType.CD, EpriceCode.REGULAR, new Date()) ;
		Video v2 = videoFactory.create("v2", VideoType.DVD, EpriceCode.NEW_RELEASE, new Date()) ;
		videos.add(v1) ;
		videos.add(v2) ;

		Rental r1 = new Rental(v1) ;
		Rental r2 = new Rental(v2) ;

		james.addRental(r1) ;
		james.addRental(r2) ;
	}

	public void listVideos() {
		for ( Video video: videos ) {
			System.out.println("Price code: " + video.getPriceCode() +"\tTitle: " + video.getTitle()) ;
		}
	}

	public void listCustomers() {
		for ( Customer customer: customers ) {
			System.out.println("Name: " + customer.getName() +
					"\tRentals: " + customer.getRentals().size()) ;
			customer.showRentals();
		}
	}

	public void getCustomerReport(String customerName) {
		Customer foundCustomer = findCustomer(customerName);
 		if ( foundCustomer == null ) {
			System.out.println("No customer found") ;
		} else {
			String result = foundCustomer.getReport() ;
			System.out.println(result);
		}
	}

	public void rentVideo(String customerName, String videoTitle) {
		Customer foundCustomer = findCustomer(customerName);
		if ( foundCustomer == null ) return ;

		Video foundVideo = findVideo(videoTitle);
		if ( foundVideo == null ) return ;

		foundCustomer.rentVideo(foundVideo);
	}

	public void registerCustomer(String name) {
			Customer customer = new Customer(name) ;
			customers.add(customer) ;
	}

	public void registerVideo(String title, int videoType, int priceCode) {
		Date registeredDate = new Date();
		VideoFactory videoFactory = new VideoFactory();
		Video video = videoFactory.create(title, VideoType.trans(videoType), EpriceCode.trans(priceCode), registeredDate) ;
		videos.add(video) ;
	}
}
