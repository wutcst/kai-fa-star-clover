package cn.edu.whut.sept.zuul;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {
    private String description;
    private Map<String, Room> exits;
    private List<Item> items;

    public Room(String description) {
        this.description = description;
        exits = new HashMap<>();
        items = new ArrayList<>();
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Item removeItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                items.remove(item);
                return item;
            }
        }
        return null;
    }

    public void showItems() {
        System.out.println("\nItems in this room:");
        if (items.isEmpty()) {
            System.out.println("  (This room is empty)");
            return;
        }
        for (Item item : items) {
            System.out.println("  - " + item);
        }
        System.out.println("Total weight of items in room: " + getItemsTotalWeight());
    }

    public int getItemsTotalWeight() {
        int total = 0;
        for (Item item : items) {
            total += item.getWeight();
        }
        return total;
    }

    public String getLongDescription() {
        return "You are in: " + description + "\n" + getExitString();
    }

    private String getExitString() {
        StringBuilder sb = new StringBuilder("Exits: ");
        for (String key : exits.keySet()) {
            sb.append(key).append(" ");
        }
        return sb.toString();
    }

    public List<Item> getItems() {
        return items;
    }
}