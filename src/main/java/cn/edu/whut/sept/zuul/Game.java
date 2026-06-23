/**
 * 该类是“World-of-Zuul”应用程序的主类。
 * 《World of Zuul》是一款简单的文本冒险游戏。用户可以在一些房间组成的迷宫中探险。
 * 你们可以通过扩展该游戏的功能使它更有趣!.
 *
 * 如果想开始执行这个游戏，用户需要创建Game类的一个实例并调用“play”方法。
 *
 * Game类的实例将创建并初始化所有其他类:它创建所有房间，并将它们连接成迷宫；它创建解析器
 * 接收用户输入，并将用户输入转换成命令后开始运行游戏。
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 1.0
 */
package cn.edu.whut.sept.zuul;

import java.util.Stack;
import java.util.HashMap;
import java.util.HashSet;

public class Game
{
    private Parser parser;
    private Player player;

    private Stack<Room> roomHistory;
    private HashMap<Room, NPC> npcMap;

    private GameTime time;
    private HashSet<Room> visitedRooms;

    // ===== 房间成员变量（关键修复）=====
    private Room outside;

    private Room library;
    private Room reading;
    private Room archive;

    private Room theater;
    private Room classA;
    private Room classB;
    private Room professorOffice;

    private Room lab;
    private Room computer;
    private Room server;
    private Room data;
    private Room ai;

    private Room pub;
    private Room lake;
    private Room forest;
    private Room garden;

    private Room dorm;
    private Room cafe;
    private Room gym;
    private Room laundry;

    private Room tunnel;
    private Room cave;
    private Room dragon;

    private boolean gameEnded = false;

    public Game()
    {
        parser = new Parser();
        roomHistory = new Stack<>();
        npcMap = new HashMap<>();
        visitedRooms = new HashSet<>();

        createRooms();        // ✔ 先创建地图
        setupNPCs();          // ✔ 再绑定NPC

        player = new Player(outside); // ✔ 玩家出生点
        time = new GameTime();
    }

    // ===================== 地图 =====================
    private void createRooms()
    {
        // ===== 中心 =====
        outside = new Room("Campus Square");

        // ===== 图书馆 =====
        library = new Room("Library");
        reading = new Room("Reading Room");
        archive = new Room("Archive");

        // ===== 教学区 =====
        theater = new Room("Theater");
        classA = new Room("Classroom A");
        classB = new Room("Classroom B");
        professorOffice = new Room("Professor Office");

        // ===== 实验区 =====
        lab = new Room("Lab");
        computer = new Room("Computer Room");
        server = new Room("Server Room");
        data = new Room("Data Center");
        ai = new Room("AI Lab");

        // ===== 娱乐区 =====
        pub = new Room("Pub");
        lake = new Room("Lake");
        forest = new Room("Forest");
        garden = new Room("Garden");

        // ===== 生活区 =====
        dorm = new Room("Dormitory");
        cafe = new Room("Cafeteria");
        gym = new Room("Gym");
        laundry = new Room("Laundry");

        // ===== 隐藏区 =====
        tunnel = new Room("Secret Tunnel");
        cave = new Room("Crystal Cave");
        dragon = new Room("Dragon Lair");

        // ===== 连接 =====
        outside.setExit("north", library);
        outside.setExit("south", dorm);
        outside.setExit("east", theater);
        outside.setExit("west", pub);

        // 图书馆
        library.setExit("east", reading);
        reading.setExit("east", archive);

        // 教学区
        theater.setExit("east", classA);
        classA.setExit("east", classB);
        classB.setExit("east", professorOffice);

        // 实验区
        lab.setExit("east", computer);
        computer.setExit("east", server);
        server.setExit("east", data);
        data.setExit("east", ai);

        // 娱乐区
        pub.setExit("east", lake);
        lake.setExit("east", forest);
        forest.setExit("east", garden);

        // 生活区
        dorm.setExit("east", cafe);
        cafe.setExit("east", gym);
        gym.setExit("east", laundry);

        // 隐藏区
        archive.setExit("down", tunnel);
        tunnel.setExit("down", cave);
        cave.setExit("down", dragon);
    }

    // ===================== NPC =====================
    private void setupNPCs()
    {
        NPC librarian = new NPC("Librarian", "Knowledge is hidden in books.");
        librarian.addQuest(new Quest("FindBook", "Find the lost ancient book", "GoldKey"));

        NPC professor = new NPC("Professor", "Help me complete my research.");
        professor.addQuest(new Quest("ResearchHelp", "Collect AI data samples", "AccessCard"));
        professor.addQuest(new Quest("LabCleanup", "Clean the lab equipment", "LabKey"));

        NPC merchant = new NPC("Merchant", "I trade rare items.");
        merchant.addQuest(new Quest("TradeRun", "Bring rare item from cave", "DragonKey"));

        NPC guard = new NPC("Guard", "Only the worthy may pass.");
        guard.addQuest(new Quest("ProofStrength", "Defeat wolf in forest", "GatePass"));

        NPC sage = new NPC("Sage", "The dragon guards the truth.");
        sage.addQuest(new Quest("DragonWarning", "Reach Dragon Lair", "TruthKey"));

        // ===== 正确绑定（修复核心错误）=====
        npcMap.put(library, librarian);
        npcMap.put(professorOffice, professor);
        npcMap.put(pub, merchant);
        npcMap.put(garden, guard);
        npcMap.put(cave, sage);
    }

    // ===================== 游戏主循环 =====================
    public void play()
    {
        System.out.println("Welcome to World of Zuul!");

        boolean finished = false;

        while (!finished) {
            Command command = parser.getCommand();

            if (command != null) {
                finished = command.execute(this);
            } else {
                System.out.println("I don't understand...");
            }
        }

        System.out.println("Game Over.");
    }

    // ===================== 基础方法 =====================
    public Player getPlayer()
    {
        return player;
    }

    public Room getCurrentRoom()
    {
        return player.getCurrentRoom();
    }

    public void setCurrentRoom(Room room)
    {
        player.setCurrentRoom(room);
    }

    public void pushHistory(Room room)
    {
        roomHistory.push(room);
    }

    public Room popHistory()
    {
        if (roomHistory.isEmpty()) return null;
        return roomHistory.pop();
    }

    public NPC getNPC(Room room)
    {
        return npcMap.get(room);
    }

    public GameTime getTime()
    {
        return time;
    }

    public HashSet<Room> getVisitedRooms()
    {
        return visitedRooms;
    }

    // ===================== 结局系统 =====================
    public void checkEnding()
    {
        if (gameEnded) return;

        Player p = getPlayer();

        System.out.println("\n======================");
        System.out.println("        GAME END");
        System.out.println("======================\n");

        boolean firstBlood = p.getAchievements().get("first_blood") != null
                && p.getAchievements().get("first_blood").isUnlocked();

        boolean collector = p.getAchievements().get("collector") != null
                && p.getAchievements().get("collector").isUnlocked();

        boolean explorer = p.getAchievements().get("explorer") != null
                && p.getAchievements().get("explorer").isUnlocked();

        int hp = p.getHp();

        // ================= 隐藏结局（最高优先级）=================
        if (firstBlood && collector && explorer && hp > 90) {

            System.out.println("🌟 SECRET TRUE ENDING");
            System.out.println("You have mastered everything in the campus.");
            System.out.println("The world reveals its hidden truth...");

        }

        // ================= GOOD ENDING =================
        else if (hp > 80 && firstBlood) {

            System.out.println("🏆 GOOD ENDING");
            System.out.println("You defeated the boss and became the hero of campus.");

        }

        // ================= NORMAL ENDING =================
        else if (hp > 40) {

            System.out.println("⚖️ NORMAL ENDING");
            System.out.println("You survived the campus, but many mysteries remain.");

        }

        // ================= BAD ENDING =================
        else {

            System.out.println("💀 BAD ENDING");
            System.out.println("You were lost in the campus forever...");
        }

        System.out.println("\n======================\n");

        gameEnded = true;
    }
}