package net.quatulo.lobby.utilities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreboardService {
    public Player client;
    public ScoreboardService() {}
    public ScoreboardService(Player client) {
        this.client = client;
    }
    @SuppressWarnings("deprecation")
    public ScoreboardService setScoreboard() {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.getObjective("aaa");
        if (objective == null) {
            objective = scoreboard.registerNewObjective("aaa", "bbb");
        }
        objective.setDisplayName("§6§lQUATULO§8.§6§lNET");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.getScore(" ").setScore(12);
        objective.getScore("§r§lDein Rang§7:").setScore(11);
        objective.getScore("§7§lloading...").setScore(10);
        objective.getScore("  ").setScore(9);
        objective.getScore("§r§lCoins§7:").setScore(8);
        //objective.getScore(updateTeam(scoreboard, "RangTeam", " §8● §7", new PlayerManager().getPrefix(client), ChatColor.BLACK)).setScore(6);
        objective.getScore("§6§l0").setScore(7);
        objective.getScore("   ").setScore(6);
        objective.getScore("§r§lDein Clan§7:").setScore(5);
        objective.getScore("§6§lHundesohn").setScore(4);
        objective.getScore("    ").setScore(3);
        objective.getScore("§r§lSpielzeit§7:").setScore(2);
        objective.getScore("§6§l1d 20min").setScore(1);
        objective.getScore("     ").setScore(0);

        this.client.setScoreboard(scoreboard);
        return this;
    }
    @SuppressWarnings("deprecation")
    public ScoreboardService updateScoreboard() {
        org.bukkit.scoreboard.Scoreboard scoreboard = this.client.getScoreboard();
        Objective objective = scoreboard.getObjective("aaa");
        if (objective == null) {
            setScoreboard();
        }
        //objective.getScore(updateTeam(scoreboard, "RangTeam", " §8● §7", new PlayerManager().getPrefix(client), ChatColor.BLACK)).setScore(6);

        return this;
    }

}

