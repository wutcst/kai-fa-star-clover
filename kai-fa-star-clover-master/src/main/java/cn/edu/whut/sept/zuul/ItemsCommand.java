package cn.edu.whut.sept.zuul;

public class ItemsCommand extends Command {
    public ItemsCommand(String firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Game game) {
        Room currentRoom = game.getCurrentRoom();
        currentRoom.showItems();
        game.getPlayer().showBackpack();
        return false;
    }
}