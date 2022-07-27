package adhdmc.adhdjobs.PlacementListeners;

import adhdmc.adhdjobs.BlockLists;
import com.gestankbratwurst.playerblocktracker.PlayerBlockTracker;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;
import java.util.List;

public class TrackBlocksListener implements Listener {

    @EventHandler
    public void playerPlaceBlock(BlockPlaceEvent event){
        Block block = event.getBlock();
        Material blockType = block.getType();
        if (!BlockLists.trackThesePlacedBlocks.contains(blockType)) return;
        PlayerBlockTracker.track(block);
    }

    @EventHandler
    public void pistonPushBlock(BlockPistonExtendEvent event){
        BlockFace direction = event.getDirection();
        List<Block> blocks = event.getBlocks();
        for (Block b : blocks){
            if(!PlayerBlockTracker.isTracked(b)) break;
            Block newBlock = b.getRelative(direction);
            PlayerBlockTracker.moveTrack(b, newBlock);
        }
    }

    @EventHandler
    public void pistonPullBlock(BlockPistonRetractEvent event){
        BlockFace direction = event.getDirection();
        List<Block> blocks = event.getBlocks();
        for (Block b : blocks){
            if(!PlayerBlockTracker.isTracked(b)) break;
            Block newBlock = b.getRelative(direction);
            PlayerBlockTracker.moveTrack(b, newBlock);
        }
    }

}
