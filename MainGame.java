/**
 * Created by Elnette on 2017/11/28.
 */

public class MainGame
{
    public static void main(String[] args)
    {
        final int CELLS = 100;
        final int MAXJUMP = 5000;
        final int MAXX = 5000;
        final int MAXY = 5000;
        final int MAXZ = 5000;

        Cycle play = new Cycle();

        play.NanoVirusMovements(CELLS, MAXX, MAXY, MAXZ, MAXJUMP);
    }
}
