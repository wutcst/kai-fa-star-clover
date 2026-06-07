package cn.edu.whut.sept.zuul;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private Room currentRoom;
    private List<Item> backpack;
    private int maxWeight;
    private int currentWeight;

    public Player(String name, Room startRoom) {
        this.name = name;
        this.currentRoom = startRoom;
        this.backpack = new ArrayList<>();
        this.maxWeight = 10; // Max carry weight
        this.currentWeight = 0;
    }

    public boolean takeItem(Item item) {
        if (currentWeight + item.getWeight() > maxWeight) {
            System.out.println("Weight limit exceeded! Cannot pick up this item.");
            return false;
        }
        backpack.add(item);
        currentWeight += item.getWeight();
        return true;
    }

    public Item dropItem(String itemName) {
        for (Item item : backpack) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                backpack.remove(item);
                currentWeight -= item.getWeight();
                return item;
            }
        }
        System.out.println("You don't have that item in your inventory!");
        return null;
    }

    public void showBackpack() {
        System.out.println("\nYour inventory:");
        if (backpack.isEmpty()) {
            System.out.println("  (Your inventory is empty)");
            return;
        }
        for (Item item : backpack) {
            System.out.println("  - " + item);
        }
        System.out.println("Current weight: " + currentWeight + " / " + maxWeight);
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    public List<Item> getBackpack() {
        return backpack;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }
}