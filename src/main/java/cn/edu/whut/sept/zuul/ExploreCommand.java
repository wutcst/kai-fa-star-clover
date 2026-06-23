package cn.edu.whut.sept.zuul;

public class ExploreCommand extends Command
{
    public boolean execute(Game game)
    {
        int total = 5; // 你当前房间数（可以后面改动态）
        int visited = game.getVisitedRooms().size();

        System.out.println("Exploration Progress: " + visited + "/" + total);
        System.out.println("Progress: " + (visited * 100 / total) + "%");

        return false;
    }
}