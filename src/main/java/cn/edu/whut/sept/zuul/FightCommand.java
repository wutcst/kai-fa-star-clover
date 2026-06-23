package cn.edu.whut.sept.zuul;

import java.util.Random;

public class FightCommand extends Command
{
    public boolean execute(Game game)
    {
        Player player = game.getPlayer();
        Room room = game.getCurrentRoom();

        Enemy enemy;

        // ⭐ 根据房间判断敌人（为以后Boss做准备）
        if (room.getShortDescription().contains("Dragon")) {
            enemy = new Enemy("Dragon", 120, 25);
            System.out.println("🔥 FINAL BOSS: DRAGON APPEARS!");
        }
        else if (room.getShortDescription().contains("Forest")) {
            enemy = new Enemy("Wolf", 50, 10);
            System.out.println("🐺 A wild Wolf appears!");
        }
        else {
            enemy = new Enemy("Slime", 30, 5);
            System.out.println("🟢 A Slime appears!");
        }

        Random r = new Random();

        while (!enemy.isDead()) {

            int playerDamage = r.nextInt(20) + 5;
            enemy.damage(playerDamage);

            System.out.println("You hit the enemy for " + playerDamage);

            if (enemy.isDead()) break;

            System.out.println("Enemy HP: " + enemy.getHp());

            System.out.println("Enemy attacks you!");

            player.setHp(player.getHp() - enemy.getAttack());

            System.out.println("You lose " + enemy.getAttack() + " HP");

            if (player.getHp() <= 0) {
                System.out.println("💀 You died! Game Over.");
                game.checkEnding();   // ⭐ 加这个
                return true;
            }
        }

        System.out.println("🎉 Enemy defeated!");

        if (enemy.getName().equals("Dragon")) {
            game.checkEnding();  // ⭐ Boss结局触发
        }

        // ⭐ 掉落系统（未来可扩展）
        player.addItem(new Item(enemy.getName() + "Claw"));

        // ⭐ 成就系统
        player.unlockAchievement("first_blood");

        return false;
    }
}
