package sockmatching;

public class Sock {

    private Integer sockIndex;
    private Integer sockColor;
    private boolean isPicked;

    public Sock(Integer c, Integer i)
    {
        sockColor = c;
        sockIndex = i;
        isPicked = false;
    }

    public synchronized boolean pickUpSock()
    {
        boolean curStatus = isPicked;
        isPicked = true;
        return !curStatus;
    }

    public Integer getColor() 
    {
        return sockColor;
    }

    public Integer getIndex()
    {
        return sockIndex;
    }
}