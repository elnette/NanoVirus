import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.FormatterClosedException;

/**
 * Created by Elnette on 2017/12/04.
 */
public class TextFile
{
    private Formatter log;

    public void OpenFile()
    {
        try
        {
            log = new Formatter("NanoVirusLog.txt");
        }
        catch(FileNotFoundException fileNotFoundException)
        {
            System.err.println("Error opening or creating file");
            System.exit(1);
        }
    }

    public void AddCycle(int cycle, String action, int red, int white, int tumor, int[] nanoVirus)
    {
        try
        {
            log.format("Cycle: %d%nNano Virus coordinates: %d;%d;%d%nAction taken: %s%nRed cells remaining: %d%nWhite cells remaining: %d%nTumor cells remaining: %d%n%n", cycle, nanoVirus[0], nanoVirus[1], nanoVirus[2], action, red, white, tumor);
        }
        catch(FormatterClosedException formatterClosedException)
        {
            System.err.println("Error writing to file");
        }
    }

    public void CloseFile()
    {
        log.close();
    }
}
