package cn.edu.whut.sept.zuul;

public class SubmitCommand extends Command
{
    public boolean execute(Game game)
    {
        if (!hasSecondWord()) {
            System.out.println("Submit what?");
            return false;
        }

        String questName = getSecondWord();

        Player p = game.getPlayer();
        Quest q = p.getQuest(questName);

        if (q == null) {
            System.out.println("You don't have this quest.");
        }
        else {
            q.complete();
            System.out.println("Quest submitted: " + questName);

            // 奖励简单处理
            p.addItem(new Item(q.getReward()));
            System.out.println("You received: " + q.getReward());
        }

        return false;
    }
}
