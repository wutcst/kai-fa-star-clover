package cn.edu.whut.sept.zuul;

public class CommandWords {
    private static final String[] VALID_COMMANDS = {
            "go", "take", "drop", "items", "help", "quit"
    };

    public boolean isCommand(String aString) {
        for (String command : VALID_COMMANDS) {
            if (command.equals(aString)) {
                return true;
            }
        }
        return false;
    }

    public void showAllCommands() {
        System.out.print("Available commands: ");
        for (String command : VALID_COMMANDS) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}