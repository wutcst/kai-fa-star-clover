package cn.edu.whut.sept.zuul;

public class QuitCommand extends Command
{
    public boolean execute(Game game)
    {
        if (hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            game.checkEnding();  // ⭐ 加这个
            return true;
        }
    }
}
