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

    public Game()
    {
        parser = new Parser();
        roomHistory = new Stack<>();

        player = new Player(createRooms());
        npcMap = new HashMap<>();
        setupNPCs();
        time = new GameTime();
        visitedRooms = new HashSet<>();
    }

    private Room createRooms()
    {
        Room outside, theater, pub, lab, office;

        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");

        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theater.setExit("west", outside);
        pub.setExit("east", outside);
        lab.setExit("north", outside);
        lab.setExit("east", office);
        office.setExit("west", lab);

        return outside;
    }

    public void play()
    {
        printWelcome();

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            if (command == null) {
                System.out.println("I don't understand...");
            } else {
                finished = command.execute(this);
            }
        }

        System.out.println("Thank you for playing. Goodbye.");
    }

    private void printWelcome()
    {
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("Commands: go, back, status, map, search, talk, quests, fight, save, load");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

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

    private void setupNPCs()
    {
        NPC librarian = new NPC("Librarian", "Help me find a lost book.");

        Quest q1 = new Quest("FindBook", "Find the lost book in the library", "GoldKey");

        librarian.addQuest(q1);

        npcMap.put(getCurrentRoom(), librarian);
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


}