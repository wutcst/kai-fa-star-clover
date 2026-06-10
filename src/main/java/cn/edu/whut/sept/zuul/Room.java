package cn.edu.whut.sept.zuul;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Room
{
    private String description;
    private HashMap<String, Room> exits;
    // 新增：房间地面物品列表
    private List<Item> roomItems;

    public Room(String description)
    {
        this.description = description;
        exits = new HashMap<>();
        roomItems = new ArrayList<>();
    }

    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString() + "\n" + getRoomItemString();
    }

    // 拼接房间出口字符串（原有逻辑不变）
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    // 新增：拼接房间地面物品描述
    private String getRoomItemString() {
        if (roomItems.isEmpty()) {
            return "地面上没有任何物品";
        }
        StringBuilder sb = new StringBuilder("地面上能看到：");
        for (Item item : roomItems) {
            sb.append(item.getName()).append(" ");
        }
        return sb.toString();
    }

    public Room getExit(String direction)
    {
        return exits.get(direction);
    }

    // 新增：房间添加地面物品
    public void addRoomItem(Item item) {
        roomItems.add(item);
    }

    // 新增：移除房间地面物品（玩家拾取时调用）
    public void removeRoomItem(Item item) {
        roomItems.remove(item);
    }

    // 根据物品名查找房间地面物品
    public Item findRoomItemByName(String itemName) {
        for (Item item : roomItems) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    // 获取房间所有地面物品
    public List<Item> getRoomItems() {
        return new ArrayList<>(roomItems);
    }
}