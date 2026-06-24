package cn.edu.whut.sept.zuul;

import java.util.List;
import java.util.Map;

public class GameResponse {

    // ===== 基础反馈 =====
    private String message;

    // ===== 核心游戏状态 =====
    private String room;
    private int hp;
    private int level;

    // ===== 资源 =====
    private List<String> inventory;

    // ===== 扩展信息 =====
    private String mapInfo;
    private String questInfo;
    private String npcInfo;

    // ⭐ 统一类型：Achievement
    private Map<String, Achievement> achievements;

    // ===== 构造方法（统一修复）=====
    public GameResponse(String message,
                        String room,
                        int hp,
                        int level,
                        List<String> inventory,
                        String mapInfo,
                        String questInfo,
                        String npcInfo,
                        Map<String, Achievement> achievements) {

        this.message = message;
        this.room = room;
        this.hp = hp;
        this.level = level;
        this.inventory = inventory;
        this.mapInfo = mapInfo;
        this.questInfo = questInfo;
        this.npcInfo = npcInfo;
        this.achievements = achievements;
    }

    // ===== Getter =====

    public String getMessage() {
        return message;
    }

    public String getRoom() {
        return room;
    }

    public int getHp() {
        return hp;
    }

    public int getLevel() {
        return level;
    }

    public List<String> getInventory() {
        return inventory;
    }

    public String getMapInfo() {
        return mapInfo;
    }

    public String getQuestInfo() {
        return questInfo;
    }

    public String getNpcInfo() {
        return npcInfo;
    }

    public Map<String, Achievement> getAchievements() {
        return achievements;
    }
}