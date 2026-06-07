package cn.edu.whut.sept.zuul;

public class TakeCommand extends Command {
    public TakeCommand(String firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Game game) {
        if (!hasSecondWord()) {
            System.out.println("Enter the name of the item to take (e.g., take cookie)");
            return false;
        }

        String itemName = getSecondWord();
        Room currentRoom = game.getCurrentRoom();
        Item item = currentRoom.removeItem(itemName);

        if (item == null) {
            System.out.println("That item is not in this room!");
            return false;
        }

        if (game.getPlayer().takeItem(item)) {
            System.out.println("Successfully picked up: " + item);
        } else {
            // Return item to room if weight limit exceeded
            currentRoom.addItem(item);
        }
        return false;
    }
}