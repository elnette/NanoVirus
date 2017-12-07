/**
 * Created by Elnette on 2017/12/04.
 */
import java.util.Random;

public class Actions
{
    Random ranNum = new Random();
    Checks test = new Checks();

    public int Kill(int arr[][], int cell, int tumors)
    {
        arr[0][cell] = 6000;
        arr[1][cell] = 6000;
        arr[2][cell] = 6000;

        tumors--;
        return tumors;
    }

    public int[] Move(int[][] arr, int x, int y, int z)
    {
        int newCell;
        int[] nextCell = new int[3];

        double d;

        newCell = ranNum.nextInt(100);
        nextCell[0] = arr[0][newCell];
        nextCell[1] = arr[1][newCell];
        nextCell[2] = arr[2][newCell];

        d = test.findDistance(x, y, z, nextCell[0], nextCell[1], nextCell[2]);

        if(nextCell[0] == 6000)
            d = 6000;


        while(d == 0 || d > 5000)
        {
            newCell = ranNum.nextInt(100);
            nextCell[0] = arr[0][newCell];
            nextCell[1] = arr[1][newCell];
            nextCell[2] = arr[2][newCell];

            d = test.findDistance(x, y, z, nextCell[0], nextCell[1], nextCell[2]);

            if(nextCell[0] == 6000)
                d = 6000;
        }

        return nextCell;
    }

    public int[] Stay(int x, int y, int z)
    {
        int[] thisCell = new int[3];

        thisCell[0] = x;
        thisCell[1] = y;
        thisCell[2] = z;

        return thisCell;
    }
}
