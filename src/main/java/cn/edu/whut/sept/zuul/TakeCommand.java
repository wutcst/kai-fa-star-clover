package cn.edu.whut.sept.zuul;

public class TakeCommand extends Command
{
    public boolean execute(Game game)
    {
        if (!hasSecondWord()) {
            System.out.println("Take what?");
            return false;
        }

        String itemName = getSecondWord();

        Room room = game.getCurrentRoom();
        Item item = room.removeItem(itemName);

        if (item == null) {
            System.out.println("No such item in this room.");
        }
        else {
            game.getPlayer().addItem(item);
            System.out.println("You picked up: " + item.getName());
            //成就触发
            game.getPlayer().unlockAchievement("collector");
        }

        return false;
    }
}