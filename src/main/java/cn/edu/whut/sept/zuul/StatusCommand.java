package cn.edu.whut.sept.zuul;

public class StatusCommand extends Command
{
    public boolean execute(Game game)
    {
        Player p = game.getPlayer();

        System.out.println("===== PLAYER STATUS =====");
        System.out.println("HP: " + p.getHp());
        System.out.println("Level: " + p.getLevel());
        System.out.println("=========================");

        return false;
    }
}
