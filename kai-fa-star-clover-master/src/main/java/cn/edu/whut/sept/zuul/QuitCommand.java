package cn.edu.whut.sept.zuul;

public class QuitCommand extends Command {
    public QuitCommand(String firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Game game) {
        System.out.println("Exiting game. Goodbye!");
        return true;
    }
}