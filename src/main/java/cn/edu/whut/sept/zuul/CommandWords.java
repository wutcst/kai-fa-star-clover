package cn.edu.whut.sept.zuul;

import java.util.HashMap;

public class CommandWords
{
    private HashMap<String, Command> commands;

    public CommandWords()
    {
        commands = new HashMap<>();

        commands.put("go", new GoCommand());
        commands.put("help", new HelpCommand(this));
        commands.put("quit", new QuitCommand());
        commands.put("back", new BackCommand());
        commands.put("status", new StatusCommand());
        commands.put("take", new TakeCommand());
        commands.put("drop", new DropCommand());
        commands.put("items", new ItemsCommand());
        commands.put("talk", new TalkCommand());
        commands.put("quests", new QuestCommand());
        commands.put("accept", new AcceptCommand());
        commands.put("submit", new SubmitCommand());
        commands.put("map", new MapCommand());
        commands.put("search", new SearchCommand());
        commands.put("fight", new FightCommand());
        commands.put("save", new SaveCommand());
        commands.put("load", new LoadCommand());
        commands.put("explore", new ExploreCommand());
        commands.put("achievements", new AchievementCommand());
    }

    public Command get(String word)
    {
        return commands.get(word);
    }

    public void showAll()
    {
        for (String key : commands.keySet()) {
            System.out.print(key + "  ");
        }
        System.out.println();
    }
}
