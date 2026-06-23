package cn.edu.whut.sept.zuul;

import java.util.Random;

public class Event
{
    private static Random random = new Random();

    public static void trigger(Game game)
    {
        int r = random.nextInt(100);

        if (r < 20) {
            System.out.println("💥 Trap triggered! You lost 10 HP.");
        }
        else if (r < 40) {
            System.out.println("🎁 You found a treasure! +GoldKey");
            game.getPlayer().addItem(new Item("GoldKey"));
        }
        else if (r < 50) {
            System.out.println("✨ Teleport activated!");
            game.setCurrentRoom(game.getPlayer().getCurrentRoom());
        }
    }
}