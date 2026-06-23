package cn.edu.whut.sept.zuul;

import java.util.Random;

public class SearchCommand extends Command
{
    public boolean execute(Game game)
    {
        Random r = new Random();
        int chance = r.nextInt(100);

        System.out.println("🔍 You search the surroundings...");

        if (chance < 30) {
            System.out.println("🎁 You found a hidden item: GoldKey!");

            game.getPlayer().addItem(new Item("GoldKey"));

            // ⭐ 成就（可选）
            game.getPlayer().unlockAchievement("collector");
        }
        else if (chance < 60) {
            System.out.println("💰 You found nothing but footprints...");
        }
        else {
            System.out.println("😶 Nothing unusual here.");
        }

        return false;
    }
}