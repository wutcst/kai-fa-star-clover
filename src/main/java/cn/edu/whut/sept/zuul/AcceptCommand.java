package cn.edu.whut.sept.zuul;

public class AcceptCommand extends Command
{
    public boolean execute(Game game)
    {
        if (!hasSecondWord()) {
            System.out.println("Accept what?");
            return false;
        }

        String questName = getSecondWord();

        Room room = game.getCurrentRoom();
        NPC npc = game.getNPC(room);

        if (npc == null) {
            System.out.println("No NPC here.");
            return false;
        }

        Quest q = npc.getQuest(questName);

        if (q == null) {
            System.out.println("No such quest.");
        } else {
            game.getPlayer().addQuest(q);
            System.out.println("Quest accepted: " + questName);
        }

        return false;
    }
}