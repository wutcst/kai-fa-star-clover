package cn.edu.whut.sept.zuul;

public class DropCommand extends Command {
    public DropCommand(String firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Game game) {
        if (!hasSecondWord()) {
            System.out.println("Enter the name of the item to drop (e.g., drop stone)");
            return false;
        }

        String itemName = getSecondWord();
        Item item = game.getPlayer().dropItem(itemName);
        if (item != null) {
            game.getCurrentRoom().addItem(item);
            System.out.println("Successfully dropped: " + item);
        }
        return false;
    }
}