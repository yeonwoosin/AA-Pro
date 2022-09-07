import java.util.Date;

public class VideoFactory {
    public static final int VHS = 1;
    public static final int CD = 2;
    public static final int DVD = 3;

    public Video create(String title, int videoType, Video.EpriceCode priceCode, Date registeredDate) {
        switch (videoType) {
            case VHS:
                return new VideoVHS(title, priceCode, registeredDate);

            case CD:
                return new VideoCD(title, priceCode, registeredDate);

            case DVD:
                return new VideoDVD(title, priceCode, registeredDate);

        }
        return null;
    }
}
