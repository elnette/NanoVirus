/**
 * Created by Elnette on 2017/11/29.
 */

import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.Random;

public class Cycle
{
    Random ranNum = new Random();
    GenerateCells newCell = new GenerateCells();
    Actions act = new Actions();
    Checks test = new Checks();
    TextFile text = new TextFile();

    public void NanoVirusMovements(int cells, int maxX, int maxY, int maxZ, int jump)
    {
        int kills = 0;
        int red;
        int white;
        int tumor;
        int cycle = 0;
        int[] nanoVirus;
        int[][] organ;

        String action = "Start";

        double e;

        e = 0.7 * cells;
        red = (int) e;

        e = 0.25 * cells;
        white = (int) e;

        e = 0.05 * cells;
        tumor = (int) e;

        organ = newCell.makeCells(cells, maxX, maxY, maxZ);
        int[][] redCells = new int[3][red];
        int[][] whiteCells = new int[3][white];
        int[][] tumorCells = new int[3][cells];

        for(int i=0; i<cells; i++)
        {
            int j;
            if(i<red)
            {
                redCells[0][i] = organ[0][i];
                redCells[1][i] = organ[1][i];
                redCells[2][i] = organ[2][i];
            }
            else if(i< red+white)
            {
                j = i - red;
                whiteCells[0][j] = organ[0][i];
                whiteCells[1][j] = organ[1][i];
                whiteCells[2][j] = organ[2][i];
            }
            else
            {
                j = i - red - white;
                tumorCells[0][j] = organ[0][i];
                tumorCells[1][j] = organ[1][i];
                tumorCells[2][j] = organ[2][i];
            }
        }

        nanoVirus = newCell.generateStartCell(redCells, red);

        text.OpenFile();
        text.AddCycle(cycle, action, red, white, tumor, nanoVirus);

        while (white > 0 && tumor < cells)
        {
            cycle++;

            if (test.isTumor(tumor, nanoVirus[0], nanoVirus[1], nanoVirus[2], tumorCells))
            {
                int cellNo = test.CellNumber(tumorCells, nanoVirus[0], nanoVirus[1], nanoVirus[2]);
                tumor = act.Kill(tumorCells, cellNo, tumor);
                action = "Kill cell";
                kills++;
            }
            else if (test.isRed(red, nanoVirus[0], nanoVirus[1], nanoVirus[2], redCells))
            {
                if (cycle % 5 == 0)
                {
                    nanoVirus = act.Stay(nanoVirus[0], nanoVirus[1], nanoVirus[2]);
                    action = "Stay at current cell";
                }
                else
                {
                    nanoVirus = act.Move(organ, nanoVirus[0], nanoVirus[1], nanoVirus[2]);
                    action = "Move to new cell";
                }
            }
            else if (test.isWhite(white, nanoVirus[0], nanoVirus[1], nanoVirus[2], whiteCells))
            {
                if (red > 0)
                {
                    nanoVirus = act.Move(organ, nanoVirus[0], nanoVirus[1], nanoVirus[2]);
                    action = "Move to new cell";
                }
                else if (cycle % 5 == 0)
                {
                    nanoVirus = act.Stay(nanoVirus[0], nanoVirus[1], nanoVirus[2]);
                    action = "Stay at current cell";
                }
                else
                {
                    nanoVirus = act.Move(organ, nanoVirus[0], nanoVirus[1], nanoVirus[2]);
                    action = "Move to new cell";
                }
            }
            else
            {
                nanoVirus = act.Move(organ, nanoVirus[0], nanoVirus[1], nanoVirus[2]);
                action = "Move to new cell";
            }

            if (cycle % 5 == 0)
            {
                int infected;

                if (red > 0)
                {
                    infected = newCell.Infect(redCells, tumorCells, tumor, red);
                    red = red - (infected - tumor);
                    tumor = infected;

                    if(red<0)
                        red = 0;
                }
                else
                {
                    infected = newCell.Infect(whiteCells, tumorCells, tumor, white);
                    white = white - (infected - tumor);
                    tumor = infected;

                    if(white<0)
                        white = 0;
                }
            }

            text.AddCycle(cycle, action, red, white, tumor, nanoVirus);
        }
        //System.out.printf("no kills:%d", kills);
        text.CloseFile();
    }
}
