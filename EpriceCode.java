public enum EpriceCode {
    REGULAR, NEW_RELEASE;
    public static EpriceCode trans(int input)
    {
        switch (input)
        {
            case 1: return REGULAR;
            case 2: return NEW_RELEASE;
        }
        return null;
    }
}
