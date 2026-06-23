package cn.edu.whut.sept.zuul;

public class DropCommand extends Command
{
    public boolean execute(Game game)
    {
        if (!hasSecondWord()) {
            System.out.println("Drop what?");
            return false;
        }

        String itemName = getSecondWord();

        Player player = game.getPlayer();
        Item item = player.removeItem(itemName);

        if (item == null) {
            System.out.println("You don't have this item.");
        }
        else {
            game.getCurrentRoom().addItem(item);
            System.out.println("Dropped: " + item.getName());
        }

        return false;
    }
}
