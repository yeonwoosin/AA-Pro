import java.util.Date;

public class Video {
	private String title ;

	public enum EpriceCode{
		REGULAR,
		NEW_RELEASE
	};
	private EpriceCode priceCode;

	// polymorphic solution
	private int videoType ;
	public static final int VHS = 1 ;
	public static final int CD = 2 ;
	public static final int DVD = 3 ;

	private Date registeredDate ;
	private boolean rented ;

	// long param - 관련없는 param
	public Video(String title, int videoType, EpriceCode priceCode, Date registeredDate) {
		this.setTitle(title) ;
		this.setVideoType(videoType) ;
		this.setPriceCode(priceCode) ;
		this.registeredDate = registeredDate ;
	}

	public int getPointPenalty() {
		int pentalty = 0 ;
		switch ( videoType ) {
			case VHS: pentalty = 1 ; break ;
			case CD: pentalty = 2 ; break ;
			case DVD: pentalty = 3 ; break ;
		}
		return pentalty ;
	}

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

	public int getVideoType() {
		return videoType;
	}

	public void setVideoType(int videoType) {
		this.videoType = videoType;
	}

	public double GetVideoCharge(int DaysRented) {
		if (priceCode == Video.EpriceCode.REGULAR){
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
		return (getPriceCode() == Video.EpriceCode.NEW_RELEASE);
	}
}
