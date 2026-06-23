package cn.edu.whut.sept.zuul;

public class QuestCommand extends Command
{
    public boolean execute(Game game)
    {
        Player p = game.getPlayer();

        System.out.println("=== ACTIVE QUESTS ===");

        // 简化输出（当前版本）
        System.out.println("Check NPC for quests.");

        return false;
    }
}
