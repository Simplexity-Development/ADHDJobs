package adhdmc.adhdjobs.JobListeners;

import adhdmc.adhdjobs.ADHDJobs;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BuildingListener implements Listener {
    NamespacedKey diggerJob = new NamespacedKey(ADHDJobs.instance, "diggerJob");
    NamespacedKey diggerLevel = new NamespacedKey(ADHDJobs.instance, "diggerLevel");
    NamespacedKey diggerExperience = new NamespacedKey(ADHDJobs.instance, "diggerExperience");
    byte f = 0;
    byte t = 1;
    public void playerPlaceBlock(BlockPlaceEvent event){
        Block block = event.getBlock();
        Player player = event.getPlayer();

    }
}
