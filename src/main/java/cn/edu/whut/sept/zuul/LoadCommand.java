package cn.edu.whut.sept.zuul;

import java.io.BufferedReader;
import java.io.FileReader;

public class LoadCommand extends Command
{
    public boolean execute(Game game)
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader("save.txt"));

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Loaded: " + line);
            }

            br.close();

            System.out.println("📂 Game loaded (demo version)");
        }
        catch (Exception e) {
            System.out.println("Load failed.");
        }

        return false;
    }
}
