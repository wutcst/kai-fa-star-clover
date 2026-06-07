package cn.edu.whut.sept.zuul;

public class GoCommand extends Command {
    public GoCommand(String firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Game game) {
        if (!hasSecondWord()) {
            System.out.println("Where to go? Enter a direction (e.g., go east)");
            return false;
        }

        String direction = getSecondWord();
        Room nextRoom = game.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("You can't go that way!");
        } else {
            game.setCurrentRoom(nextRoom);
            System.out.println(nextRoom.getLongDescription());
            game.triggerRandomEvent();
        }
        return false;
    }
}