import java.util.Date;

public class VideoFactory {
    public Video create(String title, VideoType videoType, EpriceCode priceCode, Date registeredDate) {
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
