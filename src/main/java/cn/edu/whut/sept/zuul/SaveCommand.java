package cn.edu.whut.sept.zuul;

import java.io.FileWriter;

public class SaveCommand extends Command
{
    public boolean execute(Game game)
    {
        try {
            FileWriter writer = new FileWriter("save.txt");

            Player p = game.getPlayer();

            writer.write("HP:" + p.getHp() + "\n");
            writer.write("Level:" + p.getLevel() + "\n");
            writer.write("Room:" + game.getCurrentRoom().getShortDescription() + "\n");

            writer.close();

            System.out.println("💾 Game saved!");
        }
        catch (Exception e) {
            System.out.println("Save failed.");
        }

        return false;
    }
}
