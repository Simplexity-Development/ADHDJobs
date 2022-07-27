package adhdmc.adhdjobs.JobListeners;

import adhdmc.adhdjobs.ADHDJobs;
import adhdmc.adhdjobs.MathHandling.LevelHandling;
import com.destroystokyo.paper.MaterialTags;
import com.gestankbratwurst.playerblocktracker.PlayerBlockTracker;
import org.bukkit.GameMode;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class DiggingListener implements Listener {
    NamespacedKey diggerJob = new NamespacedKey(ADHDJobs.instance, "diggerJob");
    NamespacedKey diggerLevel = new NamespacedKey(ADHDJobs.instance, "diggerLevel");
    NamespacedKey diggerExperience = new NamespacedKey(ADHDJobs.instance, "diggerExperience");
    byte f = 0;
    byte t = 1;



    @EventHandler
    public void tempMinerOptIn(PlayerSwapHandItemsEvent event){
        Player player = event.getPlayer();
        PersistentDataContainer playerPDC = player.getPersistentDataContainer();
        if(!playerPDC.has(diggerJob) || playerPDC.get(diggerJob, PersistentDataType.BYTE).equals(f)){
            playerPDC.set(diggerJob, PersistentDataType.BYTE, t);
            player.sendMessage("OPTED IN TO DIGGER JOB");
            return;
        }
        if(playerPDC.get(diggerJob, PersistentDataType.BYTE).equals(t)){
            playerPDC.set(diggerJob, PersistentDataType.BYTE, f);
            player.sendMessage("OPTED OUT OF DIGGER JOB");
        }
    }


    @EventHandler
    public void playerBreakBlock(BlockBreakEvent event){
        Player player = event.getPlayer();
        Block block = event.getBlock();
        //PersistentDataContainer blockPDC = new CustomBlockData(block, ADHDJobs.instance);
        PersistentDataContainer playerPDC = player.getPersistentDataContainer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if(player.getGameMode() != GameMode.SURVIVAL){
            player.sendMessage("You must be in survival to get paid for this! Returning");
            return;
        }
        if(!MaterialTags.SHOVELS.isTagged(item)) {
            player.sendMessage("Not dug with a shovel, returning");
            return;
        }
        if(!Tag.MINEABLE_SHOVEL.isTagged(block.getType())) {
            player.sendMessage(item.getType() + " is not the preferred tool to break " + block.getType());
            return;
        }
        if(!playerPDC.has(diggerJob)) {
            player.sendMessage("you are not opted into digger job, returning");
            return;
        }
        if(playerPDC.get(diggerJob, PersistentDataType.BYTE).equals(f)) {
            player.sendMessage("digger job set to false, returning");
            return;
        }
        if(PlayerBlockTracker.isTracked(block)) {
            player.sendMessage("THAT BLOCK HAS BEEN PLACED PLAYERBLOCKTRACKER");
            return;
        }
        LevelHandling.level(playerPDC, player,diggerLevel,diggerExperience);
        //TODO: Hook into vault for payout
        player.sendMessage("Paid X MONEY, ONCE I GET VAULT HOOKED UP");

    }
}

