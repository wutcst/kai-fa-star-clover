package cn.edu.whut.sept.zuul;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
@CrossOrigin
public class GameController {

    private final Game game = new Game();

    // ===================== 核心命令接口 =====================
    @PostMapping("/command")
    public GameResponse command(@RequestParam String input) {
        return game.executeCommand(input);
    }

    // ===================== 状态 =====================
    @GetMapping("/status")
    public GameResponse status() {
        return game.getGameState("status");
    }

    // ===================== 房间 =====================
    @GetMapping("/room")
    public String room() {
        return game.getCurrentRoom().getLongDescription();
    }

    // ===================== 地图 =====================
    @GetMapping("/map")
    public String map() {
        return "🗺️ Campus Map:\n" +
                "Library → Reading → Archive\n" +
                "Theater → Classroom → Professor Office\n" +
                "Lab → Computer → AI Lab\n" +
                "Pub → Lake → Forest → Garden\n" +
                "Dorm → Cafe → Gym → Laundry";
    }

    // ===================== 背包 =====================
    @GetMapping("/items")
    public List<String> items() {
        return game.getPlayer().getInventoryNames();
    }

    // ===================== 任务 =====================
    @GetMapping("/quests")
    public String quests() {
        return "📋 Quests:\n" +
                "- FindBook\n" +
                "- ResearchHelp\n" +
                "- TradeRun\n" +
                "- ProofStrength\n" +
                "- DragonWarning";
    }

    // ===================== NPC =====================
    @GetMapping("/npc")
    public String npc() {
        return "💬 NPC:\n" +
                "- Librarian\n" +
                "- Professor\n" +
                "- Merchant\n" +
                "- Guard\n" +
                "- Sage";
    }

    // ===================== 成就 =====================
    @GetMapping("/achievements")
    public Object achievements() {
        return game.getPlayer().getAchievements();
    }

    // ===================== 结局 =====================
    @GetMapping("/ending")
    public String ending() {
        game.checkEnding();
        return "ending checked";
    }
}