import java.util.Date;

public class Rental {
	private Video video ;
	private int status ; // 0 for Rented, 1 for Returned <- explicit
	private Date rentDate ;
	private Date returnDate ;

	public Rental(Video video) {
		this.video = video ;
		status = 0 ;
		rentDate = new Date() ;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public int getStatus() {
		return status;
	}

	public void returnVideo() {
		if ( status == 1 ) {
			returnDate = new Date() ;
		}
	}
	public Date getRentDate() {
		return rentDate;
	}

	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	// feature envy, duplicate, switch?
	public int getDaysRentedLimit() {
		int limit = 0 ;
		int daysRented = getDaysRental();
		if ( daysRented <= 2) return limit ;

		switch ( video.getVideoType() ) {
			case Video.VHS: limit = 5 ; break ;
			case Video.CD: limit = 3 ; break ;
			case Video.DVD: limit = 2 ; break ;
		}
		return limit ;
	}

	public int getDaysRental()
	{
		long diff;
	    if (getStatus() == 1) { // returned Video
	    	diff = getReturnDate().getTime() - getRentDate().getTime();
	    } else { // not yet returned
	        diff = new Date().getTime() - getRentDate().getTime();
	    }
	    return ((int) (diff / (1000 * 60 * 60 * 24)) + 1);
	}
    
	public double getRentalCharge()
	{
		return getVideo().GetVideoCharge(getDaysRental());
	}

	public int getLateReturnPointPenalty()
	{
		if (getDaysRental() > getDaysRentedLimit())
			return getVideo().getPointPenalty();
		return 0;
	}

}
