package cn.edu.whut.sept.zuul;

public class TakeCommand extends Command {
    @Override
    public boolean execute(Game game) {
        if (!hasSecondWord()) {
            System.out.println("Take what? 请输入要拾取的物品名称");
            return false;
        }
        String itemName = getSecondWord();
        Player player = game.getPlayer();
        Room room = player.getCurrentRoom();
        Item target = room.findRoomItemByName(itemName);

        if (target == null) {
            System.out.println("地面上没有这个物品！");
            return false;
        }

        boolean pickSuccess = player.pickupItem(target);
        if (pickSuccess) {
            System.out.println("成功拾取：" + target.getName());
        } else {
            System.out.println("背包已满，无法拾取！");
        }
        return false;
    }
}