package cn.edu.whut.sept.zuul;

/**
 * 玩家类：持有背包、当前所在房间，提供拾取/丢弃物品接口
 */
public class Player {
    private Backpack backpack;
    private Room currentRoom;

    public Player(Room startRoom) {
        this.backpack = new Backpack();
        this.currentRoom = startRoom;
    }

    // 拾取房间内物品
    public boolean pickupItem(Item item) {
        boolean addSuccess = backpack.addItem(item);
        if (addSuccess) {
            currentRoom.removeRoomItem(item);
        }
        return addSuccess;
    }

    // 丢弃物品到当前房间
    public boolean dropItem(String itemName) {
        Item target = backpack.findItemByName(itemName);
        if (target == null) {
            return false;
        }
        backpack.removeItem(itemName);
        currentRoom.addRoomItem(target);
        return true;
    }

    // 查看背包
    public void showBackpack() {
        backpack.showInventory();
    }

    // 根据名称查找背包内物品
    public Item getItemFromBag(String itemName) {
        return backpack.findItemByName(itemName);
    }

    // 切换玩家所在房间
    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
}