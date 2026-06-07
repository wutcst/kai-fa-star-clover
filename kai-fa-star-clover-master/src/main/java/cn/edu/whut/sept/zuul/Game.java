package cn.edu.whut.sept.zuul;

import java.util.Random;

public class Game {
    private Parser parser;
    private Player player;
    private Random random;

    public Game() {
        parser = new Parser();
        random = new Random();
        createRooms();
    }

    private void createRooms() {
        Room outside, theater, pub, lab, office;

        // Create rooms (all English descriptions)
        outside = new Room("Castle Courtyard");
        theater = new Room("Theater Hall");
        pub = new Room("Rustic Tavern");
        lab = new Room("Alchemy Laboratory");
        office = new Room("Administrator Office");

        // Set exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
        theater.setExit("west", outside);
        pub.setExit("east", outside);
        lab.setExit("north", outside);
        lab.setExit("east", office);
        office.setExit("west", lab);

        // Add items (all English names/descriptions)
        outside.addItem(new Item("stone", "Stone", 2));
        theater.addItem(new Item("cookie", "Magic Cookie", 1));
        pub.addItem(new Item("bread", "Loaf of Bread", 3));
        lab.addItem(new Item("potion", "Health Potion", 2));

        // Initialize player
        player = new Player("Adventurer", outside);
    }

    public void play() {
        printWelcome();
        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = command.execute(this);
        }
        System.out.println("Game over. Thanks for playing!");
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the text adventure game!");
        System.out.println("Type 'help' to see available commands.");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
        triggerRandomEvent();
    }

    public void triggerRandomEvent() {
        if (random.nextDouble() < 0.3) {
            System.out.println("\nA random event has occurred!");
            if (random.nextBoolean()) {
                // Player finds a coin
                Item reward = new Item("coin", "Gold Coin", 1);
                player.getCurrentRoom().addItem(reward);
                System.out.println("You found a gold coin! It has been added to the room's items.");
            } else {
                // Player loses an item
                if (!player.getBackpack().isEmpty()) {
                    Item lost = player.getBackpack().remove(random.nextInt(player.getBackpack().size()));
                    player.getCurrentRoom().addItem(lost);
                    System.out.println("You accidentally dropped an item: " + lost.getName());
                }
            }
        }
    }

    public Room getCurrentRoom() {
        return player.getCurrentRoom();
    }

    public void setCurrentRoom(Room room) {
        player.setCurrentRoom(room);
    }

    public Player getPlayer() {
        return player;
    }

    public static void main(String[] args) {
        new Game().play();
    }
}