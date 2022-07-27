package adhdmc.adhdjobs;

import adhdmc.adhdjobs.JobListeners.DiggingListener;
import adhdmc.adhdjobs.JobListeners.MiningListener;
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
        if (getServer().getPluginManager().getPlugin("PlayerBlockTracker") != null){
        getServer().getPluginManager().registerEvents(new MiningListener(), this);
        getServer().getPluginManager().registerEvents(new DiggingListener(), this);
        System.out.println("PLAYER BLOCK TRACKER FOUND, ENABLING EVVENTS");
    } else {
        System.out.println("NOT FOUND, DISABLING PLUGIN");
        getServer().getPluginManager().disablePlugin(this);
        }
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


}
