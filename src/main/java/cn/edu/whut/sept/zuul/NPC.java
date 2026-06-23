package cn.edu.whut.sept.zuul;

import java.util.HashMap;

public class NPC
{
    private String name;
    private String dialogue;

    private HashMap<String, Quest> quests;

    public NPC(String name, String dialogue)
    {
        this.name = name;
        this.dialogue = dialogue;
        this.quests = new HashMap<>();
    }

    public String getName()
    {
        return name;
    }

    public void talk()
    {
        System.out.println(name + ": " + dialogue);
    }

    public void addQuest(Quest quest)
    {
        quests.put(quest.getName(), quest);
    }

    public Quest getQuest(String name)
    {
        return quests.get(name);
    }

    public void showQuests()
    {
        if (quests.isEmpty()) {
            System.out.println("No quests available.");
            return;
        }

        System.out.println("Available quests:");
        for (String q : quests.keySet()) {
            System.out.println("- " + q);
        }
    }
}
