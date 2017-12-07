/**
 * Created by Elnette on 2017/12/04.
 */
import java.util.Random;

public class GenerateCells
{
    Random ranNum = new Random();
    Checks test = new Checks();

    public int[][] makeCells(int noCells, int maxX, int maxY, int maxZ)
    {
        int[][] cells = new int[3][noCells];

        cells[0][0] = ranNum.nextInt(maxX);
        cells[1][0] = ranNum.nextInt(maxY);
        cells[2][0] = ranNum.nextInt(maxZ);

        for(int i=1; i<noCells; i++)
        {
            cells[0][i] = ranNum.nextInt(maxX);
            cells[1][i] = ranNum.nextInt(maxY);
            cells[2][i] = ranNum.nextInt(maxZ);

            for(int j=0; j<i; j++)
                if(cells[0][j] == cells[0][i])
                    if(cells[1][j] == cells[1][i])
                        if(cells[2][j] == cells[2][i])
                        {
                            j = 0;

                            cells[0][i] = ranNum.nextInt(maxX);
                            cells[1][i] = ranNum.nextInt(maxY);
                            cells[2][i] = ranNum.nextInt(maxZ);
                        }
        }

        return cells;
    }

    public int[] generateStartCell(int[][] arr, int red)
    {
        int[] startCell = new int[3];

        int s = ranNum.nextInt(red);
        startCell[0] = arr[0][s];
        startCell[1] = arr[1][s];
        startCell[2] = arr[2][s];

        return startCell;
    }

    public int Infect(int[][] targetCells, int[][] tumorCells, int tumors, int targets)
    {
        System.out.println(tumors);
        int newTumors = tumors;

        for(int i = 0; i< targetCells.length; i++)
        {
            int targetX = -1;
            int targetY = -1;
            int targetZ = -1;
            int chosenCell = 0;

            double d;
            double small = 0;

            if(targetCells[0][i]>0)
            {
                for(int j=0; j<tumors; j++)
                {
                    d = test.findDistance(tumorCells[0][j], tumorCells[1][j], tumorCells[2][j], targetCells[0][i], targetCells[1][i], targetCells[2][i]);

                    if(d<0)
                        d = -d;

                    if(d<small)
                    {
                        small = d;
                        targetX = targetCells[0][j];
                        targetY = targetCells[1][j];
                        targetZ = targetCells[2][j];
                        chosenCell = j;
                    }
                }
            }

            tumorCells[0][newTumors] = targetCells[0][chosenCell];
            targetCells[0][chosenCell] = -targetX;

            tumorCells[1][newTumors] = targetCells[1][chosenCell];
            targetCells[1][chosenCell] = -targetY;

            tumorCells[2][newTumors] = targetCells[2][chosenCell];
            targetCells[2][chosenCell] = -targetZ;

            newTumors++;
        }

        System.out.println(newTumors);
        return newTumors;
    }
}
