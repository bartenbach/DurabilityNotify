package co.proxa.durabilitynotify.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.vehicle.VehicleDamageEvent;
import org.bukkit.event.vehicle.VehicleEvent;
import org.bukkit.event.vehicle.VehicleUpdateEvent;

// This may be impossible.

public class CarrotStickListener implements Listener {

    //PlayerInteractEvent - Works, but basically fires over everything.  Would be tedious to code.
    //PlayerInteractEntityEvent - Only seems to fire if the pig is right clicked.
    //EntityInteractEvent - test
    //PlayerItemConsumeEvent - it did not fire
    //EntityRegainHealthEvent - it did not fire

   // @EventHandler
    //void onPlayerUseCarrotStick(PlayerItemHeldEvent event) {
        //Bukkit.getServer().broadcastMessage("it fired");
       // if (event.getItem().getType() == Material.CARROT_STICK) {
       //     Bukkit.getServer().broadcastMessage("bingo");
       // }
   // }

}
