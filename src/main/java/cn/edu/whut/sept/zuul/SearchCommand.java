package cn.edu.whut.sept.zuul;

import java.util.Random;

public class SearchCommand extends Command
{
    public boolean execute(Game game)
    {
        Random r = new Random();

        int chance = r.nextInt(100);

        if (chance < 30) {
            Room secret = new Room("in a SECRET ROOM filled with treasure!");
            game.setCurrentRoom(secret);

            System.out.println("🔓 You discovered a SECRET ROOM!");
        }
        else {
            System.out.println("Nothing found...");
        }

        return false;
    }
}