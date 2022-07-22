package adhdmc.adhdjobs.MathHandling;

import adhdmc.adhdjobs.ADHDJobs;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class LevelHandling {



    public static void level(PersistentDataContainer playerPDC, Player player, NamespacedKey level, NamespacedKey experience){

        int currentXP = 0;
        if(!playerPDC.has(experience) || playerPDC.get(experience, PersistentDataType.INTEGER) == null){
            playerPDC.set(experience, PersistentDataType.INTEGER, 1);
            player.sendMessage("You had no xp, setting xp to 1");
        } else {
            currentXP = playerPDC.get(experience, PersistentDataType.INTEGER);
            int newXP = currentXP + 1;
            playerPDC.set(experience, PersistentDataType.INTEGER, newXP);
            player.sendMessage("New XP amt: " + newXP);
        }
        if(!playerPDC.has(level) || playerPDC.get(level, PersistentDataType.INTEGER) == null){
            playerPDC.set(level, PersistentDataType.INTEGER, 0);
        }
        int playerLvl = playerPDC.get(level, PersistentDataType.INTEGER);
        if (playerLvl == 0 && currentXP > 100) {
            playerLvl = 1;
            player.sendMessage("Good going, leveled to level 1");
        }
        else if ((currentXP >= playerLvl * 100 * 1.25) && playerLvl != 0){
            playerLvl = playerLvl + 1;
            player.sendMessage(currentXP + "xp points, leveled up to level: " + playerLvl);
        }
        playerPDC.set(level, PersistentDataType.INTEGER, playerLvl);
        player.sendMessage("Level set to: " + playerLvl);

    }
}
