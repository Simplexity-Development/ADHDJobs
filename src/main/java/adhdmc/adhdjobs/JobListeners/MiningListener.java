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

public class MiningListener implements Listener {
    NamespacedKey minerJob = new NamespacedKey(ADHDJobs.instance, "minerJob");
    NamespacedKey minerLevel = new NamespacedKey(ADHDJobs.instance, "minerLevel");
    NamespacedKey minerExperience = new NamespacedKey(ADHDJobs.instance, "minerExperience");
    byte f = 0;
    byte t = 1;


    @EventHandler
    public void tempMinerOptIn(PlayerSwapHandItemsEvent event){
        Player player = event.getPlayer();
        PersistentDataContainer playerPDC = player.getPersistentDataContainer();
        if(!playerPDC.has(minerJob) || playerPDC.get(minerJob, PersistentDataType.BYTE).equals(f)){
            playerPDC.set(minerJob, PersistentDataType.BYTE, t);
            player.sendMessage("OPTED IN TO MINER JOB");
            return;
        }
        if(playerPDC.get(minerJob, PersistentDataType.BYTE).equals(t)){
            playerPDC.set(minerJob, PersistentDataType.BYTE, f);
            player.sendMessage("OPTED OUT OF MINER JOB");
        }
    }


    @EventHandler
    public void playerBreakBlock(BlockBreakEvent event){
        Player player = event.getPlayer();
        Block block = event.getBlock();
        PersistentDataContainer playerPDC = player.getPersistentDataContainer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if(player.getGameMode() != GameMode.SURVIVAL){
            player.sendMessage("You must be in survival to get paid for this! Returning");
            return;
        }
        if(!MaterialTags.PICKAXES.isTagged(item)) {
            player.sendMessage("Not mined with a pick, returning");
            return;
        }
        if(!Tag.MINEABLE_PICKAXE.isTagged(block.getType())) {
            player.sendMessage(item.getType() + " is not the preferred tool to break " + block.getType());
            return;
        }
        if(!playerPDC.has(minerJob)) {
            player.sendMessage("you are not opted into miner job, returning");
            return;
        }
        if(playerPDC.get(minerJob, PersistentDataType.BYTE).equals(f)) {
            player.sendMessage("miner job set to false, returning");
            return;
        }
        if(PlayerBlockTracker.isTracked(block)) {
            player.sendMessage("THAT BLOCK HAS BEEN PLACED");
            return;
        }
        LevelHandling.level(playerPDC, player,minerLevel,minerExperience);
        //TODO: Hook into vault for payout
        player.sendMessage("Paid X MONEY, ONCE I GET VAULT HOOKED UP");

    }

}
    /* lvl * 100 * 1.25 = xp to get to next lvl
    100, 125, 250, 375, 500,
    */

