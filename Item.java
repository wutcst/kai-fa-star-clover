package cn.edu.whut.sept.zuul;

/**
 * 物品基类，存储名称、描述，预留使用扩展接口
 */
public class Item {
    private String name;
    private String description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // 预留：物品使用逻辑，后续可扩展消耗品/装备重写
    public void use(Player player) {
        System.out.println("这个物品暂时无法使用：" + name);
    }
}