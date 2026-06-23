package cn.edu.whut.sept.zuul;

public class TalkCommand extends Command
{
    public boolean execute(Game game)
    {
        Player p = game.getPlayer();
        Room room = p.getCurrentRoom();

        NPC npc = game.getNPC(room);

        if (npc == null) {
            System.out.println("No one here to talk to.");
        } else {
            npc.talk();
            npc.showQuests();
        }

        return false;
    }
}
