package cn.edu.whut.sept.zuul;

import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;

public class Room
{
    private String description;
    private HashMap<String, Room> exits;
    private ArrayList<Item> items;

    public Room(String description)
    {
        this.description = description;
        exits = new HashMap<>();
        items = new ArrayList<>();
    }

    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    public Room getExit(String direction)
    {
        return exits.get(direction);
    }

    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString() + "\n" + getItemString();
    }

    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    // ⭐ 新增物品系统
    public void addItem(Item item)
    {
        items.add(item);
    }

    public Item removeItem(String name)
    {
        for (Item i : items) {
            if (i.getName().equals(name)) {
                items.remove(i);
                return i;
            }
        }
        return null;
    }

    private String getItemString()
    {
        if (items.isEmpty()) {
            return "Items: none";
        }

        String s = "Items:";
        for (Item i : items) {
            s += " " + i.getName();
        }
        return s;
    }
}