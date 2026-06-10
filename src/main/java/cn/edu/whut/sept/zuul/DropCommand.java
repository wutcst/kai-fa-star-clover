package cn.edu.whut.sept.zuul;

public class DropCommand extends Command {
    @Override
    public boolean execute(Game game) {
        if (!hasSecondWord()) {
            System.out.println("Drop what? 请输入要丢弃的物品名称");
            return false;
        }
        String itemName = getSecondWord();
        Player player = game.getPlayer();
        boolean dropSuccess = player.dropItem(itemName);

        if (dropSuccess) {
            System.out.println("你把 " + itemName + " 丢在了地上");
        } else {
            System.out.println("你的背包里没有这个物品！");
        }
        return false;
    }
}