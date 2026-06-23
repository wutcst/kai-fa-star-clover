package cn.edu.whut.sept.zuul;

public class MapCommand extends Command
{
    public boolean execute(Game game)
    {
        Room r = game.getCurrentRoom();

        System.out.println();
        System.out.println("======= WORLD MAP =======");

        String desc = r.getShortDescription();

        if (desc.contains("outside")) {
            System.out.println("        Theater");
            System.out.println("           |");
            System.out.println("Pub -- Outside -- Lab -- Office");
        }
        else if (desc.contains("theater")) {
            System.out.println("      [Theater]");
            System.out.println("           |");
            System.out.println("Pub -- Outside -- Lab -- Office");
        }
        else {
            System.out.println("Map not fully explored yet...");
        }

        System.out.println("=========================");
        return false;
    }
}
