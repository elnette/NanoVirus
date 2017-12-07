/**
 * Created by Elnette on 2017/12/04.
 */
public class Checks
{
    public boolean isRed(int noRed, int x, int y, int z, int[][] arr)
    {
        boolean red = false;

       for(int i=0; i<noRed; i++)
            if(arr[0][i] == x)
                if(arr[1][i] == y)
                    if(arr[2][i] == z)
                        red = true;

        return red;
    }

    public boolean isWhite(int noWhite, int x, int y, int z, int[][] arr)
    {
        boolean white = false;

        for(int i=0; i<noWhite; i++)
            if(arr[0][i] == x)
                if(arr[1][i] == y)
                    if(arr[2][i] == z)
                        white = true;

        return white;
    }

    public boolean isTumor(int noTumors, int x, int y, int z, int[][] arr)
    {
        boolean tumor = false;

        for(int i=0; i<noTumors; i++)
            if(arr[0][i] == x)
                if(arr[1][i] == y)
                    if(arr[2][i] == z)
                        tumor = true;

        return tumor;
    }

    public double findDistance(int currX, int currY, int currZ, int newX, int newY, int newZ)
    {
        int diffX = currX - newX;
        int diffY = currY - newY;
        int diffZ = currZ - newZ;
        double d = Math.pow((Math.pow(diffX, 2) + Math.pow(diffY, 2) + Math.pow(diffZ, 2)), 0.5);

        return d;
    }

    public int CellNumber(int[][] arr, int x, int y, int z)
    {
        int i = -1;
        int cellNo = -1;

        while(cellNo == -1)
        {
            i++;

            if(arr[0][i] == x)
                if(arr[1][i] == y)
                    if(arr[2][i] == z)
                        cellNo = i;
        }

        return cellNo;
    }
}
