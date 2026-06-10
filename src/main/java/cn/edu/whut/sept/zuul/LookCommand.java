package cn.edu.whut.sept.zuul;

/**
 * look 查看命令：
 * 1. look 无参数：重新打印房间全貌+地面物品
 * 2. look inventory / look bag：查看玩家背包
 * 3. look [物品名]：查看房间地面/背包内物品详情
 */
public class LookCommand extends Command {
    @Override
    public boolean execute(Game game) {
        Player player = game.getPlayer();
        Room currentRoom = player.getCurrentRoom();

        // 分支1：无第二个单词，look 查看当前房间
        if (!hasSecondWord()) {
            System.out.println(currentRoom.getLongDescription());
            return false;
        }

        String subCmd = getSecondWord().toLowerCase();
        // 分支2：look inventory / look bag 查看背包
        if ("inventory".equals(subCmd) || "bag".equals(subCmd)) {
            player.showBackpack();
            return false;
        }

        // 分支3：look xxx 查看指定物品详情（先查地面，再查背包）
        String targetItemName = subCmd;
        Item roomItem = currentRoom.findRoomItemByName(targetItemName);
        if (roomItem != null) {
            System.out.println("地面上的【" + roomItem.getName() + "】：" + roomItem.getDescription());
            return false;
        }

        Item bagItem = player.getItemFromBag(targetItemName);
        if (bagItem != null) {
            System.out.println("你背包里的【" + bagItem.getName() + "】：" + bagItem.getDescription());
            return false;
        }

        // 无匹配物品
        System.out.println("找不到名为 " + targetItemName + " 的物品！");
        return false;
    }
}