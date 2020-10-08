package sockmatching;
import java.util.*;

class SockMatchingRoom {
    
    public static int whitePairs;
    public static int blackPairs;
    public static int bluePairs;
    public static int greyPairs;
    public static int numRobots;
    public static int totalPairs;

    private static List<Sock> getList()
    {
        List<Sock> allSocks = new ArrayList<>();
        int curIdx = 0;

        //Insert all the whiteSocks in the list of allSocks
        for(int i=0;i<whitePairs;i++) {
            Integer color = Constants.WHITE_SOCK;
            allSocks.add(new Sock(color, curIdx));
            allSocks.add(new Sock(color, curIdx+1));
            curIdx += 2;
        }

        //Insert all the blackSocks in the list of allSocks
        for(int i=0;i<blackPairs;i++) {
            Integer color = Constants.BLACK_SOCK;
            allSocks.add(new Sock(color, curIdx));
            allSocks.add(new Sock(color, curIdx+1));
            curIdx += 2;
        }
        
        //Insert all the blueSocks in the list of allSocks
        for(int i=0;i<bluePairs;i++) {
            Integer color = Constants.BLUE_SOCK;
            allSocks.add(new Sock(color, curIdx));
            allSocks.add(new Sock(color, curIdx+1));
            curIdx += 2;
        }

        //Insert all the greySocks in the list of allSocks
        for(int i=0;i<greyPairs;i++) {
            Integer color = Constants.GREY_SOCK;
            allSocks.add(new Sock(color, curIdx));
            allSocks.add(new Sock(color, curIdx+1));
            curIdx += 2;
        }

        return allSocks;
    }

    public static void main(String[] args)
    {
        if(args.length < 5)
        {
            System.out.println("Please input in the form: {Number of Robot Arms} {White Sock Pairs} {Black Sock Pairs} {Blue Sock Pairs} {Grey Sock Pairs}");
            return;
        }   

        numRobots = Integer.parseInt(args[0]);
        whitePairs = Integer.parseInt(args[1]);
        blackPairs = Integer.parseInt(args[2]);
        bluePairs = Integer.parseInt(args[3]);
        greyPairs = Integer.parseInt(args[4]);
        totalPairs = whitePairs + blackPairs + bluePairs + greyPairs;

        List<Sock> allSocks = getList();
        java.util.Collections.shuffle(allSocks); //Shuffle all the socks in allSocks

        HeapOfSocks h = new HeapOfSocks(allSocks);
        MatchMakingMachine machine = new MatchMakingMachine();

        for(int i=0;i<numRobots;i++)
        {
            RoboticArm rArm = new RoboticArm(i+1, h, machine);
            rArm.start();
        }

        machine.start();
    }
}







