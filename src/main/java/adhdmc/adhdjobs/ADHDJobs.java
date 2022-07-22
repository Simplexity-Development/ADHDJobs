package adhdmc.adhdjobs;

import adhdmc.adhdjobs.JobListeners.DiggingListener;
import adhdmc.adhdjobs.JobListeners.MiningListener;
import com.jeff_media.customblockdata.CustomBlockData;
import net.coreprotect.CoreProtect;
import net.coreprotect.CoreProtectAPI;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
/*
* What am I doing with this :D
* I don't know  :D
* job types: Mining, digging, fishing, building, artisan (enchant, craft,
*
* */
public final class ADHDJobs extends JavaPlugin {

    public static ADHDJobs instance;


    @Override

    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new MiningListener(), this);
        getServer().getPluginManager().registerEvents(new DiggingListener(), this);
        //getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


}
