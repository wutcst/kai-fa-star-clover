package cn.edu.whut.sept.zuul;

import java.util.HashMap;

public class AchievementCommand extends Command
{
    public boolean execute(Game game)
    {
        Player p = game.getPlayer();

        HashMap<String, Achievement> map = p.getAchievements();

        System.out.println();
        System.out.println("===== ACHIEVEMENTS =====");

        for (String key : map.keySet()) {
            Achievement a = map.get(key);

            if (a.isUnlocked()) {
                System.out.println("✔ " + key);
            } else {
                System.out.println("❌ " + key);
            }
        }

        System.out.println("========================");

        return false;
    }
}