package cn.edu.whut.sept.zuul;

public class BackCommand extends Command
{
    public boolean execute(Game game)
    {
        Room previousRoom = game.popHistory();

        if (previousRoom == null) {
            System.out.println("No previous room!");
        }
        else {
            game.setCurrentRoom(previousRoom);
            System.out.println(previousRoom.getLongDescription());
        }

        return false;
    }
}
