package cn.edu.whut.sept.zuul;

import java.util.Random;

public class FightCommand extends Command
{
    public boolean execute(Game game)
    {
        Enemy enemy = new Enemy("Wolf", 50, 10);
        Player player = game.getPlayer();

        System.out.println("⚔️ A wild " + enemy.getName() + " appears!");

        Random r = new Random();

        while (!enemy.isDead()) {

            int playerDamage = r.nextInt(20) + 5;
            enemy.damage(playerDamage);

            System.out.println("You hit the enemy for " + playerDamage);

            if (enemy.isDead()) {
                break;
            }

            System.out.println("Enemy HP: " + enemy.getHp());

            System.out.println("Enemy attacks you!");
            System.out.println("You lose " + enemy.getAttack() + " HP (not tracked yet)");
        }

        System.out.println("🎉 Enemy defeated!");
        player.addItem(new Item("WolfClaw"));
        game.getPlayer().unlockAchievement("first_blood");

        return false;
    }
}
