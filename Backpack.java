package cn.edu.whut.sept.zuul;

import java.util.ArrayList;
import java.util.List;

/**
 * 玩家背包系统，存储携带物品，限制容量
 */
public class Backpack {
    private static final int MAX_CAPACITY = 10; // 背包最大容量
    private List<Item> itemList;

    public Backpack() {
        itemList = new ArrayList<>();
    }

    // 添加物品，返回true代表添加成功，false背包已满
    public boolean addItem(Item item) {
        if (itemList.size() >= MAX_CAPACITY) {
            return false;
        }
        itemList.add(item);
        return true;
    }

    // 根据物品名称移除物品
    public boolean removeItem(String itemName) {
        Item target = findItemByName(itemName);
        if (target != null) {
            itemList.remove(target);
            return true;
        }
        return false;
    }

    // 根据名称查找物品
    public Item findItemByName(String itemName) {
        for (Item item : itemList) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    // 获取全部物品
    public List<Item> getAllItems() {
        return new ArrayList<>(itemList);
    }

    // 判断背包是否已满
    public boolean isFull() {
        return itemList.size() >= MAX_CAPACITY;
    }

    // 判断背包是否为空
    public boolean isEmpty() {
        return itemList.isEmpty();
    }

    // 打印背包所有物品信息
    public void showInventory() {
        if (isEmpty()) {
            System.out.println("你的背包空空如也！");
            return;
        }
        System.out.println("===== 你的背包 =====");
        for (Item item : itemList) {
            System.out.println("- " + item.getName() + "：" + item.getDescription());
        }
        System.out.println("===================");
    }
}