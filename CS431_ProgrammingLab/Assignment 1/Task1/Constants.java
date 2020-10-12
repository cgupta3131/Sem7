public class Constants {
    static final int WHITE_SOCK = 1;
    static final int BLACK_SOCK = 2;
    static final int BLUE_SOCK = 3;
    static final int GREY_SOCK = 4;

    public static String getColor(Integer i)
    {
        if(i == 1)
            return "WHITE";
        if(i == 2)
            return "BLACK";
        if(i == 3)
            return "BLUE";
        if(i == 4)
            return "GREY";
        
        return null;
    }
}
