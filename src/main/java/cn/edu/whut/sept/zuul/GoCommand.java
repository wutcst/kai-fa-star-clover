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
            // ⭐ 关键：记录当前房间，用于 back
            game.pushHistory(currentRoom);

            // 移动到新房间
            game.setCurrentRoom(nextRoom);

            System.out.println(nextRoom.getLongDescription());
        }

        return false;
    }
}