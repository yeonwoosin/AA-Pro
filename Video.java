import java.util.Date;

public abstract class Video {
	private String title ;
	private EpriceCode priceCode;

	private Date registeredDate ;
	private boolean rented ;

	// long param - 관련없는 param
	public Video(String title, EpriceCode priceCode, Date registeredDate) {
		this.setTitle(title) ;
		this.setPriceCode(priceCode); ;
		this.registeredDate = registeredDate ;
	}

	public abstract int getPointPenalty();

	public void setPriceCode(EpriceCode priceCode) {
		this.priceCode = priceCode;
	}
	public EpriceCode getPriceCode(){return priceCode;}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public abstract int getLimit();

	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	public abstract int getVideoType();


	public double GetVideoCharge(int DaysRented) {
		if (priceCode == EpriceCode.REGULAR){
			if (DaysRented <= 2)
				return 2;
			else
				return DaysRented * 1.5;
		}
		else {
			return DaysRented * 3;
		}
	}
	public boolean isNewReleaseType() {
		return (getPriceCode() == EpriceCode.NEW_RELEASE);
	}
}

class VideoVHS extends Video {
	public VideoVHS(String title, EpriceCode priceCode, Date registeredDate) {
		super(title, priceCode, registeredDate);
	}
	public int getPointPenalty() {
		return 1;
	}
	public int getLimit() { return 5;}
	public int getVideoType() { return 1;};
}

class VideoCD extends Video {
	public VideoCD(String title, EpriceCode priceCode, Date registeredDate) {
		super(title, priceCode, registeredDate);
	}

	public int getPointPenalty() {
		return 2;
	}
	public int getLimit() { return 3;}
	public int getVideoType() { return 2;};
}
class VideoDVD extends Video {
	public VideoDVD(String title, EpriceCode priceCode, Date registeredDate) {
		super(title, priceCode, registeredDate);
	}
	public int getPointPenalty() {
		return 3;
	}
	public int getLimit() { return 2;}
	public int getVideoType() { return 3;};
}

