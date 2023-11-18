package br.com.joaocansi.terrapurge.plot.events;

import br.com.joaocansi.terrapurge.shared.Items;
import br.com.joaocansi.terrapurge.gui.TerraPurgeConfirmationGUI;
import br.com.joaocansi.terrapurge.plot.Terra;
import com.plotsquared.core.PlotAPI;
import com.plotsquared.core.player.PlotPlayer;
import com.plotsquared.core.plot.Plot;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class TerraPurgeListener implements Listener {
    @EventHandler
    public void onTerraPurgeItemClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack item = e.getItem();

        if ((e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.RIGHT_CLICK_AIR) || item == null || !Items.TERRA_PURGE_ITEM.isSimilar(item))
            return;

        e.setCancelled(true);

        PlotAPI api = new PlotAPI();
        PlotPlayer<?> pp = api.wrapPlayer(p.getUniqueId());
        if (pp == null)
            return;

        Plot plot = pp.getCurrentPlot();
        if (plot == null || !plot.isOwner(p.getUniqueId()))
            return;

        Terra terraGround = new Terra(plot);
        Terra.data.put(p.getUniqueId(), terraGround);
        new TerraPurgeConfirmationGUI(p);
    }
}
