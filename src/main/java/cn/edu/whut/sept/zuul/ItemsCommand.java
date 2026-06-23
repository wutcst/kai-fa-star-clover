package cn.edu.whut.sept.zuul;

public class ItemsCommand extends Command
{
    public boolean execute(Game game)
    {
        game.getPlayer().showInventory();
        return false;
    }
}