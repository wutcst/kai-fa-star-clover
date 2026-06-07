package cn.edu.whut.sept.zuul;

import java.util.Scanner;

public class Parser {
    private CommandWords commands;
    private Scanner reader;

    public Parser() {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    public Command getCommand() {
        String inputLine;
        String word1 = null;
        String word2 = null;

        System.out.print("> ");
        inputLine = reader.nextLine();

        Scanner tokenizer = new Scanner(inputLine);
        if (tokenizer.hasNext()) {
            word1 = tokenizer.next();
            if (tokenizer.hasNext()) {
                word2 = tokenizer.next();
            }
        }

        if (commands.isCommand(word1)) {
            if (word1.equals("go")) {
                return new GoCommand(word1, word2);
            } else if (word1.equals("take")) {
                return new TakeCommand(word1, word2);
            } else if (word1.equals("drop")) {
                return new DropCommand(word1, word2);
            } else if (word1.equals("items")) {
                return new ItemsCommand(word1, word2);
            } else if (word1.equals("help")) {
                return new HelpCommand(word1, word2);
            } else if (word1.equals("quit")) {
                return new QuitCommand(word1, word2);
            }
        }
        return new UnknownCommand(null, word2);
    }
}

class UnknownCommand extends Command {
    public UnknownCommand(String firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Game game) {
        System.out.println("I don't understand that command.");
        return false;
    }
}