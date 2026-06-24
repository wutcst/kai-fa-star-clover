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
    private Player player;

    private Stack<Room> roomHistory;
    private HashMap<Room, NPC> npcMap;

    private GameTime time;
    private HashSet<Room> visitedRooms;

    private boolean gameEnded = false;

    // ===== 房间 =====
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

    private CommandWords commands = new CommandWords();



    public Game()
    {
        roomHistory = new Stack<>();
        npcMap = new HashMap<>();
        visitedRooms = new HashSet<>();

        createRooms();
        setupNPCs();

        player = new Player(outside);
        time = new GameTime();
    }

    // ===================== 创建地图 =====================
    private void createRooms()
    {
        outside = new Room("Campus Square");

        library = new Room("Library");
        reading = new Room("Reading Room");
        archive = new Room("Archive");

        theater = new Room("Theater");
        classA = new Room("Classroom A");
        classB = new Room("Classroom B");
        professorOffice = new Room("Professor Office");

        lab = new Room("Lab");
        computer = new Room("Computer Room");
        server = new Room("Server Room");
        data = new Room("Data Center");
        ai = new Room("AI Lab");

        pub = new Room("Pub");
        lake = new Room("Lake");
        forest = new Room("Forest");
        garden = new Room("Garden");

        dorm = new Room("Dormitory");
        cafe = new Room("Cafeteria");
        gym = new Room("Gym");
        laundry = new Room("Laundry");

        tunnel = new Room("Secret Tunnel");
        cave = new Room("Crystal Cave");
        dragon = new Room("Dragon Lair");

        // ===== 连接 =====
        outside.setExit("north", library);
        outside.setExit("south", dorm);
        outside.setExit("east", theater);
        outside.setExit("west", pub);

        library.setExit("east", reading);
        reading.setExit("east", archive);

        theater.setExit("east", classA);
        classA.setExit("east", classB);
        classB.setExit("east", professorOffice);

        lab.setExit("east", computer);
        computer.setExit("east", server);
        server.setExit("east", data);
        data.setExit("east", ai);

        pub.setExit("east", lake);
        lake.setExit("east", forest);
        forest.setExit("east", garden);

        dorm.setExit("east", cafe);
        cafe.setExit("east", gym);
        gym.setExit("east", laundry);

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

        NPC merchant = new NPC("Merchant", "I trade rare items.");
        merchant.addQuest(new Quest("TradeRun", "Bring rare item from cave", "DragonKey"));

        NPC guard = new NPC("Guard", "Only the worthy may pass.");
        guard.addQuest(new Quest("ProofStrength", "Defeat wolf in forest", "GatePass"));

        NPC sage = new NPC("Sage", "The dragon guards the truth.");
        sage.addQuest(new Quest("DragonWarning", "Reach Dragon Lair", "TruthKey"));

        npcMap.put(library, librarian);
        npcMap.put(professorOffice, professor);
        npcMap.put(pub, merchant);
        npcMap.put(garden, guard);
        npcMap.put(cave, sage);
    }

    // =====================================================
    // ⭐⭐⭐ 前端核心入口（重点改造）
    // =====================================================
    public GameResponse executeCommand(String input)
    {
        if (input == null || input.isEmpty()) {
            return getGameState("Empty command");
        }

        String[] parts = input.split(" ");
        String word1 = parts[0];
        String word2 = parts.length > 1 ? parts[1] : null;

        Command command = new CommandWords().get(word1);

        if (command == null) {
            return getGameState("Unknown command");
        }

        command.setSecondWord(word2);

        // ⭐ 关键：只执行，不接返回值
        command.execute(this);

        return getGameState("Command executed");
    }
    // ===================== 状态 =====================
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

    // ===================== 结局 =====================
    public void checkEnding()
    {
        if (gameEnded) return;

        Player p = getPlayer();

        boolean firstBlood = p.getAchievements().get("first_blood") != null
                && p.getAchievements().get("first_blood").isUnlocked();

        boolean collector = p.getAchievements().get("collector") != null
                && p.getAchievements().get("collector").isUnlocked();

        boolean explorer = p.getAchievements().get("explorer") != null
                && p.getAchievements().get("explorer").isUnlocked();

        int hp = p.getHp();

        System.out.println("\n===== GAME END =====");

        if (firstBlood && collector && explorer && hp > 90) {
            System.out.println("🌟 TRUE ENDING");
        }
        else if (hp > 80 && firstBlood) {
            System.out.println("🏆 GOOD ENDING");
        }
        else if (hp > 40) {
            System.out.println("⚖️ NORMAL ENDING");
        }
        else {
            System.out.println("💀 BAD ENDING");
        }

        System.out.println("====================\n");

        gameEnded = true;
    }

    private GameResponse buildResponse(String msg)
    {
        return new GameResponse(
                msg,
                getCurrentRoom().getShortDescription(),
                player.getHp(),
                player.getLevel(),
                player.getInventoryNames(),
                "Map system ready",
                "Quest system ready",
                "NPC system ready",
                player.getAchievements()
        );
    }

    public GameResponse getGameState(String message)
    {
        return new GameResponse(
                message,
                getCurrentRoom().getShortDescription(),
                player.getHp(),
                player.getLevel(),
                player.getInventoryNames(),
                "Map system ready",
                "Quest system ready",
                "NPC system ready",
                player.getAchievements()
        );
    }
}