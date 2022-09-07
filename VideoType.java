import javax.lang.model.type.NullType;

public enum VideoType {
    VHS, CD, DVD;

    public static VideoType trans(int input)
    {
        switch(input){
            case 1: return VHS;
            case 2: return CD;
            case 3: return DVD;
        }
        return null;
    }
}

//public static enum VideoType {VHS, CD, DVD}
//	public static final int VHS = 1;
//	public static final int CD = 2;
//	public static final int DVD = 3;

