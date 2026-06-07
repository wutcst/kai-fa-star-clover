package cn.edu.whut.sept.zuul;

public class HelpCommand extends Command {
    public HelpCommand(String firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Game game) {
        System.out.println("\nAvailable commands:");
        new CommandWords().showAllCommands();
        System.out.println("Examples: go east, take cookie, drop stone, items, help, quit");
        return false;
    }
}