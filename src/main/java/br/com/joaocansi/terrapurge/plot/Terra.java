package br.com.joaocansi.terrapurge.plot;

import com.plotsquared.core.plot.Plot;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldedit.world.block.BlockTypes;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Terra {
    public static HashMap<UUID, Terra> data = new HashMap<>();
    private final Plot plot;

    public Terra(Plot plot) {
        this.plot = plot;
    }

    public void clear() {
        String worldName = plot.getWorldName();
        if (worldName == null)
            return;

        World world = BukkitAdapter.adapt(Bukkit.getWorld(worldName));
        for (CuboidRegion rg : plot.getRegions()) {
            try (EditSession editSession = WorldEdit.getInstance().newEditSession(world)) {
                assert BlockTypes.AIR != null;
                editSession.setBlocks((Region) new CuboidRegion(rg.getMinimumPoint(), rg.getMaximumPoint()), BlockTypes.AIR.getDefaultState().toBaseBlock());
            }
        }
    }
}
