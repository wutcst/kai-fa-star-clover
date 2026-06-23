package cn.edu.whut.sept.zuul;

public class GoCommand extends Command
{
    public boolean execute(Game game)
    {
        if (!hasSecondWord()) {
            System.out.println("Go where?");
            return false;
        }

        String direction = getSecondWord();

        Room currentRoom = game.getCurrentRoom();
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            game.pushHistory(currentRoom);
            game.setCurrentRoom(nextRoom);
            game.getVisitedRooms().add(nextRoom);

            System.out.println(nextRoom.getLongDescription());

            game.getTime().tick();
            System.out.println(game.getTime().getTime());

            // ⭐ 随机事件
            Event.trigger(game);
        }

        return false;
    }
}