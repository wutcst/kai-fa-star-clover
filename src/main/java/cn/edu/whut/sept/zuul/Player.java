package cn.edu.whut.sept.zuul;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.HashMap;

public class Player
{
    private Room currentRoom;
    private int hp;
    private int level;

    private ArrayList<Item> inventory;
    private HashMap<String, Quest> activeQuests;
    private HashMap<String, Achievement> achievements;

    public Player(Room startRoom)
    {
        this.currentRoom = startRoom;
        this.hp = 100;
        this.level = 1;

        this.inventory = new ArrayList<>();
        this.activeQuests = new HashMap<>();

        achievements = new HashMap<>();
        achievements.put("first_blood", new Achievement("First Blood"));
        achievements.put("explorer", new Achievement("Explorer"));
        achievements.put("collector", new Achievement("Collector"));
    }

    // ========================
    // 基础状态
    // ========================

    public Room getCurrentRoom()
    {
        return currentRoom;
    }

    public void setCurrentRoom(Room room)
    {
        this.currentRoom = room;
    }

    public int getHp()
    {
        return hp;
    }

    public void setHp(int hp)
    {
        this.hp = hp;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    // ========================
    // 背包系统
    // ========================

    public void addItem(Item item)
    {
        inventory.add(item);
    }

    public Item removeItem(String name)
    {
        Iterator<Item> it = inventory.iterator();

        while (it.hasNext()) {
            Item i = it.next();
            if (i.getName().equals(name)) {
                it.remove();
                return i;
            }
        }
        return null;
    }

    public void showInventory()
    {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        System.out.println("Inventory:");
        for (Item i : inventory) {
            System.out.println("- " + i.getName());
        }
    }

    // ========================
    // 任务系统
    // ========================

    public void addQuest(Quest quest)
    {
        activeQuests.put(quest.getName(), quest);
    }

    public Quest getQuest(String name)
    {
        return activeQuests.get(name);
    }

    public void completeQuest(String name)
    {
        Quest q = activeQuests.get(name);

        if (q != null) {
            q.complete();
            System.out.println("Quest completed: " + name);
        }
        else {
            System.out.println("No such quest.");
        }
    }

    public void showQuests()
    {
        if (activeQuests.isEmpty()) {
            System.out.println("No active quests.");
            return;
        }

        System.out.println("Active Quests:");
        for (String key : activeQuests.keySet()) {
            System.out.println("- " + key);
        }
    }

    public void unlockAchievement(String key)
    {
        Achievement a = achievements.get(key);
        if (a != null) {
            a.unlock();
        }
    }

    public HashMap<String, Achievement> getAchievements()
    {
        return achievements;
    }
}
